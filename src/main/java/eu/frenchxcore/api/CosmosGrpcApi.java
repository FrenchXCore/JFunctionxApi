package eu.frenchxcore.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import cosmos.base.query.v1beta1.Pagination;
import eu.frenchxcore.tools.LocalExecutor;
import eu.frenchxcore.tools.XLogger;
import fx.gravity.crosschain.v1.QueryGrpc;
import fx.gravity.crosschain.v1.QueryOuterClass;
import ibc.applications.fee.v1.FeeOuterClass;
import ibc.core.channel.v1.ChannelOuterClass;
import ibc.core.client.v1.Client;
import ibc.core.connection.v1.Connection;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class CosmosGrpcApi {

    /**
     * FX Cosmos gRPC API is described here :
     * FX-specific: https://app.swaggerhub.com/apis/functionx/fxCore-rest-gRPC-Gateway-docs/1.0.0
     * Cosmos :     https://buf.build/cosmos/cosmos-sdk/docs/main
     */

    private static final Metadata.Key<String> BLOCK_HEIGHT_KEY = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);

    private final ManagedChannel cosmosChannel;

    private final cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub authQueryStub;
    private final cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub authzQueryStub;
    private final cosmos.authz.v1beta1.MsgGrpc.MsgFutureStub authzMsgStub;
    private final cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub bankQueryStub;
    private final cosmos.bank.v1beta1.MsgGrpc.MsgFutureStub bankMsgStub;
    private final cosmos.crisis.v1beta1.MsgGrpc.MsgFutureStub crisisMsgStub;
    private final cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub distributionQueryStub;
    private final cosmos.distribution.v1beta1.MsgGrpc.MsgFutureStub distributionMsgStub;
    private final cosmos.evidence.v1beta1.QueryGrpc.QueryFutureStub evidenceQueryStub;
    private final cosmos.evidence.v1beta1.MsgGrpc.MsgFutureStub evidenceMsgStub;
    private final cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub feeGrantQueryStub;
    private final cosmos.feegrant.v1beta1.MsgGrpc.MsgFutureStub feegrantMsgStub;
    private final cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub govQueryStubV1;
    private final cosmos.gov.v1beta1.MsgGrpc.MsgFutureStub govMsgStubV1;
    private final cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub mintQueryStub;
    private final cosmos.params.v1beta1.QueryGrpc.QueryFutureStub paramsQueryStub;
    private final cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub slashingQueryStub;
    private final cosmos.slashing.v1beta1.MsgGrpc.MsgFutureStub slashingMsgStub;
    private final cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stakingQueryStub;
    private final cosmos.staking.v1beta1.MsgGrpc.MsgFutureStub stakingMsgStub;
    private final cosmos.tx.v1beta1.ServiceGrpc.ServiceFutureStub txServiceStub;
    private final cosmos.upgrade.v1beta1.QueryGrpc.QueryFutureStub upgradeQueryStub;
    private final cosmos.vesting.v1beta1.MsgGrpc.MsgFutureStub vestingMsgStub;

    private final ethermint.evm.v1.QueryGrpc.QueryFutureStub ethermintEvmQueryStub;
    private final ethermint.evm.v1.MsgGrpc.MsgFutureStub ethermintEvmMsgStub;
    private final ethermint.feemarket.v1.QueryGrpc.QueryFutureStub evmosFeemarketQueryStub;

    private final fx.gravity.v1.QueryGrpc.QueryFutureStub fxGravityQueryStub;
    private final fx.gravity.v1.MsgGrpc.MsgFutureStub fxGravityMsgStub;
    private final fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub fxCrosschainQueryStub;
    private final fx.gravity.crosschain.v1.MsgGrpc.MsgFutureStub fxCrosschainMsgStub;
    private final fx.migrate.v1.QueryGrpc.QueryFutureStub fxMigrateQueryStub;
    private final fx.migrate.v1.MsgGrpc.MsgFutureStub fxMigrateMsgStub;
    private final fx.other.QueryGrpc.QueryFutureStub fxOtherQueryStub;

    private final ibc.applications.interchain_accounts.controller.v1.QueryGrpc.QueryFutureStub ibcApplicationsInterchainAccountsControllerQueryStub;
    private final ibc.applications.interchain_accounts.host.v1.QueryGrpc.QueryFutureStub ibcApplicationsInterchainAccountsHostQueryStub;
    private final ibc.applications.fee.v1.QueryGrpc.QueryFutureStub ibcApplicationsFeeQueryStub;
    private final ibc.applications.fee.v1.MsgGrpc.MsgFutureStub ibcApplicationsFeeMsgStub;
    private final ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub ibcQueryStub;
    private final ibc.applications.transfer.v1.MsgGrpc.MsgFutureStub ibcMsgStub;
    private final ibc.core.channel.v1.QueryGrpc.QueryFutureStub ibcCoreChannelQueryStub;
    private final ibc.core.channel.v1.MsgGrpc.MsgFutureStub ibcCoreChannelMsgStub;
    private final ibc.core.client.v1.QueryGrpc.QueryFutureStub ibcCoreClientQueryStub;
    private final ibc.core.client.v1.MsgGrpc.MsgFutureStub ibcCoreClientMsgStub;
    private final ibc.core.connection.v1.QueryGrpc.QueryFutureStub ibcCoreConnectionQueryStub;
    private final ibc.core.connection.v1.MsgGrpc.MsgFutureStub ibcCoreConnectionMsgStub;

    private final tendermint.abci.ABCIApplicationGrpc.ABCIApplicationFutureStub tendermintAbciAppStub;
    private final tendermint.rpc.grpc.BroadcastAPIGrpc.BroadcastAPIFutureStub tendermintRpcBroadcastStub;

    private final cosmos.base.reflection.v1beta1.ReflectionServiceGrpc.ReflectionServiceFutureStub baseReflectionServiceStub;
    private final cosmos.base.tendermint.v1beta1.ServiceGrpc.ServiceFutureStub cosmosBaseTendermintServiceStub;

    private final static Map<String, CosmosGrpcApi> instances = new HashMap<>();

    public static CosmosGrpcApi getInstance(String ip, int cosmosPort) {
        return getInstance(ip, cosmosPort, null);
    }

    public static CosmosGrpcApi getInstance(String ip, int cosmosPort, Executor executor) {
        XLogger.getMainLogger().trace("Creating new CosmosGrpcApi instance on " + ip + ":" + cosmosPort);
        CosmosGrpcApi ret = null;
        try {
            InetAddress ia = InetAddress.getByName(ip);
            String key = ia.getHostAddress() + ":" + cosmosPort;
            if ((ret = (instances.get(key))) == null) {
                ret = new CosmosGrpcApi(ip, cosmosPort, executor);
                instances.put(key, ret);
            }
        } catch (UnknownHostException ex) {
            XLogger.getMainLogger().error(ex.getMessage());
        }
        return ret;
    }

    /**
     * https://docs.cosmos.network/master/core/grpc_rest.html
     * @param ip IP of the FunctionX node
     * @param cosmosPort Cosmos gRPC port number (as stated in '.fxcore/config/app.toml' file)
     * @param _executor Executor service for requests
     */
    private CosmosGrpcApi(String ip, int cosmosPort, Executor _executor) {
        Executor exec = (_executor == null ? LocalExecutor.getInstance().get() : _executor);
        this.cosmosChannel =
                ManagedChannelBuilder
                        .forAddress(ip, cosmosPort)
                        .executor(exec)
                        .enableRetry()
                        .maxRetryAttempts(10)
                        .keepAliveWithoutCalls(true)
                        .usePlaintext()
                        .build();

        authQueryStub = cosmos.auth.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        authzQueryStub = cosmos.authz.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        authzMsgStub = cosmos.authz.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        bankQueryStub = cosmos.bank.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        bankMsgStub = cosmos.bank.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        baseReflectionServiceStub = cosmos.base.reflection.v1beta1.ReflectionServiceGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        crisisMsgStub = cosmos.crisis.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        distributionQueryStub = cosmos.distribution.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        distributionMsgStub = cosmos.distribution.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        evidenceQueryStub = cosmos.evidence.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        evidenceMsgStub = cosmos.evidence.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        feeGrantQueryStub = cosmos.feegrant.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        feegrantMsgStub = cosmos.feegrant.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        govQueryStubV1 = cosmos.gov.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        govMsgStubV1 = cosmos.gov.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        mintQueryStub = cosmos.mint.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        paramsQueryStub = cosmos.params.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        slashingQueryStub = cosmos.slashing.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        slashingMsgStub = cosmos.slashing.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        stakingQueryStub = cosmos.staking.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        stakingMsgStub = cosmos.staking.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        upgradeQueryStub = cosmos.upgrade.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        vestingMsgStub = cosmos.vesting.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        ethermintEvmQueryStub = ethermint.evm.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ethermintEvmMsgStub = ethermint.evm.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        evmosFeemarketQueryStub = ethermint.feemarket.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        fxCrosschainMsgStub = fx.gravity.crosschain.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        fxCrosschainQueryStub = fx.gravity.crosschain.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        fxGravityMsgStub = fx.gravity.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        fxGravityQueryStub = fx.gravity.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        fxMigrateQueryStub = fx.migrate.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        fxMigrateMsgStub = fx.migrate.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        txServiceStub = cosmos.tx.v1beta1.ServiceGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        cosmosBaseTendermintServiceStub = cosmos.base.tendermint.v1beta1.ServiceGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        ibcApplicationsInterchainAccountsControllerQueryStub = ibc.applications.interchain_accounts.controller.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcApplicationsInterchainAccountsHostQueryStub = ibc.applications.interchain_accounts.host.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcApplicationsFeeQueryStub = ibc.applications.fee.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcApplicationsFeeMsgStub = ibc.applications.fee.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcCoreChannelQueryStub = ibc.core.channel.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcCoreChannelMsgStub = ibc.core.channel.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcCoreClientQueryStub = ibc.core.client.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcCoreClientMsgStub = ibc.core.client.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcCoreConnectionQueryStub = ibc.core.connection.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcCoreConnectionMsgStub = ibc.core.connection.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcMsgStub = ibc.applications.transfer.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcQueryStub = ibc.applications.transfer.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        tendermintAbciAppStub = tendermint.abci.ABCIApplicationGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        tendermintRpcBroadcastStub = tendermint.rpc.grpc.BroadcastAPIGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        fxOtherQueryStub = fx.other.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
    }

    public void close() {
        if (this.cosmosChannel != null) {
            try {
                this.cosmosChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                XLogger.getMainLogger().error(ex);
            }
        }
    }

    /*
     Cosmos Module 'auth' : QUERY
     https://docs.cosmos.network/master/modules/auth/
     */

    /**
     * 'authQueryAccounts' returns all the existing accounts until latest block height known.
     * @return First page only of all the existing accounts
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryOuterClass.QueryAccountsResponse> authQueryAccounts() {
        return this.authQueryAccounts(null, null);
    }

    /**
     * 'authQueryAccounts' returns all the existing accounts for the specified page until latest block height known.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all the existing accounts
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryOuterClass.QueryAccountsResponse> authQueryAccounts(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.authQueryAccounts(pageRequest, null);
    }

    /**
     * 'authQueryAccounts' returns all the existing accounts at specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all the existing accounts known at specified block height.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryOuterClass.QueryAccountsResponse> authQueryAccounts(
            BigInteger height
    ) {
        return this.authQueryAccounts(null, height);
    }

    /**
     * 'authQueryAccounts' returns all the existing accounts at specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all the existing accounts known at specified block height.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryOuterClass.QueryAccountsResponse> authQueryAccounts(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.auth.v1beta1.QueryOuterClass.QueryAccountsRequest.Builder _builder = cosmos.auth.v1beta1.QueryOuterClass.QueryAccountsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authQueryStub;
        }
        return stub.accounts(_builder.build());
    }

    /**
     * 'authQueryAccount' returns account details based on specified address until latest block height known.
     * @param address the address to query for.
     * @return the account details.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryOuterClass.QueryAccountResponse> authQueryAccount(
            @NotNull String address
    ) {
        return this.authQueryAccount(address, null);
    }

    /**
     * 'authQueryAccount' returns account details based on specified address until specified block height.
     * @param address the address to query for.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the account details.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryOuterClass.QueryAccountResponse> authQueryAccount(
            @NotNull String address,
            BigInteger height
    ) {
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authQueryStub;
        }
        return stub.account(cosmos.auth.v1beta1.QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(address).build());
    }

    /**
     * 'authQueryParams' queries all 'auth' module parameters until latest block height known.
     * @return all 'auth' module parameters until latest block height known.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryOuterClass.QueryParamsResponse> authQueryParams() {
        return this.authQueryParams(null);
    }

    /**
     * 'authQueryParams' queries all 'auth' module parameters until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return all 'auth' module parameters until specified block height.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryOuterClass.QueryParamsResponse> authQueryParams(
            BigInteger height
    ) {
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authQueryStub;
        }
        return stub.params(cosmos.auth.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /********************************************************
     ***** Cosmos Module 'authz' : QUERY
     ***** https://docs.cosmos.network/master/modules/authz/
     *******************************************************/

    /**
     * 'authzQueryGranterGrants' queries all the granter's grants until latest block height known.
     * @param granter The granter
     * @return The first page only of all the granter's grants until latest block height known.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzQueryGranterGrants(
            @NotNull String granter
    ) {
        return this.authzQueryGranterGrants(granter, null, null);
    }

    /**
     * 'authzQueryGranterGrants' queries all the granter's grants until latest block height known.
     * @param granter The granter
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of all the granter's grants until latest block height known.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzQueryGranterGrants(
            @NotNull String granter,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.authzQueryGranterGrants(granter, pageRequest, null);
    }

    /**
     * 'authzQueryGranterGrants' queries all the granter's grants until specified block height.
     * @param granter The granter
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of all the granter's grants until specified block height.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzQueryGranterGrants(
            @NotNull String granter,
            BigInteger height
    ) {
        return this.authzQueryGranterGrants(granter, null, height);
    }

    /**
     * 'authzQueryGranterGrants' queries all the granter's grants until specified block height.
     * @param granter The granter
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of all the granter's grants until specified block height.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzQueryGranterGrants(
            @NotNull String granter,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest.Builder _builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authzQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authzQueryStub;
        }
        return stub.granterGrants(_builder.setGranter(granter).build());
    }

    /**
     * 'authzQueryGrants' queries all grants authorized by granter to grantee until latest block height known.
     * @param granter The granter
     * @param grantee The grantee
     * @return The first page only of all grants authorized by granter to grantee until latest block height known.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzQueryGrants(
            @NotNull String granter,
            @NotNull String grantee
    ) {
        return this.authzQueryGrants(granter, grantee, null, null);
    }

    /**
     * 'authzQueryGrants' queries all grants authorized by granter to grantee until latest block height known.
     * @param granter The granter
     * @param grantee The grantee
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of all grants authorized by granter to grantee until latest block height known.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzQueryGrants(
            @NotNull String granter,
            @NotNull String grantee,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.authzQueryGrants(granter, grantee, pageRequest, null);
    }

    /**
     * 'authzQueryGrants' queries all grants authorized by granter to grantee until specified block height.
     * @param granter The granter
     * @param grantee The grantee
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of all grants authorized by granter to grantee until specified block height.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzQueryGrants(
            @NotNull String granter,
            @NotNull String grantee,
            BigInteger height
    ) {
        return this.authzQueryGrants(granter, grantee, null, height);
    }

    /**
     * 'authzQueryGrants' queries all grants authorized by granter to grantee until specified block height.
     * @param granter The granter
     * @param grantee The grantee
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of all grants authorized by granter to grantee until specified block height.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzQueryGrants(
            @NotNull String granter,
            @NotNull String grantee,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsRequest.Builder _builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authzQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authzQueryStub;
        }
        return stub.grants(_builder.setGranter(granter).setGrantee(grantee).build());
    }

    /**
     * 'authzQueryGranteeGrants' returns a list of `GrantAuthorization` by grantee, as known at the latest available block height.
     * @since cosmos-sdk 0.45.2
     * @param grantee The grantee
     * @return The specified page of the list of `GrantAuthorization` by grantee, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranteeGrantsResponse> authzQueryGranteeGrants(
            @NotNull String grantee
    ) {
        return this.authzQueryGranteeGrants(grantee, null, null);
    }

    /**
     * 'authzQueryGranteeGrants' returns a list of `GrantAuthorization` by grantee, as known at the latest available block height.
     * @since cosmos-sdk 0.45.2
     * @param grantee The grantee
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of the list of `GrantAuthorization` by grantee, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranteeGrantsResponse> authzQueryGranteeGrants(
            @NotNull String grantee,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.authzQueryGranteeGrants(grantee, pageRequest, null);
    }

    /**
     * 'authzQueryGranteeGrants' returns a list of `GrantAuthorization` by grantee, as known at the specified block height.
     * @since cosmos-sdk 0.45.2
     * @param grantee The grantee
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of the list of `GrantAuthorization` by grantee, as known at the specified block height.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranteeGrantsResponse> authzQueryGranteeGrants(
            @NotNull String grantee,
            BigInteger height
    ) {
        return this.authzQueryGranteeGrants(grantee, null, height);
    }

    /**
     * 'authzQueryGranteeGrants' returns a list of `GrantAuthorization` by grantee, as known at the specified block height.
     * @since cosmos-sdk 0.45.2
     * @param grantee The grantee
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of the list of `GrantAuthorization` by grantee, as known at the specified block height.
     */
    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranteeGrantsResponse> authzQueryGranteeGrants(
            @NotNull String grantee,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGranteeGrantsRequest.Builder _builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGranteeGrantsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authzQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authzQueryStub;
        }
        return stub.granteeGrants(_builder.setGrantee(grantee).build());
    }

    /********************************************************
     ***** Cosmos Module 'authz' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/authz/
     *******************************************************/

    /**
     * An authorization grant is created using the MsgGrant message. If there is already a grant for the (granter, grantee, Authorization)
     * triple, then the new grant overwrites the previous one. To update or extend an existing grant, a new grant with the same
     * (granter, grantee, Authorization) triple should be created.
     * The message handling should fail if:
     * - both granter and grantee have the same address.
     * - provided Expiration time is less than current unix timestamp (but a grant will be created if no expiration time is provided since expiration is optional).
     * - provided Grant.Authorization is not implemented.
     * - Authorization.MsgTypeURL() is not defined in the router (there is no defined handler in the app router to handle that Msg types).
     * @param granter Granter FX address
     * @param grantee Grantee FX address
     * @param grant The {@link cosmos.authz.v1beta1.Authz.Grant Grant} object to be granted.
     * @return the grant response.
     */
    public ListenableFuture<cosmos.authz.v1beta1.Tx.MsgGrantResponse> authzMsgGrant(
            @NotNull String granter,
            @NotNull String grantee,
            @NotNull cosmos.authz.v1beta1.Authz.Grant grant
    ) {
        return authzMsgStub.grant(cosmos.authz.v1beta1.Tx.MsgGrant.newBuilder().setGranter(granter).setGrantee(grantee).setGrant(grant).build());
    }

    /**
     * A grant can be removed with the MsgRevoke message.
     * The message handling should fail if:
     * - both granter and grantee have the same address.
     * - provided MsgTypeUrl is empty.
     * NOTE: The MsgExec message removes a grant if the grant has expired.
     * @param granter Granter FX address
     * @param grantee Grantee FX address
     * @param msgTypeUrl Message type url of the grant to be revoked
     * @return the revoke response.
     */
    public ListenableFuture<cosmos.authz.v1beta1.Tx.MsgRevokeResponse> authzMsgRevoke(
            @NotNull String granter,
            @NotNull String grantee,
            @NotNull String msgTypeUrl
    ) {
        return authzMsgStub.revoke(cosmos.authz.v1beta1.Tx.MsgRevoke.newBuilder().setGranter(granter).setGrantee(grantee).setMsgTypeUrl(msgTypeUrl).build());
    }

    /**
     * When a grantee wants to execute a transaction on behalf of a granter, they must send MsgExec.
     * The message handling should fail if:
     * - provided Authorization is not implemented.
     * - grantee doesn't have permission to run the transaction.
     * - granted authorization is expired.
     * @param grantee FX address of the grantee.
     * @param messages Authorization Msg requests to execute. Each msg must implement Authorization interface. The x/authz will try to find a grant matching (msg.signers[0], grantee, MsgTypeURL(msg)) triple and validate it.
     * @return the exec response.
     */
    public ListenableFuture<cosmos.authz.v1beta1.Tx.MsgExecResponse> authzMsgExec(
            @NotNull String grantee,
            @NotNull Iterable<? extends com.google.protobuf.Any> messages
    ) {
        return authzMsgStub.exec(cosmos.authz.v1beta1.Tx.MsgExec.newBuilder().setGrantee(grantee).addAllMsgs(messages).build());
    }

    /********************************************************
     ***** Cosmos Module 'bank' : QUERY
     ***** https://docs.cosmos.network/master/modules/bank/
     *******************************************************/

    /**
     * 'bankQueryAllBalances' queries all balances for the specified FX address until latest block height known.
     * @param address The specified FX address for which to query all balances.
     * @return The first page only of all balances of the specified FX address until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse> bankQueryAllBalances(
            @NotNull String address
    ) {
        return this.bankQueryAllBalances(address, null, null);
    }

    /**
     * 'bankQueryAllBalances' queries all balances for the specified FX address until latest block height known.
     * @param address The specified FX address for which to query all balances.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of all balances of the specified FX address until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse> bankQueryAllBalances(
            @NotNull String address,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.bankQueryAllBalances(address, pageRequest, null);
    }

    /**
     * 'bankQueryAllBalances' queries all balances for the specified FX address until specified block height.
     * @param address The specified FX address for which to query all balances.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of all balances of the specified FX address until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse> bankQueryAllBalances(
            @NotNull String address,
            BigInteger height
    ) {
        return this.bankQueryAllBalances(address, null, height);
    }

    /**
     * 'bankQueryAllBalances' queries all balances for the specified FX address until specified block height.
     * @param address The specified FX address for which to query all balances.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of all balances of the specified FX address until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse> bankQueryAllBalances(
            @NotNull String address,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesRequest.Builder _builder = cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.allBalances(_builder.setAddress(address).build());
    }

    /**
     * 'bankQueryBalance' queries the balance for the specified coin denomination of the specified FX address until latest block height known.
     * @param address The specified FX address for which to query the balance.
     * @param denom The coin denomination which balance is requested.
     * @return The balance of the specified coin denomination on the specified FX address until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryBalanceResponse> bankQueryBalance(
            @NotNull String address,
            @NotNull String denom
    ) {
        return this.bankQueryBalance(address, denom, null);
    }

    /**
     * 'bankQueryBalance' queries the balance for the specified coin denomination of the specified FX address until specified block height.
     * @param address The specified FX address for which to query the balance.
     * @param denom The coin denomination which balance is requested.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The balance of the specified coin denomination on the specified FX address until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryBalanceResponse> bankQueryBalance(
            @NotNull String address,
            @NotNull String denom,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.balance(cosmos.bank.v1beta1.QueryOuterClass.QueryBalanceRequest.newBuilder().setAddress(address).setDenom(denom).build());
    }

    /**
     * 'bankQueryDenomsMetadata' queries the coins' denominations metadata until latest block height known.
     * @return The first page only of coins' denominations metadata until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryDenomsMetadataResponse> bankQueryDenomsMetadata() {
        return this.bankQueryDenomsMetadata(null, null);
    }

    /**
     * 'bankQueryDenomsMetadata' queries the coins' denominations metadata until latest block height known.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of coins' denominations metadata until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryDenomsMetadataResponse> bankQueryDenomsMetadata(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.bankQueryDenomsMetadata(pageRequest, null);
    }

    /**
     * 'bankQueryDenomsMetadata' queries the coins' denominations metadata until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of coins' denominations metadata until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryDenomsMetadataResponse> bankQueryDenomsMetadata(
            BigInteger height
    ) {
        return this.bankQueryDenomsMetadata(null, height);
    }

    /**
     * 'bankQueryDenomsMetadata' queries the coins' denominations metadata until specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of coins' denominations metadata until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryDenomsMetadataResponse> bankQueryDenomsMetadata(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryOuterClass.QueryDenomsMetadataRequest.Builder _builder = cosmos.bank.v1beta1.QueryOuterClass.QueryDenomsMetadataRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.denomsMetadata(cosmos.bank.v1beta1.QueryOuterClass.QueryDenomsMetadataRequest.newBuilder().build());
    }

    /**
     * 'bankQueryDenomMetadata' queries the specified coin denomination metadata until latest block height known.
     * @param denom The coin denomination for which the query the metadata.
     * @return The coin denomination metadata until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryDenomMetadataResponse> bankQueryDenomMetadata(
            @NotNull String denom
    ) {
        return this.bankQueryDenomMetadata(denom, null);
    }

    /**
     * 'bankQueryDenomMetadata' queries the specified coin denomination metadata until specified block height.
     * @param denom The coin denomination for which the query the metadata.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The coin denomination metadata until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryDenomMetadataResponse> bankQueryDenomMetadata(
            @NotNull String denom,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.denomMetadata(cosmos.bank.v1beta1.QueryOuterClass.QueryDenomMetadataRequest.newBuilder().setDenom(denom).build());
    }

    /**
     * 'bankQueryParams' queries all 'bank' module parameters until latest block height known.
     * @return All 'bank' module parameters until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryParamsResponse> bankQueryParams() {
        return this.bankQueryParams(null);
    }

    /**
     * 'bankQueryParams' queries all 'bank' module parameters until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return All 'bank' module parameters until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryParamsResponse> bankQueryParams(
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.params(cosmos.bank.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /**
     * 'bankQuerySpendableBalances' queries the spendable balance of all coins for a single account, as known at the latest available block height.
     * @param address The FX account address.
     * @return The first page only of the spendable balance of all coins for a single account, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QuerySpendableBalancesResponse> bankQuerySpendableBalances(
            @NotNull String address
    ) {
        return this.bankQuerySpendableBalances(address, null, null);
    }

    /**
     * 'bankQuerySpendableBalances' queries the spendable balance of all coins for a single account, as known at the latest available block height.
     * @param address The FX account address.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of the spendable balance of all coins for a single account, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QuerySpendableBalancesResponse> bankQuerySpendableBalances(
            @NotNull String address,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.bankQuerySpendableBalances(address, pageRequest, null);
    }

    /**
     * 'bankQuerySpendableBalances' queries the spendable balance of all coins for a single account, as known at the specified block height.
     * @param address The FX account address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of the spendable balance of all coins for a single account, as known at the specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QuerySpendableBalancesResponse> bankQuerySpendableBalances(
            @NotNull String address,
            BigInteger height
    ) {
        return this.bankQuerySpendableBalances(address, null, height);
    }

    /**
     * 'bankQuerySpendableBalances' queries the spendable balance of all coins for a single account, as known at the specified block height.
     * @param address The FX account address.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of the spendable balance of all coins for a single account, as known at the specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QuerySpendableBalancesResponse> bankQuerySpendableBalances(
            @NotNull String address,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryOuterClass.QuerySpendableBalancesRequest.Builder _builder = cosmos.bank.v1beta1.QueryOuterClass.QuerySpendableBalancesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.spendableBalances(cosmos.bank.v1beta1.QueryOuterClass.QuerySpendableBalancesRequest.newBuilder().setAddress(address).build());
    }

    /**
     * 'bankQuerySupplyOf' queries the supply of a coin denomination until latest block height known.
     * @param denom The coin denomination which supply is being queried.
     * @return All 'bank' module parameters until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QuerySupplyOfResponse> bankQuerySupplyOf(
            @NotNull String denom
    ) {
        return this.bankQuerySupplyOf(denom, null);
    }

    /**
     * 'bankQuerySupplyOf' queries the supply of a coin denomination until specified block height.
     * @param denom The coin denomination which supply is being queried.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return All 'bank' module parameters until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QuerySupplyOfResponse> bankQuerySupplyOf(
            @NotNull String denom,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.supplyOf(cosmos.bank.v1beta1.QueryOuterClass.QuerySupplyOfRequest.newBuilder().setDenom(denom).build());
    }

    /**
     * 'bankQueryTotalSupply' queries the total supply of all coin denominations until latest block height known.
     * @return The first page only of the total supply of all coin denominations until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryTotalSupplyResponse> bankQueryTotalSupply() {
        return this.bankQueryTotalSupply(null, null);
    }

    /**
     * 'bankQueryTotalSupply' queries the total supply of all coin denominations until latest block height known.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of the total supply of all coin denominations until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryTotalSupplyResponse> bankQueryTotalSupply(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.bankQueryTotalSupply(pageRequest, null);
    }

    /**
     * 'bankQueryTotalSupply' queries the total supply of all coin denominations until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of the total supply of all coin denominations until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryTotalSupplyResponse> bankQueryTotalSupply(
            BigInteger height
    ) {
        return this.bankQueryTotalSupply(null, height);
    }

    /**
     * 'bankQueryTotalSupply' queries the total supply of all coin denominations until specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of the total supply of all coin denominations until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryOuterClass.QueryTotalSupplyResponse> bankQueryTotalSupply(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryOuterClass.QueryTotalSupplyRequest.Builder _builder = cosmos.bank.v1beta1.QueryOuterClass.QueryTotalSupplyRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.totalSupply(_builder.build());
    }

    /********************************************************
     ***** Cosmos Module 'bank' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/bank/
     *******************************************************/

    /**
     * Send coins from and to a series of different address. If com.google.protobuf.Any of the receiving addresses do not correspond
     * to an existing account, a new account is created.
     * The message will fail under the following conditions:
     * - com.google.protobuf.Any of the coins do not have sending enabled
     * - com.google.protobuf.Any of the to addresses are restricted
     * - com.google.protobuf.Any of the coins are locked
     * - The inputs and outputs do not correctly correspond to one another
     * @param inputs Iterable of source FX addresses, coins and amounts
     * @param outputs Iterable of destination FX addresses, coins and amounts
     * @return the MsgMultiSend response.
     */
    public ListenableFuture<cosmos.bank.v1beta1.Tx.MsgMultiSendResponse> bankMsgMultiSend(
            @NotNull Iterable<? extends cosmos.bank.v1beta1.Bank.Input> inputs,
            @NotNull Iterable<? extends cosmos.bank.v1beta1.Bank.Output> outputs
    ) {
        cosmos.bank.v1beta1.Tx.MsgMultiSend.Builder _builder = cosmos.bank.v1beta1.Tx.MsgMultiSend.newBuilder();
        if (inputs != null) {
            _builder.addAllInputs(inputs);
        }
        if (outputs != null) {
            _builder.addAllOutputs(outputs);
        }
        return bankMsgStub.multiSend(_builder.build());
    }

    /**
     * Send coins from one address to another.
     * The message will fail under the following conditions:
     * - The coins do not have sending enabled
     * - The 'to' address is restricted
     * @param from Source FX address
     * @param to Destination FX address
     * @param amount Type and amount of coins to transfer
     * @return the MsgSend response.
     */
    public ListenableFuture<cosmos.bank.v1beta1.Tx.MsgSendResponse> bankMsgSend(
            @NotNull String from,
            @NotNull String to,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount
    ) {
        return bankMsgStub.send(cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().setFromAddress(from).setToAddress(to).addAmount(amount).build());
    }

    /**
     * Send coins from one address to another.
     * The message will fail under the following conditions:
     * - The coins do not have sending enabled
     * - The 'to' address is restricted
     * @param from Source FX address
     * @param to Destination FX address
     * @param amounts Type and amount of coins to transfer
     * @return
     */
    public ListenableFuture<cosmos.bank.v1beta1.Tx.MsgSendResponse> bankMsgSend(
            @NotNull String from,
            @NotNull String to,
            @NotNull Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> amounts
    ) {
        return bankMsgStub.send(cosmos.bank.v1beta1.Tx.MsgSend.newBuilder().setFromAddress(from).setToAddress(to).addAllAmount(amounts).build());
    }

    /**********************************************************
     ***** Cosmos Module 'base.reflection' : QUERY
     *********************************************************/

    public ListenableFuture<cosmos.base.reflection.v1beta1.Reflection.ListAllInterfacesResponse> baseReflectionListAllInterfaces() {
        return baseReflectionServiceStub.listAllInterfaces(cosmos.base.reflection.v1beta1.Reflection.ListAllInterfacesRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.base.reflection.v1beta1.Reflection.ListImplementationsResponse> baseReflectionListImplementations() {
        return baseReflectionServiceStub.listImplementations(cosmos.base.reflection.v1beta1.Reflection.ListImplementationsRequest.newBuilder().build());
    }

    /********************************************************
     ***** Cosmos Module 'crisis' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/crisis/
     *******************************************************/

    /**
     * VerifyInvariant defines a method to verify a particular invariance.
     * @param sender
     * @param invariantModuleName
     * @param invariantRoute
     * @return
     */
    public ListenableFuture<cosmos.crisis.v1beta1.Tx.MsgVerifyInvariantResponse> crisisMsgVerifyInvariantResponse(
            @NotNull String sender,
            @NotNull String invariantModuleName,
            @NotNull String invariantRoute
    ) {
        return crisisMsgStub.verifyInvariant(cosmos.crisis.v1beta1.Tx.MsgVerifyInvariant.newBuilder().setSender(sender).setInvariantModuleName(invariantModuleName).setInvariantRoute(invariantRoute).build());
    }

    /***************************************************************
     ***** Cosmos Module 'distribution' : QUERY
     ***** https://docs.cosmos.network/master/modules/distribution/
     **************************************************************/

    /**
     * 'distributionQueryCommunityPool' queries information about the Community Pool until latest block height known.
     * @return Information about the Community Pool until latest block height known.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolResponse> distributionQueryCommunityPool() {
        return this.distributionQueryCommunityPool();
    }

    /**
     * 'distributionQueryCommunityPool' queries information about the Community Pool until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Information about the Community Pool until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolResponse> distributionQueryCommunityPool(
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.communityPool(cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolRequest.newBuilder().build());
    }

    /**
     * 'distributionQueryDelegationRewards' queries the total rewards accrued by a delegator with a validator until latest block height known.
     * @param delegatorAddress the delegator FX address.
     * @param validatorAddress the validator operator FX address.
     * @return the total rewards accrued by a delegator with a validator until latest block height known
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionQueryDelegationRewards(
            @NotNull String delegatorAddress,
            @NotNull String validatorAddress
    ) {
        return this.distributionQueryDelegationRewards(delegatorAddress, validatorAddress, null);
    }

    /**
     * 'distributionQueryDelegationRewards' queries the total rewards accrued by a delegator with a validator until specified block height.
     * @param delegatorAddress the delegator FX address.
     * @param validatorAddress the validator operator FX address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the total rewards accrued by a delegator with a validator until specified block height
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionQueryDelegationRewards(
            @NotNull String delegatorAddress,
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.delegationRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsRequest.newBuilder().setDelegatorAddress(delegatorAddress).setValidatorAddress(validatorAddress).build());
    }

    /**
     * DelegationTotalRewards queries the total rewards accrued by a delegator, and for each validator until latest block height known.
     * @param delegatorAddress the delegator FX address.
     * @return the total rewards accrued by a delegator for each validator until latest block height known.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsResponse> distributionQueryDelegationTotalRewardsAddress(
            @NotNull String delegatorAddress
    ) {
        return this.distributionQueryDelegationTotalRewardsAddress(delegatorAddress, null);
    }

    /**
     * DelegationTotalRewards queries the total rewards accrued by a delegator, and for each validator until specified block height.
     * @param delegatorAddress the delegator FX address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the total rewards accrued by a delegator for each validator until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsResponse> distributionQueryDelegationTotalRewardsAddress(
            @NotNull String delegatorAddress,
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.delegationTotalRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    /**
     * 'distributionQueryDelegatorValidators' queries the validators of a delegator until latest block height known.
     * @param delegatorAddress the delegator FX address.
     * @return the validators of a delegator until latest block height known.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> distributionQueryDelegatorValidators(
            @NotNull String delegatorAddress
    ) {
        return this.distributionQueryDelegatorValidators(delegatorAddress, null);
    }

    /**
     * 'distributionQueryDelegatorValidators' queries the validators of a delegator until specified block height.
     * @param delegatorAddress the delegator FX address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the validators of a delegator until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> distributionQueryDelegatorValidators(
            @NotNull String delegatorAddress, 
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.delegatorValidators(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    /**
     * 'distributionQueryDelegatorWithdrawAddress' queries withdraw reward address of a delegator until latest block height known.
     * @param delegatorAddress the delegator FX address.
     * @return the withdraw reward address of a delegator until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressResponse> distributionQueryDelegatorWithdrawAddress(
            @NotNull String delegatorAddress
    ) {
        return this.distributionQueryDelegatorWithdrawAddress(delegatorAddress, null);
    }

    /**
     * 'distributionQueryDelegatorWithdrawAddress' queries withdraw reward address of a delegator until specified block height.
     * @param delegatorAddress the delegator FX address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the withdraw reward address of a delegator until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressResponse> distributionQueryDelegatorWithdrawAddress(
            @NotNull String delegatorAddress,
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.delegatorWithdrawAddress(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    /**
     * 'distributionQueryParams' queries the 'distribution' module params until latest block height known.
     * @return the 'distribution' module params until latest block height known.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryParamsResponse> distributionQueryParams() {
        return this.distributionQueryParams(null);
    }

    /**
     * 'distributionQueryParams' queries the 'distribution' module params until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the 'distribution' module params until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryParamsResponse> distributionQueryParams(
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.params(cosmos.distribution.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /**
     * 'distributionQueryValidatorCommission' queries accumulated commission  (excluding already withdrawn commissions) for a validator until latest block height known.
     * @param validatorAddress the validator FX VALOPER address to query for.
     * @return accumulated commission  (excluding already withdrawn commissions) for a validator until latest block height known.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionResponse> distributionQueryValidatorCommission(
            @NotNull String validatorAddress
    ) {
        return this.distributionQueryValidatorCommission(validatorAddress, null);
    }

    /**
     * 'distributionQueryValidatorCommission' queries accumulated commission  (excluding already withdrawn commissions) for a validator until specified block height.
     * @param validatorAddress the validator FX VALOPER address to query for.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return accumulated commission  (excluding already withdrawn commissions) for a validator until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionResponse> distributionQueryValidatorCommission(
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.validatorCommission(cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionRequest.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    /**
     * 'distributionQueryValidatorOutstandingRewards' queries pending rewards of a validator for all its delegators until latest block height known.
     * @param validatorAddress the validator FX VALOPER address to query for.
     * @return pending rewards of a validator for all its delegators until latest block height known.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsResponse> distributionQueryValidatorOutstandingRewards(@NotNull String validatorAddress) {
        return this.distributionQueryValidatorOutstandingRewards(validatorAddress, null);
    }

    /**
     * 'distributionQueryValidatorOutstandingRewards' queries pending rewards of a validator for all its delegators until specified block height.
     * @param validatorAddress the validator FX VALOPER address to query for.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return pending rewards of a validator for all its delegators until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsResponse> distributionQueryValidatorOutstandingRewards(
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.validatorOutstandingRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsRequest.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    /**
     * 'distributionQueryValidatorSlashes' queries slash events of a validator until latest block height known.
     * @param validatorAddress the validator FX VALOPER address to query for.
     * @return The first page only of slash events of a validator until latest block height known.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionQueryValidatorSlashes(
            @NotNull String validatorAddress
    ) {
        return this.distributionQueryValidatorSlashes(validatorAddress, null, null);
    }

    /**
     * 'distributionQueryValidatorSlashes' queries slash events of a validator until latest block height known.
     * @param validatorAddress the validator FX VALOPER address to query for.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of slash events of a validator until latest block height known.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionQueryValidatorSlashes(
            @NotNull String validatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.distributionQueryValidatorSlashes(validatorAddress, pageRequest, null);
    }

    /**
     * 'distributionQueryValidatorSlashes' queries slash events of a validator until specified block height.
     * @param validatorAddress the validator FX VALOPER address to query for.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of slash events of a validator until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionQueryValidatorSlashes(
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        return this.distributionQueryValidatorSlashes(validatorAddress, null, height);
    }

    /**
     * 'distributionQueryValidatorSlashes' queries slash events of a validator until specified block height.
     * @param validatorAddress the validator FX VALOPER address to query for.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of slash events of a validator until specified block height.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionQueryValidatorSlashes(
            @NotNull String validatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesRequest.Builder _builder = cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.validatorSlashes(_builder.setValidatorAddress(validatorAddress).build());
    }

    /***************************************************************
     ***** Cosmos Module 'distribution' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/distribution/
     **************************************************************/

    /**
     * 'distributionMsgFundCommunityPool' defines a method to allow an account to directly fund the Community Pool.
     * @param depositor The FX depositor address.
     * @param amounts The coin amounts to deposit.
     * @return the result of the Community Pool funding.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgFundCommunityPoolResponse> distributionMsgFundCommunityPool(
            @NotNull String depositor,
            Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> amounts
    ) {
        return distributionMsgStub.fundCommunityPool(cosmos.distribution.v1beta1.Tx.MsgFundCommunityPool.newBuilder().setDepositor(depositor).addAllAmount(amounts).build());
    }

    /**
     * 'distributionMsgFundCommunityPool' defines a method to allow an account to directly fund the Community Pool.
     * @param depositor The FX depositor address.
     * @param amount The coin amount to deposit.
     * @return the result of the Community Pool funding.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgFundCommunityPoolResponse> distributionMsgFundCommunityPool(
            @NotNull String depositor,
            cosmos.base.v1beta1.CoinOuterClass.Coin amount
    ) {
        return distributionMsgStub.fundCommunityPool(cosmos.distribution.v1beta1.Tx.MsgFundCommunityPool.newBuilder().setDepositor(depositor).addAmount(amount).build());
    }

    /**
     * 'distributionMsgSetWithdrawAddress' defines a method to change the withdraw address for a delegator (or validator self-delegation).
     * @param delegatorAddress The FX delegator (or validator self-bonded) address.
     * @param withdrawAddress The FX withdraw address.
     * @return the result of the delegator's withdraw address change.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddressResponse> distributionMsgSetWithdrawAddress(
            String delegatorAddress,
            String withdrawAddress
    ) {
        return distributionMsgStub.setWithdrawAddress(cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress.newBuilder().setDelegatorAddress(delegatorAddress).setWithdrawAddress(withdrawAddress).build());
    }

    /**
     * 'distributionMsgWithdrawDelegatorReward' defines a method to withdraw rewards of delegator from a single validator.
     * @param delegatorAddress The FX delegator (or validator self-bonded) address.
     * @param validatorAddress The FX validator (VALOPER) address.
     * @return the result of the delegator's withdraw reward operation.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorRewardResponse> distributionMsgWithdrawDelegatorReward(
            String delegatorAddress,
            String validatorAddress
    ) {
        return distributionMsgStub.withdrawDelegatorReward(cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(delegatorAddress).setValidatorAddress(validatorAddress).build());
    }

    /**
     * 'distributionMsgWithdrawValidatorCommission' defines a method to withdraw the full commission to the validator address.
     * @param validatorAddress The FX validator (VALOPER) address.
     * @return the result of the delegator's withdraw commission operation.
     */
    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgWithdrawValidatorCommissionResponse> distributionMsgWithdrawValidatorCommission(
            String validatorAddress
    ) {
        return distributionMsgStub.withdrawValidatorCommission(cosmos.distribution.v1beta1.Tx.MsgWithdrawValidatorCommission.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    /***********************************************************
     ***** Cosmos Module 'evidence' : QUERY
     ***** https://docs.cosmos.network/master/modules/evidence/
     **********************************************************/

    /**
     * 'evidenceQueryAllEvidence' queries all evidence until latest block height known.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return the specified page of all evidence result until latest block height known.
     */
    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceQueryAllEvidence(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.evidenceQueryAllEvidence(pageRequest, null);
    }

    /**
     * 'evidenceQueryAllEvidence' queries all evidence until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the first page only of all evidence result until specified block height.
     */
    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceQueryAllEvidence(
            BigInteger height
    ) {
        return this.evidenceQueryAllEvidence(null, height);
    }

    /**
     * 'evidenceQueryAllEvidence' queries all evidence until specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the specified page of all evidence result until specified block height.
     */
    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceQueryAllEvidence(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceRequest.Builder _builder = cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.evidence.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = evidenceQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = evidenceQueryStub;
        }
        return stub.allEvidence(_builder.build());
    }

    /**
     * 'evidenceQueryEvidence' queries the specified evidence information.
     * @return the specified evidence information.
     */
    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceResponse> evidenceQueryEvidence(
            @NotNull byte[] evidenceHash
    ) {
        return evidenceQueryStub.evidence(cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceRequest.newBuilder().setEvidenceHash(com.google.protobuf.ByteString.copyFrom(evidenceHash)).build());
    }

    /************************************************************
     ***** Cosmos Module 'evidence' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/evidence/
     ***********************************************************/

    /**
     * SubmitEvidence submits an arbitrary Evidence of misbehavior such as equivocation or counterfactual signing.
     * @param submitter The submitter FX address
     * @param evidence The submitted evidence
     * @return The submitEvidence response.
     */
    public ListenableFuture<cosmos.evidence.v1beta1.Tx.MsgSubmitEvidenceResponse> distributionMsgSubmitEvidence(
            String submitter,
            com.google.protobuf.Any evidence
    ) {
        return evidenceMsgStub.submitEvidence(cosmos.evidence.v1beta1.Tx.MsgSubmitEvidence.newBuilder().setSubmitter(submitter).setEvidence(evidence).build());
    }

    /************************************************************
     ***** Cosmos Module 'feegrant' : QUERY
     ***** https://docs.cosmos.network/master/modules/feegrant/
     ***********************************************************/

    /**
     * 'feegrantQueryAllowances' returns all the grants for the grantee until latest block height known.
     * @param grantee FX grantee address for which grants are queried.
     * @return the first page only of all the grants for the grantee until latest block height known.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantQueryAllowances(
            @NotNull String grantee
    ) {
        return this.feegrantQueryAllowances(grantee, null, null);
    }

    /**
     * 'feegrantQueryAllowances' returns all the grants for the grantee until latest block height known.
     * @param grantee FX grantee address for which grants are queried.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return the specified page of all the grants for the grantee  until latest block height known.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantQueryAllowances(
            @NotNull String grantee,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.feegrantQueryAllowances(grantee, pageRequest, null);
    }

    /**
     * 'feegrantQueryAllowances' returns all the grants for the grantee until specified block height.
     * @param grantee FX grantee address for which grants are queried.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the first page only of all the grants for the grantee until specified block height.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantQueryAllowances(
            @NotNull String grantee,
            BigInteger height
    ) {
        return this.feegrantQueryAllowances(grantee, null, height);
    }

    /**
     * 'feegrantQueryAllowances' returns all the grants for the grantee.
     * @param grantee FX grantee address for which grants are queried.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the specified page of all the grants for the grantee until specified block height.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantQueryAllowances(
            @NotNull String grantee,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesRequest.Builder _builder = cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = feeGrantQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = feeGrantQueryStub;
        }
        return stub.allowances(_builder.setGrantee(grantee).build());
    }

    /**
     * 'feegrantQueryAllowance' returns the grant allowed to a grantee by the granter, until latest block height known.
     * @param grantee FX grantee address for which grants are queried.
     * @param granter FX granter address for which grants are queried.
     * @return the grant allowed to a grantee by the granter, until latest block height known.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceResponse> feegrantQueryAllowance(
            @NotNull String grantee,
            @NotNull String granter
    ) {
        return this.feegrantQueryAllowance(grantee, granter, null);
    }

    /**
     * 'feegrantQueryAllowance' returns the grant allowed to a grantee by the granter, until specified block height. .
     * @param grantee FX grantee address for which grants are queried.
     * @param granter FX granter address for which grants are queried.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the grant allowed to a grantee by the granter, until specified block height.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceResponse> feegrantQueryAllowance(
            @NotNull String grantee,
            @NotNull String granter,
            BigInteger height
    ) {
        cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = feeGrantQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = feeGrantQueryStub;
        }
        return stub.allowance(cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceRequest.newBuilder().setGrantee(grantee).setGranter(granter).build());
    }

    /**
     * 'feegrantQueryAllowancesByGranter' returns all the grants given by an FX address (granter), as known at the latest available block height.
     * @since Cosmos v0.46
     * @param granter FX granter address for which grants are queried.
     * @return The first page only of all the grants given by an FX address (granter), as known at the latest available block height.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesByGranterResponse> feegrantQueryAllowancesByGranter(
            @NotNull String granter
    ) {
        return this.feegrantQueryAllowancesByGranter(granter, null, null);
    }

    /**
     * 'feegrantQueryAllowancesByGranter' returns all the grants given by an FX address (granter), as known at the latest available block height.
     * @since Cosmos v0.46
     * @param granter FX granter address for which grants are queried.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of all the grants given by an FX address (granter), as known at the latest available block height.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesByGranterResponse> feegrantQueryAllowancesByGranter(
            @NotNull String granter,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.feegrantQueryAllowancesByGranter(granter, pageRequest, null);
    }

    /**
     * 'feegrantQueryAllowancesByGranter' returns all the grants given by an FX address (granter), as known at the specified block height.
     * @since Cosmos v0.46
     * @param granter FX granter address for which grants are queried.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of all the grants given by an FX address (granter), as known at the specified block height.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesByGranterResponse> feegrantQueryAllowancesByGranter(
            @NotNull String granter,
            BigInteger height
    ) {
        return this.feegrantQueryAllowancesByGranter(granter, null, height);
    }

    /**
     * 'feegrantQueryAllowancesByGranter' returns all the grants given by an FX address (granter), as known at the specified block height.
     * @since Cosmos v0.46
     * @param granter FX granter address for which grants are queried.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of all the grants given by an FX address (granter), as known at the specified block height.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesByGranterResponse> feegrantQueryAllowancesByGranter(
            @NotNull String granter,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesByGranterRequest.Builder _builder = cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesByGranterRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = feeGrantQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = feeGrantQueryStub;
        }
        return stub.allowancesByGranter(_builder.setGranter(granter).build());
    }

    /************************************************************
     ***** Cosmos Module 'feegrant' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/feegrant/
     ***********************************************************/

    /**
     * 'feegrantMsgGrantAllowance' grants fee allowance to the grantee on the granter's account with the provided expiration time.
     * @param granter the address of the user granting an allowance of their funds.
     * @param grantee the address of the user being granted an allowance of another user's funds.
     * @param allowance allowance can be com.google.protobuf.Any of {@see cosmos.feegrant.v1beta1.Feegrant.BasicAllowance}, {@see cosmos.feegrant.v1beta1.Feegrant.PeriodicAllowance}, {@see cosmos.feegrant.v1beta1.Feegrant.AllowedMsgAllowance} allowance.
     * @return the grant allowance response.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.Tx.MsgGrantAllowanceResponse> feegrantMsgGrantAllowance(
            @NotNull String granter,
            @NotNull String grantee,
            @NotNull com.google.protobuf.Any allowance) {
        return feegrantMsgStub.grantAllowance(cosmos.feegrant.v1beta1.Tx.MsgGrantAllowance.newBuilder().setGranter(granter).setGrantee(grantee).setAllowance(allowance).build());
    }

    /**
     * 'feegrantMsgRevokeAllowance' revokes com.google.protobuf.Any fee allowance of granter's account that has been granted to the grantee.
     * @param granter the address of the user granting an allowance of their funds.
     * @param grantee the address of the user being granted an allowance of another user's funds.
     * @return the grant revoke response.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.Tx.MsgRevokeAllowanceResponse> feegrantMsgRevokeAllowance(
            @NotNull String granter,
            @NotNull String grantee
    ) {
        return feegrantMsgStub.revokeAllowance(cosmos.feegrant.v1beta1.Tx.MsgRevokeAllowance.newBuilder().setGranter(granter).setGrantee(grantee).build());
    }

    /********************************************************
     ***** Cosmos Module 'gov' : QUERY
     ***** https://docs.cosmos.network/master/modules/gov/
     *******************************************************/

    /**
     * 'govQueryDepositV1' queries single deposit information based on proposalID and depositAddr until latest block height known.
     * @param proposalId the proposal ID
     * @param depositor the depositor FX address
     * @return single deposit information based on proposalID and depositAddr until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryDepositResponse> govQueryDepositV1(
            @NotNull Long proposalId,
            @NotNull String depositor
    ) {
        return this.govQueryDepositV1(proposalId, depositor, null);
    }

    /**
     * 'govQueryDepositV1' queries single deposit information based on proposalID and depositAddr until specified block height.
     * @param proposalId the proposal ID
     * @param depositor the depositor FX address
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return single deposit information based on proposalID and depositAddr until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryDepositResponse> govQueryDepositV1(
            @NotNull Long proposalId,
            @NotNull String depositor,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.deposit(cosmos.gov.v1beta1.QueryOuterClass.QueryDepositRequest.newBuilder().setProposalId(proposalId).setDepositor(depositor).build());
    }

    /**
     * 'govQueryDepositsV1' queries all deposits of a single proposal until latest block height known.
     * @param proposalId the proposal ID
     * @return first page only of all deposits of a single proposal until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryDepositsResponse> govQueryDepositsV1(
            @NotNull Long proposalId
    ) {
        return this.govQueryDepositsV1(proposalId, null, null);
    }

    /**
     * 'govQueryDepositsV1' queries all deposits of a single proposal until latest block height known.
     * @param proposalId the proposal ID
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return specified page of all deposits of a single proposal until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryDepositsResponse> govQueryDepositsV1(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryDepositsV1(proposalId, pageRequest, null);
    }

    /**
     * 'govQueryDepositsV1' queries all deposits of a single proposal until specified block height.
     * @param proposalId the proposal ID
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return first page only of all deposits of a single proposal until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryDepositsResponse> govQueryDepositsV1(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        return this.govQueryDepositsV1(proposalId, null, height);
    }

    /**
     * 'govQueryDepositsV1' queries all deposits of a single proposal until specified block height.
     * @param proposalId the proposal ID
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return specified page of all deposits of a single proposal until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryDepositsResponse> govQueryDepositsV1(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryOuterClass.QueryDepositsRequest.Builder _builder = cosmos.gov.v1beta1.QueryOuterClass.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.deposits(_builder.setProposalId(proposalId).build());
    }

    /**
     * 'govQueryParamsV1' queries all parameters of the gov module at the latest block height known.
     * @param paramsType The params type ("voting", "tally" or "deposit")
     * @return all parameters of the gov module at the latest block height known
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryParamsResponse> govQueryParamsV1(
            @NotNull String paramsType
    ) {
        return this.govQueryParamsV1(paramsType, null);
    }

    /**
     * 'govQueryParamsV1' queries all parameters of the gov module at the specified block height.
     * @param paramsType The params type ("voting", "tally" or "deposit")
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return all parameters of the gov module at the specified block height
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryParamsResponse> govQueryParamsV1(
            @NotNull String paramsType,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.params(cosmos.gov.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().setParamsType(paramsType).build());
    }

    /**
     * 'govQueryProposalV1' queries proposal details based on ProposalID at the latest block height known.
     * @param proposalId the proposal ID
     * @return proposal details based on ProposalID at the latest block height known
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryProposalResponse> govQueryProposalV1(
            @NotNull Long proposalId
    ) {
        return this.govQueryProposalV1(proposalId, null);
    }

    /**
     * 'govQueryProposalV1' queries proposal details based on ProposalID at the specified block height.
     * @param proposalId the proposal ID
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return proposal details based on ProposalID at the specified block height
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryProposalResponse> govQueryProposalV1(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.proposal(cosmos.gov.v1beta1.QueryOuterClass.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    /**
     * 'govQueryProposalsV1' queries all proposals until latest known block height.
     * @return First page only of all proposals until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryProposalsResponse> govQueryProposalsV1() {
        return this.govQueryProposalsV1(null, null, null, null, null);
    }

    /**
     * 'govQueryProposalsV1' queries all proposals until latest known block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all proposals until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryProposalsResponse> govQueryProposalsV1(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryProposalsV1(null, null, null, pageRequest, null);
    }

    /**
     * 'govQueryProposalsV1' queries all proposals until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all proposals until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryProposalsResponse> govQueryProposalsV1(
            BigInteger height
    ) {
        return this.govQueryProposalsV1(null, null, null, null, height);
    }

    /**
     * 'govQueryProposalsV1' queries all proposals based on given status, FX voter address or FX depositor address, until latest known block height.
     * @param depositor The FX depositor address (optional)
     * @param voter The FX voter address (optional)
     * @param proposalStatus The proposal status (optional)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all proposals based on given status, FX voter address or FX depositor address, until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryProposalsResponse> govQueryProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryProposalsV1(depositor, voter, proposalStatus, pageRequest, null);
    }

    /**
     * 'govQueryProposalsV1' queries all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     * @param depositor The FX depositor address (optional)
     * @param voter The FX voter address (optional)
     * @param proposalStatus The proposal status (optional)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryProposalsResponse> govQueryProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            BigInteger height
    ) {
        return this.govQueryProposalsV1(depositor, voter, proposalStatus, null, height);
    }

    /**
     * 'govQueryProposalsV1' queries all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     * @param depositor The FX depositor address (optional)
     * @param voter The FX voter address (optional)
     * @param proposalStatus The proposal status (optional)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryProposalsResponse> govQueryProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryOuterClass.QueryProposalsRequest.Builder _builder = cosmos.gov.v1beta1.QueryOuterClass.QueryProposalsRequest.newBuilder();
        if (depositor != null) {
            _builder.setDepositor(depositor);
        }
        if (voter != null) {
            _builder.setVoter(voter);
        }
        if (proposalStatus != null) {
            _builder.setProposalStatus(proposalStatus);
        }
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.proposals(_builder.build());
    }

    /**
     * 'govQueryTallyResultV1' queries the tally of a proposal vote at the latest known block height.
     * @param proposalId The proposal ID
     * @return the tally of a proposal vote at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryTallyResultResponse> govQueryTallyResultV1(
            @NotNull Long proposalId
    ) {
        return this.govQueryTallyResultV1(proposalId, null);
    }

    /**
     * 'govQueryTallyResultV1' queries the tally of a proposal vote at the specified block height.
     * @param proposalId The proposal ID
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the tally of a proposal vote at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryTallyResultResponse> govQueryTallyResultV1(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.tallyResult(cosmos.gov.v1beta1.QueryOuterClass.QueryTallyResultRequest.newBuilder().setProposalId(proposalId).build());
    }

    /**
     * 'govQueryVotesV1' queries votes of a given proposal at the latest known block height.
     * @param proposalId The proposal ID
     * @return First page only of votes of a given proposal at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryVotesResponse> govQueryVotesV1(
            @NotNull Long proposalId
    ) {
        return this.govQueryVotesV1(proposalId, null, null);
    }

    /**
     * 'govQueryVotesV1' queries votes of a given proposal at the latest known block height.
     * @param proposalId The proposal ID
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of votes of a given proposal at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryVotesResponse> govQueryVotesV1(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryVotesV1(proposalId, pageRequest, null);
    }

    /**
     * 'govQueryVotesV1' queries votes of a given proposal at the specified block height.
     * @param proposalId The proposal ID
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of votes of a given proposal at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryVotesResponse> govQueryVotesV1(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        return this.govQueryVotesV1(proposalId, null, height);
    }

    /**
     * 'govQueryVotesV1' queries votes of a given proposal at the specified block height.
     * @param proposalId The proposal ID
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of votes of a given proposal at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryVotesResponse> govQueryVotesV1(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryOuterClass.QueryVotesRequest.Builder _builder = cosmos.gov.v1beta1.QueryOuterClass.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.votes(_builder.setProposalId(proposalId).build());
    }

    /**
     * 'govQueryVoteV1' queries vote information based on proposalID and voter address at the latest known block height.
     * @param proposalId The proposal ID.
     * @param voter The FX voter address.
     * @return vote information based on proposalID and voter address at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryVoteResponse> govQueryVoteV1(
            @NotNull Long proposalId,
            @NotNull String voter
    ) {
        return this.govQueryVoteV1(proposalId, voter, null);
    }

    /**
     * 'govQueryVoteV1' queries vote information based on proposalID and voter address at the specified block height.
     * @param proposalId The proposal ID.
     * @param voter The FX voter address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return vote information based on proposalID and voter address at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryOuterClass.QueryVoteResponse> govQueryVoteV1(
            @NotNull Long proposalId,
            @NotNull String voter,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.vote(cosmos.gov.v1beta1.QueryOuterClass.QueryVoteRequest.newBuilder().setProposalId(proposalId).setVoter(voter).build());
    }

    /*************************************************************
     ***** Cosmos Module 'gov' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/gov/
     ************************************************************/

    /**
     * 'govMsgSubmitProposalV1' defines a method to create new proposal given a content.
     * @param proposer Proposer FX address
     * @param content com.google.protobuf.Any of cosmos.distribution.v1beta1.Distribution.CommunityPoolSpendProposal, cosmos.gov.v1beta1.Gov.TextProposal, cosmos.params.v1beta1.params.ParameterChangeProposal, fx.gravity.crosschain.v1.Crosschain.InitCrossChainParamsProposal, cosmos.upgrade.v1beta1.Upgrade.SoftwareUpgradeProposal, cosmos.upgrade.v1beta1.Upgrade.CancelSoftwareUpgradeProposal
     * @param deposits Coin amounts for deposits
     * @return the submitProposal response
     */
    public ListenableFuture<cosmos.gov.v1beta1.Tx.MsgSubmitProposalResponse> govMsgSubmitProposalV1(
            String proposer,
            com.google.protobuf.Any content,
            Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits
    ) {
        return govMsgStubV1.submitProposal(cosmos.gov.v1beta1.Tx.MsgSubmitProposal.newBuilder().setProposer(proposer).setContent(content).addAllInitialDeposit(deposits).build());
    }

    /**
     * 'govMsgSubmitProposalV1' defines a method to create new proposal given a content.
     * @param proposer Proposer FX address
     * @param content com.google.protobuf.Any of cosmos.distribution.v1beta1.Distribution.CommunityPoolSpendProposal, cosmos.gov.v1beta1.Gov.TextProposal, cosmos.params.v1beta1.params.ParameterChangeProposal, fx.gravity.crosschain.v1.Crosschain.InitCrossChainParamsProposal, cosmos.upgrade.v1beta1.Upgrade.SoftwareUpgradeProposal, cosmos.upgrade.v1beta1.Upgrade.CancelSoftwareUpgradeProposal
     * @param deposit Coin amount for deposit
     * @return the submitProposal response
     */
    public ListenableFuture<cosmos.gov.v1beta1.Tx.MsgSubmitProposalResponse> govMsgSubmitProposalV1(
            String proposer,
            com.google.protobuf.Any content,
            cosmos.base.v1beta1.CoinOuterClass.Coin deposit
    ) {
        return govMsgStubV1.submitProposal(cosmos.gov.v1beta1.Tx.MsgSubmitProposal.newBuilder().setProposer(proposer).setContent(content).addInitialDeposit(deposit).build());
    }

    /**
     * 'govMsgDepositV1' defines a method to add deposit on a specific proposal.
     * @param proposalId The proposal ID
     * @param depositor The depositor FX address
     * @param deposits Coin amounts for deposits
     * @return the deposit response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.Tx.MsgDepositResponse> govMsgDepositV1(
            Long proposalId,
            String depositor,
            Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits
    ) {
        return govMsgStubV1.deposit(cosmos.gov.v1beta1.Tx.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAllAmount(deposits).build());
    }

    /**
     * 'govMsgDepositV1' defines a method to add deposit on a specific proposal.
     * @param proposalId The proposal ID
     * @param depositor The depositor FX address
     * @param deposit Coin amount for deposit
     * @return the deposit response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.Tx.MsgDepositResponse> govMsgDepositV1(
            Long proposalId,
            String depositor,
            cosmos.base.v1beta1.CoinOuterClass.Coin deposit
    ) {
        return govMsgStubV1.deposit(cosmos.gov.v1beta1.Tx.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAmount(deposit).build());
    }

    /**
     * 'govMsgVoteV1' defines a method to add a vote on a specific proposal.
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param option The vote option
     * @return the vote response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.Tx.MsgVoteResponse> govMsgVoteV1(
            Long proposalId,
            String voter,
            cosmos.gov.v1beta1.Gov.VoteOption option
    ) {
        return govMsgStubV1.vote(cosmos.gov.v1beta1.Tx.MsgVote.newBuilder().setProposalId(proposalId).setVoter(voter).setOption(option).build());
    }

    /**
     * 'govMsgVoteWeightedV1' defines a method to add a weighted vote on a specific proposal.
     * @Since cosmos-sdk 0.43
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param options The vote options
     * @return the voteWeighted response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.Tx.MsgVoteWeightedResponse> govMsgVoteWeightedV1(
            Long proposalId,
            String voter,
            Iterable<? extends cosmos.gov.v1beta1.Gov.WeightedVoteOption> options
    ) {
        return govMsgStubV1.voteWeighted(cosmos.gov.v1beta1.Tx.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addAllOptions(options).build());
    }

    /**
     * 'govMsgVoteWeightedV1' defines a method to add a weighted vote on a specific proposal.
     * @Since cosmos-sdk 0.43
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param option The vote option
     * @return the voteWeighted response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.Tx.MsgVoteWeightedResponse> govMsgVoteWeightedV1(
            Long proposalId,
            String voter,
            cosmos.gov.v1beta1.Gov.WeightedVoteOption option
    ) {
        return govMsgStubV1.voteWeighted(cosmos.gov.v1beta1.Tx.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addOptions(option).build());
    }

    /********************************************************
     ***** Cosmos Module 'mint' : QUERY
     ***** https://docs.cosmos.network/master/modules/mint/
     *******************************************************/

    /**
     * 'mintQueryAnnualProvisions' queries current minting annual provisions value at the latest known height.
     * @return The current minting annual provisions value at the latest known block height.
     */
    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsResponse> mintQueryAnnualProvisions() {
        return this.mintQueryAnnualProvisions(null);
    }

    /**
     * 'mintQueryAnnualProvisions' queries current minting annual provisions value at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The current minting annual provisions value at the specified block height.
     */
    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsResponse> mintQueryAnnualProvisions(
            BigInteger height
    ) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = mintQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintQueryStub;
        }
        return stub.annualProvisions(cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsRequest.newBuilder().build());
    }

    /**
     * 'mintQueryInflation' returns the current minting inflation value at the latest known block height.
     * @return The current minting inflation value at the latest known block height.
     */
    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryInflationResponse> mintQueryInflation() {
        return this.mintQueryInflation(null);
    }

    /**
     * 'mintQueryInflation' returns the current minting inflation value at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The current minting inflation value at the specified block height.
     */
    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryInflationResponse> mintQueryInflation(
            BigInteger height
    ) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = mintQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintQueryStub;
        }
        return stub.inflation(cosmos.mint.v1beta1.QueryOuterClass.QueryInflationRequest.newBuilder().build());
    }

    /**
     * 'mintQueryParams' returns the total set of minting parameters at the latest known block height.
     * @return The total set of minting parameters at the latest known block height.
     */
    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryParamsResponse> mintQueryParams() {
        return this.mintQueryParams(null);
    }

    /**
     * 'mintQueryParams' returns the total set of minting parameters at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The total set of minting parameters at the specified block height.
     */
    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryParamsResponse> mintQueryParams(
            BigInteger height
    ) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = mintQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintQueryStub;
        }
        return stub.params(cosmos.mint.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /********************************************************
     ***** Cosmos Module 'params' : QUERY
     ***** https://docs.cosmos.network/master/modules/params/
     *******************************************************/

    /**
     * 'paramsQueryParams' queries a specific parameter of a module, given its subspace and key, as known at the latest available height.
     * @param subspace The subspace name
     * @param key The key name
     * @return A specific parameter of a module, given its subspace and key, as known at the latest available height.
     */
    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsQueryParams(
            @NotNull String subspace,
            @NotNull String key
    ) {
        return this.paramsQueryParams(subspace, null);
    }

    /**
     * 'paramsQueryParams' queries a specific parameter of a module, given its subspace and key, as known at the specified height.
     * @param subspace The subspace name
     * @param key The key name
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return A specific parameter of a module, given its subspace and key, as known at the specified height.
     */
    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsQueryParams(
            @NotNull String subspace,
            @NotNull String key,
            BigInteger height
    ) {
        cosmos.params.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = paramsQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = paramsQueryStub;
        }
        return stub.params(cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().setSubspace(subspace).setKey(key).build());
    }

    /************************************************************
     ***** Cosmos Module 'slashing' : QUERY
     ***** https://docs.cosmos.network/master/modules/slashing/
     ***********************************************************/

    /**
     * 'slashingQueryParams' queries the parameters of slashing module, as known at the latest available block height.
     * @return Parameters of slashing module, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsResponse> slashingQueryParams() {
        return this.slashingQueryParams(null);
    }

    /**
     * 'slashingQueryParams' queries the parameters of slashing module, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Parameters of slashing module, as known at the specified block height.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsResponse> slashingQueryParams(
            BigInteger height
    ) {
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = slashingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingQueryStub;
        }
        return stub.params(cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /**
     * 'slashingQuerySigningInfo' queries the signing info of a given validator consensus address, as known at the latest available block height.
     * @param valConsAddress Validator consensus address ('fxvalcons1...')
     * @return The signing info of a given validator consensus address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoResponse> slashingQuerySigningInfo(
            @NotNull String valConsAddress
    ) {
        return this.slashingQuerySigningInfo(valConsAddress, null);
    }

    /**
     * 'slashingQuerySigningInfo' queries the signing info of a given validator consensus address, as known at the specified block height.
     * @param valConsAddress Validator consensus address ('fxvalcons1...')
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The signing info of a given validator consensus address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoResponse> slashingQuerySigningInfo(
            @NotNull String valConsAddress,
            BigInteger height
    ) {
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = slashingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingQueryStub;
        }
        return stub.signingInfo(cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoRequest.newBuilder().setConsAddress(valConsAddress).build());
    }

    /**
     * 'slashingQuerySigningInfos' queries the signing infos of all validators, as known at the latest available block height.
     * @return The first page of signing infos of all validators, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingQuerySigningInfos() {
        return this.slashingQuerySigningInfos(null, null);
    }

    /**
     * 'slashingQuerySigningInfos' queries the signing infos of all validators, as known at the latest available block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of signing infos of all validators, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingQuerySigningInfos(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.slashingQuerySigningInfos(pageRequest, null);
    }

    /**
     * 'slashingQuerySigningInfos' queries the signing infos of all validators, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page of signing infos of all validators, as known at the specified block height.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingQuerySigningInfos(
            BigInteger height
    ) {
        return this.slashingQuerySigningInfos(null, height);
    }

    /**
     * 'slashingQuerySigningInfos' queries the signing infos of all validators, as known at the specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of signing infos of all validators, as known at the specified block height.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingQuerySigningInfos(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.Builder _builder = cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = slashingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingQueryStub;
        }
        return stub.signingInfos(_builder.build());
    }

    /************************************************************
     ***** Cosmos Module 'slashing' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/slashing/
     ***********************************************************/

    /**
     * 'slashingMsgUnjail' defines a method for unjailing a jailed validator, thus returning
     * them into the bonded validator set, so they can begin receiving provisions and rewards again.
     * @param validatorAddr Validator address ('fxvaloper1....')
     * @return The Unjail response.
     */
    public ListenableFuture<cosmos.slashing.v1beta1.Tx.MsgUnjailResponse> slashingMsgUnjail(
            @NotNull String validatorAddr
    ) {
        return slashingMsgStub.unjail(cosmos.slashing.v1beta1.Tx.MsgUnjail.newBuilder().setValidatorAddr(validatorAddr).build());
    }

    /************************************************************
     ***** Cosmos Module 'staking' : QUERY
     ***** https://docs.cosmos.network/master/modules/staking/
     ***********************************************************/

    /**
     * 'stakingQueryDelegation' queries delegate info for given validator-delegator pair, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @return The Delegation response, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationResponse> stakingQueryDelegation(
            @NotNull String delegatorAddress,
            @NotNull String validatorAddress
    ) {
        return this.stakingQueryDelegation(delegatorAddress, validatorAddress, null);
    }

    /**
     * 'stakingQueryDelegation' queries delegate info for given validator-delegator pair, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The Delegation response, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationResponse> stakingQueryDelegation(
            @NotNull String delegatorAddress,
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegation(cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    /**
     * 'stakingQueryDelegatorDelegations' queries all delegations of a given delegator address, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @return The first page only of all delegations of a given delegator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingQueryDelegatorDelegations(
            @NotNull String delegatorAddress
    ) {
        return this.stakingQueryDelegatorDelegations(delegatorAddress, null, null);
    }

    /**
     * 'stakingQueryDelegatorDelegations' queries all delegations of a given delegator address, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of all delegations of a given delegator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingQueryDelegatorDelegations(
            @NotNull String delegatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.stakingQueryDelegatorDelegations(delegatorAddress, pageRequest, null);
    }

    /**
     * 'stakingQueryDelegatorDelegations' queries all delegations of a given delegator address, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of all delegations of a given delegator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingQueryDelegatorDelegations(
            @NotNull String delegatorAddress,
            BigInteger height
    ) {
        return this.stakingQueryDelegatorDelegations(delegatorAddress, null, height);
    }

    /**
     * 'stakingQueryDelegatorDelegations' queries all delegations of a given delegator address, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of all delegations of a given delegator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingQueryDelegatorDelegations(
            @NotNull String delegatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorDelegations(_builder.setDelegatorAddr(delegatorAddress).build());
    }

    /**
     * 'stakingQueryDelegatorUnbondingDelegations' queries all unbonding delegations of a given delegator address, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @return First page only of all unbonding delegations of a given delegator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingQueryDelegatorUnbondingDelegations(
            @NotNull String delegatorAddress
    ) {
        return this.stakingQueryDelegatorUnbondingDelegations(delegatorAddress, null, null);
    }

    /**
     * 'stakingQueryDelegatorUnbondingDelegations' queries all unbonding delegations of a given delegator address, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all unbonding delegations of a given delegator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingQueryDelegatorUnbondingDelegations(
            @NotNull String delegatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.stakingQueryDelegatorUnbondingDelegations(delegatorAddress, pageRequest, null);
    }

    /**
     * 'stakingQueryDelegatorUnbondingDelegations' queries all unbonding delegations of a given delegator address, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all unbonding delegations of a given delegator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingQueryDelegatorUnbondingDelegations(
            @NotNull String delegatorAddress,
            BigInteger height
    ) {
        return this.stakingQueryDelegatorUnbondingDelegations(delegatorAddress, null, height);
    }

    /**
     * 'stakingQueryDelegatorUnbondingDelegations' queries all unbonding delegations of a given delegator address, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all unbonding delegations of a given delegator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingQueryDelegatorUnbondingDelegations(
            @NotNull String delegatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorUnbondingDelegations(_builder.setDelegatorAddr(delegatorAddress).build());
    }

    /**
     * 'stakingQueryDelegatorValidators' queries all validators info for given delegator address, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @return First page only of all validators info for given delegator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingQueryDelegatorValidators(
            @NotNull String delegatorAddress
    ) {
        return this.stakingQueryDelegatorValidators(delegatorAddress, null, null);
    }

    /**
     * 'stakingQueryDelegatorValidators' queries all validators info for given delegator address, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all validators info for given delegator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingQueryDelegatorValidators(
            @NotNull String delegatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.stakingQueryDelegatorValidators(delegatorAddress, pageRequest, null);
    }

    /**
     * 'stakingQueryDelegatorValidators' queries all validators info for given delegator address, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only page of all validators info for given delegator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingQueryDelegatorValidators(
            @NotNull String delegatorAddress,
            BigInteger height
    ) {
        return this.stakingQueryDelegatorValidators(delegatorAddress, null, height);
    }

    /**
     * 'stakingQueryDelegatorValidators' queries all validators info for given delegator address, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all validators info for given delegator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingQueryDelegatorValidators(
            @NotNull String delegatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorValidators(_builder.setDelegatorAddr(delegatorAddress).build());
    }

    /**
     * 'stakingQueryDelegatorValidator' queries validator info for given delegator validator pair, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @return Validator info for given delegator validator pair, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorResponse> stakingQueryDelegatorValidator(
            @NotNull String delegatorAddress,
            @NotNull String validatorAddress
    ) {
        return this.stakingQueryDelegatorValidator(delegatorAddress, validatorAddress, null);
    }

    /**
     * 'stakingQueryDelegatorValidator' queries validator info for given delegator validator pair, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Validator info for given delegator validator pair, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorResponse> stakingQueryDelegatorValidator(
            @NotNull String delegatorAddress,
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorValidator(cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    /**
     * 'stakingQueryHistoricalInfo' queries the historical info for the given block height.
     * @param height Block height used for the query.
     * @return Historical info for the given block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> stakingQueryHistoricalInfo(
            Long height
    ) {
        return stakingQueryStub.historicalInfo(cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest.newBuilder().setHeight(height).build());
    }

    /**
     * 'stakingQueryParams' queries the staking parameters, as known at the latest available block height.
     * @return The staking parameters, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryParamsResponse> stakingQueryParams() {
        return this.stakingQueryParams(null);
    }

    /**
     * 'stakingQueryParams' queries the staking parameters, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The staking parameters, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryParamsResponse> stakingQueryParams(
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.params(cosmos.staking.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /**
     * 'stakingQueryPool' queries the community pool information, as known at the latest available block height.
     * @return The community pool information, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryPoolResponse> stakingQueryPool() {
        return this.stakingQueryPool(null);
    }

    /**
     * 'stakingQueryPool' queries the community pool information, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The community pool information, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryPoolResponse> stakingQueryPool(
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.pool(cosmos.staking.v1beta1.QueryOuterClass.QueryPoolRequest.newBuilder().build());
    }

    /**
     * 'stakingQueryRedelegations' queries redelegations of a given delegator address, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @return First page only of redelegations of a given delegator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingQueryRedelegations(
            @NotNull String delegatorAddress
    ) {
        return this.stakingQueryRedelegations(delegatorAddress, null, null);
    }

    /**
     * 'stakingQueryRedelegations' queries redelegations of a given delegator address, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of redelegations of a given delegator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingQueryRedelegations(
            @NotNull String delegatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.stakingQueryRedelegations(delegatorAddress, pageRequest, null);
    }

    /**
     * 'stakingQueryRedelegations' queries redelegations of a given delegator address, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of redelegations of a given delegator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingQueryRedelegations(
            @NotNull String delegatorAddress,
            BigInteger height
    ) {
        return this.stakingQueryRedelegations(delegatorAddress, null, height);
    }

    /**
     * 'stakingQueryRedelegations' queries redelegations of a given delegator address, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of redelegations of a given delegator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingQueryRedelegations(
            @NotNull String delegatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.redelegations(_builder.setDelegatorAddr(delegatorAddress).build());
    }

    /**
     * 'stakingQueryUnbondingDelegation' queries unbonding info for a given validator-delegator pair, as known at the latest available block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @return Unbonding info for a given validator-delegator pair, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationResponse> stakingQueryUnbondingDelegation(
            @NotNull String delegatorAddress,
            @NotNull String validatorAddress
    ) {
        return this.stakingQueryUnbondingDelegation(delegatorAddress, validatorAddress, null);
    }

    /**
     * 'stakingQueryUnbondingDelegation' queries unbonding info for a given validator-delegator pair, as known at the specified block height.
     * @param delegatorAddress Delegator FX address (fx1...)
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Unbonding info for a given validator-delegator pair, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationResponse> stakingQueryUnbondingDelegation(
            @NotNull String delegatorAddress,
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.unbondingDelegation(cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    /**
     * 'stakingQueryValidator' queries validator info for given validator address, as known at the latest available block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @return Validator info for given validator address, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> stakingQueryValidator(
            @NotNull String validatorAddress
    ) {
        return this.stakingQueryValidator(validatorAddress, null);
    }

    /**
     * 'stakingQueryValidator' queries validator info for given validator address, as known at the specified block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Validator info for given validator address, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> stakingQueryValidator(
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validator(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest.newBuilder().setValidatorAddr(validatorAddress).build());
    }

    /**
     * 'stakingQueryValidators' queries all validators, as known at the latest available block height.
     * @return First page of all validators, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators() {
        return this.stakingQueryValidators(null, null, null);
    }

    /**
     * 'stakingQueryValidators' queries all validators, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all validators, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(
            BigInteger height
    ) {
        return this.stakingQueryValidators(null, null, height);
    }

    /**
     * 'stakingQueryValidators' queries all validators that match the given status, as known at the latest available block height.
     * @param status Validator status to match.
     * @return First page only of all validators that match the given status, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(
            String status
    ) {
        return this.stakingQueryValidators(status, null, null);
    }

    /**
     * 'stakingQueryValidators' queries all validators that match the given status, as known at the latest available block height.
     * @param status Validator status to match.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all validators that match the given status, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(
            String status,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.stakingQueryValidators(status, pageRequest, null);
    }

    /**
     * 'stakingQueryValidators' queries all validators that match the given status, as known at the specified block height.
     * @param status Validator status to match.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all validators that match the given status, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(
            String status,
            BigInteger height
    ) {
        return this.stakingQueryValidators(status, null, height);
    }

    /**
     * 'stakingQueryValidators' queries all validators, as known at the specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all validators, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        return this.stakingQueryValidators(null, pageRequest, height);
    }

    /**
     * 'stakingQueryValidators' queries all validators that match the given status, as known at the specified block height.
     * @param status Validator status to match.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all validators that match the given status, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(
            String status,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest.newBuilder();
        if (status != null) {
            _builder.setStatus(status);
        }
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validators(_builder.build());
    }

    /**
     * 'stakingQueryValidatorDelegations' queries delegate info for a given validator, as known at the latest available block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @return First page only of delegate info for a given validator, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingQueryValidatorDelegations(
            @NotNull String validatorAddress
    ) {
        return this.stakingQueryValidatorDelegations(validatorAddress, null, null);
    }

    /**
     * 'stakingQueryValidatorDelegations' queries delegate info for a given validator, as known at the latest available block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of delegate info for a given validator, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingQueryValidatorDelegations(
            @NotNull String validatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.stakingQueryValidatorDelegations(validatorAddress, pageRequest, null);
    }

    /**
     * 'stakingQueryValidatorDelegations' queries delegate info for a given validator, as known at the specified block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of delegate info for a given validator, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingQueryValidatorDelegations(
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        return this.stakingQueryValidatorDelegations(validatorAddress, null, height);
    }

    /**
     * 'stakingQueryValidatorDelegations' queries delegate info for a given validator, as known at the specified block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of delegate info for a given validator, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingQueryValidatorDelegations(
            @NotNull String validatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsRequest.newBuilder()
                .setValidatorAddr(validatorAddress);
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validatorDelegations(_builder.build());
    }

    /**
     * 'stakingQueryValidatorUnbondingDelegations' queries unbonding delegations of a validator, as known at the latest available block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of unbonding delegations of a validator, as known at the latest available block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(
            @NotNull String validatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.stakingQueryValidatorUnbondingDelegations(validatorAddress, pageRequest, null);
    }

    /**
     * 'stakingQueryValidatorUnbondingDelegations' queries unbonding delegations of a validator, as known at the specified block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of unbonding delegations of a validator, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        return this.stakingQueryValidatorUnbondingDelegations(validatorAddress, null, height);
    }

    /**
     * 'stakingQueryValidatorUnbondingDelegations' queries unbonding delegations of a validator, as known at the specified block height.
     * @param validatorAddress Validator FX address (fxvaloper1...)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of unbonding delegations of a validator, as known at the specified block height.
     */
    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(
            @NotNull String validatorAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validatorUnbondingDelegations(_builder.setValidatorAddr(validatorAddress).build());
    }

    /************************************************************
     ***** Cosmos Module 'staking' : MESSAGE
     ***** https://docs.cosmos.network/master/modules/staking/
     ***********************************************************/

    /**
     * 'stakingMsgCreateValidator' defines a method for creating a new validator.
     * @param commissionRates The commission rates defined for the new validator.
     * @param validatorAddress The FX validator address (fxvaloper1...)
     * @param delegatorAddress The FX validator's self-bonding delegator address (fx1...)
     * @param description The FX validator description
     * @param minSelfDelegation The minimum self-bonding delegation amount, below which the validator will automatically unbind
     * @param publicKey The validator public key
     * @param amountToBind The FX amount to bind to the delegator
     * @return The CreateValidator response
     */
    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgCreateValidatorResponse> stakingMsgCreateValidator(
            @NotNull cosmos.staking.v1beta1.Staking.CommissionRates commissionRates,
            @NotNull String validatorAddress,
            @NotNull String delegatorAddress,
            @NotNull cosmos.staking.v1beta1.Staking.Description description,
            @NotNull String minSelfDelegation,
            @NotNull com.google.protobuf.Any publicKey,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amountToBind
    ) {
        return stakingMsgStub.createValidator(cosmos.staking.v1beta1.Tx.MsgCreateValidator.newBuilder()
                .setValidatorAddress(validatorAddress)
                .setDelegatorAddress(delegatorAddress)
                .setPubkey(publicKey)
                .setCommission(commissionRates)
                .setDescription(description)
                .setMinSelfDelegation(minSelfDelegation)
                .setValue(amountToBind)
                .build());
    }

    /**
     * 'stakingMsgEditValidator' defines a method for editing an existing validator.
     * @param commissionRate The new commission rate of the validator.
     * @param validatorAddress The FX validator address (fxvaloper1...)
     * @param description The new FX validator description
     * @param minSelfDelegation The new (increased) minimum self-bonding delegation amount, below which the validator will automatically unbind
     * @return The EditValidator response.
     */
    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgEditValidatorResponse> stakingMsgEditValidator(
            String commissionRate,
            @NotNull String validatorAddress,
            @NotNull cosmos.staking.v1beta1.Staking.Description description,
            @NotNull String minSelfDelegation
    ) {
        cosmos.staking.v1beta1.Tx.MsgEditValidator.Builder _builder = cosmos.staking.v1beta1.Tx.MsgEditValidator.newBuilder();
        if (commissionRate != null) {
            _builder.setCommissionRate(commissionRate);
        }
        if (description != null) {
            _builder.setDescription(description);
        }
        if (minSelfDelegation != null) {
            _builder.setMinSelfDelegation(minSelfDelegation);
        }
        return stakingMsgStub.editValidator(_builder
                .setValidatorAddress(validatorAddress)
                .build());
    }

    /**
     * 'stakingMsgDelegate' defines a method for performing a delegation of coins from a delegator to a validator.
     * @param validatorAddress The FX validator address (fxvaloper1...)
     * @param delegatorAddress The FX delegator address (fx1...)
     * @param amount The FX amount to delegate to the validator.
     * @return The Delegate response.
     */
    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgDelegateResponse> stakingMsgDelegate(
            @NotNull String validatorAddress,
            @NotNull String delegatorAddress,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount
    ) {
        return stakingMsgStub.delegate(cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder()
                .setDelegatorAddress(delegatorAddress)
                .setValidatorAddress(validatorAddress)
                .setAmount(amount)
                .build());
    }

    /**
     * 'stakingMsgUndelegate' defines a method for performing an undelegation of coins from a delegator to a validator.
     * @param validatorAddress The FX validator address (fxvaloper1...)
     * @param delegatorAddress The FX delegator address (fx1...)
     * @param amount The FX amount to undelegate from the validator.
     * @return The Undelegate response.
     */
    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgUndelegateResponse> stakingMsgUndelegate(
            @NotNull String validatorAddress,
            @NotNull String delegatorAddress,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount
    ) {
        return stakingMsgStub.undelegate(cosmos.staking.v1beta1.Tx.MsgUndelegate.newBuilder()
                .setDelegatorAddress(delegatorAddress)
                .setValidatorAddress(validatorAddress)
                .setAmount(amount)
                .build());
    }

    /**
     * 'stakingMsgBeginRedelegate' defines a method for performing a redelegation of coins from a delegator and source validator to a destination validator.
     * @param validatorSrcAddress The source FX validator address (fxvaloper1...)
     * @param validatorDstAddress The destination FX validator address (fxvaloper1...)
     * @param delegatorAddress The FX delegator address (fx1...)
     * @param amount The FX amount to redelegate from the source validator to the destination validator.
     * @return The BeginRedelegate response.
     */
    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgBeginRedelegateResponse> stakingMsgBeginRedelegate(
            @NotNull String validatorSrcAddress,
            @NotNull String validatorDstAddress,
            @NotNull String delegatorAddress,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount
    ) {
        return stakingMsgStub.beginRedelegate(cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.newBuilder()
                .setDelegatorAddress(delegatorAddress)
                .setValidatorSrcAddress(validatorSrcAddress)
                .setValidatorDstAddress(validatorDstAddress)
                .setAmount(amount)
                .build());
    }

    /************************************************************
     ***** Cosmos Module 'tendermint' : SERVICE
     ***********************************************************/

    /**
     * 'tmGetBlockByHeight' queries block for a given block height.
     * @param height Block height used for the query
     * @return The BlockByHeight response.
     */
    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetBlockByHeightResponse> tmGetBlockByHeight(
            @NotNull Long height
    ) {
        return cosmosBaseTendermintServiceStub.getBlockByHeight(cosmos.base.tendermint.v1beta1.Query.GetBlockByHeightRequest.newBuilder().setHeight(height).build());
    }

    /**
     * 'tmGetLatestBlock' returns the latest block.
     * @return The LatestBlock response.
     */
    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetLatestBlockResponse> tmGetLatestBlock() {
        return cosmosBaseTendermintServiceStub.getLatestBlock(cosmos.base.tendermint.v1beta1.Query.GetLatestBlockRequest.newBuilder().build());
    }

    /**
     * 'tmGetLatestValidatorSet' queries the latest validator-set.
     * @return The first page of the latest validator-set.
     */
    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetResponse> tmGetLatestValidatorSet() {
        cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetRequest.Builder _builder = cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetRequest.newBuilder();
        return this.tmGetLatestValidatorSet(null, null);
    }

    /**
     * 'tmGetLatestValidatorSet' queries the latest validator-set.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query
     * @return The specified page of the latest validator-set.
     */
    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetResponse> tmGetLatestValidatorSet(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.base.tendermint.v1beta1.ServiceGrpc.ServiceFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = cosmosBaseTendermintServiceStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = cosmosBaseTendermintServiceStub;
        }
        cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetRequest.Builder _builder = cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return cosmosBaseTendermintServiceStub.getLatestValidatorSet(_builder.build());
    }

    /**
     * 'tmGetNodeInfo' queries the current local node info.
     * @return The NodeInfo response.
     */
    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetNodeInfoResponse> tmGetNodeInfo() {
        return cosmosBaseTendermintServiceStub.getNodeInfo(cosmos.base.tendermint.v1beta1.Query.GetNodeInfoRequest.newBuilder().build());
    }

    /**
     * 'tmGetSyncing' queries node syncing.
     * @return The Syncing response.
     */
    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetSyncingResponse> tmGetSyncing() {
        return cosmosBaseTendermintServiceStub.getSyncing(cosmos.base.tendermint.v1beta1.Query.GetSyncingRequest.newBuilder().build());
    }

    /**
     * 'tmGetValidatorSetByHeight' queries validator-set at a given block height.
     * @param height Block height used for the query
     * @return The ValidatorSetByHeight response.
     */
    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetValidatorSetByHeightResponse> tmGetValidatorSetByHeight(
            @NotNull Long height
    ) {
        return cosmosBaseTendermintServiceStub.getValidatorSetByHeight(cosmos.base.tendermint.v1beta1.Query.GetValidatorSetByHeightRequest.newBuilder().setHeight(height).build());
    }

    /********************************************************
     ***** Cosmos Module 'tx' : SERVICE
     *******************************************************/

    /**
     * 'txBroadcastTx' broadcasts transaction on the FX network.
     * @param mode The broadcast mode.
     * @param txBytes The transaction (Tx) bytes.
     * @return The BroadcastTx response.
     */
    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxResponse> txBroadcastTx(
            cosmos.tx.v1beta1.ServiceOuterClass.BroadcastMode mode,
            byte[] txBytes
    ) {;
        return txServiceStub.broadcastTx(cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxRequest.newBuilder().setMode(mode).setTxBytes(com.google.protobuf.ByteString.copyFrom(txBytes)).build());
    }

    /**
     * 'txGetTx' fetches a transaction (Tx) by its hash.
     * @param txHash The transaction (Tx) hash
     * @return the Tx response.
     */
    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxResponse> txGetTx(
            String txHash
    ) {
        return txServiceStub.getTx(cosmos.tx.v1beta1.ServiceOuterClass.GetTxRequest.newBuilder().setHash(txHash).build());
    }

    /**
     * 'txGetTxsEvent' fetches txs by event.
     * Cosmos events are described here :
     *  Bank:            https://github.com/cosmos/cosmos-sdk/blob/main/x/bank/spec/04_events.md
     *  Crisis:          https://github.com/cosmos/cosmos-sdk/blob/main/x/crisis/spec/03_events.md
     *  Distribution:    https://github.com/cosmos/cosmos-sdk/blob/main/x/distribution/spec/06_events.md
     *  Evidence:        https://github.com/cosmos/cosmos-sdk/blob/main/x/evidence/spec/04_events.md
     *  Feegrant:        https://github.com/cosmos/cosmos-sdk/blob/main/x/feegrant/spec/04_events.md
     *  Gov:             https://github.com/cosmos/cosmos-sdk/blob/main/x/gov/spec/04_events.md
     *  Group:           https://github.com/cosmos/cosmos-sdk/blob/main/x/group/spec/04_events.md
     *  Mint:            https://github.com/cosmos/cosmos-sdk/blob/main/x/mint/spec/05_events.md
     *  Slashing:        https://github.com/cosmos/cosmos-sdk/blob/main/x/slashing/spec/06_events.md
     *  Staking:         https://github.com/cosmos/cosmos-sdk/blob/main/x/staking/spec/07_events.md
     *
     * @param event The event to fetch
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of TxsEvent response.
     */
    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventResponse> txGetTxsEvent(
            String event,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return txServiceStub.getTxsEvent(_builder.addEvents(event).build());
    }

    /**
     * 'txGetTxsEvent' fetches txs by event.
     * Cosmos events are described here :
     *  Bank:            https://github.com/cosmos/cosmos-sdk/blob/main/x/bank/spec/04_events.md
     *  Crisis:          https://github.com/cosmos/cosmos-sdk/blob/main/x/crisis/spec/03_events.md
     *  Distribution:    https://github.com/cosmos/cosmos-sdk/blob/main/x/distribution/spec/06_events.md
     *  Evidence:        https://github.com/cosmos/cosmos-sdk/blob/main/x/evidence/spec/04_events.md
     *  Feegrant:        https://github.com/cosmos/cosmos-sdk/blob/main/x/feegrant/spec/04_events.md
     *  Gov:             https://github.com/cosmos/cosmos-sdk/blob/main/x/gov/spec/04_events.md
     *  Group:           https://github.com/cosmos/cosmos-sdk/blob/main/x/group/spec/04_events.md
     *  Mint:            https://github.com/cosmos/cosmos-sdk/blob/main/x/mint/spec/05_events.md
     *  Slashing:        https://github.com/cosmos/cosmos-sdk/blob/main/x/slashing/spec/06_events.md
     *  Staking:         https://github.com/cosmos/cosmos-sdk/blob/main/x/staking/spec/07_events.md
     *
     * @param events The events (logical AND) to fetch
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned to the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of TxsEvent response.
     */
    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventResponse> txGetTxsEvent(
            Iterable<String> events,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return txServiceStub.getTxsEvent(_builder.addAllEvents(events).build());
    }

    /**
     * 'txSimulate' simulates executing a transaction for estimating gas usage.
     * @param tx The transaction to simulate.
     * @return The Simulate response.
     */
    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.SimulateResponse> txSimulate(
            cosmos.tx.v1beta1.TxOuterClass.Tx tx
    ) {
        cosmos.tx.v1beta1.ServiceOuterClass.SimulateRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.SimulateRequest.newBuilder();
        return txServiceStub.simulate(_builder.setTx(tx).build());
    }

    /**
     * 'txSimulate' GetBlockWithTxs fetches a block with decoded txs.
     * @since cosmos-sdk 0.45.2
     * @param height The block height.
     * @return The block with decoded txs.
     */
    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetBlockWithTxsResponse> txGetBlockWithTxs(
            @NotNull long height
    ) {
        return txServiceStub.getBlockWithTxs(cosmos.tx.v1beta1.ServiceOuterClass.GetBlockWithTxsRequest.newBuilder().setHeight(height).build());
    }

    /************************************************************
     ***** Cosmos Module 'upgrade' : QUERY
     ***** https://docs.cosmos.network/master/modules/upgrade/
     ***********************************************************/

    /**
     * 'upgradeQueryAppliedPlan' queries a previously applied upgrade plan by its name.
     * @param name The upgrade plan name to search for.
     * @return The AppliedPlan response.
     */
    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryAppliedPlanResponse> upgradeQueryAppliedPlan(
            String name
    ) {
        return upgradeQueryStub.appliedPlan(cosmos.upgrade.v1beta1.QueryOuterClass.QueryAppliedPlanRequest.newBuilder().setName(name).build());
    }

    /**
     * 'upgradeQueryCurrentPlan' queries the current upgrade plan.
     * @return The CurrentPlan response.
     */
    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryCurrentPlanResponse> upgradeQueryCurrentPlan() {
        return upgradeQueryStub.currentPlan(cosmos.upgrade.v1beta1.QueryOuterClass.QueryCurrentPlanRequest.newBuilder().build());
    }

    /**
     * 'upgradeQueryModuleVersions' queries the list of module versions from state.
     * @Since cosmos-sdk 0.43
     * @param moduleName The module name for which versions are being queried.
     * @return The ModuleVersions response.
     */
    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryModuleVersionsResponse> upgradeQueryModuleVersions(
            String moduleName
    ) {
        return upgradeQueryStub.moduleVersions(cosmos.upgrade.v1beta1.QueryOuterClass.QueryModuleVersionsRequest.newBuilder().setModuleName(moduleName).build());
    }

    /************************************************************
     ***** Cosmos Module 'vesting' : MESSAGE
     ***********************************************************/

    /**
     * 'vestingMsgCreateVestingAccount' defines a method that enables creating a vesting account.
     * @param delayed Whether the vesting account should be delayed or not
     * @param fromAddress The FX 'from' address
     * @param toAddress The FX 'to' address
     * @param endTime End time, i.e. when vesting closes.
     * @param amounts The currencies' amounts to vest.
     * @return The CreateVestingAccount response.
     */
    public ListenableFuture<cosmos.vesting.v1beta1.Tx.MsgCreateVestingAccountResponse> vestingMsgCreateVestingAccount(
            boolean delayed,
            String fromAddress,
            String toAddress,
            Long endTime,
            Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> amounts
    ) {
        return vestingMsgStub.createVestingAccount(cosmos.vesting.v1beta1.Tx.MsgCreateVestingAccount.newBuilder()
                .setDelayed(delayed)
                .setFromAddress(fromAddress)
                .setToAddress(toAddress)
                .setEndTime(endTime)
                .addAllAmount(amounts)
                .build());
    }

    /*****************************************************************
     ***** Ethermint Module 'evm' : QUERY
     * https://docs.ethermint.zone/modules/evm/
     *****************************************************************/

    /**
     * 'ethermintEvmQueryAccount' queries an EthAccount information.
     * @param address The Ethereum 0x... account address.
     * @return The Ethereum account information.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryAccountResponse> ethermintEvmQueryAccount(
            @NotNull String address
    ) {
        return ethermintEvmQueryStub.account(ethermint.evm.v1.QueryOuterClass.QueryAccountRequest.newBuilder()
                .setAddress(address)
                .build());
    }

    /**
     * 'ethermintEvmQueryBalance' queries the balance of the EVM denominations for a single EthAccount.
     * @param address The Ethereum 0x... account address.
     * @return The balance of the EVM denominations for a single EthAccount.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryBalanceResponse> ethermintEvmQueryBalance(
            @NotNull String address
    ) {
        return ethermintEvmQueryStub.balance(ethermint.evm.v1.QueryOuterClass.QueryBalanceRequest.newBuilder()
                .setAddress(address)
                .build());
    }

    /**
     * 'ethermintEvmQueryBaseFee' queries the base fee of the parent block of the current block.
     * It's similar to feemarket module's method, but also checks london hardfork status.
     * @return The base fee of the parent block of the current block.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryBaseFeeResponse> ethermintEvmQueryBaseFee() {
        return ethermintEvmQueryStub.baseFee(ethermint.evm.v1.QueryOuterClass.QueryBaseFeeRequest.newBuilder().build());
    }

    /**
     * 'ethermintEvmQueryCode' queries the balance of all coins for a single account.
     * @param address The Ethereum 0x... account address.
     * @return The balance of all coins for a single account
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryCodeResponse> ethermintEvmQueryCode(
            @NotNull String address
    ) {
        return ethermintEvmQueryStub.code(ethermint.evm.v1.QueryOuterClass.QueryCodeRequest.newBuilder()
                .setAddress(address)
                .build());
    }

    /**
     * 'ethermintEvmQueryCosmosAccount' queries an Ethereum account's Cosmos Address.
     * @param address The Ethereum 0x... account address.
     * @return The Ethereum account's Cosmos Address.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountResponse> ethermintEvmQueryCosmosAccount(
            @NotNull String address
    ) {
        return ethermintEvmQueryStub.cosmosAccount(ethermint.evm.v1.QueryOuterClass.QueryCosmosAccountRequest.newBuilder()
                .setAddress(address)
                .build());
    }

    /**
     * 'ethermintEvmQueryEstimateGas' implements the `eth_estimateGas` RPC API.
     * @param args The EVM transaction bytes.
     * @param gasCap The gas cap.
     * @return The estimated gas for an EVM transaction.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.EstimateGasResponse> ethermintEvmQueryEstimateGas(
            @NotNull byte[] args,
            @NotNull Long gasCap
    ) {
        return ethermintEvmQueryStub.estimateGas(ethermint.evm.v1.QueryOuterClass.EthCallRequest.newBuilder()
                .setArgs(ByteString.copyFrom(args))
                .setGasCap(gasCap)
                .build());
    }

    /**
     * 'ethermintEvmQueryEthCall' implements the `eth_call` RPC API.
     * @param args The EVM transaction bytes.
     * @param gasCap The gas cap.
     * @return The ETH transaction execution response.
     */
    public ListenableFuture<ethermint.evm.v1.Tx.MsgEthereumTxResponse> ethermintEvmQueryEthCall(
            @NotNull byte[] args,
            @NotNull Long gasCap
    ) {
        return ethermintEvmQueryStub.ethCall(ethermint.evm.v1.QueryOuterClass.EthCallRequest.newBuilder()
                .setArgs(ByteString.copyFrom(args))
                .setGasCap(gasCap)
                .build());
    }

    /**
     * 'ethermintEvmQueryParams' queries the parameters of 'x/evm' module.
     * @return The parameters of the 'x/evm' module.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryParamsResponse> ethermintEvmQueryParams() {
        return ethermintEvmQueryStub.params(ethermint.evm.v1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryStorageResponse> ethermintEvmQueryStorage(
            @NotNull String address,
            @NotNull String key
    ) {
        return ethermintEvmQueryStub.storage(ethermint.evm.v1.QueryOuterClass.QueryStorageRequest.newBuilder()
                .setAddress(address)
                .setKey(key)
                .build());
    }

    /**
     * 'ethermintEvmQueryTraceBlock' implements the `debug_traceBlockByNumber` and `debug_traceBlockByHash` RPC API.
     * @param blockHash The block hash.
     * @param blockTime The block time.
     * @param traceConfig The trace configuration.
     * @param txs The EVM transactions.
     * @return The TraceBlock response.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryTraceBlockResponse> ethermintEvmQueryTraceBlock(
            @NotNull String blockHash,
            @NotNull Timestamp blockTime,
            @NotNull ethermint.evm.v1.Evm.TraceConfig traceConfig,
            @NotNull Iterable<? extends ethermint.evm.v1.Tx.MsgEthereumTx> txs
            ) {
        return ethermintEvmQueryStub.traceBlock(ethermint.evm.v1.QueryOuterClass.QueryTraceBlockRequest.newBuilder()
                .setBlockHash(blockHash)
                .setBlockTime(blockTime)
                .setTraceConfig(traceConfig)
                .addAllTxs(txs)
                .build());
    }

    /**
     * 'ethermintEvmQueryTraceBlock' implements the `debug_traceBlockByNumber` and `debug_traceBlockByHash` RPC API.
     * @param blockHash The block hash.
     * @param blockTime The block time.
     * @param traceConfig The trace configuration.
     * @param tx The EVM transaction.
     * @return The TraceBlock response.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryTraceBlockResponse> ethermintEvmQueryTraceBlock(
            @NotNull String blockHash,
            @NotNull Timestamp blockTime,
            @NotNull ethermint.evm.v1.Evm.TraceConfig traceConfig,
            @NotNull ethermint.evm.v1.Tx.MsgEthereumTx tx
    ) {
        return ethermintEvmQueryStub.traceBlock(ethermint.evm.v1.QueryOuterClass.QueryTraceBlockRequest.newBuilder()
                .setBlockHash(blockHash)
                .setBlockTime(blockTime)
                .setTraceConfig(traceConfig)
                .addTxs(tx)
                .build());
    }

    /**
     * 'ethermintEvmQueryTraceTx' implements the `debug_traceTransaction` RPC API.
     * @param blockHash The block hash.
     * @param blockTime The block time.
     * @param msg The EVM message.
     * @param predecessors The previous messages.
     * @param traceConfig The trace configuration.
     * @return The TraceTx response.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryTraceTxResponse> ethermintEvmQueryTraceTx(
            @NotNull String blockHash,
            @NotNull Timestamp blockTime,
            @NotNull ethermint.evm.v1.Tx.MsgEthereumTx msg,
            @NotNull Iterable<? extends ethermint.evm.v1.Tx.MsgEthereumTx> predecessors,
            @NotNull ethermint.evm.v1.Evm.TraceConfig traceConfig
    ) {
        return ethermintEvmQueryStub.traceTx(ethermint.evm.v1.QueryOuterClass.QueryTraceTxRequest.newBuilder()
                .setBlockHash(blockHash)
                .setBlockTime(blockTime)
                .setMsg(msg)
                .addAllPredecessors(predecessors)
                .setTraceConfig(traceConfig)
                .build());
    }

    /**
     * 'ethermintEvmQueryTraceTx' implements the `debug_traceTransaction` RPC API.
     * @param blockHash The block hash.
     * @param blockTime The block time.
     * @param msg The EVM message.
     * @param predecessor The previous message.
     * @param traceConfig The trace configuration.
     * @return The TraceTx response.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryTraceTxResponse> ethermintEvmQueryTraceTx(
            @NotNull String blockHash,
            @NotNull Timestamp blockTime,
            @NotNull ethermint.evm.v1.Tx.MsgEthereumTx msg,
            @NotNull ethermint.evm.v1.Tx.MsgEthereumTx predecessor,
            @NotNull ethermint.evm.v1.Evm.TraceConfig traceConfig
    ) {
        return ethermintEvmQueryStub.traceTx(ethermint.evm.v1.QueryOuterClass.QueryTraceTxRequest.newBuilder()
                .setBlockHash(blockHash)
                .setBlockTime(blockTime)
                .setMsg(msg)
                .addPredecessors(predecessor)
                .setTraceConfig(traceConfig)
                .build());
    }

    /**
     * 'ethermintEvmQueryValidatorAccount' queries an Ethereum account's from a validator consensus Address.
     * @param consAddress The validator FX consensus address.
     * @return The Ethereum account's from a validator consensus Address.
     */
    public ListenableFuture<ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountResponse> ethermintEvmQueryValidatorAccount(
            @NotNull String consAddress
    ) {
        return ethermintEvmQueryStub.validatorAccount(ethermint.evm.v1.QueryOuterClass.QueryValidatorAccountRequest.newBuilder()
                .setConsAddress(consAddress)
                .build());
    }

    /*****************************************************************
     ***** Ethermint Module 'evm' : MESSAGE
     * https://docs.ethermint.zone/modules/evm/
     *****************************************************************/

    /**
     * 'ethermintEvmMsgEthereumTx' defines a method to submit Ethereum transactions.
     * @param request The message to submit to the EVM.
     * @return The transaction submission response.
     */
    public ListenableFuture<ethermint.evm.v1.Tx.MsgEthereumTxResponse> ethermintEvmMsgEthereumTx(
            @NotNull ethermint.evm.v1.Tx.MsgEthereumTx request
    ) {
        return ethermintEvmMsgStub.ethereumTx(request);
    }

    /*****************************************************************
     ***** EVMOS Module 'feemarket' : QUERY
     * https://docs.evmos.org/modules/feemarket/
     *****************************************************************/

    /**
     * 'evmosFeemarketQueryBaseFee' queries the base fee of the parent block of the current block.
     * @return The base fee of the parent block of the current block.
     */
    public ListenableFuture<ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeResponse> evmosFeemarketQueryBaseFee() {
        return evmosFeemarketQueryStub.baseFee(ethermint.feemarket.v1.QueryOuterClass.QueryBaseFeeRequest.newBuilder().build());
    }

    /**
     * 'evmosFeemarketQueryBlockGas' queries the gas used at a given block height.
     * @return The gas used at a given block height.
     */
    public ListenableFuture<ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasResponse> evmosFeemarketQueryBlockGas() {
        return evmosFeemarketQueryStub.blockGas(ethermint.feemarket.v1.QueryOuterClass.QueryBlockGasRequest.newBuilder().build());
    }

    /**
     * 'evmosFeemarketQueryParams' queries the parameters of x/feemarket module.
     * @return The parameters of x/feemarket module.
     */
    public ListenableFuture<ethermint.feemarket.v1.QueryOuterClass.QueryParamsResponse> evmosFeemarketQueryParams() {
        return evmosFeemarketQueryStub.params(ethermint.feemarket.v1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }


    /*****************************************************************
     ***** Module 'gravity' : QUERY
     * https://github.com/0xkobe/cosmos-gravity-bridge/tree/main/docs
     *****************************************************************/

    /**
     * 'gravityQueryBatchConfirm' queries a batch confirm for a given address, token contract address and nonce, as known at the latest available block height.
     * @param address The orchestrator address.
     * @param tokenContract The token contract address.
     * @param nonce The nonce.
     * @return The BatchConfirm response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> gravityQueryBatchConfirm(
            @NotNull String address,
            @NotNull String tokenContract,
            @NotNull Long nonce
    ) {
        return this.gravityQueryBatchConfirm(address, tokenContract, nonce, null);
    }

    /**
     * 'gravityQueryBatchConfirm' queries a batch confirm for a given address, token contract address and nonce, as known at the specified block height.
     * @param address The orchestrator address.
     * @param tokenContract The token contract address.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The BatchConfirm response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> gravityQueryBatchConfirm(
            @NotNull String address,
            @NotNull String tokenContract,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.batchConfirm(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest.newBuilder().setAddress(address).setTokenContract(tokenContract).setNonce(nonce).build());
    }

    /**
     * 'gravityQueryBatchConfirms' queries batch confirms for a given token contract address and nonce, as known at the latest available block height.
     * @param tokenContract The token contract address.
     * @param nonce The nonce.
     * @return The BatchConfirms response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> gravityQueryBatchConfirms(
            @NotNull String tokenContract,
            @NotNull Long nonce
    ) {
        return this.gravityQueryBatchConfirms(tokenContract, nonce, null);
    }

    /**
     * 'gravityQueryBatchConfirms' queries batch confirms for a given token contract address and nonce, as known at the specified block height.
     * @param tokenContract The token contract address.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The BatchConfirms response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> gravityQueryBatchConfirms(
            @NotNull String tokenContract,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.batchConfirms(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest.newBuilder().setTokenContract(tokenContract).setNonce(nonce).build());
    }

    /**
     * 'gravityQueryBatchFees' queries batch fees, as known at the latest available block height.
     * @return The BatchFee response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> gravityQueryBatchFees() {
        return this.gravityQueryBatchFees(null);
    }

    /**
     * 'gravityQueryBatchFees' queries batch fees, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The BatchFee response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> gravityQueryBatchFees(
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.batchFees(fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest.newBuilder().build());
    }

    /**
     * 'gravityQueryParams' queries the gravity parameters information, as known at the latest available block height.
     * @return The Params response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryParamsResponse> gravityQueryParams() {
        return this.gravityQueryParams(null);
    }

    /**
     * 'gravityQueryParams' queries the gravity parameters information, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The Params response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryParamsResponse> gravityQueryParams(
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.params(fx.gravity.v1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /**
     * 'gravityQueryBatchRequestByNonce' queries a batch request for a given token contract address and nonce, as known at the latest available block height.
     * @param contractAddress The contract address.
     * @param nonce The nonce.
     * @return The BatchRequestByNonce response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> gravityQueryBatchRequestByNonce(
            @NotNull String contractAddress,
            @NotNull Long nonce
    ) {
        return this.gravityQueryBatchRequestByNonce(contractAddress, nonce, null);
    }

    /**
     * 'gravityQueryBatchRequestByNonce' queries a batch request for a given token contract address and nonce, as known at the specified block height.
     * @param tokenContract The contract address.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The BatchRequestByNonce response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> gravityQueryBatchRequestByNonce(
            @NotNull String tokenContract,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.batchRequestByNonce(fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.newBuilder().setTokenContract(tokenContract).setNonce(nonce).build());
    }

    /**
     * 'gravityQueryCurrentValset' queries the current validator-set, as known at the latest available block height.
     * @return The CurrentValset response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> gravityQueryCurrentValset() {
        return this.gravityQueryCurrentValset(null);
    }

    /**
     * 'gravityQueryCurrentValset' queries the current validator-set, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The CurrentValset response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> gravityQueryCurrentValset(
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.currentValset(fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest.newBuilder().build());
    }

    /**
     * 'gravityQueryDenomToERC20' queries the ERC20-equivalent name of a currency on FxCore, as known at the latest available block height.
     * @param denom The FxCore currency denomination.
     * @return The 'DenomToERC20' response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> gravityQueryDenomToERC20(
            @NotNull String denom
    ) {
        return this.gravityQueryDenomToERC20(denom, null);
    }

    /**
     * 'gravityQueryDenomToERC20' queries the ERC20-equivalent name of an FxCore-token, as known at the specified block height.
     * @param denom The FxCore token denomination.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The 'DenomToERC20' response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> gravityQueryDenomToERC20(
            @NotNull String denom,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.denomToERC20(fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request.newBuilder().setDenom(denom).build());
    }

    /**
     * 'gravityQueryERC20ToDenom' queries the FxCore-equivalent name of an ERC20-token, as known at the latest available block height.
     * @param erc20 The ERC20 token denomination
     * @return The ERC20ToDenom response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> gravityQueryERC20ToDenom(
            @NotNull String erc20
    ) {
        return this.gravityQueryERC20ToDenom(erc20, null);
    }

    /**
     * 'gravityQueryERC20ToDenom' queries the FxCore-equivalent name of an ERC20-token, as known at the specified block height.
     * @param erc20 The ERC20 token denomination
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The ERC20ToDenom response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> gravityQueryERC20ToDenom(
            @NotNull String erc20,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.eRC20ToDenom(fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest.newBuilder().setErc20(erc20).build());
    }

    /**
     * 'gravityQueryDelegateKeyByEth' queries the delegate key for a given ETH address, as known at the latest available block height.
     * @param ethAddress The Ethereum address.
     * @return The DelegateKeyByEth response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> gravityQueryDelegateKeyByEth(
            @NotNull String ethAddress
    ) {
        return this.gravityQueryDelegateKeyByEth(ethAddress, null);
    }

    /**
     * 'gravityQueryDelegateKeyByEth' queries the delegate key for a given ETH address, as known at the specified block height.
     * @param ethAddress The Ethereum address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The DelegateKeyByEth response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> gravityQueryDelegateKeyByEth(
            @NotNull String ethAddress,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.getDelegateKeyByEth(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest.newBuilder().setEthAddress(ethAddress).build());
    }

    /**
     * 'gravityQueryDelegateKeyByOrchestrator' queries the delegate Ethereum and validator key for a given orchestrator, as known at the latest available block height.
     * @param orchestratorAddress The FX orchestrator address.
     * @return The 'DelegateKeyByOrchestrator' response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> gravityQueryDelegateKeyByOrchestrator(
            @NotNull String orchestratorAddress
    ) {
        return this.gravityQueryDelegateKeyByOrchestrator(orchestratorAddress, null);
    }

    /**
     * 'gravityQueryDelegateKeyByOrchestrator' queries the delegate Ethereum and validator key for a given orchestrator, as known at the specified block height.
     * @param orchestratorAddress The FX orchestrator address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The 'DelegateKeyByOrchestrator' response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> gravityQueryDelegateKeyByOrchestrator(
            @NotNull String orchestratorAddress,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.getDelegateKeyByOrchestrator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest.newBuilder().setOrchestratorAddress(orchestratorAddress).build());
    }

    /**
     * 'gravityQueryDelegateKeyByValidator', as known at the latest available block height.
     * @param validatorAddress The FX validator address.
     * @return The DelegateKeyByValidator response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> gravityQueryDelegateKeyByValidator(
            @NotNull String validatorAddress
    ) {
        return this.gravityQueryDelegateKeyByValidator(validatorAddress, null);
    }

    /**
     * 'gravityQueryDelegateKeyByValidator', as known at the specified block height.
     * @param validatorAddress The FX validator address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The DelegateKeyByValidator response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> gravityQueryDelegateKeyByValidator(
            @NotNull String validatorAddress,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.getDelegateKeyByValidator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    /**
     * 'gravityQueryIbcSequenceHeight' queries the IBC height for a given channel and sequence number, as known at the latest available block height.
     * @param sourceChannel The source channel.
     * @param sourcePort The source port.
     * @param sequence The sequence number.
     * @return The IbcSequenceHeight response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> gravityQueryIbcSequenceHeight(
            @NotNull String sourceChannel,
            @NotNull String sourcePort,
            @NotNull Long sequence
    ) {
        return this.gravityQueryIbcSequenceHeight(sourceChannel, sourcePort, sequence, null);
    }

    /**
     * 'gravityQueryIbcSequenceHeight' queries the IBC height for a given channel and sequence number, as known at the specified block height.
     * @param sourceChannel The source channel.
     * @param sourcePort The source port.
     * @param sequence The sequence number.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The IbcSequenceHeight response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> gravityQueryIbcSequenceHeight(
            @NotNull String sourceChannel,
            @NotNull String sourcePort,
            @NotNull Long sequence,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.getIbcSequenceHeightByChannel(fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest.newBuilder().setSourceChannel(sourceChannel).setSourcePort(sourcePort).setSequence(sequence).build());
    }

    /**
     * 'gravityQueryPendingSendToEth', as known at the latest available block height.
     * @param senderAddress The FX sender address.
     * @return The PendingSendToEth response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> gravityQueryPendingSendToEth(
            @NotNull String senderAddress
    ) {
        return this.gravityQueryPendingSendToEth(senderAddress, null);
    }

    /**
     * 'gravityQueryPendingSendToEth', as known at the specified block height.
     * @param senderAddress The FX sender address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The PendingSendToEth response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> gravityQueryPendingSendToEth(
            @NotNull String senderAddress,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.getPendingSendToEth(fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest.newBuilder().setSenderAddress(senderAddress).build());
    }

    /**
     * 'gravityQueryLastEventBlockHeightByAddr' queries the last event block height for a given orchestrator address, as known at the latest available block height.
     * @param orchestratorAddress The address
     * @return The QueryLastEventBlockHeightByAddr response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> gravityQueryLastEventBlockHeightByAddr(
            @NotNull String orchestratorAddress
    ) {
        return this.gravityQueryLastEventBlockHeightByAddr(orchestratorAddress);
    }

    /**
     * 'gravityQueryLastEventBlockHeightByAddr' queries the last event block height for a given orchestrator address, as known at the specified block height.
     * @param orchestratorAddress The address
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastEventBlockHeightByAddr response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> gravityQueryLastEventBlockHeightByAddr(
            @NotNull String orchestratorAddress,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.lastEventBlockHeightByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest.newBuilder().setAddress(orchestratorAddress).build());
    }

    /**
     * 'gravityQueryLastEventNonceByAddr' queries the last event nonce for a given address, as known at the latest available block height.
     * @param address The address.
     * @return The QueryLastEventNonceByAddr response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> gravityQueryLastEventNonceByAddr(
            @NotNull String address
    ) {
        return this.gravityQueryLastEventNonceByAddr(address, null);
    }

    /**
     * 'gravityQueryLastEventNonceByAddr' queries the last event nonce for a given address, as known at the specified block height.
     * @param address The address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastEventNonceByAddr response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> gravityQueryLastEventNonceByAddr(
            @NotNull String address,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.lastEventNonceByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.newBuilder().setAddress(address).build());
    }

    /**
     * 'gravityQueryLastObservedBlockHeight' queries the last observed Ethereum block height, as known at the latest available block height.
     * LastObservedEthereumBlockHeight stores the last observed Ethereum block height along with the Cosmos block height that it was observed at.
     * These two numbers can be used to project outward and always produce batches with timeouts in the future even if no Ethereum block height
     * has been relayed for a long time.
     * @return The QueryLastObservedEthBlockHeight response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> gravityQueryLastObservedEthBlockHeight() {
        return this.gravityQueryLastObservedBlockHeight(null);
    }

    /**
     * 'gravityQueryLastObservedBlockHeight' queries the last observed Ethereum block height, as known at the specified block height.
     * LastObservedEthereumBlockHeight stores the last observed Ethereum block height along with the Cosmos block height that it was observed at.
     * These two numbers can be used to project outward and always produce batches with timeouts in the future even if no Ethereum block height
     * has been relayed for a long time.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastObservedEthBlockHeight response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> gravityQueryLastObservedBlockHeight(
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.lastObservedBlockHeight(fx.gravity.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest.newBuilder().build());
    }

    /**
     * 'gravityQueryLastPendingBatchRequestByAddr' queries the last pending batch request for a given address, as known at the latest available block height.
     * @param address The address.
     * @return The QueryLastPendingBatchRequestByAddr response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> gravityQueryLastPendingBatchRequestByAddr(
            @NotNull String address
    ) {
        return this.gravityQueryLastPendingBatchRequestByAddr(address, null);
    }

    /**
     * 'gravityQueryLastPendingBatchRequestByAddr' queries the last pending batch request for a given address, as known at the specified block height.
     * @param address The address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastPendingBatchRequestByAddr response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> gravityQueryLastPendingBatchRequestByAddr(
            @NotNull String address,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.lastPendingBatchRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.newBuilder().setAddress(address).build());
    }

    /**
     * 'gravityQueryLastPendingValsetRequestByAddr' queries the last pending validator-set request for a given address, as known at the latest available block height.
     * @param address The address.
     * @return The QueryLastPendingValsetRequestByAddr response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> gravityQueryLastPendingValsetRequestByAddr(
            @NotNull String address
    ) {
        return this.gravityQueryLastPendingValsetRequestByAddr(address, null);
    }

    /**
     * 'gravityQueryLastPendingValsetRequestByAddr' queries the last pending validator-set request for a given address, as known at the specified block height.
     * @param address The address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastPendingValsetRequestByAddr response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> gravityQueryLastPendingValsetRequestByAddr(
            @NotNull String address,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.lastPendingValsetRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest.newBuilder().setAddress(address).build());
    }

    /**
     * 'gravityQueryLastValsetRequests' queries the last validator-set requests, as known at the latest available block height.
     * @return The QueryLastValsetRequests response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> gravityQueryLastValsetRequests() {
        return this.gravityQueryLastValsetRequests(null);
    }

    /**
     * 'gravityQueryLastValsetRequests' queries the last validator-set requests, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastValsetRequests response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> gravityQueryLastValsetRequests(
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.lastValsetRequests(fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest.newBuilder().build());
    }

    /**
     * 'gravityQueryOutgoingTxBatches' queries the outgoing TX batches, as known at the latest available block height.
     * @return The QueryOutgoingTxBatches response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> gravityQueryOutgoingTxBatches() {
        return this.gravityQueryOutgoingTxBatches(null);
    }

    /**
     * 'gravityQueryOutgoingTxBatches' queries the outgoing TX batches, as known at the specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryOutgoingTxBatches response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> gravityQueryOutgoingTxBatches(
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.outgoingTxBatches(fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.newBuilder().build());
    }

    /**
     * 'gravityQueryValsetConfirm' queries the validator-set confirm, as known at the latest available block height.
     * @param address The address.
     * @param nonce The nonce.
     * @return The QueryValsetConfirm response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> gravityQueryValsetConfirm(
            @NotNull String address,
            @NotNull Long nonce
    ) {
        return this.gravityQueryValsetConfirm(address, nonce, null);
    }

    /**
     * 'gravityQueryValsetConfirm' queries the validator-set confirm, as known at the specified block height.
     * @param address The address.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryValsetConfirm response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> gravityQueryValsetConfirm(
            @NotNull String address,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.valsetConfirm(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest.newBuilder().setAddress(address).setNonce(nonce).build());
    }

    /**
     * 'gravityQueryValsetConfirmsByNonce' queries the validator-set confirms for a given nonce, as known at the latest available block height.
     * @param nonce The nonce.
     * @return The QueryValsetConfirmsByNonce response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> gravityQueryValsetConfirmsByNonce(
            @NotNull Long nonce
    ) {
        return this.gravityQueryValsetConfirmsByNonce(nonce, null);
    }

    /**
     * 'gravityQueryValsetConfirmsByNonce' queries the validator-set confirms for a given nonce, as known at the specified block height.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryValsetConfirmsByNonce response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> gravityQueryValsetConfirmsByNonce(
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.valsetConfirmsByNonce(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest.newBuilder().setNonce(nonce).build());
    }

    /**
     * 'gravityQueryValsetRequest' queries the validator-set request, as known at the latest available block height.
     * @param nonce The nonce.
     * @return The QueryValsetRequest response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> gravityQueryValsetRequest(
            @NotNull Long nonce
    ) {
        return this.gravityQueryValsetRequest(nonce, null);
    }

    /**
     * 'gravityQueryValsetRequest' queries the validator-set request, as known at the specified block height.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryValsetRequest response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> gravityQueryValsetRequest(
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxGravityQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxGravityQueryStub;
        }
        return stub.valsetRequest(fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest.newBuilder().setNonce(nonce).build());
    }

    /*****************************************************************
     ***** Module 'gravity' : MESSAGE
     * https://github.com/0xkobe/cosmos-gravity-bridge/tree/main/docs
     *****************************************************************/

    /**
     * `SendToEth` allows the user to specify an Ethereum destination address, a token to send to Ethereum and a fee denominated in that same token to pay
     * the relayer. Note that this transaction will contain two fees. One fee amount to submit to the FX chain, that can be paid in any token and one fee
     * amount for the Ethereum relayer that must be paid in the same token that is being bridged.
     * User Messages : These are messages sent on the Cosmos side of the bridge by users.
     * See <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/mint-lock.md">minting and locking</a> for a more detailed summary of
     * the entire deposit and withdraw process.
     * @param sender The FX sender address
     * @param ethDest The ERC20 destination address
     * @param amount The token amount to send across the bridge (note the restriction that this is a single coin, not a set of coins that is normal in other Cosmos messages)
     * @param bridgeFee The token fee paid for the bridge, distinct from the fee paid to the chain to actually send this message in the first place. So, a successful send has two layers of fees for the user.
     * @return The SendToEth response.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgSendToEthResponse> gravityMsgSendToEth(
            @NotNull String sender,
            @NotNull String ethDest,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin bridgeFee
    ) {
        return fxGravityMsgStub.sendToEth(fx.gravity.v1.Tx.MsgSendToEth.newBuilder().setSender(sender).setEthDest(ethDest).setAmount(amount).setBridgeFee(bridgeFee).build());
    }

    /**
     * `CancelSendToEth` allows a user to retrieve a transaction that is in the batch pool but has not yet been packaged into a transaction batch by a
     * relayer running <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/messages.md/###RequestBatch">RequestBatch</a>.
     * For more details on this process, see the <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/spec/batch-creation-spec.md">batch creation spec</a>.
     * User Messages : These are messages sent on the Cosmos side of the bridge by users.
     * See <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/mint-lock.md">minting and locking</a> for a more detailed summary of
     * the entire deposit and withdraw process.
     * @param sender The sender address
     * @param transactionId The transaction ID
     * @return The CancelSendToEth response.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgCancelSendToEthResponse> gravityMsgCancelSendToEth(
            @NotNull String sender,
            @NotNull Long transactionId
    ) {
        return fxGravityMsgStub.cancelSendToEth(fx.gravity.v1.Tx.MsgCancelSendToEth.newBuilder().setSender(sender).setTransactionId(transactionId).build());
    }

    /**
     * Relayers use `QueryPendingSendToEth` in query.proto to query the potential fees for a batch of each token type.
     * When they find a batch that they wish to relay, they send in a `RequestBatch` message and the Gravity module creates a batch.
     * This then triggers the Ethereum Signers to send in ConfirmBatch messages, with the signatures required to submit the batch to the Ethereum chain.
     * At this point, any relayer can package these signatures up into a transaction and send them to Ethereum.
     * As noted above this message is unpermissioned and it is safe to allow anyone to call this message at any time thanks to the rules described in
     * the <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/spec/batch-creation-spec.md">batch creation spec</a>.
     * Relayer Messages : These are messages run by relayers. Relayers are unpermissioned and simply work to move things from Cosmos to Ethereum.
     * @param denom The token denomination.
     * @param feeReceive The fee being received.
     * @param minimumFee The minimum fee.
     * @param sender The sender address.
     * @return The RequestBatch response.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgRequestBatchResponse> gravityMsgRequestBatch(
            @NotNull String denom,
            @NotNull String feeReceive,
            @NotNull String minimumFee,
            @NotNull String sender
    ) {
        return fxGravityMsgStub.requestBatch(fx.gravity.v1.Tx.MsgRequestBatch.newBuilder().setDenom(denom).setFeeReceive(feeReceive).setMinimumFee(minimumFee).setSender(sender).build());
    }

    /**
     * Claim representing a `SendToCosmosEvent` from <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/solidity/contracts/Gravity.sol">Gravity.sol</a>.
     * When this passes the oracle vote, tokens will be issued to a Cosmos account.
     * Oracle Messages : All validators run two processes in addition to their Cosmos node : an Ethereum oracle and Ethereum signer.
     * These are bundled into a single Orchestrator binary for ease of use.
     * For further reference on this process, see the
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/overview.md">design overview</a>,
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/ethereum-signing.md">eth signer design</a>, and
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/mint-lock.md">minting and locking</a>
     * @param ethSender Hex encoded 0x Ethereum public sender address.
     * @param fxReceiver The FX receiver address.
     * @param orchestrator The orchestrator is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to
     * @param tokenContract The token contract address.
     * @param blockHeight The block height.
     * @param eventNonce The event nonce.
     * @param amount The amount claimed.
     * @param targetIbc The target IBC.
     * @return The MsgDepositClaimResponse.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgDepositClaimResponse> gravityMsgDepositClaim(
            @NotNull String ethSender,
            @NotNull String fxReceiver,
            @NotNull String orchestrator,
            @NotNull String tokenContract,
            @NotNull Long blockHeight,
            @NotNull Long eventNonce,
            @NotNull String amount,
            @NotNull String targetIbc
    ) {
        return fxGravityMsgStub.depositClaim(fx.gravity.v1.Tx.MsgDepositClaim.newBuilder().setAmount(amount).setTokenContract(tokenContract).setBlockHeight(blockHeight).setEthSender(ethSender).setEventNonce(eventNonce).setFxReceiver(fxReceiver).setOrchestrator(orchestrator).setTargetIbc(targetIbc).build());
    }

    /**
     * Claim representing a `TransactionBatchExecutedEvent` from <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/solidity/contracts/Gravity.sol">Gravity.sol</a>.
     * When this passes the oracle vote, the batch in state is cleaned up and tokens are burned/locked.
     * Oracle Messages :
     * All validators run two processes in addition to their Cosmos node : an Ethereum oracle and Ethereum signer.
     * These are bundled into a single Orchestrator binary for ease of use.
     * For further reference on this process, see the
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/overview.md">design overview</a>,
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/ethereum-signing.md">eth signer design</a>, and
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/mint-lock.md">minting and locking</a>
     * @param orchestrator The orchestrator field is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to
     * @param blockHeight The block height.
     * @param eventNonce The event nonce.
     * @param batchNonce The batch nonce.
     * @param tokenContract The token contract.
     * @return The WithdrawClaim response.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgWithdrawClaimResponse> gravityMsgWithdrawClaim(
            @NotNull String orchestrator,
            @NotNull Long blockHeight,
            @NotNull Long eventNonce,
            @NotNull Long batchNonce,
            @NotNull String tokenContract
    ) {
        return fxGravityMsgStub.withdrawClaim(fx.gravity.v1.Tx.MsgWithdrawClaim.newBuilder().setOrchestrator(orchestrator).setBatchNonce(batchNonce).setBlockHeight(blockHeight).setEventNonce(eventNonce).setTokenContract(tokenContract).build());
    }

    /**
     * Claim representing a `ValsetUpdatedEvent` from <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/solidity/contracts/Gravity.sol">Gravity.sol</a>.
     * When this passes the oracle vote, reward amounts are tallied and minted.
     * Oracle Messages :
     * All validators run two processes in addition to their Cosmos node : an Ethereum oracle and Ethereum signer.
     * These are bundled into a single Orchestrator binary for ease of use.
     * For further reference on this process, see the
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/overview.md">design overview</a>,
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/ethereum-signing.md">eth signer design</a>, and
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/mint-lock.md">minting and locking</a>
     * @param orchestrator The orchestrator field is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to
     * @param blockHeight The block height.
     * @param eventNonce The event nonce.
     * @param valsetNonce The validator-set nonce.
     * @param members The bridge validators.
     * @return The ValsetUpdatedClaim response.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgValsetUpdatedClaimResponse> gravityMsgValsetUpdatedClaim(
            @NotNull String orchestrator,
            @NotNull Long blockHeight,
            @NotNull Long eventNonce,
            @NotNull Long valsetNonce,
            @NotNull Iterable<? extends fx.gravity.v1.Types.BridgeValidator> members
    ) {
        return fxGravityMsgStub.valsetUpdateClaim(fx.gravity.v1.Tx.MsgValsetUpdatedClaim.newBuilder().setOrchestrator(orchestrator).setBlockHeight(blockHeight).setEventNonce(eventNonce).setValsetNonce(valsetNonce).addAllMembers(members).build());
    }

    /**
     * Claim representing a `ERC20DeployedEvent` from Gravity.sol.
     * When this passes the oracle vote, it is checked for accuracy and adopted or rejected as the ERC20 representation of a Cosmos asset.
     * Oracle Messages :
     * All validators run two processes in addition to their Cosmos node : an Ethereum oracle and Ethereum signer.
     * These are bundled into a single Orchestrator binary for ease of use.
     * For further reference on this process, see the
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/overview.md">design overview</a>,
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/ethereum-signing.md">eth signer design</a>, and
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/mint-lock.md">minting and locking</a>
     * @param orchestrator The orchestrator field is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to
     * @param blockHeight The block height.
     * @param decimals The number rof decimals.
     * @param eventNonce The event nonce.
     * @param name The name.
     * @param symbol The token name.
     * @param tokenContract The token contract address.
     * @return The FxOriginatedTokenClaim response.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgFxOriginatedTokenClaimResponse> gravityFxOriginatedTokenClaim(
            @NotNull String orchestrator,
            @NotNull Long blockHeight,
            @NotNull Long decimals,
            @NotNull Long eventNonce,
            @NotNull String name,
            @NotNull String symbol,
            @NotNull String tokenContract
    ) {
        return fxGravityMsgStub.fxOriginatedTokenClaim(fx.gravity.v1.Tx.MsgFxOriginatedTokenClaim.newBuilder().setBlockHeight(blockHeight).setDecimals(decimals).setEventNonce(eventNonce).setName(name).setOrchestrator(orchestrator).setSymbol(symbol).setTokenContract(tokenContract).build());
    }

    /**
     * Submits an Ethereum signature over a batch appearing in the `LastPendingBatchRequestByAddr` query.
     * Ethereum Signer Message : All validators run two processes in addition to their Cosmos node : an Ethereum oracle and Ethereum signer.
     * These are bundled into a single Orchestrator binary for ease of use.
     * For further reference on this process, see the
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/overview.md">design overview</a>,
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/ethereum-signing.md">eth signer design</a>, and
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/mint-lock.md">minting and locking</a>.
     * The Ethereum signer watches several <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/module/proto/gravity/v1/query.proto">query endpoints</a>
     * and it's only job is to submit a signature for anything that appears on those endpoints. For this reason, the validator must provide a secure RPC to a Cosmos
     * node following chain consensus. Or they risk being tricked into signing the wrong thing.
     * @param ethSigner The Ethereum signer.
     * @param nonce The nonce.
     * @param orchestrator The orchestrator address.
     * @param signature The signature.
     * @param tokenContract The token contract address.
     * @return The MsgConfirmBatchResponse.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgConfirmBatchResponse> gravityMsgConfirmBatch(
            @NotNull String ethSigner,
            @NotNull Long nonce,
            @NotNull String orchestrator,
            @NotNull String signature,
            @NotNull String tokenContract
    ) {
        return fxGravityMsgStub.confirmBatch(fx.gravity.v1.Tx.MsgConfirmBatch.newBuilder().setEthSigner(ethSigner).setNonce(nonce).setOrchestrator(orchestrator).setSignature(signature).setTokenContract(tokenContract).build());
    }

    /**
     * Submits an Ethereum signature over a batch appearing in the `LastPendingValsetRequestByAddr` query
     * Ethereum Signer Message : All validators run two processes in addition to their Cosmos node : an Ethereum oracle and Ethereum signer.
     * These are bundled into a single Orchestrator binary for ease of use.
     * For further reference on this process, see the
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/overview.md">design overview</a>,
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/ethereum-signing.md">eth signer design</a>, and
     * <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/mint-lock.md">minting and locking</a>.
     * The Ethereum signer watches several <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/module/proto/gravity/v1/query.proto">query endpoints</a>
     * and it's only job is to submit a signature for anything that appears on those endpoints. For this reason, the validator must provide a secure RPC to a Cosmos
     * node following chain consensus. Or they risk being tricked into signing the wrong thing.
     * @param ethAddress This is a hex encoded 0x Ethereum public key that will be used by this validator on Ethereum
     * @param orchestrator The orchestrator field is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to
     * @param nonce The nonce.
     * @param signature The signature.
     * @return The ValsetConfirm response.
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgValsetConfirmResponse> gravityMsgValsetConfirm(
            @NotNull String ethAddress,
            @NotNull String orchestrator,
            @NotNull Long nonce,
            @NotNull String signature
    ) {
        return fxGravityMsgStub.valsetConfirm(fx.gravity.v1.Tx.MsgValsetConfirm.newBuilder().setEthAddress(ethAddress).setOrchestrator(orchestrator).setNonce(nonce).setSignature(signature).build());
    }

    /**
     * This message sets the Orchestrator delegate keys described in the <a href="https://github.com/0xkobe/cosmos-gravity-bridge/blob/main/docs/design/overview.md">design overview</a>.
     * Validator Messages : These are messages sent directly using the validators message key.
     * @param ethAddress This is a hex encoded 0x Ethereum public key that will be used by this validator on Ethereum
     * @param orchestrator The orchestrator field is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to
     * @param validator The validator field is a fxvaloper1... string (i.e. sdk.ValAddress) that references a validator in the active set
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Tx.MsgSetOrchestratorAddressResponse> gravityMsgSetOrchestratorAddress(
            @NotNull String ethAddress,
            @NotNull String orchestrator,
            @NotNull String validator
    ) {
        return fxGravityMsgStub.setOrchestratorAddress(fx.gravity.v1.Tx.MsgSetOrchestratorAddress.newBuilder().setEthAddress(ethAddress).setOrchestrator(orchestrator).setValidator(validator).build());
    }

    /***********************************************************************************************************
     ***** Module 'crosschain' : QUERY
     ***** The main difference between 'gravity' and 'crosschain' modules is that 'crosschain' is intended for
     *****   exchanges between bridged Cosmos-based blockchains (FunctionX, PundiX, MarginX, ...) while
     *****   'gravity' is intended for external blockchains (Ethereum, ...)
     ***********************************************************************************************************/

    /**
     * 'crosschainQueryBatchConfirm' queries the batch confirm for a given chain, orchestrator, token contract and nonce, as known at the latest available block height.
     * @param chainName The chain name.
     * @param orchestratorAddress The orchestrator field is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to.
     * @param tokenContract The token contract address.
     * @param nonce The nonce.
     * @return The BatchConfirm response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> crosschainQueryBatchConfirm(
            @NotNull String chainName,
            @NotNull String orchestratorAddress,
            @NotNull String tokenContract,
            @NotNull Long nonce
    ) {
        return this.crosschainQueryBatchConfirm(chainName, orchestratorAddress, tokenContract, nonce, null);
    }

    /**
     * 'crosschainQueryBatchConfirm' queries the batch confirm for a given chain, orchestrator, token contract and nonce, as known at the specified block height.
     * @param chainName The chain name.
     * @param bridgerAddress The orchestrator field is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to.
     * @param tokenContract The token contract address.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The BatchConfirm response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> crosschainQueryBatchConfirm(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            @NotNull String tokenContract,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.batchConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest.newBuilder().setChainName(chainName).setBridgerAddress(bridgerAddress).setTokenContract(tokenContract).setNonce(nonce).build());
    }

    /**
     * 'crosschainQueryBatchConfirms' queries the batch confirms for a given chain, token contract and nonce, as known at the latest available block height.
     * @param chainName The chain name.
     * @param tokenContract The token contract address.
     * @param nonce The nonce
     * @return The BatchConfirms response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> crosschainQueryBatchConfirms(
            @NotNull String chainName,
            @NotNull String tokenContract,
            Long nonce
    ) {
        return this.crosschainQueryBatchConfirms(chainName, tokenContract, nonce, null);
    }

    /**
     * 'crosschainQueryBatchConfirms' queries the batch confirms for a given chain, token contract and nonce, as known at the specified block height.
     * @param chainName The chain name.
     * @param tokenContract The token contract address.
     * @param nonce The nonce
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The BatchConfirms response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> crosschainQueryBatchConfirms(
            @NotNull String chainName,
            @NotNull String tokenContract,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.batchConfirms(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest.newBuilder().setChainName(chainName).setTokenContract(tokenContract).setNonce(nonce).build());
    }

    /**
     * 'crosschainQueryBatchFees' queries the batch fees for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The BatchFee response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> crosschainQueryBatchFees(
            @NotNull String chainName
    ) {
        return this.crosschainQueryBatchFees(chainName, null);
    }

    /**
     * 'crosschainQueryBatchFees' queries the batch fees for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The BatchFee response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> crosschainQueryBatchFees(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.batchFees(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest.newBuilder().setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryBatchRequestByNonce' queries the batch request for a given chain, token contract address and nonce, as known at the latest available block height.
     * @param chainName The chain name.
     * @param tokenContract The token contract address.
     * @param nonce The nonce.
     * @return The BatchRequestByNonce response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> crosschainQueryBatchRequestByNonce(
            @NotNull String chainName,
            String tokenContract,
            @NotNull Long nonce
    ) {
        return this.crosschainQueryBatchRequestByNonce(chainName, tokenContract, nonce, null);
    }

    /**
     * 'crosschainQueryBatchRequestByNonce' queries the batch request for a given chain, token contract address and nonce, as known at the specified block height.
     * @param chainName The chain name.
     * @param tokenContract The contract address.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The BatchRequestByNonce response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> crosschainQueryBatchRequestByNonce(
            @NotNull String chainName,
            @NotNull String tokenContract,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.batchRequestByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.newBuilder().setChainName(chainName).setNonce(nonce).setTokenContract(tokenContract).build());
    }

    /**
     * 'crosschainQueryCurrentOracleSet' queries the current oracle set for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The CurrentOracleSet response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> crosschainQueryCurrentOracleSet(
            @NotNull String chainName
    ) {
        return this.crosschainQueryCurrentOracleSet(chainName, null);
    }

    /**
     * 'crosschainQueryCurrentOracleSet' queries the current oracle set for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The CurrentOracleSet response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> crosschainQueryCurrentOracleSet(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.currentOracleSet(fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest.newBuilder().setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryTokenToDenom' queries the denomination for a given token and chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param token The token to look for.
     * @return The DenomToToken response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse> crosschainQueryTokenToDenom(
            @NotNull String chainName,
            @NotNull String token
    ) {
        return this.crosschainQueryTokenToDenom(chainName, token, null);
    }

    /**
     * 'crosschainQueryTokenToDenom' queries the denomination for a given token and chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param token The token to look for.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The DenomToToken response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomResponse> crosschainQueryTokenToDenom(
            @NotNull String chainName,
            @NotNull String token,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.tokenToDenom(fx.gravity.crosschain.v1.QueryOuterClass.QueryTokenToDenomRequest.newBuilder().setChainName(chainName).setToken(token).build());
    }

    /**
     * 'crosschainQueryDenomToToken' queries the token for a given denomination and chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param denom The denomination to look for.
     * @return The DenomToToken response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> crosschainQueryDenomToToken(
            @NotNull String chainName,
            String denom
    ) {
        return this.crosschainQueryDenomToToken(chainName, denom, null);
    }

    /**
     * 'crosschainQueryDenomToToken' queries the token for a given denomination and chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param denom The denomination to look for.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The DenomToToken response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> crosschainQueryDenomToToken(
            @NotNull String chainName,
            @NotNull String denom,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.denomToToken(fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest.newBuilder().setDenom(denom).setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryGetOracleByAddr' queries the oracle by its address for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param oracleAddress The oracle address.
     * @return The Oracle response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByAddr(
            @NotNull String chainName,
            @NotNull String oracleAddress
    ) {
        return this.crosschainQueryGetOracleByAddr(chainName, oracleAddress);
    }

    /**
     * 'crosschainQueryGetOracleByAddr' queries the oracle by its address for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param oracleAddress The oracle address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The Oracle response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByAddr(
            @NotNull String chainName,
            @NotNull String oracleAddress,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.getOracleByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest.newBuilder().setChainName(chainName).setOracleAddress(oracleAddress).build());
    }

    /**
     * 'crosschainQueryGetOracleByExternalAddr' queries the oracle by its external address for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param oracleExternalAddress The oracle external address.
     * @return The Oracle response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByExternalAddr(
            @NotNull String chainName,
            @NotNull String oracleExternalAddress
    ) {
        return this.crosschainQueryGetOracleByExternalAddr(chainName, oracleExternalAddress, null);
    }

    /**
     * 'crosschainQueryGetOracleByExternalAddr' queries the oracle by its external address for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param oracleExternalAddress The oracle external address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The Oracle response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByExternalAddr(
            @NotNull String chainName,
            @NotNull String oracleExternalAddress,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.getOracleByExternalAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest.newBuilder().setChainName(chainName).setExternalAddress(oracleExternalAddress).build());
    }

    /**
     * 'crosschainQueryGetOracleByOrchestrator' queries the oracle for a given bridger address and chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param bridgerAddress The bridger address.
     * @return The Oracle response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByBridger(
            @NotNull String chainName,
            @NotNull String bridgerAddress
    ) {
        return this.crosschainQueryGetOracleByBridger(chainName, bridgerAddress, null);
    }

    /**
     * 'crosschainQueryGetOracleByOrchestrator' queries the oracle for a given bridger address and chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param bridgerAddress The bridger address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The Oracle response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByBridger(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.getOracleByBridgerAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByBridgerAddrRequest.newBuilder().setChainName(chainName).setBridgerAddress(bridgerAddress).build());
    }

    /**
     * 'crosschainQueryGetPendingSendToExternal' queries the pending transfers to external chain for a given FX sender and chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param senderAddress The FX sender address.
     * @return The PendingSendToExternal response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> crosschainQueryGetPendingSendToExternal(
            @NotNull String chainName,
            @NotNull String senderAddress
    ) {
        return this.crosschainQueryGetPendingSendToExternal(chainName, senderAddress, null);
    }

    /**
     * 'crosschainQueryGetPendingSendToExternal' queries the pending transfers to external chain for a given FX sender and chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param senderAddress The FX sender address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The PendingSendToExternal response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> crosschainQueryGetPendingSendToExternal(
            @NotNull String chainName,
            @NotNull String senderAddress,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.getPendingSendToExternal(fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest.newBuilder().setChainName(chainName).setSenderAddress(senderAddress).build());
    }

    /**
     * 'crosschainQueryLastEventBlockHeightByAddr' queries the last event block height for a given bridger address and chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param bridgerAddress The orchestrator address.
     * @return The QueryLastEventBlockHeightByAddr response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> crosschainQueryLastEventBlockHeightByAddr(
            @NotNull String chainName,
            @NotNull String bridgerAddress
    ) {
        return this.crosschainQueryLastEventBlockHeightByAddr(chainName, bridgerAddress, null);
    }

    /**
     * 'crosschainQueryLastEventBlockHeightByAddr' queries the last event block height for given bridger address and chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param bridgerAddress The bridger address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastEventBlockHeightByAddr response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> crosschainQueryLastEventBlockHeightByAddr(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.lastEventBlockHeightByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest.newBuilder()
                .setChainName(chainName)
                .setBridgerAddress(bridgerAddress)
                .build());
    }

    /**
     * 'crosschainQueryLastEventNonceByAddr' queries the last event nonce for a given bridger address and chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param bridgerAddress The orchestrator address.
     * @return The QueryLastEventNonceByAddr response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> crosschainQueryLastEventNonceByAddr(
            @NotNull String chainName,
            @NotNull String bridgerAddress
    ) {
        return this.crosschainQueryLastEventNonceByAddr(chainName, bridgerAddress, null);
    }

    /**
     * 'crosschainQueryLastEventNonceByAddr' queries the last event nonce for a given bridger address and chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param bridgerAddress The orchestrator address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastEventNonceByAddr response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> crosschainQueryLastEventNonceByAddr(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.lastEventNonceByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.newBuilder()
                .setChainName(chainName)
                .setBridgerAddress(bridgerAddress)
                .build());
    }

    /**
     * 'crosschainQueryLastObservedBlockHeight' queries the last observed block height for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The QueryLastObservedBlockHeight response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> crosschainQueryLastObservedBlockHeight(
            @NotNull String chainName
    ) {
        return this.crosschainQueryLastObservedBlockHeight(chainName, null);
    }

    /**
     * 'crosschainQueryLastObservedBlockHeight' queries the last observed block height for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastObservedBlockHeight response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> crosschainQueryLastObservedBlockHeight(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.lastObservedBlockHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest.newBuilder().setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryLastOracleSetRequests' queries the last Oracle-set requests for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The QueryLastOracleSetRequests response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> crosschainQueryLastOracleSetRequests(
            @NotNull String chainName
    ) {
        return this.crosschainQueryLastOracleSetRequests(chainName, null);
    }

    /**
     * 'crosschainQueryLastOracleSetRequests' queries the last Oracle-set requests for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastOracleSetRequests response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> crosschainQueryLastOracleSetRequests(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.lastOracleSetRequests(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest.newBuilder().setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryLastPendingBatchRequestByAddr' queries the last pending batch request for a given bridger address and chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param bridgerAddress The FX orchestrator address.
     * @return The QueryLastPendingBatchRequestByAddr response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> crosschainQueryLastPendingBatchRequestByAddr(
            @NotNull String chainName,
            @NotNull String bridgerAddress
    ) {
        return this.crosschainQueryLastPendingBatchRequestByAddr(chainName, bridgerAddress, null);
    }

    /**
     * 'crosschainQueryLastPendingBatchRequestByAddr' queries the last pending batch request for a given bridger address and chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param bridgerAddress The FX bridger address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastPendingBatchRequestByAddr response, as known at the specified block height.
     */
    public ListenableFuture<QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> crosschainQueryLastPendingBatchRequestByAddr(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            BigInteger height
    ) {
        QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.lastPendingBatchRequestByAddr(QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.newBuilder()
                .setChainName(chainName)
                .setBridgerAddress(bridgerAddress)
                .build());
    }

    /**
     * 'crosschainQueryLastPendingOracleSetRequestByAddr' queries the last pending Oracle-set request for a given bridger address and chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @param bridgerAddress The FX orchestrator address.
     * @return The QueryLastPendingOracleSetRequestByAddr response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> crosschainQueryLastPendingOracleSetRequestByAddr(
            @NotNull String chainName,
            @NotNull String bridgerAddress
    ) {
        return this.crosschainQueryLastPendingOracleSetRequestByAddr(chainName, bridgerAddress, null);
    }

    /**
     * 'crosschainQueryLastPendingOracleSetRequestByAddr' queries the last pending Oracle-set request for a given bridger address and chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param bridgerAddress The FX orchestrator address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryLastPendingOracleSetRequestByAddr response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> crosschainQueryLastPendingOracleSetRequestByAddr(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.lastPendingOracleSetRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest.newBuilder()
                .setChainName(chainName)
                .setBridgerAddress(bridgerAddress)
                .build());
    }

    /**
     * 'crosschainQueryOracles' queries the Oracles list for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The QueryOracles response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> crosschainQueryOracles(
            @NotNull String chainName
    ) {
        return this.crosschainQueryOracles(chainName, null);
    }

    /**
     * 'crosschainQueryOracles' queries the Oracles list for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryOracles response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> crosschainQueryOracles(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.oracles(fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest.newBuilder().setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryOracleSetConfirm' queries the Oracle-set confirm for a given chain, bridger address and nonce, as known at the latest available block height.
     * @param chainName The chain name.
     * @param orchestratorAddress The orchestrator address.
     * @param nonce The nonce.
     * @return The QueryOracleSetConfirm response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> crosschainQueryOracleSetConfirm(
            @NotNull String chainName,
            @NotNull String orchestratorAddress,
            @NotNull Long nonce
    ) {
        return this.crosschainQueryOracleSetConfirm(chainName, orchestratorAddress, nonce, null);
    }

    /**
     * 'crosschainQueryOracleSetConfirm' queries the Oracle-set confirm for a given chain, bridger address and nonce, as known at the specified block height.
     * @param chainName The chain name.
     * @param bridgerAddress The orchestrator address.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryOracleSetConfirm response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> crosschainQueryOracleSetConfirm(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            @NotNull Long nonce,
            BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.oracleSetConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest.newBuilder()
                .setChainName(chainName)
                        .setBridgerAddress(bridgerAddress)
                .setNonce(nonce)
                .build());
    }

    /**
     * 'crosschainQueryOracleSetConfirmsByNonce' queries the oracle-set confirms for a given chain and nonce, as known at the latest available block height.
     * @param chainName The chain name.
     * @param nonce The nonce.
     * @return The QueryOracleSetConfirmsByNonce response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> crosschainQueryOracleSetConfirmsByNonce(
            @NotNull String chainName,
            @NotNull Long nonce
    ) {
        return this.crosschainQueryOracleSetConfirmsByNonce(chainName, nonce, null);
    }

    /**
     * 'crosschainQueryOracleSetConfirmsByNonce' queries the oracle-set confirms for a given chain and nonce, as known at the specified block height.
     * @param chainName The chain name.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryOracleSetConfirmsByNonce response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> crosschainQueryOracleSetConfirmsByNonce(
            @NotNull String chainName,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.oracleSetConfirmsByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest.newBuilder().setChainName(chainName).setNonce(nonce).build());
    }

    /**
     * 'crosschainQueryOracleSetRequest' queries the oracle-set request for a given chain and nonce, as known at the latest available block height.
     * @param chainName The chain name.
     * @param nonce The nonce.
     * @return The QueryOracleSetRequest response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> crosschainQueryOracleSetRequest(
            @NotNull String chainName,
            @NotNull Long nonce
    ) {
        return this.crosschainQueryOracleSetRequest(chainName, nonce, null);
    }

    /**
     * 'crosschainQueryOracleSetRequest' queries the oracle-set request for a given chain and nonce, as known at the specified block height.
     * @param chainName The chain name.
     * @param nonce The nonce.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryOracleSetRequest response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> crosschainQueryOracleSetRequest(
            @NotNull String chainName,
            @NotNull Long nonce,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.oracleSetRequest(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest.newBuilder().setChainName(chainName).setNonce(nonce).build());
    }

    /**
     * 'crosschainQueryOutgoingTxBatches' queries the outgoing transaction batches for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The QueryOutgoingTxBatches response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> crosschainQueryOutgoingTxBatches(
            @NotNull String chainName
    ) {
        return this.crosschainQueryOutgoingTxBatches(chainName, null);
    }

    /**
     * 'crosschainQueryOutgoingTxBatches' queries the outgoing transaction batches for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryOutgoingTxBatches response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> crosschainQueryOutgoingTxBatches(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.outgoingTxBatches(fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.newBuilder().setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryParams' queries the parameters for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The QueryParams response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> crosschainQueryParams(
            @NotNull String chainName
    ) {
        return this.crosschainQueryParams(chainName, null);
    }

    /**
     * 'crosschainQueryParams' queries the parameters for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryParams response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> crosschainQueryParams(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.params(fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest.newBuilder().setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryBridgeTokens' queries bridge tokens for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The QueryBridgeTokens response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse> crosschainQueryBridgeTokens(
            @NotNull String chainName
    ) {
        return this.crosschainQueryBridgeTokens(chainName, null);
    }

    /**
     * 'crosschainQueryBridgeTokens' queries bridge tokens for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryBridgeTokens response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensResponse> crosschainQueryBridgeTokens(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.bridgeTokens(fx.gravity.crosschain.v1.QueryOuterClass.QueryBridgeTokensRequest.newBuilder().setChainName(chainName).build());
    }

    /**
     * 'crosschainQueryProjectedBatchTimeoutHeight' queries the projected batch timeout height for a given chain, as known at the latest available block height.
     * @param chainName The chain name.
     * @return The QueryProjectedBatchTimeoutHeight response, as known at the latest available block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> crosschainQueryProjectedBatchTimeoutHeight(
            @NotNull String chainName
    ) {
        return this.crosschainQueryProjectedBatchTimeoutHeight(chainName, null);
    }

    /**
     * 'crosschainQueryProjectedBatchTimeoutHeight' queries the projected batch timeout height for a given chain, as known at the specified block height.
     * @param chainName The chain name.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The QueryProjectedBatchTimeoutHeight response, as known at the specified block height.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightResponse> crosschainQueryProjectedBatchTimeoutHeight(
            @NotNull String chainName,
            BigInteger height
    ) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = fxCrosschainQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = fxCrosschainQueryStub;
        }
        return stub.projectedBatchTimeoutHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryProjectedBatchTimeoutHeightRequest.newBuilder().setChainName(chainName).build());
    }

    /********************************************************
     ***** Module 'crosschain' : MESSAGE
     *******************************************************/

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgUnbondedOracleResponse> crosschainMsgUnbondedOracle(
            @NotNull String chainName,
            @NotNull String oracleAddress
    ) {
        return fxCrosschainMsgStub.unbondedOracle(fx.gravity.crosschain.v1.Tx.MsgUnbondedOracle.newBuilder()
                .setChainName(chainName)
                .setOracleAddress(oracleAddress)
                .build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgWithdrawRewardResponse> crosschainMsgWithdrawReward(
            @NotNull String chainName,
            @NotNull String oracleAddress
    ) {
        return fxCrosschainMsgStub.withdrawReward(fx.gravity.crosschain.v1.Tx.MsgWithdrawReward.newBuilder()
                .setChainName(chainName)
                .setOracleAddress(oracleAddress)
                .build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgEditOracleResponse> crosschainMsgEditOracle(
            @NotNull String chainName,
            @NotNull String oracleAddress,
            @NotNull String validatorAddress
    ) {
        return fxCrosschainMsgStub.editOracle(fx.gravity.crosschain.v1.Tx.MsgEditOracle.newBuilder()
                .setChainName(chainName)
                .setOracleAddress(oracleAddress)
                .setValidatorAddress(validatorAddress)
                .build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgAddDelegateResponse> crosschainMsgAddDelegate(
            @NotNull String chainName,
            @NotNull String oracleAddress,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount
    ) {
        return fxCrosschainMsgStub.addDelegate(fx.gravity.crosschain.v1.Tx.MsgAddDelegate.newBuilder()
                .setChainName(chainName)
                .setOracleAddress(oracleAddress)
                .setAmount(amount)
                .build());
    }

    /**
     * 'crosschainMsgBondedOracle' allows to bond an oracle to a bridger
     * @param chainName The chain name.
     * @param bridgerAddress The bridger address.
     * @param oracleAddress The oracle address.
     * @param externalAddress The external address.
     * @param validatorAddress The validator address.
     * @param delegateAmount The delegated amount.
     * @return The MsgBondedOracle response.
     */
    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgBondedOracleResponse> crosschainMsgBondedOracle(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            @NotNull String oracleAddress,
            @NotNull String externalAddress,
            @NotNull String validatorAddress,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin delegateAmount
    ) {
        return fxCrosschainMsgStub.bondedOracle(fx.gravity.crosschain.v1.Tx.MsgBondedOracle.newBuilder()
                .setChainName(chainName)
                .setBridgerAddress(bridgerAddress)
                .setOracleAddress(oracleAddress)
                .setExternalAddress(externalAddress)
                .setValidatorAddress(validatorAddress)
                .setDelegateAmount(delegateAmount)
                .build());
    }

    /**
     *
     * @param chainName
     * @param bridgerAddress
     * @param tokenContract
     * @param name
     * @param symbol
     * @param decimals
     * @param blockHeight
     * @param eventNonce
     * @param channelIBC
     * @return
     */
    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse> crosschainMsgBridgeTokenClaim(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            @NotNull String tokenContract,
            @NotNull String name,
            @NotNull String symbol,
            @NotNull Long decimals,
            @NotNull Long blockHeight,
            @NotNull Long eventNonce,
            @NotNull String channelIBC
    ) {
        return fxCrosschainMsgStub.bridgeTokenClaim(fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim.newBuilder()
                .setChainName(chainName)
                .setBridgerAddress(bridgerAddress)
                .setTokenContract(tokenContract)
                .setName(name)
                .setSymbol(symbol)
                .setDecimals(decimals)
                .setBlockHeight(blockHeight)
                .setEventNonce(eventNonce)
                .setChannelIbc(channelIBC)
                .build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse> crosschainMsgCancelSendToExternal(
            @NotNull String chainName,
            @NotNull String sender,
            @NotNull Long transactionId
    ) {
        return fxCrosschainMsgStub.cancelSendToExternal(fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal.newBuilder().setChainName(chainName).setSender(sender).setTransactionId(transactionId).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse> crosschainMsgConfirmBatch(
            @NotNull String chainName,
            @NotNull String externalAddress,
            @NotNull String bridgerAddress,
            @NotNull String tokenContract,
            @NotNull Long nonce,
            @NotNull String signature
    ) {
        return fxCrosschainMsgStub.confirmBatch(fx.gravity.crosschain.v1.Tx.MsgConfirmBatch.newBuilder()
                .setChainName(chainName)
                .setExternalAddress(externalAddress)
                .setNonce(nonce)
                        .setBridgerAddress(bridgerAddress)
                .setSignature(signature)
                .setTokenContract(tokenContract)
                .build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse> crosschainMsgOracleSetConfirm(
            @NotNull String chainName,
            @NotNull String externalAddress,
            @NotNull String bridgerAddress,
            @NotNull Long nonce,
            @NotNull String signature
    ) {
        return fxCrosschainMsgStub.oracleSetConfirm(fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm.newBuilder()
                .setChainName(chainName)
                .setExternalAddress(externalAddress)
                .setNonce(nonce)
                        .setBridgerAddress(bridgerAddress)
                .setSignature(signature)
                .build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse> crosschainMsgOracleSetUpdateClaim(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            @NotNull Long oracleSetNonce,
            @NotNull Long eventNonce,
            @NotNull Long blockHeight,
            @NotNull Iterable<? extends fx.gravity.crosschain.v1.Types.BridgeValidator> members
    ) {
        return fxCrosschainMsgStub.oracleSetUpdateClaim(fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim.newBuilder()
                .setChainName(chainName)
                .setBlockHeight(blockHeight)
                .setOracleSetNonce(oracleSetNonce)
                .setEventNonce(eventNonce)
                        .setBridgerAddress(bridgerAddress)
                .addAllMembers(members)
                .build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse> crosschainMsgRequestBatch(
            @NotNull String chainName,
            @NotNull String sender,
            @NotNull String denom,
            @NotNull String feeReceive,
            @NotNull String minimumFee
    ) {
        return fxCrosschainMsgStub.requestBatch(fx.gravity.crosschain.v1.Tx.MsgRequestBatch.newBuilder().setChainName(chainName).setSender(sender).setDenom(denom).setFeeReceive(feeReceive).setMinimumFee(minimumFee).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> crosschainMsgSendToExternal(
            @NotNull String chainName,
            @NotNull String sender,
            @NotNull String dest,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin bridgeFee
    ) {
        return fxCrosschainMsgStub.sendToExternal(fx.gravity.crosschain.v1.Tx.MsgSendToExternal.newBuilder().setChainName(chainName).setSender(sender).setDest(dest).setAmount(amount).setBridgeFee(bridgeFee).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> crosschainMsgSendToExternalClaim(
            @NotNull String chainName,
            @NotNull String bridgerAddress,
            @NotNull String tokenContract,
            @NotNull Long batchNonce,
            @NotNull Long eventNonce,
            @NotNull Long blockHeight
    ) {
        return fxCrosschainMsgStub.sendToExternalClaim(fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim.newBuilder()
                .setChainName(chainName)
                .setBatchNonce(batchNonce)
                .setEventNonce(eventNonce)
                .setBridgerAddress(bridgerAddress)
                .setBlockHeight(blockHeight)
                .setTokenContract(tokenContract)
                .build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> crosschainMsgSendToExternalClaim(
            @NotNull String chainName,
            @NotNull String sender,
            @NotNull String receiver,
            @NotNull String bridgerAddress,
            @NotNull String tokenContract,
            @NotNull String amount,
            @NotNull Long eventNonce,
            @NotNull Long blockHeight,
            @NotNull String targetIBC
    ) {
        return fxCrosschainMsgStub.sendToFxClaim(fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim.newBuilder()
                .setChainName(chainName)
                .setSender(sender)
                .setReceiver(receiver)
                .setBridgerAddress(bridgerAddress)
                .setEventNonce(eventNonce)
                .setAmount(amount)
                .setBlockHeight(blockHeight)
                .setTokenContract(tokenContract)
                .setTargetIbc(targetIBC)
                .build());
    }

    /***********************************************************************
     ***** Module 'ibc.applications.interchain_accounts.controller' : QUERY
     ***********************************************************************/

    public ListenableFuture<ibc.applications.interchain_accounts.controller.v1.QueryOuterClass.QueryParamsResponse> ibcAppInterchainAccountsControllerQueryParams() {
        return ibcApplicationsInterchainAccountsControllerQueryStub.params(ibc.applications.interchain_accounts.controller.v1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /*****************************************************************
     ***** Module 'ibc.applications.interchain_accounts.host' : QUERY
     *****************************************************************/

    public ListenableFuture<ibc.applications.interchain_accounts.host.v1.QueryOuterClass.QueryParamsResponse> ibcAppInterchainAccountsHostQueryParams() {
        return ibcApplicationsInterchainAccountsHostQueryStub.params(ibc.applications.interchain_accounts.host.v1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /*****************************************************************
     ***** Module 'ibc.applications.fee' : QUERY
     *****************************************************************/

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryCounterpartyPayeeResponse> ibcAppFeeQueryCounterpartyPayee(
            @NotNull String channelID,
            @NotNull String relayer
    ) {
        return ibcApplicationsFeeQueryStub.counterpartyPayee(ibc.applications.fee.v1.QueryOuterClass.QueryCounterpartyPayeeRequest.newBuilder()
                .setChannelId(channelID)
                .setRelayer(relayer)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryFeeEnabledChannelResponse> ibcAppFeeQueryFeeEnabledChannel(
            @NotNull String channelID,
            @NotNull String portID
    ) {
        return ibcApplicationsFeeQueryStub.feeEnabledChannel(ibc.applications.fee.v1.QueryOuterClass.QueryFeeEnabledChannelRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryFeeEnabledChannelsResponse> ibcAppFeeQueryFeeEnabledChannels(
            @NotNull Pagination.PageRequest pageRequest,
            @NotNull Long queryHeight
    ) {
        return ibcApplicationsFeeQueryStub.feeEnabledChannels(ibc.applications.fee.v1.QueryOuterClass.QueryFeeEnabledChannelsRequest.newBuilder()
                .setPagination(pageRequest)
                .setQueryHeight(queryHeight)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryIncentivizedPacketResponse> ibcAppFeeQueryIncentivizedPacket(
            @NotNull ChannelOuterClass.PacketId packetID,
            @NotNull Long queryHeight
    ) {
        return ibcApplicationsFeeQueryStub.incentivizedPacket(ibc.applications.fee.v1.QueryOuterClass.QueryIncentivizedPacketRequest.newBuilder()
                .setPacketId(packetID)
                .setQueryHeight(queryHeight)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryIncentivizedPacketsResponse> ibcAppFeeQueryIncentivizedPackets(
            @NotNull Pagination.PageRequest pageRequest,
            @NotNull Long queryHeight
    ) {
        return ibcApplicationsFeeQueryStub.incentivizedPackets(ibc.applications.fee.v1.QueryOuterClass.QueryIncentivizedPacketsRequest.newBuilder()
                .setPagination(pageRequest)
                .setQueryHeight(queryHeight)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryIncentivizedPacketsForChannelResponse> ibcAppFeeQueryIncentivizedPackets(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Pagination.PageRequest pageRequest,
            @NotNull Long queryHeight
    ) {
        return ibcApplicationsFeeQueryStub.incentivizedPacketsForChannel(ibc.applications.fee.v1.QueryOuterClass.QueryIncentivizedPacketsForChannelRequest.newBuilder()
                .setPagination(pageRequest)
                .setChannelId(channelID)
                .setPortId(portID)
                .setQueryHeight(queryHeight)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryPayeeResponse> ibcAppFeeQueryPayee(
            @NotNull String channelID,
            @NotNull String relayer
    ) {
        return ibcApplicationsFeeQueryStub.payee(ibc.applications.fee.v1.QueryOuterClass.QueryPayeeRequest.newBuilder()
                .setChannelId(channelID)
                .setRelayer(relayer)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryTotalAckFeesResponse> ibcAppFeeQueryTotalAckFees(
            @NotNull ChannelOuterClass.PacketId packetID
    ) {
        return ibcApplicationsFeeQueryStub.totalAckFees(ibc.applications.fee.v1.QueryOuterClass.QueryTotalAckFeesRequest.newBuilder()
                .setPacketId(packetID)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryTotalRecvFeesResponse> ibcAppFeeQueryTotalRecvFees(
            @NotNull ChannelOuterClass.PacketId packetID
    ) {
        return ibcApplicationsFeeQueryStub.totalRecvFees(ibc.applications.fee.v1.QueryOuterClass.QueryTotalRecvFeesRequest.newBuilder()
                .setPacketId(packetID)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.QueryOuterClass.QueryTotalTimeoutFeesResponse> ibcAppFeeQueryTotalTimeoutFees(
            @NotNull ChannelOuterClass.PacketId packetID
    ) {
        return ibcApplicationsFeeQueryStub.totalTimeoutFees(ibc.applications.fee.v1.QueryOuterClass.QueryTotalTimeoutFeesRequest.newBuilder()
                .setPacketId(packetID)
                .build());
    }

    /*****************************************************************
     ***** Module 'ibc.applications.fee' : MESSAGE
     *****************************************************************/

    public ListenableFuture<ibc.applications.fee.v1.Tx.MsgPayPacketFeeResponse> ibcAppFeeMsgPayPacketFee(
            @NotNull String sourceChannelID,
            @NotNull String sourcePortID,
            @NotNull Iterable<String> relayers,
            @NotNull FeeOuterClass.Fee fee,
            @NotNull String signer
    ) {
        return ibcApplicationsFeeMsgStub.payPacketFee(ibc.applications.fee.v1.Tx.MsgPayPacketFee.newBuilder()
                .setSourceChannelId(sourceChannelID)
                .setSourcePortId(sourcePortID)
                .addAllRelayers(relayers)
                .setFee(fee)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.Tx.MsgPayPacketFeeAsyncResponse> ibcAppFeeMsgPayPacketFeeAsync(
            @NotNull ChannelOuterClass.PacketId packetID,
            @NotNull FeeOuterClass.PacketFee packetFee
    ) {
        return ibcApplicationsFeeMsgStub.payPacketFeeAsync(ibc.applications.fee.v1.Tx.MsgPayPacketFeeAsync.newBuilder()
                .setPacketId(packetID)
                .setPacketFee(packetFee)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.Tx.MsgRegisterCounterpartyPayeeResponse> ibcAppFeeMsgRegisterCounterpartyPayee(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull String relayer,
            @NotNull String counterpartyPayee
    ) {
        return ibcApplicationsFeeMsgStub.registerCounterpartyPayee(ibc.applications.fee.v1.Tx.MsgRegisterCounterpartyPayee.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setRelayer(relayer)
                .setCounterpartyPayee(counterpartyPayee)
                .build());
    }

    public ListenableFuture<ibc.applications.fee.v1.Tx.MsgRegisterPayeeResponse> ibcAppFeeMsgRegisterPayee(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull String relayer,
            @NotNull String payee
    ) {
        return ibcApplicationsFeeMsgStub.registerPayee(ibc.applications.fee.v1.Tx.MsgRegisterPayee.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setRelayer(relayer)
                .setPayee(payee)
                .build());
    }

    /*****************************************************************
     ***** Module 'ibc.core.channel' : QUERY
     *****************************************************************/

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryChannelResponse> ibcCoreChannelQueryChannel(
            @NotNull String channelID,
            @NotNull String portID
    ) {
        return ibcCoreChannelQueryStub.channel(ibc.core.channel.v1.QueryOuterClass.QueryChannelRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse> ibcCoreChannelQueryChannelClientState(
            @NotNull String channelID,
            @NotNull String portID
    ) {
        return ibcCoreChannelQueryStub.channelClientState(ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateResponse> ibcCoreChannelQueryChannelConsensusState(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Long revisionHeight,
            @NotNull Long revisionNumber
            ) {
        return ibcCoreChannelQueryStub.channelConsensusState(ibc.core.channel.v1.QueryOuterClass.QueryChannelConsensusStateRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setRevisionHeight(revisionHeight)
                .setRevisionNumber(revisionNumber)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryChannelsResponse> ibcCoreChannelQueryChannels(
            @NotNull Pagination.PageRequest pageRequest
    ) {
        return ibcCoreChannelQueryStub.channels(ibc.core.channel.v1.QueryOuterClass.QueryChannelsRequest.newBuilder()
                .setPagination(pageRequest)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsResponse> ibcCoreChannelQueryConnectionChannels(
            @NotNull Pagination.PageRequest pageRequest,
            @NotNull String connection
            ) {
        return ibcCoreChannelQueryStub.connectionChannels(ibc.core.channel.v1.QueryOuterClass.QueryConnectionChannelsRequest.newBuilder()
                .setPagination(pageRequest)
                .setConnection(connection)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveResponse> ibcCoreChannelQueryNextSequenceReceive(
            @NotNull String channelID,
            @NotNull String portID
    ) {
        return ibcCoreChannelQueryStub.nextSequenceReceive(ibc.core.channel.v1.QueryOuterClass.QueryNextSequenceReceiveRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementResponse> ibcCoreChannelQueryPacketAcknowledgement(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Long sequence
    ) {
        return ibcCoreChannelQueryStub.packetAcknowledgement(ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setSequence(sequence)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsResponse> ibcCoreChannelQueryPacketAcknowledgements(
            @NotNull Pagination.PageRequest pageRequest,
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Iterable<? extends Long> packetCommitmentSequences
    ) {
        return ibcCoreChannelQueryStub.packetAcknowledgements(ibc.core.channel.v1.QueryOuterClass.QueryPacketAcknowledgementsRequest.newBuilder()
                .setPagination(pageRequest)
                .setChannelId(channelID)
                .setPortId(portID)
                .addAllPacketCommitmentSequences(packetCommitmentSequences)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentResponse> ibcCoreChannelQueryPacketCommitment(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Long sequence
    ) {
        return ibcCoreChannelQueryStub.packetCommitment(ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setSequence(sequence)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsResponse> ibcCoreChannelQueryPacketCommitments(
            @NotNull Pagination.PageRequest pageRequest,
            @NotNull String channelID,
            @NotNull String portID
    ) {
        return ibcCoreChannelQueryStub.packetCommitments(ibc.core.channel.v1.QueryOuterClass.QueryPacketCommitmentsRequest.newBuilder()
                .setPagination(pageRequest)
                .setChannelId(channelID)
                .setPortId(portID)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptResponse> ibcCoreChannelQueryPacketReceipt(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Long sequence
    ) {
        return ibcCoreChannelQueryStub.packetReceipt(ibc.core.channel.v1.QueryOuterClass.QueryPacketReceiptRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setSequence(sequence)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksResponse> ibcCoreChannelQueryUnreceivedAcks(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Iterable<? extends Long> packetAckSequences
    ) {
        return ibcCoreChannelQueryStub.unreceivedAcks(ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedAcksRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .addAllPacketAckSequences(packetAckSequences)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsResponse> ibcCoreChannelQueryUnreceivedPackets(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Iterable<? extends Long> packetCommitmentSequences
    ) {
        return ibcCoreChannelQueryStub.unreceivedPackets(ibc.core.channel.v1.QueryOuterClass.QueryUnreceivedPacketsRequest.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .addAllPacketCommitmentSequences(packetCommitmentSequences)
                .build());
    }

    /*****************************************************************
     ***** Module 'ibc.core.channel' : MESSAGE
     *****************************************************************/

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgAcknowledgementResponse> ibcCoreChannelMsgAcknowledgement(
            @NotNull byte[] acknowledgement,
            @NotNull ChannelOuterClass.Packet packet,
            @NotNull byte[] proofAcked,
            @NotNull Client.Height proofHeight,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.acknowledgement(ibc.core.channel.v1.Tx.MsgAcknowledgement.newBuilder()
                .setAcknowledgement(ByteString.copyFrom(acknowledgement))
                .setPacket(packet)
                .setProofAcked(ByteString.copyFrom(proofAcked))
                .setProofHeight(proofHeight)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgChannelCloseConfirmResponse> ibcCoreChannelMsgChannelCloseConfirm(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Client.Height proofHeight,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.channelCloseConfirm(ibc.core.channel.v1.Tx.MsgChannelCloseConfirm.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setProofHeight(proofHeight)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgChannelCloseInitResponse> ibcCoreChannelMsgChannelCloseInit(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.channelCloseInit(ibc.core.channel.v1.Tx.MsgChannelCloseInit.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgChannelOpenAckResponse> ibcCoreChannelMsgChannelOpenAck(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull String counterpartyChannelID,
            @NotNull String counterpartyVersion,
            @NotNull Client.Height proofHeight,
            @NotNull byte[] proofTry,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.channelOpenAck(ibc.core.channel.v1.Tx.MsgChannelOpenAck.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setCounterpartyChannelId(counterpartyChannelID)
                .setCounterpartyVersion(counterpartyVersion)
                .setProofHeight(proofHeight)
                .setProofTry(ByteString.copyFrom(proofTry))
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgChannelOpenConfirmResponse> ibcCoreChannelMsgChannelOpenConfirm(
            @NotNull String channelID,
            @NotNull String portID,
            @NotNull Client.Height proofHeight,
            @NotNull byte[] proofAck,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.channelOpenConfirm(ibc.core.channel.v1.Tx.MsgChannelOpenConfirm.newBuilder()
                .setChannelId(channelID)
                .setPortId(portID)
                .setProofHeight(proofHeight)
                .setProofAck(ByteString.copyFrom(proofAck))
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgChannelOpenInitResponse> ibcCoreChannelMsgChannelOpenInit(
            @NotNull ChannelOuterClass.Channel channel,
            @NotNull String portID,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.channelOpenInit(ibc.core.channel.v1.Tx.MsgChannelOpenInit.newBuilder()
                .setChannel(channel)
                .setPortId(portID)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgChannelOpenTryResponse> ibcCoreChannelMsgChannelOpenTry(
            @NotNull ChannelOuterClass.Channel channel,
            @NotNull String portID,
            @NotNull String previousChannelID,
            @NotNull String counterpartyVersion,
            @NotNull Client.Height proofHeight,
            @NotNull byte[] proofInit,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.channelOpenTry(ibc.core.channel.v1.Tx.MsgChannelOpenTry.newBuilder()
                .setChannel(channel)
                .setPortId(portID)
                .setPreviousChannelId(previousChannelID)
                .setCounterpartyVersion(counterpartyVersion)
                .setProofHeight(proofHeight)
                .setProofInit(ByteString.copyFrom(proofInit))
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgRecvPacketResponse> ibcCoreChannelMsgRecvPacket(
            @NotNull ChannelOuterClass.Packet packet,
            @NotNull Client.Height proofHeight,
            @NotNull byte[] proofCommitment,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.recvPacket(ibc.core.channel.v1.Tx.MsgRecvPacket.newBuilder()
                .setPacket(packet)
                .setProofHeight(proofHeight)
                .setProofCommitment(ByteString.copyFrom(proofCommitment))
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgTimeoutResponse> ibcCoreChannelMsgTimeout(
            @NotNull ChannelOuterClass.Packet packet,
            @NotNull Long nextSequenceRecv,
            @NotNull Client.Height proofHeight,
            @NotNull byte[] proofUnreceived,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.timeout(ibc.core.channel.v1.Tx.MsgTimeout.newBuilder()
                .setPacket(packet)
                .setNextSequenceRecv(nextSequenceRecv)
                .setProofHeight(proofHeight)
                .setProofUnreceived(ByteString.copyFrom(proofUnreceived))
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.channel.v1.Tx.MsgTimeoutOnCloseResponse> ibcCoreChannelMsgTimeoutOnClose(
            @NotNull ChannelOuterClass.Packet packet,
            @NotNull Long nextSequenceRecv,
            @NotNull Client.Height proofHeight,
            @NotNull byte[] proofClose,
            @NotNull byte[] proofUnreceived,
            @NotNull String signer
    ) {
        return ibcCoreChannelMsgStub.timeoutOnClose(ibc.core.channel.v1.Tx.MsgTimeoutOnClose.newBuilder()
                .setPacket(packet)
                .setNextSequenceRecv(nextSequenceRecv)
                .setProofHeight(proofHeight)
                .setProofClose(ByteString.copyFrom(proofClose))
                .setProofUnreceived(ByteString.copyFrom(proofUnreceived))
                .setSigner(signer)
                .build());
    }

    /********************************************************
     ***** Module 'ibc.core.client' : QUERY
     *******************************************************/

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryClientParamsResponse> ibcCoreClientQueryClientParams() {
        return ibcCoreClientQueryStub.clientParams(ibc.core.client.v1.QueryOuterClass.QueryClientParamsRequest.newBuilder().build());
    }

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryClientStateResponse> ibcCoreClientQueryClientState(
            @NotNull String clientID
    ) {
        return ibcCoreClientQueryStub.clientState(ibc.core.client.v1.QueryOuterClass.QueryClientStateRequest.newBuilder()
                .setClientId(clientID)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryClientStatesResponse> ibcCoreClientQueryClientStates(
            @NotNull Pagination.PageRequest pageRequest
    ) {
        return ibcCoreClientQueryStub.clientStates(ibc.core.client.v1.QueryOuterClass.QueryClientStatesRequest.newBuilder()
                .setPagination(pageRequest)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryClientStatusResponse> ibcCoreClientQueryClientStatus(
            @NotNull String clientID
    ) {
        return ibcCoreClientQueryStub.clientStatus(ibc.core.client.v1.QueryOuterClass.QueryClientStatusRequest.newBuilder()
                .setClientId(clientID)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateResponse> ibcCoreClientQueryConsensusState(
            @NotNull String clientID,
            @NotNull Long revisionHeight,
            @NotNull Long revisionNumber,
            @NotNull Boolean latestHeight
            ) {
        return ibcCoreClientQueryStub.consensusState(ibc.core.client.v1.QueryOuterClass.QueryConsensusStateRequest.newBuilder()
                .setClientId(clientID)
                .setRevisionHeight(revisionHeight)
                .setRevisionNumber(revisionNumber)
                .setLatestHeight(latestHeight)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesResponse> ibcCoreClientQueryConsensusStates(
            @NotNull Pagination.PageRequest pageRequest,
            @NotNull String clientID
    ) {
        return ibcCoreClientQueryStub.consensusStates(ibc.core.client.v1.QueryOuterClass.QueryConsensusStatesRequest.newBuilder()
                .setPagination(pageRequest)
                .setClientId(clientID)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryConsensusStateHeightsResponse> ibcCoreClientQueryConsensusStateHeights(
            @NotNull Pagination.PageRequest pageRequest,
            @NotNull String clientID
    ) {
        return ibcCoreClientQueryStub.consensusStateHeights(ibc.core.client.v1.QueryOuterClass.QueryConsensusStateHeightsRequest.newBuilder()
                .setPagination(pageRequest)
                .setClientId(clientID)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateResponse> ibcCoreClientQueryUpgradedClientState() {
        return ibcCoreClientQueryStub.upgradedClientState(ibc.core.client.v1.QueryOuterClass.QueryUpgradedClientStateRequest.newBuilder().build());
    }

    public ListenableFuture<ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateResponse> ibcCoreClientQueryUpgradedConsensusState() {
        return ibcCoreClientQueryStub.upgradedConsensusState(ibc.core.client.v1.QueryOuterClass.QueryUpgradedConsensusStateRequest.newBuilder().build());
    }

    /********************************************************
     ***** Module 'ibc.core.client' : MESSAGE
     *******************************************************/

    public ListenableFuture<ibc.core.client.v1.Tx.MsgCreateClientResponse> ibcCoreClientMsgCreateClient(
            @NotNull Any clientState,
            @NotNull Any consensusState,
            @NotNull String signer
    ) {
        return ibcCoreClientMsgStub.createClient(ibc.core.client.v1.Tx.MsgCreateClient.newBuilder()
                .setClientState(clientState)
                .setConsensusState(consensusState)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.Tx.MsgSubmitMisbehaviourResponse> ibcCoreClientMsgSubmitMisbehaviour(
            @NotNull String clientID,
            @NotNull Any misbehaviour,
            @NotNull String signer
    ) {
        return ibcCoreClientMsgStub.submitMisbehaviour(ibc.core.client.v1.Tx.MsgSubmitMisbehaviour.newBuilder()
                .setClientId(clientID)
                .setMisbehaviour(misbehaviour)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.Tx.MsgUpdateClientResponse> ibcCoreClientMsgUpdateClient(
            @NotNull String clientID,
            @NotNull Any header,
            @NotNull String signer
    ) {
        return ibcCoreClientMsgStub.updateClient(ibc.core.client.v1.Tx.MsgUpdateClient.newBuilder()
                .setClientId(clientID)
                .setHeader(header)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.client.v1.Tx.MsgUpgradeClientResponse> ibcCoreClientMsgUpgradeClient(
            @NotNull String clientID,
            @NotNull Any clientState,
            @NotNull Any consensusState,
            @NotNull byte[] proofUpgradeClient,
            @NotNull String signer
    ) {
        return ibcCoreClientMsgStub.upgradeClient(ibc.core.client.v1.Tx.MsgUpgradeClient.newBuilder()
                .setClientId(clientID)
                .setClientState(clientState)
                .setConsensusState(consensusState)
                .setProofUpgradeClient(ByteString.copyFrom(proofUpgradeClient))
                .setSigner(signer)
                .build());
    }

    /********************************************************
     ***** Module 'ibc.core.connection' : QUERY
     *******************************************************/

    public ListenableFuture<ibc.core.connection.v1.QueryOuterClass.QueryClientConnectionsResponse> ibcCoreConnectionQueryClientConnections(
            @NotNull String clientID
    ) {
        return ibcCoreConnectionQueryStub.clientConnections(ibc.core.connection.v1.QueryOuterClass.QueryClientConnectionsRequest.newBuilder()
                .setClientId(clientID)
                .build());
    }

    public ListenableFuture<ibc.core.connection.v1.QueryOuterClass.QueryConnectionResponse> ibcCoreConnectionQueryConnection(
            @NotNull String connectionID
    ) {
        return ibcCoreConnectionQueryStub.connection(ibc.core.connection.v1.QueryOuterClass.QueryConnectionRequest.newBuilder()
                .setConnectionId(connectionID)
                .build());
    }

    public ListenableFuture<ibc.core.connection.v1.QueryOuterClass.QueryConnectionsResponse> ibcCoreConnectionQueryConnections(
            @NotNull Pagination.PageRequest pageRequest
    ) {
        return ibcCoreConnectionQueryStub.connections(ibc.core.connection.v1.QueryOuterClass.QueryConnectionsRequest.newBuilder()
                .setPagination(pageRequest)
                .build());
    }

    public ListenableFuture<ibc.core.connection.v1.QueryOuterClass.QueryConnectionClientStateResponse> ibcCoreConnectionQueryConnectionClientState(
            @NotNull String connectionID
    ) {
        return ibcCoreConnectionQueryStub.connectionClientState(ibc.core.connection.v1.QueryOuterClass.QueryConnectionClientStateRequest.newBuilder()
                .setConnectionId(connectionID)
                .build());
    }

    public ListenableFuture<ibc.core.connection.v1.QueryOuterClass.QueryConnectionConsensusStateResponse> ibcCoreConnectionQueryConnectionConsensusState(
            @NotNull String connectionID,
            @NotNull Long revisionHeight,
            @NotNull Long revisionNumber
            ) {
        return ibcCoreConnectionQueryStub.connectionConsensusState(ibc.core.connection.v1.QueryOuterClass.QueryConnectionConsensusStateRequest.newBuilder()
                .setConnectionId(connectionID)
                .setRevisionHeight(revisionHeight)
                .setRevisionNumber(revisionNumber)
                .build());
    }

    /********************************************************
     ***** Module 'ibc.core.connection' : MESSAGE
     *******************************************************/

    public ListenableFuture<ibc.core.connection.v1.Tx.MsgConnectionOpenAckResponse> ibcCoreConnectionMsgConnectionOpenAck(
            @NotNull String connectionID,
            @NotNull String counterpartyConnectionID,
            @NotNull Any clientState,
            @NotNull Connection.Version version,
            @NotNull Client.Height consensusHeight,
            @NotNull Client.Height proofHeight,
            @NotNull byte[] proofClient,
            @NotNull byte[] proofTry,
            @NotNull byte[] proofConsensus,
            @NotNull String signer
            ) {
        return ibcCoreConnectionMsgStub.connectionOpenAck(ibc.core.connection.v1.Tx.MsgConnectionOpenAck.newBuilder()
                .setConnectionId(connectionID)
                .setCounterpartyConnectionId(counterpartyConnectionID)
                .setClientState(clientState)
                .setVersion(version)
                .setConsensusHeight(consensusHeight)
                .setProofHeight(proofHeight)
                .setProofClient(ByteString.copyFrom(proofClient))
                .setProofTry(ByteString.copyFrom(proofTry))
                .setProofConsensus(ByteString.copyFrom(proofConsensus))
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.connection.v1.Tx.MsgConnectionOpenConfirmResponse> ibcCoreConnectionMsgConnectionOpenConfirm(
            @NotNull String connectionID,
            @NotNull Client.Height proofHeight,
            @NotNull byte[] proofAck,
            @NotNull String signer
    ) {
        return ibcCoreConnectionMsgStub.connectionOpenConfirm(ibc.core.connection.v1.Tx.MsgConnectionOpenConfirm.newBuilder()
                .setConnectionId(connectionID)
                .setProofHeight(proofHeight)
                .setProofAck(ByteString.copyFrom(proofAck))
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.connection.v1.Tx.MsgConnectionOpenInitResponse> ibcCoreConnectionMsgConnectionOpenInit(
            @NotNull String clientID,
            @NotNull Connection.Counterparty counterparty,
            @NotNull Long delayPeriod,
            @NotNull Connection.Version version,
            @NotNull String signer
    ) {
        return ibcCoreConnectionMsgStub.connectionOpenInit(ibc.core.connection.v1.Tx.MsgConnectionOpenInit.newBuilder()
                .setClientId(clientID)
                .setCounterparty(counterparty)
                .setDelayPeriod(delayPeriod)
                .setVersion(version)
                .setSigner(signer)
                .build());
    }

    public ListenableFuture<ibc.core.connection.v1.Tx.MsgConnectionOpenTryResponse> ibcCoreConnectionMsgConnectionOpenTry(
            @NotNull String clientID,
            @NotNull String previousConnectionID,
            @NotNull Connection.Counterparty counterparty,
            @NotNull Iterable<? extends Connection.Version> counterpartyVersions,
            @NotNull Any clientState,
            @NotNull Long delayPeriod,
            @NotNull Client.Height consensusHeight,
            @NotNull byte[] proofClient,
            @NotNull byte[] proofInit,
            @NotNull byte[] proofConsensus,
            @NotNull String signer
    ) {
        return ibcCoreConnectionMsgStub.connectionOpenTry(ibc.core.connection.v1.Tx.MsgConnectionOpenTry.newBuilder()
                .setClientId(clientID)
                .setPreviousConnectionId(previousConnectionID)
                .setCounterparty(counterparty)
                .addAllCounterpartyVersions(counterpartyVersions)
                .setClientState(clientState)
                .setDelayPeriod(delayPeriod)
                .setConsensusHeight(consensusHeight)
                .setProofClient(ByteString.copyFrom(proofClient))
                .setProofInit(ByteString.copyFrom(proofInit))
                .setProofConsensus(ByteString.copyFrom(proofConsensus))
                .setSigner(signer)
                .build());
    }

    /********************************************************
     ***** Module 'ibc.applications.transfer' : QUERY
     *******************************************************/

    public ListenableFuture<ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> ibcQueryDenomTrace(
            @NotNull String hash
    ) {
        return this.ibcQueryDenomTrace(hash, null);
    }

    public ListenableFuture<ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> ibcQueryDenomTrace(
            @NotNull String hash,
            BigInteger height
    ) {
        ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = ibcQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = ibcQueryStub;
        }
        return stub.denomTrace(ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest.newBuilder().setHash(hash).build());
    }

    public ListenableFuture<ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> ibcQueryDenomTraces(
            cosmos.base.query.v1beta1.Pagination.PageRequest pagination
    ) {
        return this.ibcQueryDenomTraces(pagination, null);
    }

    public ListenableFuture<ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> ibcQueryDenomTraces(
            cosmos.base.query.v1beta1.Pagination.PageRequest pagination,
            BigInteger height
    ) {
        ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = ibcQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = ibcQueryStub;
        }
        return stub.denomTraces(ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest.newBuilder().setPagination(pagination).build());
    }

    public ListenableFuture<ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> ibcQueryParams() {
        return this.ibcQueryParams(null);
    }

    public ListenableFuture<ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> ibcQueryParams(
            BigInteger height
    ) {
        ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = ibcQueryStub.withInterceptors(io.grpc.stub.MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = ibcQueryStub;
        }
        return stub.params(ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /********************************************************
     ***** Module 'ibc.applications.transfer' : MESSAGE
     *******************************************************/

    public ListenableFuture<ibc.applications.transfer.v1.Tx.MsgTransferResponse> ibcMsgTransfer(
            @NotNull String sender,
            @NotNull String receiver,
            @NotNull String router,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin token,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin fee,
            @NotNull String sourceChannel,
            @NotNull String sourcePort,
            @NotNull ibc.core.client.v1.Client.Height timeoutHeight
    ) {
        return ibcMsgStub.transfer(ibc.applications.transfer.v1.Tx.MsgTransfer.newBuilder().setSender(sender).setReceiver(receiver).setRouter(router).setToken(token).setFee(fee).setSourceChannel(sourceChannel).setSourcePort(sourcePort).setTimeoutHeight(timeoutHeight).build());
    }

    /**********************************************************
     ***** Module 'tendermint.abci.ABCIApplicationGrpc' : APP
     *********************************************************/

    public ListenableFuture<tendermint.abci.Types.ResponseApplySnapshotChunk> tmAbciAppApplySnapshotChunk(
            @NotNull String sender,
            @NotNull Integer index,
            @NotNull byte[] chunk
            ) {
        return tendermintAbciAppStub.applySnapshotChunk(tendermint.abci.Types.RequestApplySnapshotChunk.newBuilder()
                .setSender(sender)
                .setIndex(index)
                .setChunk(ByteString.copyFrom(chunk))
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseBeginBlock> tmAbciAppBeginBlock(
            @NotNull tendermint.types.Types.Header header,
            @NotNull tendermint.abci.Types.LastCommitInfo lastCommitInfo,
            @NotNull Iterable<? extends tendermint.abci.Types.Evidence> byzantineValidators,
            @NotNull byte[] hash
    ) {
        return tendermintAbciAppStub.beginBlock(tendermint.abci.Types.RequestBeginBlock.newBuilder()
                .setHeader(header)
                .setLastCommitInfo(lastCommitInfo)
                .addAllByzantineValidators(byzantineValidators)
                .setHash(ByteString.copyFrom(hash))
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseCheckTx> tmAbciAppCheckTx(
            @NotNull tendermint.abci.Types.CheckTxType type,
            @NotNull byte[] tx,
            @NotNull Iterable<? extends tendermint.abci.Types.Evidence> byzantineValidators,
            @NotNull byte[] hash
    ) {
        return tendermintAbciAppStub.checkTx(tendermint.abci.Types.RequestCheckTx.newBuilder()
                .setType(type)
                .setTx(ByteString.copyFrom(tx))
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseCommit> tmAbciAppCommit() {
        return tendermintAbciAppStub.commit(tendermint.abci.Types.RequestCommit.newBuilder().build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseDeliverTx> tmAbciAppDeliverTx(
            @NotNull byte[] tx
    ) {
        return tendermintAbciAppStub.deliverTx(tendermint.abci.Types.RequestDeliverTx.newBuilder()
                .setTx(ByteString.copyFrom(tx))
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseEcho> tmAbciAppEcho(
            @NotNull String message
    ) {
        return tendermintAbciAppStub.echo(tendermint.abci.Types.RequestEcho.newBuilder()
                .setMessage(message)
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseEndBlock> tmAbciAppEndBlock(
            @NotNull Long height
    ) {
        return tendermintAbciAppStub.endBlock(tendermint.abci.Types.RequestEndBlock.newBuilder()
                .setHeight(height)
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseFlush> tmAbciAppFlush() {
        return tendermintAbciAppStub.flush(tendermint.abci.Types.RequestFlush.newBuilder().build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseInfo> tmAbciAppInfo(
            @NotNull String version,
            @NotNull Long blockVersion,
            @NotNull Long p2pVersion
            ) {
        return tendermintAbciAppStub.info(tendermint.abci.Types.RequestInfo.newBuilder()
                .setVersion(version)
                .setBlockVersion(blockVersion)
                .setP2PVersion(p2pVersion)
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseInitChain> tmAbciAppInitChain(
            @NotNull String chainID,
            @NotNull Long initialHeight,
            @NotNull tendermint.abci.Types.ConsensusParams consensusParams,
            @NotNull Timestamp time,
            @NotNull byte[] appState,
            @NotNull Iterable<? extends tendermint.abci.Types.ValidatorUpdate> validators
            ) {
        return tendermintAbciAppStub.initChain(tendermint.abci.Types.RequestInitChain.newBuilder()
                .setChainId(chainID)
                .setInitialHeight(initialHeight)
                .setConsensusParams(consensusParams)
                .setTime(time)
                .setAppStateBytes(ByteString.copyFrom(appState))
                .addAllValidators(validators)
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseLoadSnapshotChunk> tmAbciAppLoadSnapshotChunk(
            @NotNull Long height,
            @NotNull Integer format,
            @NotNull Integer chunk
    ) {
        return tendermintAbciAppStub.loadSnapshotChunk(tendermint.abci.Types.RequestLoadSnapshotChunk.newBuilder()
                .setHeight(height)
                .setFormat(format)
                .setChunk(chunk)
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseListSnapshots> tmAbciAppListSnapshots() {
        return tendermintAbciAppStub.listSnapshots(tendermint.abci.Types.RequestListSnapshots.newBuilder().build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseOfferSnapshot> tmAbciAppOfferSnapshot(
            @NotNull tendermint.abci.Types.Snapshot snapshot,
            @NotNull byte[] appHash
    ) {
        return tendermintAbciAppStub.offerSnapshot(tendermint.abci.Types.RequestOfferSnapshot.newBuilder()
                .setSnapshot(snapshot)
                .setAppHash(ByteString.copyFrom(appHash))
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseQuery> tmAbciAppQuery(
            @NotNull Long height,
            @NotNull String path,
            @NotNull byte[] data,
            @NotNull Boolean prove
            ) {
        return tendermintAbciAppStub.query(tendermint.abci.Types.RequestQuery.newBuilder()
                .setHeight(height)
                .setPath(path)
                .setData(ByteString.copyFrom(data))
                .setProve(prove)
                .build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseSetOption> tmAbciAppSetOption(
            @NotNull String key,
            @NotNull String value
    ) {
        return tendermintAbciAppStub.setOption(tendermint.abci.Types.RequestSetOption.newBuilder()
                .setKey(key)
                .setValue(value)
                .build());
    }

    /**********************************************************
     ***** Module 'tendermint.rpc.grpc.BroadcastAPIGrpc' : APP
     *********************************************************/

    public ListenableFuture<tendermint.rpc.grpc.Types.ResponseBroadcastTx> tmRpcRequestBroadcastTx(
            @NotNull byte[] tx
    ) {
        return tendermintRpcBroadcastStub.broadcastTx(tendermint.rpc.grpc.Types.RequestBroadcastTx.newBuilder()
                .setTx(ByteString.copyFrom(tx))
                .build());
    }

    public ListenableFuture<tendermint.rpc.grpc.Types.ResponsePing> tmRpcRequestPing() {
        return tendermintRpcBroadcastStub.ping(tendermint.rpc.grpc.Types.RequestPing.newBuilder().build());
    }

    /********************************************************
     ***** Module 'fx.migrate' : QUERY
     *******************************************************/

    public ListenableFuture<fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountResponse> fxMigrateQueryMigrateCheckAccount(
            @NotNull String from,
            @NotNull String to
    ) {
        return fxMigrateQueryStub.migrateCheckAccount(fx.migrate.v1.QueryOuterClass.QueryMigrateCheckAccountRequest.newBuilder()
                .setFrom(from)
                .setTo(to)
                .build());
    }

    public ListenableFuture<fx.migrate.v1.QueryOuterClass.QueryMigrateRecordResponse> fxMigrateQueryMigrateRecord(
            @NotNull String address
    ) {
        return fxMigrateQueryStub.migrateRecord(fx.migrate.v1.QueryOuterClass.QueryMigrateRecordRequest.newBuilder()
                .setAddress(address)
                .build());
    }

    /********************************************************
     ***** Module 'fx.migrate' : MESSAGE
     *******************************************************/

    public ListenableFuture<fx.migrate.v1.Tx.MsgMigrateAccountResponse> fxMigrateMsgMigrateAccount(
            @NotNull String from,
            @NotNull String to,
            @NotNull String signature
    ) {
        return fxMigrateMsgStub.migrateAccount(fx.migrate.v1.Tx.MsgMigrateAccount.newBuilder()
                .setFrom(from)
                .setTo(to)
                .setSignature(signature)
                .build());
    }

    /********************************************************
     ***** Module 'fx.other' : MESSAGE
     *******************************************************/

    public ListenableFuture<fx.other.QueryOuterClass.GasPriceResponse> fxOtherQueryGasPrice() {
        return fxOtherQueryStub.gasPrice(fx.other.QueryOuterClass.GasPriceRequest.newBuilder().build());
    }

}