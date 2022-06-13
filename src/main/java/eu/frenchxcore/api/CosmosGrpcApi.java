package eu.frenchxcore.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import cosmos.base.query.v1beta1.Pagination;
import cosmos.staking.v1beta1.Staking;
import cosmos.vesting.v1beta1.Vesting;
import eu.frenchxcore.tools.LocalExecutor;
import eu.frenchxcore.tools.XLogger;
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
    private final cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub govQueryStubV2;
    private final cosmos.gov.v1beta2.MsgGrpc.MsgFutureStub govMsgStubV2;

    private final cosmos.group.v1beta1.QueryGrpc.QueryFutureStub groupQueryStub;
    private final cosmos.group.v1beta1.MsgGrpc.MsgFutureStub groupMsgStub;
    private final cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub mintQueryStub;
    private final cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub nftQueryStub;
    private final cosmos.nft.v1beta1.MsgGrpc.MsgFutureStub nftMsgStub;
    private final cosmos.params.v1beta1.QueryGrpc.QueryFutureStub paramsQueryStub;
    private final cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub slashingQueryStub;
    private final cosmos.slashing.v1beta1.MsgGrpc.MsgFutureStub slashingMsgStub;
    private final cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stakingQueryStub;
    private final cosmos.staking.v1beta1.MsgGrpc.MsgFutureStub stakingMsgStub;
    private final cosmos.upgrade.v1beta1.QueryGrpc.QueryFutureStub upgradeQueryStub;
    private final cosmos.vesting.v1beta1.MsgGrpc.MsgFutureStub vestingMsgStub;
    private final fx.gravity.v1.QueryGrpc.QueryFutureStub gravityQueryStub;
    private final fx.gravity.v1.MsgGrpc.MsgFutureStub gravityMsgStub;
    private final fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub crosschainQueryStub;
    private final fx.gravity.crosschain.v1.MsgGrpc.MsgFutureStub crosschainMsgStub;
    private final fx.ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub ibcQueryStub;
    private final fx.ibc.applications.transfer.v1.MsgGrpc.MsgFutureStub ibcMsgStub;
    private final fx.other.QueryGrpc.QueryFutureStub fxOtherQueryStub;

    private final cosmos.base.tendermint.v1beta1.ServiceGrpc.ServiceFutureStub tendermintServiceStub;
    private final cosmos.tx.v1beta1.ServiceGrpc.ServiceFutureStub txServiceStub;

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

        // https://docs.cosmos.network/master/modules/bank/
        bankQueryStub = cosmos.bank.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        bankMsgStub = cosmos.bank.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        crisisMsgStub = cosmos.crisis.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        distributionQueryStub = cosmos.distribution.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        distributionMsgStub = cosmos.distribution.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        evidenceQueryStub = cosmos.evidence.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        evidenceMsgStub = cosmos.evidence.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        feeGrantQueryStub = cosmos.feegrant.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        feegrantMsgStub = cosmos.feegrant.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        govQueryStubV1 = cosmos.gov.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        govQueryStubV2 = cosmos.gov.v1beta2.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        govMsgStubV1 = cosmos.gov.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        govMsgStubV2 = cosmos.gov.v1beta2.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        groupQueryStub = cosmos.group.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        groupMsgStub = cosmos.group.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        mintQueryStub = cosmos.mint.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        nftQueryStub = cosmos.nft.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        nftMsgStub = cosmos.nft.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        paramsQueryStub = cosmos.params.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        slashingQueryStub = cosmos.slashing.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        slashingMsgStub = cosmos.slashing.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        stakingQueryStub = cosmos.staking.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        stakingMsgStub = cosmos.staking.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        upgradeQueryStub = cosmos.upgrade.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        vestingMsgStub = cosmos.vesting.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        txServiceStub = cosmos.tx.v1beta1.ServiceGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        tendermintServiceStub = cosmos.base.tendermint.v1beta1.ServiceGrpc.newFutureStub(cosmosChannel).withExecutor(exec);

        gravityMsgStub = fx.gravity.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        gravityQueryStub = fx.gravity.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        crosschainMsgStub = fx.gravity.crosschain.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        crosschainQueryStub = fx.gravity.crosschain.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcMsgStub = fx.ibc.applications.transfer.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
        ibcQueryStub = fx.ibc.applications.transfer.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(exec);
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

    /********************************************************
     ***** Cosmos Module 'auth' : QUERY
     ***** https://docs.cosmos.network/master/modules/auth/
     *******************************************************/

    /**
     * 'authQueryAccounts' returns all the existing accounts until latest block height known.
     * @return First page only of all the existing accounts
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authQueryAccounts() {
        return this.authQueryAccounts(null, null);
    }

    /**
     * 'authQueryAccounts' returns all the existing accounts for the specified page until latest block height known.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all the existing accounts
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authQueryAccounts(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return authQueryAccounts(pageRequest, null);
    }

    /**
     * 'authQueryAccounts' returns all the existing accounts at specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all the existing accounts known at specified block height.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authQueryAccounts(
            BigInteger height
    ) {
        return authQueryAccounts(null, height);
    }

    /**
     * 'authQueryAccounts' returns all the existing accounts at specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all the existing accounts known at specified block height.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authQueryAccounts(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.auth.v1beta1.QueryAccountsRequest.Builder _builder = cosmos.auth.v1beta1.QueryAccountsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountResponse> authQueryAccount(
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
    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountResponse> authQueryAccount(
            @NotNull String address,
            BigInteger height
    ) {
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authQueryStub;
        }
        return stub.account(cosmos.auth.v1beta1.QueryAccountRequest.newBuilder().setAddress(address).build());
    }

    /**
     * 'authQueryBech32Prefix' returns the Bech32 address prefix for the blockchain until latest block height known.
     * @return the Bech32 address prefix for the blockchain at latest block height known.
     */
    public ListenableFuture<cosmos.auth.v1beta1.Bech32PrefixResponse> authQueryBech32Prefix() {
        return this.authQueryBech32Prefix(null);
    }

    /**
     * 'authQueryBech32Prefix' returns the Bech32 address prefix for the blockchain until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the Bech32 address prefix for the blockchain at specified block height.
     */
    public ListenableFuture<cosmos.auth.v1beta1.Bech32PrefixResponse> authQueryBech32Prefix(
            BigInteger height
    ) {
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authQueryStub;
        }
        return stub.bech32Prefix(cosmos.auth.v1beta1.Bech32PrefixRequest.newBuilder().build());
    }

    /**
     * 'authQueryModuleAccounts' returns all the existing module accounts until latest block height known.
     * @return all the existing module accounts until latest block height known.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryModuleAccountsResponse> authQueryModuleAccounts() {
        return this.authQueryModuleAccounts(null);
    }

    /**
     * 'authQueryModuleAccounts' returns all the existing module accounts until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return all the existing module accounts until specified block height.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryModuleAccountsResponse> authQueryModuleAccounts(
            BigInteger height
    ) {
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authQueryStub;
        }
        return stub.moduleAccounts(cosmos.auth.v1beta1.QueryModuleAccountsRequest.newBuilder().build());
    }

    /**
     * 'authQueryParams' queries all 'auth' module parameters until latest block height known.
     * @return all 'auth' module parameters until latest block height known.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryParamsResponse> authQueryParams() {
        return this.authQueryParams(null);
    }

    /**
     * 'authQueryParams' queries all 'auth' module parameters until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return all 'auth' module parameters until specified block height.
     */
    public ListenableFuture<cosmos.auth.v1beta1.QueryParamsResponse> authQueryParams(
            BigInteger height
    ) {
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authQueryStub;
        }
        return stub.params(cosmos.auth.v1beta1.QueryParamsRequest.newBuilder().build());
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
            stub = authzQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
            stub = authzQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authzQueryStub;
        }
        return stub.grants(_builder.setGranter(granter).setGrantee(grantee).build());
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
            @NotNull Iterable<? extends Any> messages
    ) {
        return authzMsgStub.exec(cosmos.authz.v1beta1.Tx.MsgExec.newBuilder().setGrantee(grantee).addAllMsgs(messages).build());
    }

    /********************************************************
     ***** Cosmos Module 'bank' : QUERY
     *******************************************************/

    /**
     * 'bankQueryAllBalances' queries all balances for the specified FX address until latest block height known.
     * @param address The specified FX address for which to query all balances.
     * @return The first page only of all balances of the specified FX address until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances(
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of all balances of the specified FX address until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances(
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
    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances(
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of all balances of the specified FX address until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances(
            @NotNull String address,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryAllBalancesRequest.Builder _builder = cosmos.bank.v1beta1.QueryAllBalancesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
    public ListenableFuture<cosmos.bank.v1beta1.QueryBalanceResponse> bankQueryBalance(
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
    public ListenableFuture<cosmos.bank.v1beta1.QueryBalanceResponse> bankQueryBalance(
            @NotNull String address,
            @NotNull String denom,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.balance(cosmos.bank.v1beta1.QueryBalanceRequest.newBuilder().setAddress(address).setDenom(denom).build());
    }

    /**
     * 'bankQueryDenomsMetadata' queries the coins' denominations metadata until latest block height known.
     * @return The first page only of coins' denominations metadata until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> bankQueryDenomsMetadata() {
        return this.bankQueryDenomsMetadata(null, null);
    }

    /**
     * 'bankQueryDenomsMetadata' queries the coins' denominations metadata until latest block height known.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of coins' denominations metadata until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> bankQueryDenomsMetadata(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.bankQueryDenomsMetadata(pageRequest, null);
    }

    /**
     * 'bankQueryDenomsMetadata' queries the coins' denominations metadata until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of coins' denominations metadata until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> bankQueryDenomsMetadata(
            BigInteger height
    ) {
        return this.bankQueryDenomsMetadata(null, height);
    }

    /**
     * 'bankQueryDenomsMetadata' queries the coins' denominations metadata until specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of coins' denominations metadata until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> bankQueryDenomsMetadata(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryDenomsMetadataRequest.Builder _builder = cosmos.bank.v1beta1.QueryDenomsMetadataRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.denomsMetadata(cosmos.bank.v1beta1.QueryDenomsMetadataRequest.newBuilder().build());
    }

    /**
     * 'bankQueryDenomMetadata' queries the specified coin denomination metadata until latest block height known.
     * @param denom The coin denomination for which the query the metadata.
     * @return The coin denomination metadata until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomMetadataResponse> bankQueryDenomMetadata(
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
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomMetadataResponse> bankQueryDenomMetadata(
            @NotNull String denom,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.denomMetadata(cosmos.bank.v1beta1.QueryDenomMetadataRequest.newBuilder().setDenom(denom).build());
    }

    /**
     * 'bankQueryDenomOwners' queries all owners of the specified coin denomination until latest block height known.
     * @param denom The coin denomination for which to query the owners.
     * @return The first page only of all owners of the specified coin denomination until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankQueryDenomOwners(
            @NotNull String denom
    ) {
        return this.bankQueryDenomOwners(denom, null, null);
    }

    /**
     * 'bankQueryDenomOwners' queries all owners of the specified coin denomination until latest block height known.
     * @param denom The coin denomination for which to query the owners.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of owners of the specified coin denomination until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankQueryDenomOwners(
            @NotNull String denom,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.bankQueryDenomOwners(denom, pageRequest, null);
    }

    /**
     * 'bankQueryDenomOwners' queries all owners of the specified coin denomination until specified block height.
     * @param denom The coin denomination for which to query the owners.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of owners of the specified coin denomination until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankQueryDenomOwners(
            @NotNull String denom,
            BigInteger height
    ) {
        return this.bankQueryDenomOwners(denom, null, height);
    }

    /**
     * 'bankQueryDenomOwners' queries all owners of the specified coin denomination until specified block height.
     * @param denom The coin denomination for which to query the owners.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of owners of the specified coin denomination until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankQueryDenomOwners(
            @NotNull String denom,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryDenomOwnersRequest.Builder _builder = cosmos.bank.v1beta1.QueryDenomOwnersRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.denomOwners(_builder.setDenom(denom).build());
    }

    /**
     * 'bankQueryParams' queries all 'bank' module parameters until latest block height known.
     * @return All 'bank' module parameters until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryParamsResponse> bankQueryParams() {
        return this.bankQueryParams(null);
    }

    /**
     * 'bankQueryParams' queries all 'bank' module parameters until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return All 'bank' module parameters until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryParamsResponse> bankQueryParams(
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.params(cosmos.bank.v1beta1.QueryParamsRequest.newBuilder().build());
    }

    /**
     * 'bankQuerySupplyOf' queries the supply of a coin denomination until latest block height known.
     * @param denom The coin denomination which supply is being queried.
     * @return All 'bank' module parameters until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QuerySupplyOfResponse> bankQuerySupplyOf(
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
    public ListenableFuture<cosmos.bank.v1beta1.QuerySupplyOfResponse> bankQuerySupplyOf(
            @NotNull String denom,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.supplyOf(cosmos.bank.v1beta1.QuerySupplyOfRequest.newBuilder().setDenom(denom).build());
    }

    /**
     * 'bankQueryTotalSupply' queries the total supply of all coin denominations until latest block height known.
     * @return The first page only of the total supply of all coin denominations until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankQueryTotalSupply() {
        return this.bankQueryTotalSupply(null, null);
    }

    /**
     * 'bankQueryTotalSupply' queries the total supply of all coin denominations until latest block height known.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return The specified page of the total supply of all coin denominations until latest block height known.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankQueryTotalSupply(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.bankQueryTotalSupply(pageRequest, null);
    }

    /**
     * 'bankQueryTotalSupply' queries the total supply of all coin denominations until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The first page only of the total supply of all coin denominations until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankQueryTotalSupply(
            BigInteger height
    ) {
        return this.bankQueryTotalSupply(null, height);
    }

    /**
     * 'bankQueryTotalSupply' queries the total supply of all coin denominations until specified block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return The specified page of the total supply of all coin denominations until specified block height.
     */
    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankQueryTotalSupply(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.bank.v1beta1.QueryTotalSupplyRequest.Builder _builder = cosmos.bank.v1beta1.QueryTotalSupplyRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.totalSupply(_builder.build());
    }

    /********************************************************
     ***** Cosmos Module 'bank' : MESSAGE
     *******************************************************/

    /**
     * Send coins from and to a series of different address. If any of the receiving addresses do not correspond
     * to an existing account, a new account is created.
     * The message will fail under the following conditions:
     * - Any of the coins do not have sending enabled
     * - Any of the to addresses are restricted
     * - Any of the coins are locked
     * - The inputs and outputs do not correctly correspond to one another
     * @param inputs Iterable of source FX addresses, coins and amounts
     * @param outputs Iterable of destination FX addresses, coins and amounts
     * @return the MsgMultiSend response.
     */
    public ListenableFuture<cosmos.bank.v1beta1.MsgMultiSendResponse> bankMsgMultiSend(
            @NotNull Iterable<? extends cosmos.bank.v1beta1.Bank.Input> inputs,
            @NotNull Iterable<? extends cosmos.bank.v1beta1.Bank.Output> outputs
    ) {
        cosmos.bank.v1beta1.MsgMultiSend.Builder _builder = cosmos.bank.v1beta1.MsgMultiSend.newBuilder();
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
    public ListenableFuture<cosmos.bank.v1beta1.MsgSendResponse> bankMsgSend(
            @NotNull String from,
            @NotNull String to,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount
    ) {
        return bankMsgStub.send(cosmos.bank.v1beta1.MsgSend.newBuilder().setFromAddress(from).setToAddress(to).addAmount(amount).build());
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
    public ListenableFuture<cosmos.bank.v1beta1.MsgSendResponse> bankMsgSend(
            @NotNull String from,
            @NotNull String to,
            @NotNull Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> amounts
    ) {
        return bankMsgStub.send(cosmos.bank.v1beta1.MsgSend.newBuilder().setFromAddress(from).setToAddress(to).addAllAmount(amounts).build());
    }

    /********************************************************
     ***** Cosmos Module 'crisis' : MESSAGE
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

    /********************************************************
     ***** Cosmos Module 'distribution' : QUERY
     *******************************************************/

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
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.communityPool(cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolRequest.newBuilder().build());
    }

    /**
     * 'distributionQueryDelegationRewards' queries the total rewards accrued by a delegator address until latest block height known.
     * @param delegatorAddress the delegator FX address.
     * @return the total rewards accrued by a delegator address until latest block height known
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionQueryDelegationRewards(
            @NotNull String delegatorAddress
    ) {
        return this.distributionQueryDelegationRewards(delegatorAddress, null);
    }

    /**
     * 'distributionQueryDelegationRewards' queries the total rewards accrued by a delegator address until specified block height.
     * @param delegatorAddress the delegator FX address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the total rewards accrued by a delegator address until specified block height
     */
    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionQueryDelegationRewards(
            @NotNull String delegatorAddress,
            BigInteger height
    ) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.delegationRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
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
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.validatorSlashes(_builder.setValidatorAddress(validatorAddress).build());
    }

    /********************************************************
     ***** Cosmos Module 'distribution' : MESSAGE
     *******************************************************/

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

    /********************************************************
     ***** Cosmos Module 'evidence' : QUERY
     *******************************************************/

    /**
     * 'evidenceQueryAllEvidence' queries all evidence until latest block height known.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
            stub = evidenceQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
        return evidenceQueryStub.evidence(cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceRequest.newBuilder().setEvidenceHash(ByteString.copyFrom(evidenceHash)).build());
    }

    /********************************************************
     ***** Cosmos Module 'evidence' : MESSAGE
     *******************************************************/

    /**
     * SubmitEvidence submits an arbitrary Evidence of misbehavior such as equivocation or counterfactual signing.
     * @param submitter The submitter FX address
     * @param evidence The submitted evidence
     * @return The submitEvidence response.
     */
    public ListenableFuture<cosmos.evidence.v1beta1.Tx.MsgSubmitEvidenceResponse> distributionMsgSubmitEvidence(
            String submitter,
            Any evidence
    ) {
        return evidenceMsgStub.submitEvidence(cosmos.evidence.v1beta1.Tx.MsgSubmitEvidence.newBuilder().setSubmitter(submitter).setEvidence(evidence).build());
    }

    /********************************************************
     ***** Cosmos Module 'feegrant' : QUERY
     *******************************************************/

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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
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
            stub = feeGrantQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
            stub = feeGrantQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = feeGrantQueryStub;
        }
        return stub.allowance(cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceRequest.newBuilder().setGrantee(grantee).setGranter(granter).build());
    }

    /********************************************************
     ***** Cosmos Module 'feegrant' : MESSAGE
     *******************************************************/

    /**
     * 'feegrantMsgGrantAllowance' grants fee allowance to the grantee on the granter's account with the provided expiration time.
     * @param granter the address of the user granting an allowance of their funds.
     * @param grantee the address of the user being granted an allowance of another user's funds.
     * @param allowance allowance can be any of {@see cosmos.feegrant.v1beta1.Feegrant.BasicAllowance}, {@see cosmos.feegrant.v1beta1.Feegrant.PeriodicAllowance}, {@see cosmos.feegrant.v1beta1.Feegrant.AllowedMsgAllowance} allowance.
     * @return the grant allowance response.
     */
    public ListenableFuture<cosmos.feegrant.v1beta1.Tx.MsgGrantAllowanceResponse> feegrantMsgGrantAllowance(
            @NotNull String granter,
            @NotNull String grantee,
            @NotNull Any allowance) {
        return feegrantMsgStub.grantAllowance(cosmos.feegrant.v1beta1.Tx.MsgGrantAllowance.newBuilder().setGranter(granter).setGrantee(grantee).setAllowance(allowance).build());
    }

    /**
     * 'feegrantMsgRevokeAllowance' revokes any fee allowance of granter's account that has been granted to the grantee.
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
     ***** Cosmos Module 'gov' : QUERY (v1beta1)
     *******************************************************/

    /**
     * 'govQueryDepositV1' queries single deposit information based on proposalID and depositAddr until latest block height known.
     * @param proposalId the proposal ID
     * @param depositor the depositor FX address
     * @return single deposit information based on proposalID and depositAddr until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositResponse> govQueryDepositV1(
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositResponse> govQueryDepositV1(
            @NotNull Long proposalId,
            @NotNull String depositor,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.deposit(cosmos.gov.v1beta1.QueryDepositRequest.newBuilder().setProposalId(proposalId).setDepositor(depositor).build());
    }

    /**
     * 'govQueryDepositsV1' queries all deposits of a single proposal until latest block height known.
     * @param proposalId the proposal ID
     * @return first page only of all deposits of a single proposal until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govQueryDepositsV1(
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return specified page of all deposits of a single proposal until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govQueryDepositsV1(
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govQueryDepositsV1(
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return specified page of all deposits of a single proposal until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govQueryDepositsV1(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryDepositsRequest.Builder _builder = cosmos.gov.v1beta1.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryParamsResponse> govQueryParamsV1(
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryParamsResponse> govQueryParamsV1(
            @NotNull String paramsType,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.params(cosmos.gov.v1beta1.QueryParamsRequest.newBuilder().setParamsType(paramsType).build());
    }

    /**
     * 'govQueryProposalV1' queries proposal details based on ProposalID at the latest block height known.
     * @param proposalId the proposal ID
     * @return proposal details based on ProposalID at the latest block height known
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalResponse> govQueryProposalV1(
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalResponse> govQueryProposalV1(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.proposal(cosmos.gov.v1beta1.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    /**
     * 'govQueryProposalsV1' queries all proposals until latest known block height.
     * @return First page only of all proposals until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1() {
        return this.govQueryProposalsV1(null, null, null, null, null);
    }

    /**
     * 'govQueryProposalsV1' queries all proposals until latest known block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all proposals until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryProposalsV1(null, null, null, pageRequest, null);
    }

    /**
     * 'govQueryProposalsV1' queries all proposals until specified block height.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all proposals until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all proposals based on given status, FX voter address or FX depositor address, until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryProposalsRequest.Builder _builder = cosmos.gov.v1beta1.QueryProposalsRequest.newBuilder();
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
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryTallyResultResponse> govQueryTallyResultV1(
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryTallyResultResponse> govQueryTallyResultV1(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.tallyResult(cosmos.gov.v1beta1.QueryTallyResultRequest.newBuilder().setProposalId(proposalId).build());
    }

    /**
     * 'govQueryVotesV1' queries votes of a given proposal at the latest known block height.
     * @param proposalId The proposal ID
     * @return First page only of votes of a given proposal at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govQueryVotesV1(
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of votes of a given proposal at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govQueryVotesV1(
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govQueryVotesV1(
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
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of votes of a given proposal at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govQueryVotesV1(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryVotesRequest.Builder _builder = cosmos.gov.v1beta1.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govQueryVoteV1(
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
    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govQueryVoteV1(
            @NotNull Long proposalId,
            @NotNull String voter,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.vote(cosmos.gov.v1beta1.QueryVoteRequest.newBuilder().setProposalId(proposalId).setVoter(voter).build());
    }

    /*************************************************************
     ***** Cosmos Module 'gov' : MESSAGE (v1beta1)
     ************************************************************/

    /**
     * 'govMsgSubmitProposalV1' defines a method to create new proposal given a content.
     * @param proposer Proposer FX address
     * @param content Any of cosmos.distribution.v1beta1.Distribution.CommunityPoolSpendProposal, cosmos.gov.v1beta1.Gov.TextProposal, cosmos.params.v1beta1.params.ParameterChangeProposal, fx.gravity.crosschain.v1.Crosschain.InitCrossChainParamsProposal, cosmos.upgrade.v1beta1.Upgrade.SoftwareUpgradeProposal, cosmos.upgrade.v1beta1.Upgrade.CancelSoftwareUpgradeProposal
     * @param deposits Coin amounts for deposits
     * @return the submitProposal response
     */
    public ListenableFuture<cosmos.gov.v1beta1.MsgSubmitProposalResponse> govMsgSubmitProposalV1(
            String proposer,
            Any content,
            Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits
    ) {
        return govMsgStubV1.submitProposal(cosmos.gov.v1beta1.MsgSubmitProposal.newBuilder().setProposer(proposer).setContent(content).addAllInitialDeposit(deposits).build());
    }

    /**
     * 'govMsgSubmitProposalV1' defines a method to create new proposal given a content.
     * @param proposer Proposer FX address
     * @param content Any of cosmos.distribution.v1beta1.Distribution.CommunityPoolSpendProposal, cosmos.gov.v1beta1.Gov.TextProposal, cosmos.params.v1beta1.params.ParameterChangeProposal, fx.gravity.crosschain.v1.Crosschain.InitCrossChainParamsProposal, cosmos.upgrade.v1beta1.Upgrade.SoftwareUpgradeProposal, cosmos.upgrade.v1beta1.Upgrade.CancelSoftwareUpgradeProposal
     * @param deposit Coin amount for deposit
     * @return the submitProposal response
     */
    public ListenableFuture<cosmos.gov.v1beta1.MsgSubmitProposalResponse> govMsgSubmitProposalV1(
            String proposer,
            Any content,
            cosmos.base.v1beta1.CoinOuterClass.Coin deposit
    ) {
        return govMsgStubV1.submitProposal(cosmos.gov.v1beta1.MsgSubmitProposal.newBuilder().setProposer(proposer).setContent(content).addInitialDeposit(deposit).build());
    }

    /**
     * 'govMsgDepositV1' defines a method to add deposit on a specific proposal.
     * @param proposalId The proposal ID
     * @param depositor The depositor FX address
     * @param deposits Coin amounts for deposits
     * @return the deposit response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.MsgDepositResponse> govMsgDepositV1(
            Long proposalId,
            String depositor,
            Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits
    ) {
        return govMsgStubV1.deposit(cosmos.gov.v1beta1.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAllAmount(deposits).build());
    }

    /**
     * 'govMsgDepositV1' defines a method to add deposit on a specific proposal.
     * @param proposalId The proposal ID
     * @param depositor The depositor FX address
     * @param deposit Coin amount for deposit
     * @return the deposit response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.MsgDepositResponse> govMsgDepositV1(
            Long proposalId,
            String depositor,
            cosmos.base.v1beta1.CoinOuterClass.Coin deposit
    ) {
        return govMsgStubV1.deposit(cosmos.gov.v1beta1.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAmount(deposit).build());
    }

    /**
     * 'govMsgVoteV1' defines a method to add a vote on a specific proposal.
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param option The vote option
     * @return the vote response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.MsgVoteResponse> govMsgVoteV1(
            Long proposalId,
            String voter,
            cosmos.gov.v1beta1.Gov.VoteOption option
    ) {
        return govMsgStubV1.vote(cosmos.gov.v1beta1.MsgVote.newBuilder().setProposalId(proposalId).setVoter(voter).setOption(option).build());
    }

    /**
     * 'govMsgVoteWeightedV1' defines a method to add a weighted vote on a specific proposal.
     * @Since cosmos-sdk 0.43
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param options The vote options
     * @return the voteWeighted response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.MsgVoteWeightedResponse> govMsgVoteWeightedV1(
            Long proposalId,
            String voter,
            Iterable<? extends cosmos.gov.v1beta1.Gov.WeightedVoteOption> options
    ) {
        return govMsgStubV1.voteWeighted(cosmos.gov.v1beta1.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addAllOptions(options).build());
    }

    /**
     * 'govMsgVoteWeightedV1' defines a method to add a weighted vote on a specific proposal.
     * @Since cosmos-sdk 0.43
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param option The vote option
     * @return the voteWeighted response.
     */
    public ListenableFuture<cosmos.gov.v1beta1.MsgVoteWeightedResponse> govMsgVoteWeightedV1(
            Long proposalId,
            String voter,
            cosmos.gov.v1beta1.Gov.WeightedVoteOption option
    ) {
        return govMsgStubV1.voteWeighted(cosmos.gov.v1beta1.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addOptions(option).build());
    }

    /********************************************************
     ***** Cosmos Module 'gov' : QUERY (v1beta2)
     *******************************************************/

    /**
     * 'govQueryDepositV2' queries single deposit information based on proposalID and depositAddr until latest block height known.
     * @param proposalId the proposal ID
     * @param depositor the depositor FX address
     * @return single deposit information based on proposalID and depositAddr until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> govQueryDepositV2(
            @NotNull Long proposalId,
            @NotNull String depositor
    ) {
        return this.govQueryDepositV2(proposalId, depositor, null);
    }

    /**
     * 'govQueryDepositV2' queries single deposit information based on proposalID and depositAddr until specified block height.
     * @param proposalId the proposal ID
     * @param depositor the depositor FX address
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return single deposit information based on proposalID and depositAddr until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> govQueryDepositV2(
            @NotNull Long proposalId,
            @NotNull String depositor,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.deposit(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest.newBuilder().setDepositor(depositor).setProposalId(proposalId).build());
    }

    /**
     * 'govQueryDepositsV2' queries all deposits of a single proposal until latest block height known.
     * @param proposalId the proposal ID
     * @return first page only of all deposits of a single proposal until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govQueryDepositsV2(
            @NotNull Long proposalId
    ) {
        return this.govQueryDepositsV2(proposalId, null, null);
    }

    /**
     * 'govQueryDepositsV2' queries all deposits of a single proposal until latest block height known.
     * @param proposalId the proposal ID
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return specified page of all deposits of a single proposal until latest block height known.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govQueryDepositsV2(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryDepositsV2(proposalId, pageRequest, null);
    }

    /**
     * 'govQueryDepositsV2' queries all deposits of a single proposal until specified block height.
     * @param proposalId the proposal ID
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return first page only of all deposits of a single proposal until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govQueryDepositsV2(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        return this.govQueryDepositsV2(proposalId, null, height);
    }

    /**
     * 'govQueryDepositsV2' queries all deposits of a single proposal until specified block height.
     * @param proposalId the proposal ID
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return specified page of all deposits of a single proposal until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govQueryDepositsV2(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.Builder _builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.deposits(_builder.setProposalId(proposalId).build());
    }

    /**
     * 'govQueryParamsV2' queries all parameters of the gov module at the latest block height known.
     * @param paramsType The params type ("voting", "tally" or "deposit")
     * @return all parameters of the gov module at the latest block height known
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> govQueryParamsV2(
            @NotNull String paramsType
    ) {
        return this.govQueryParamsV2(paramsType, null);
    }

    /**
     * 'govQueryParamsV2' queries all parameters of the gov module at the specified block height.
     * @param paramsType The params type ("voting", "tally" or "deposit")
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return all parameters of the gov module at the specified block height
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> govQueryParamsV2(
            @NotNull String paramsType,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.params(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest.newBuilder().setParamsType(paramsType).build());
    }

    /**
     * 'govQueryProposalV2' queries proposal details based on ProposalID at the latest block height known.
     * @param proposalId the proposal ID
     * @return proposal details based on ProposalID at the latest block height known
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> govQueryProposalV2(
            @NotNull Long proposalId
    ) {
        return this.govQueryProposalV2(proposalId, null);
    }

    /**
     * 'govQueryProposalV2' queries proposal details based on ProposalID at the specified block height.
     * @param proposalId the proposal ID
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return proposal details based on ProposalID at the specified block height
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> govQueryProposalV2(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.proposal(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    /**
     * 'govQueryProposalsV2' queries all proposals until latest known block height.
     * @return First page only of all proposals until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2() {
        return this.govQueryProposalsV2(null, null, null, null, null);
    }

    /**
     * 'govQueryProposalsV2' queries all proposals until latest known block height.
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all proposals until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2(
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryProposalsV2(null, null, null, pageRequest, null);
    }

    /**
     * 'govQueryProposalsV2' queries all proposals based on given status, FX voter address or FX depositor address, until latest known block height.
     * @param depositor The FX depositor address (optional)
     * @param voter The FX voter address (optional)
     * @param proposalStatus The proposal status (optional)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of all proposals based on given status, FX voter address or FX depositor address, until latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryProposalsV2(depositor, voter, proposalStatus, pageRequest, null);
    }

    /**
     * 'govQueryProposalsV2' queries all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     * @param depositor The FX depositor address (optional)
     * @param voter The FX voter address (optional)
     * @param proposalStatus The proposal status (optional)
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            BigInteger height
    ) {
        return this.govQueryProposalsV2(depositor, voter, proposalStatus, null, height);
    }

    /**
     * 'govQueryProposalsV2' queries all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     * @param depositor The FX depositor address (optional)
     * @param voter The FX voter address (optional)
     * @param proposalStatus The proposal status (optional)
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of all proposals based on given status, FX voter address or FX depositor address, until specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest.Builder _builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest.newBuilder();
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
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.proposals(_builder.build());
    }

    /**
     * 'govQueryTallyResultV2' queries the tally of a proposal vote at the latest known block height.
     * @param proposalId The proposal ID
     * @return the tally of a proposal vote at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> govQueryTallyResultV2(
            @NotNull Long proposalId
    ) {
        return this.govQueryTallyResultV2(proposalId, null);
    }

    /**
     * 'govQueryTallyResultV2' queries the tally of a proposal vote at the specified block height.
     * @param proposalId The proposal ID
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return the tally of a proposal vote at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> govQueryTallyResultV2(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.tallyResult(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest.newBuilder().setProposalId(proposalId).build());
    }

    /**
     * 'govQueryVotesV2' queries votes of a given proposal at the latest known block height.
     * @param proposalId The proposal ID
     * @return First page only of votes of a given proposal at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govQueryVotesV2(
            @NotNull Long proposalId
    ) {
        return this.govQueryVotesV2(proposalId, null, null);
    }

    /**
     * 'govQueryVotesV2' queries votes of a given proposal at the latest known block height.
     * @param proposalId The proposal ID
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @return Specified page of votes of a given proposal at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govQueryVotesV2(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryVotesV2(proposalId, pageRequest, null);
    }

    /**
     * 'govQueryVotesV2' queries votes of a given proposal at the specified block height.
     * @param proposalId The proposal ID
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return First page only of votes of a given proposal at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govQueryVotesV2(
            @NotNull Long proposalId,
            BigInteger height
    ) {
        return this.govQueryVotesV2(proposalId, null, height);
    }

    /**
     * 'govQueryVotesV2' queries votes of a given proposal at the specified block height.
     * @param proposalId The proposal ID
     * @param pageRequest pageRequest.key is a value returned in PageResponse.next_key to begin querying the next page most efficiently.
     *                    Only one of offset or key should be set. pageRequest.offset is a numeric offset that can be used when key is unavailable.
     *                    It is less efficient than using key. Only one of offset or key should be set. pageRequest.limit is the total number of
     *                    results to be returned in the result page. If left empty it will default to a value to be set by each app.
     *                    pagerequest.count_total is set to true to indicate that the result set should include a count of the total number of items
     *                    available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return Specified page of votes of a given proposal at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govQueryVotesV2(
            @NotNull Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.Builder _builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.votes(_builder.setProposalId(proposalId).build());
    }

    /**
     * 'govQueryVoteV2' queries vote information based on proposalID and voter address at the latest known block height.
     * @param proposalId The proposal ID.
     * @param voter The FX voter address.
     * @return vote information based on proposalID and voter address at the latest known block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govQueryVoteV2(
            @NotNull Long proposalId,
            @NotNull String voter
    ) {
        return this.govQueryVoteV2(proposalId, voter, null);
    }

    /**
     * 'govQueryVoteV2' queries vote information based on proposalID and voter address at the specified block height.
     * @param proposalId The proposal ID.
     * @param voter The FX voter address.
     * @param height Block height used for the query (Notice: depends on your node pruning configuration {@see app.toml configuration file}
     * @return vote information based on proposalID and voter address at the specified block height.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govQueryVoteV2(
            @NotNull Long proposalId,
            @NotNull String voter,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.vote(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.newBuilder().setProposalId(proposalId).setVoter(voter).build());
    }

    /*************************************************************
     ***** Cosmos Module 'gov' : MESSAGE (v1beta2)
     ************************************************************/

    /**
     * 'govMsgSubmitProposalV2' defines a method to create new proposal given messages.
     * @param proposer Proposer FX address
     * @param messages The messages
     * @param deposits Coin amounts for deposits
     * @return the submitProposal response
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposalResponse> govMsgSubmitProposalV2(
            @NotNull String proposer,
            @NotNull Iterable<? extends Any> messages,
            @NotNull Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits
    ) {
        return govMsgStubV2.submitProposal(cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposal.newBuilder().setProposer(proposer).addAllMessages(messages).addAllInitialDeposit(deposits).build());
    }

    /**
     * 'govMsgSubmitProposalV2' defines a method to create new proposal given messages.
     * @param proposer Proposer FX address
     * @param messages The messages to be voted for
     * @param deposit Coin amount for deposit
     * @return the submitProposal response
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposalResponse> govMsgSubmitProposalV2(
            @NotNull String proposer,
            @NotNull Iterable<? extends Any> messages,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin deposit
    ) {
        return govMsgStubV2.submitProposal(cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposal.newBuilder().setProposer(proposer).addAllMessages(messages).addInitialDeposit(deposit).build());
    }

    /**
     * 'govMsgSubmitProposalV2' defines a method to create new proposal given messages.
     * @param proposer Proposer FX address
     * @param message The message to be voted for
     * @param deposits Coin amounts for deposits
     * @return the submitProposal response
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposalResponse> govMsgSubmitProposalV2(
            @NotNull String proposer,
            @NotNull Any message,
            @NotNull Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits
    ) {
        return govMsgStubV2.submitProposal(cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposal.newBuilder().setProposer(proposer).addMessages(message).addAllInitialDeposit(deposits).build());
    }

    /**
     * 'govMsgSubmitProposalV2' defines a method to create new proposal given messages.
     * @param proposer Proposer FX address
     * @param message The message to be voted for
     * @param deposit Coin amount for deposit
     * @return the submitProposal response
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposalResponse> govMsgSubmitProposalV2(
            @NotNull String proposer,
            @NotNull Any message,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin deposit
    ) {
        return govMsgStubV2.submitProposal(cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposal.newBuilder().setProposer(proposer).addMessages(message).addInitialDeposit(deposit).build());
    }

    /**
     * 'govMsgDepositV2' defines a method to add deposit on a specific proposal.
     * @param proposalId The proposal ID
     * @param depositor The depositor FX address
     * @param deposits Coin amounts for deposits
     * @return the deposit response.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgDepositResponse> govMsgDepositV2(
            @NotNull Long proposalId,
            @NotNull String depositor,
            @NotNull Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits
    ) {
        return govMsgStubV2.deposit(cosmos.gov.v1beta2.CosmosGovTxProto.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAllAmount(deposits).build());
    }

    /**
     * 'govMsgDepositV2' defines a method to add deposit on a specific proposal.
     * @param proposalId The proposal ID
     * @param depositor The depositor FX address
     * @param deposit Coin amount for deposit
     * @return the deposit response.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgDepositResponse> govMsgDepositV2(
            @NotNull Long proposalId,
            @NotNull String depositor,
            @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin deposit
    ) {
        return govMsgStubV2.deposit(cosmos.gov.v1beta2.CosmosGovTxProto.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAmount(deposit).build());
    }

    /**
     * 'govMsgVoteV2' defines a method to add a vote on a specific proposal.
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param option The vote option
     * @return the vote response.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteResponse> govMsgVoteV2(
            @NotNull Long proposalId,
            @NotNull String voter,
            @NotNull cosmos.gov.v1beta2.Gov.VoteOption option) {
        return govMsgStubV2.vote(cosmos.gov.v1beta2.CosmosGovTxProto.MsgVote.newBuilder().setProposalId(proposalId).setVoter(voter).setOption(option).build());
    }

    /**
     * 'govMsgVoteWeightedV2' defines a method to add a weighted vote on a specific proposal.
     * @Since cosmos-sdk 0.43
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param options The vote options
     * @return the voteWeighted response.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteWeightedResponse> govMsgVoteWeightedV2(
            @NotNull Long proposalId,
            @NotNull String voter,
            @NotNull Iterable<? extends cosmos.gov.v1beta2.Gov.WeightedVoteOption> options
    ) {
        return govMsgStubV2.voteWeighted(cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addAllOptions(options).build());
    }

    /**
     * 'govMsgVoteWeightedV2' defines a method to add a weighted vote on a specific proposal.
     * @Since cosmos-sdk 0.43
     * @param proposalId The proposal ID
     * @param voter The voter FX address
     * @param option The vote option
     * @return the voteWeighted response.
     */
    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteWeightedResponse> govMsgVoteWeightedV2(
            @NotNull Long proposalId,
            @NotNull String voter,
            @NotNull cosmos.gov.v1beta2.Gov.WeightedVoteOption option
    ) {
        return govMsgStubV2.voteWeighted(cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addOptions(option).build());
    }

    /********************************************************
     ***** Cosmos Module 'group' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> groupQueryGroupAccountInfo(
            String address
    ) {
        return this.groupQueryGroupAccountInfo(address, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> groupQueryGroupAccountInfo(
            String address,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupAccountInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupQueryGroupAccountsByGroup(
            Long groupId
    ) {
        return this.groupQueryGroupAccountsByGroup(groupId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupQueryGroupAccountsByGroup(
            Long groupId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.groupQueryGroupAccountsByGroup(groupId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupQueryGroupAccountsByGroup(
            Long groupId,
            BigInteger height
    ) {
        return this.groupQueryGroupAccountsByGroup(groupId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupQueryGroupAccountsByGroup(
            Long groupId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.Builder _builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupAccountsByGroup(_builder.setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupQueryGroupAccountsByAdmin(
            String admin
    ) {
        return this.groupQueryGroupAccountsByAdmin(admin, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupQueryGroupAccountsByAdmin(
            String admin,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.groupQueryGroupAccountsByAdmin(admin, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupQueryGroupAccountsByAdmin(
            String admin,
            BigInteger height
    ) {
        return this.groupQueryGroupAccountsByAdmin(admin, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupQueryGroupAccountsByAdmin(
            String admin,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height
    ) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.Builder _builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupAccountsByAdmin(_builder.setAdmin(admin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupQueryGroupByAdmin(
            String admin
    ) {
        return this.groupQueryGroupByAdmin(admin, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupQueryGroupByAdmin(
            String admin,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.groupQueryGroupByAdmin(admin, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupQueryGroupByAdmin(
            String admin,
            BigInteger height
    ) {
        return this.groupQueryGroupByAdmin(admin, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupQueryGroupByAdmin(
            String admin,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.Builder _builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupsByAdmin(_builder.setAdmin(admin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupQueryGroupByMember(
            String memberAddress
    ) {
        return this.groupQueryGroupByMember(memberAddress, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupQueryGroupByMember(
            String memberAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.groupQueryGroupByMember(memberAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupQueryGroupByMember(
            String memberAddress,
            BigInteger height
    ) {
        return this.groupQueryGroupByMember(memberAddress, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupQueryGroupByMember(
            String memberAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberRequest.Builder _builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupsByMember(_builder.setAddress(memberAddress).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> groupQueryGroupAccountsByAdmin(
            Long groupId
    ) {
        return groupQueryStub.groupInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest.newBuilder().setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupQueryGroupMembers(
            Long groupId
    ) {
        return this.groupQueryGroupMembers(groupId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupQueryGroupMembers(
            Long groupId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.groupQueryGroupMembers(groupId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupQueryGroupMembers(
            Long groupId,
            BigInteger height
    ) {
        return this.groupQueryGroupMembers(groupId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupQueryGroupMembers(
            Long groupId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.Builder _builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupMembers(_builder.setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> groupQueryProposal(
            Long proposalId
    ) {
        return this.groupQueryProposal(proposalId, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> groupQueryProposal(
            Long proposalId,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.proposal(cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupQueryProposalsByGroupAccount(
            String groupAddress
    ) {
        return this.groupQueryProposalsByGroupAccount(groupAddress, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupQueryProposalsByGroupAccount(
            String groupAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.groupQueryProposalsByGroupAccount(groupAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupQueryProposalsByGroupAccount(
            String groupAddress,
            BigInteger height
    ) {
        return this.groupQueryProposalsByGroupAccount(groupAddress, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupQueryProposalsByGroupAccount(
            String groupAddress,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.Builder _builder = cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.proposalsByGroupAccount(_builder.setAddress(groupAddress).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> groupQueryVoteByProposalVoter(
            Long proposalId,
            String voter
    ) {
        return this.groupQueryVoteByProposalVoter(proposalId, voter, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> groupQueryVoteByProposalVoter(
            Long proposalId,
            String voter,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.voteByProposalVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest.newBuilder().setProposalId(proposalId).setVoter(voter).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupQueryVotesByProposal(
            Long proposalId
    ) {
        return this.groupQueryVotesByProposal(proposalId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupQueryVotesByProposal(
            Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.groupQueryVotesByProposal(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupQueryVotesByProposal(
            Long proposalId,
            BigInteger height
    ) {
        return this.groupQueryVotesByProposal(proposalId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupQueryVotesByProposal(
            Long proposalId,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.Builder _builder = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.votesByProposal(_builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupQueryVotesByVoter(
            String voter
    ) {
        return this.groupQueryVotesByVoter(voter, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupQueryVotesByVoter(
            String voter,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.groupQueryVotesByVoter(voter, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupQueryVotesByVoter(
            String voter,
            BigInteger height
    ) {
        return this.groupQueryVotesByVoter(voter, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupQueryVotesByVoter(
            String voter,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.Builder _builder = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.votesByVoter(_builder.setVoter(voter).build());
    }

    /********************************************************
     ***** Cosmos Module 'group' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateGroupResponse> groupMsgCreateGroup(
            String admin,
            byte[] metadata,
            Iterable<? extends cosmos.group.v1beta1.Types.Member> members
    ) {
        return groupMsgStub.createGroup(cosmos.group.v1beta1.Tx.MsgCreateGroup.newBuilder().setAdmin(admin).setMetadata(ByteString.copyFrom(metadata)).addAllMembers(members).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateGroupResponse> groupMsgCreateGroup(
            String admin,
            byte[] metadata,
            cosmos.group.v1beta1.Types.Member member
    ) {
        return groupMsgStub.createGroup(cosmos.group.v1beta1.Tx.MsgCreateGroup.newBuilder().setAdmin(admin).setMetadata(ByteString.copyFrom(metadata)).addMembers(member).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateGroupAccountResponse> groupMsgCreateGroupAccount(
            String admin,
            long groupId,
            byte[] metadata,
            Any decisionPolicy
    ) {
        return groupMsgStub.createGroupAccount(cosmos.group.v1beta1.Tx.MsgCreateGroupAccount.newBuilder().setAdmin(admin).setGroupId(groupId).setMetadata(ByteString.copyFrom(metadata)).setDecisionPolicy(decisionPolicy).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateProposalResponse> groupMsgCreateProposal(
            String address,
            Iterable<String> proposers,
            Iterable<? extends Any> messages
    ) {
        return groupMsgStub.createProposal(cosmos.group.v1beta1.Tx.MsgCreateProposal.newBuilder().setAddress(address).addAllProposers(proposers).addAllMsgs(messages).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateProposalResponse> groupMsgCreateProposal(
            String address,
            Iterable<String> proposers,
            Any message
    ) {
        return groupMsgStub.createProposal(cosmos.group.v1beta1.Tx.MsgCreateProposal.newBuilder().setAddress(address).addAllProposers(proposers).addMsgs(message).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateProposalResponse> groupMsgCreateProposal(
            String address,
            String proposer,
            Iterable<? extends Any> messages
    ) {
        return groupMsgStub.createProposal(cosmos.group.v1beta1.Tx.MsgCreateProposal.newBuilder().setAddress(address).addProposers(proposer).addAllMsgs(messages).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateProposalResponse> groupMsgCreateProposal(
            String address,
            String proposer,
            Any message
    ) {
        return groupMsgStub.createProposal(cosmos.group.v1beta1.Tx.MsgCreateProposal.newBuilder().setAddress(address).addProposers(proposer).addMsgs(message).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgVoteResponse> groupMsgVote(
            Long proposalId,
            String voter,
            cosmos.group.v1beta1.Types.Choice choice,
            cosmos.group.v1beta1.Tx.Exec exec,
            byte[] metadata
    ) {
        return groupMsgStub.vote(cosmos.group.v1beta1.Tx.MsgVote.newBuilder().setProposalId(proposalId).setVoter(voter).setChoice(choice).setExec(exec).setMetadata(ByteString.copyFrom(metadata)).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgExecResponse> groupMsgExec(
            Long proposalId,
            String signer
    ) {
        return groupMsgStub.exec(cosmos.group.v1beta1.Tx.MsgExec.newBuilder().setProposalId(proposalId).setSigner(signer).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountAdminResponse> groupMsgUpdateGroupAccountAdmin(
            String address,
            String admin,
            String newAdmin
    ) {
        return groupMsgStub.updateGroupAccountAdmin(cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountAdmin.newBuilder().setAdmin(admin).setNewAdmin(newAdmin).setAddress(address).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse> groupMsgUpdateGroupAccountDecisionPolicy(
            String address,
            String admin,
            Any decisionPolicy
    ) {
        return groupMsgStub.updateGroupAccountDecisionPolicy(cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountDecisionPolicy.newBuilder().setAdmin(admin).setAddress(address).setDecisionPolicy(decisionPolicy).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountMetadataResponse> groupMsgUpdateGroupAccountMetadata(
            String address,
            String admin,
            byte[] metadata
    ) {
        return groupMsgStub.updateGroupAccountMetadata(cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountMetadata.newBuilder().setAdmin(admin).setAddress(address).setMetadata(ByteString.copyFrom(metadata)).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupAdminResponse> groupMsgUpdateGroupAdmin(
            Long groupId,
            String admin,
            String newAdmin
    ) {
        return groupMsgStub.updateGroupAdmin(cosmos.group.v1beta1.Tx.MsgUpdateGroupAdmin.newBuilder().setGroupId(groupId).setAdmin(admin).setNewAdmin(newAdmin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupMembersResponse> groupMsgUpdateGroupMembers(
            Long groupId,
            String admin,
            Iterable<? extends cosmos.group.v1beta1.Types.Member> members
    ) {
        return groupMsgStub.updateGroupMembers(cosmos.group.v1beta1.Tx.MsgUpdateGroupMembers.newBuilder().setGroupId(groupId).setAdmin(admin).addAllMemberUpdates(members).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupMetadataResponse> groupMsgUpdateGroupMetadata(
            Long groupId,
            String admin,
            byte[] metadata
    ) {
        return groupMsgStub.updateGroupMetadata(cosmos.group.v1beta1.Tx.MsgUpdateGroupMetadata.newBuilder().setGroupId(groupId).setAdmin(admin).setMetadata(ByteString.copyFrom(metadata)).build());
    }

    /********************************************************
     ***** Cosmos Module 'mint' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsResponse> mintQueryAnnualProvisions() {
        return this.mintQueryAnnualProvisions(null);
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsResponse> mintQueryAnnualProvisions(BigInteger height) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = mintQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintQueryStub;
        }
        return stub.annualProvisions(cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryInflationResponse> mintQueryInflation() {
        return this.mintQueryInflation(null);
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryInflationResponse> mintQueryInflation(BigInteger height) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = mintQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintQueryStub;
        }
        return stub.inflation(cosmos.mint.v1beta1.QueryOuterClass.QueryInflationRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryParamsResponse> mintQueryParams() {
        return this.mintQueryParams(null);
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryParamsResponse> mintQueryParams(BigInteger height) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = mintQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintQueryStub;
        }
        return stub.params(cosmos.mint.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /********************************************************
     ***** Cosmos Module 'nft' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceResponse> nftQueryBalance(String owner) {
        return this.nftQueryBalance(owner, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceResponse> nftQueryBalance(String owner, String classId) {
        return this.nftQueryBalance(owner, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceResponse> nftQueryBalance(String owner, String classId, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceRequest.Builder _builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceRequest.newBuilder();
        if (classId != null) {
            _builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.balance(_builder.setOwner(owner).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassResponse> nftQueryClass(String classId) {
        return this.nftQueryClass(classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassResponse> nftQueryClass(String classId, BigInteger height) {
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.class_(cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassRequest.newBuilder().setClassId(classId).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftQueryClasses() {
        return this.nftQueryClasses(null, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftQueryClasses(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.nftQueryClasses(pageRequest, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftQueryClasses(BigInteger height) {
        return this.nftQueryClasses(null, height);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftQueryClasses(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesRequest.Builder _builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.classes(_builder.build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTResponse> nftQueryNft(String id, String classId) {
        return this.nftQueryNft(id, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTResponse> nftQueryNft(String id, String classId, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTRequest.Builder _builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTRequest.newBuilder();
        if (classId != null) {
            _builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.nFT(_builder.setId(id).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftQueryNftsOfClass(String owner, String classId) {
        return this.nftQueryNftsOfClass(owner, classId, null, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftQueryNftsOfClass(String owner, String classId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.nftQueryNftsOfClass(owner, classId, pageRequest, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftQueryNftsOfClass(String owner, String classId, BigInteger height) {
        return this.nftQueryNftsOfClass(owner, classId, null, height);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftQueryNftsOfClass(String owner, String classId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassRequest.Builder _builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.nFTsOfClass(_builder.setOwner(owner).setClassId(classId).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerResponse> nftQueryOwner(String id, String classId) {
        return this.nftQueryOwner(id, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerResponse> nftQueryOwner(String id, String classId, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerRequest.Builder _builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerRequest.newBuilder();
        if (classId != null) {
            _builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.owner(_builder.setId(id).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QuerySupplyResponse> nftQuerySupply(String classId) {
        return this.nftQuerySupply(classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QuerySupplyResponse> nftQuerySupply(String classId, BigInteger height) {
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.supply(cosmos.nft.v1beta1.CosmosNftQueryProto.QuerySupplyRequest.newBuilder().setClassId(classId).build());
    }

    /********************************************************
     ***** Cosmos Module 'params' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsQueryParams() {
        return this.paramsQueryParams(null, null);
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsQueryParams(String subspace) {
        return this.paramsQueryParams(subspace, null);
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsQueryParams(String subspace, Integer height) {
        cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.Builder _builder = cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder();
        if (subspace != null) {
            _builder.setSubspace(subspace);
        }
        cosmos.params.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = paramsQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = paramsQueryStub;
        }
        return stub.params(_builder.build());
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QuerySubspacesResponse> paramsQuerySubspaces() {
        return this.paramsQuerySubspaces(null);
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QuerySubspacesResponse> paramsQuerySubspaces(BigInteger height) {
        cosmos.params.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = paramsQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = paramsQueryStub;
        }
        return stub.subspaces(cosmos.params.v1beta1.QueryOuterClass.QuerySubspacesRequest.newBuilder().build());
    }

    /********************************************************
     ***** Cosmos Module 'slashing' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsResponse> slashingQueryParams() {
        return this.slashingQueryParams(null);
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsResponse> slashingQueryParams(BigInteger height) {
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = slashingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingQueryStub;
        }
        return stub.params(cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoResponse> slashingQuerySigningInfo(String consAddress) {
        return this.slashingQuerySigningInfo(consAddress, null);
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoResponse> slashingQuerySigningInfo(String consAddress, BigInteger height) {
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = slashingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingQueryStub;
        }
        return stub.signingInfo(cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoRequest.newBuilder().setConsAddress(consAddress).build());
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingQuerySigningInfos(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.slashingQuerySigningInfos(pageRequest, null);
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingQuerySigningInfos(BigInteger height) {
        return this.slashingQuerySigningInfos(null, height);
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingQuerySigningInfos(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.Builder _builder = cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = slashingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingQueryStub;
        }
        return stub.signingInfos(_builder.build());
    }

    /********************************************************
     ***** Cosmos Module 'staking' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationResponse> stakingQueryDelegation(String delegatorAddress, String validatorAddress) {
        return this.stakingQueryDelegation(delegatorAddress, validatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationResponse> stakingQueryDelegation(String delegatorAddress, String validatorAddress, BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegation(cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingQueryDelegatorDelegations(String delegatorAddress) {
        return this.stakingQueryDelegatorDelegations(delegatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingQueryDelegatorDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingQueryDelegatorDelegations(delegatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingQueryDelegatorDelegations(String delegatorAddress, BigInteger height) {
        return this.stakingQueryDelegatorDelegations(delegatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingQueryDelegatorDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorDelegations(_builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingQueryDelegatorUnbondingDelegations(String delegatorAddress) {
        return this.stakingQueryDelegatorUnbondingDelegations(delegatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingQueryDelegatorUnbondingDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingQueryDelegatorUnbondingDelegations(delegatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingQueryDelegatorUnbondingDelegations(String delegatorAddress, BigInteger height) {
        return this.stakingQueryDelegatorUnbondingDelegations(delegatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingQueryDelegatorUnbondingDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorUnbondingDelegations(_builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingQueryDelegatorValidators(String delegatorAddress) {
        return this.stakingQueryDelegatorValidators(delegatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingQueryDelegatorValidators(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingQueryDelegatorValidators(delegatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingQueryDelegatorValidators(String delegatorAddress, BigInteger height) {
        return this.stakingQueryDelegatorValidators(delegatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingQueryDelegatorValidators(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorValidators(_builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorResponse> stakingQueryDelegatorValidator(String delegatorAddress, String validatorAddress) {
        return this.stakingQueryDelegatorValidator(delegatorAddress, validatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorResponse> stakingQueryDelegatorValidator(String delegatorAddress, String validatorAddress, BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorValidator(cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> stakingQueryHistoricalInfo(Long height) {
        return stakingQueryStub.historicalInfo(cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest.newBuilder().setHeight(height).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryParamsResponse> stakingQueryParams() {
        return this.stakingQueryParams(null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryParamsResponse> stakingQueryParams(BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.params(cosmos.staking.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryPoolResponse> stakingQueryPool() {
        return this.stakingQueryPool(null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryPoolResponse> stakingQueryPool(BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.pool(cosmos.staking.v1beta1.QueryOuterClass.QueryPoolRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingQueryRedelegations(String delegatorAddress) {
        return this.stakingQueryRedelegations(delegatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingQueryRedelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingQueryRedelegations(delegatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingQueryRedelegations(String delegatorAddress, BigInteger height) {
        return this.stakingQueryRedelegations(delegatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingQueryRedelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.redelegations(_builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationResponse> stakingQueryUnbondingDelegation(String delegatorAddress, String validatorAddress) {
        return this.stakingQueryUnbondingDelegation(delegatorAddress, validatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationResponse> stakingQueryUnbondingDelegation(String delegatorAddress, String validatorAddress, BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.unbondingDelegation(cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> stakingQueryValidator(String validatorAddress) {
        return this.stakingQueryValidator(validatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> stakingQueryValidator(String validatorAddress, BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validator(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest.newBuilder().setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators() {
        return this.stakingQueryValidators(null, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(BigInteger height) {
        return this.stakingQueryValidators(null, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(String status) {
        return this.stakingQueryValidators(status, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(String status, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingQueryValidators(status, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(String status, BigInteger height) {
        return this.stakingQueryValidators(status, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        return this.stakingQueryValidators(null, pageRequest, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingQueryValidators(String status, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
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
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validators(_builder.build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingQueryValidatorDelegations(String validatorAddress) {
        return this.stakingQueryValidatorDelegations(validatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingQueryValidatorDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingQueryValidatorDelegations(validatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingQueryValidatorDelegations(String validatorAddress, BigInteger height) {
        return this.stakingQueryValidatorDelegations(validatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingQueryValidatorDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger heightValue) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsRequest.newBuilder()
                .setValidatorAddr(validatorAddress);
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (heightValue != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, heightValue.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validatorDelegations(_builder.build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingQueryValidatorUnbondingDelegations(validatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(String validatorAddress, BigInteger height) {
        return this.stakingQueryValidatorUnbondingDelegations(validatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.Builder _builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validatorUnbondingDelegations(_builder.setValidatorAddr(validatorAddress).build());
    }

    /********************************************************
     ***** Cosmos Module 'upgrade' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryAppliedPlanResponse> upgradeQueryAppliedPlan(String name) {
        return upgradeQueryStub.appliedPlan(cosmos.upgrade.v1beta1.QueryOuterClass.QueryAppliedPlanRequest.newBuilder().setName(name).build());
    }

    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryCurrentPlanResponse> upgradeQueryCurrentPlan() {
        return upgradeQueryStub.currentPlan(cosmos.upgrade.v1beta1.QueryOuterClass.QueryCurrentPlanRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryModuleVersionsResponse> upgradeQueryModuleVersions(String moduleName) {
        return upgradeQueryStub.moduleVersions(cosmos.upgrade.v1beta1.QueryOuterClass.QueryModuleVersionsRequest.newBuilder().setModuleName(moduleName).build());
    }

    /********************************************************
     ***** Cosmos Module 'nft' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftTxProto.MsgSendResponse> nftMsgSend(String id, String classId, String sender, String receiver) {
        return nftMsgStub.send(cosmos.nft.v1beta1.CosmosNftTxProto.MsgSend.newBuilder().setId(id).setClassId(classId).setSender(sender).setReceiver(receiver).build());
    }

    /********************************************************
     ***** Cosmos Module 'slashing' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.slashing.v1beta1.Tx.MsgUnjailResponse> slashingMsgUnjail(String validatorAddr) {
        return slashingMsgStub.unjail(cosmos.slashing.v1beta1.Tx.MsgUnjail.newBuilder().setValidatorAddr(validatorAddr).build());
    }

    /********************************************************
     ***** Cosmos Module 'staking' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgCreateValidatorResponse> stakingMsgCreateValidator(@NotNull Staking.CommissionRates commissionRates, String validatorAddress, String delegatorAddress, @NotNull Staking.Description description, @NotNull String minSelfDelegation, Any publicKey, @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin value) {
        cosmos.staking.v1beta1.Tx.MsgCreateValidator.Builder _builder = cosmos.staking.v1beta1.Tx.MsgCreateValidator.newBuilder();
        if (validatorAddress != null) {
            _builder.setValidatorAddress(validatorAddress);
        }
        if (delegatorAddress != null) {
            _builder.setDelegatorAddress(delegatorAddress);
        }
        if (publicKey != null) {
            _builder.setPubkey(publicKey);
        }
        return stakingMsgStub.createValidator(_builder
                        .setCommission(commissionRates)
                        .setDescription(description)
                        .setMinSelfDelegation(minSelfDelegation)
                        .setValue(value)
                .build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgEditValidatorResponse> stakingMsgEditValidator(String commissionRate, @NotNull String validatorAddress, String delegatorAddress, @NotNull Staking.Description description, @NotNull String minSelfDelegation, Any publicKey, @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin value) {
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

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgDelegateResponse> stakingMsgDelegate(@NotNull String validatorAddress, @NotNull String delegatorAddress, @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount) {
        return stakingMsgStub.delegate(cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder()
                .setDelegatorAddress(delegatorAddress)
                .setValidatorAddress(validatorAddress)
                .setAmount(amount)
                .build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgUndelegateResponse> stakingMsgUndelegate(@NotNull String validatorAddress, @NotNull String delegatorAddress, @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount) {
        return stakingMsgStub.undelegate(cosmos.staking.v1beta1.Tx.MsgUndelegate.newBuilder()
                .setDelegatorAddress(delegatorAddress)
                .setValidatorAddress(validatorAddress)
                .setAmount(amount)
                .build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgBeginRedelegateResponse> stakingMsgBeginRedelegate(@NotNull String validatorSrcAddress, @NotNull String validatorDstAddress, @NotNull String delegatorAddress, @NotNull cosmos.base.v1beta1.CoinOuterClass.Coin amount) {
        return stakingMsgStub.beginRedelegate(cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.newBuilder()
                .setDelegatorAddress(delegatorAddress)
                .setValidatorSrcAddress(validatorSrcAddress)
                .setValidatorDstAddress(validatorDstAddress)
                .setAmount(amount)
                .build());
    }

    /********************************************************
     ***** Cosmos Module 'vesting' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.vesting.v1beta1.Tx.MsgCreateVestingAccountResponse> vestingMsgCreateVestingAccount(boolean delayed, String fromAddress, String toAddress, long endTime, Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> amounts) {
        return vestingMsgStub.createVestingAccount(cosmos.vesting.v1beta1.Tx.MsgCreateVestingAccount.newBuilder()
                .setDelayed(delayed)
                .setFromAddress(fromAddress)
                .setToAddress(toAddress)
                .setEndTime(endTime)
                .addAllAmount(amounts)
                .build());
    }

    public ListenableFuture<cosmos.vesting.v1beta1.Tx.MsgCreatePeriodicVestingAccountResponse> vestingMsgCreatePeriodicVestingAccount(String fromAddress, String toAddress, long startTime, Iterable<? extends Vesting.Period> periods) {
        return vestingMsgStub.createPeriodicVestingAccount(cosmos.vesting.v1beta1.Tx.MsgCreatePeriodicVestingAccount.newBuilder()
                .setFromAddress(fromAddress)
                .setToAddress(toAddress)
                .addAllVestingPeriods(periods)
                .setStartTime(startTime)
                .build());
    }

    /********************************************************
     ***** Cosmos Module 'tx' : SERVICE
     *******************************************************/

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxResponse> txBroadcastTx(cosmos.tx.v1beta1.ServiceOuterClass.BroadcastMode mode, byte[] txBytes) {;
        return txServiceStub.broadcastTx(cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxRequest.newBuilder().setMode(mode).setTxBytes(ByteString.copyFrom(txBytes)).build());
    }

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxResponse> txGetTx(String txHash) {
        return txServiceStub.getTx(cosmos.tx.v1beta1.ServiceOuterClass.GetTxRequest.newBuilder().setHash(txHash).build());
    }

    /**
     * Cosmos events are described here :
     * Bank:            https://github.com/cosmos/cosmos-sdk/blob/main/x/bank/spec/04_events.md
     * Crisis:          https://github.com/cosmos/cosmos-sdk/blob/main/x/crisis/spec/03_events.md
     * Distribution:    https://github.com/cosmos/cosmos-sdk/blob/main/x/distribution/spec/06_events.md
     * Evidence:        https://github.com/cosmos/cosmos-sdk/blob/main/x/evidence/spec/04_events.md
     * Feegrant:        https://github.com/cosmos/cosmos-sdk/blob/main/x/feegrant/spec/04_events.md
     * Gov:             https://github.com/cosmos/cosmos-sdk/blob/main/x/gov/spec/04_events.md
     * Group:           https://github.com/cosmos/cosmos-sdk/blob/main/x/group/spec/04_events.md
     * Mint:            https://github.com/cosmos/cosmos-sdk/blob/main/x/mint/spec/05_events.md
     * Slashing:        https://github.com/cosmos/cosmos-sdk/blob/main/x/slashing/spec/06_events.md
     * Staking:         https://github.com/cosmos/cosmos-sdk/blob/main/x/staking/spec/07_events.md
     */

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventResponse> txGetTxsEvent(String events, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return txServiceStub.getTxsEvent(_builder.addEvents(events).build());
    }

    /**
     * Cosmos events are described here :
     * Bank:            https://github.com/cosmos/cosmos-sdk/blob/main/x/bank/spec/04_events.md
     * Crisis:          https://github.com/cosmos/cosmos-sdk/blob/main/x/crisis/spec/03_events.md
     * Distribution:    https://github.com/cosmos/cosmos-sdk/blob/main/x/distribution/spec/06_events.md
     * Evidence:        https://github.com/cosmos/cosmos-sdk/blob/main/x/evidence/spec/04_events.md
     * Feegrant:        https://github.com/cosmos/cosmos-sdk/blob/main/x/feegrant/spec/04_events.md
     * Gov:             https://github.com/cosmos/cosmos-sdk/blob/main/x/gov/spec/04_events.md
     * Group:           https://github.com/cosmos/cosmos-sdk/blob/main/x/group/spec/04_events.md
     * Mint:            https://github.com/cosmos/cosmos-sdk/blob/main/x/mint/spec/05_events.md
     * Slashing:        https://github.com/cosmos/cosmos-sdk/blob/main/x/slashing/spec/06_events.md
     * Staking:         https://github.com/cosmos/cosmos-sdk/blob/main/x/staking/spec/07_events.md
     */

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventResponse> txGetTxsEvent(Iterable<String> events, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return txServiceStub.getTxsEvent(_builder.addAllEvents(events).build());
    }

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.SimulateResponse> txSimulate(cosmos.tx.v1beta1.TxOuterClass.Tx tx) {
        cosmos.tx.v1beta1.ServiceOuterClass.SimulateRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.SimulateRequest.newBuilder();
        return txServiceStub.simulate(_builder.setTx(tx).build());
    }

    /********************************************************
     ***** Cosmos Module 'tendermint' : SERVICE
     *******************************************************/

    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetBlockByHeightResponse> tmGetBlockByHeight(long height) {
        return tendermintServiceStub.getBlockByHeight(cosmos.base.tendermint.v1beta1.Query.GetBlockByHeightRequest.newBuilder().setHeight(height).build());
    }

    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetLatestBlockResponse> tmGetLatestBlock() {
        return tendermintServiceStub.getLatestBlock(cosmos.base.tendermint.v1beta1.Query.GetLatestBlockRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetResponse> tmGetLatestValidatorSet(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetRequest.Builder _builder = cosmos.base.tendermint.v1beta1.Query.GetLatestValidatorSetRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return tendermintServiceStub.getLatestValidatorSet(_builder.build());
    }

    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetNodeInfoResponse> tmGetNodeInfo() {
        return tendermintServiceStub.getNodeInfo(cosmos.base.tendermint.v1beta1.Query.GetNodeInfoRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetSyncingResponse> tmGetSyncing() {
        return tendermintServiceStub.getSyncing(cosmos.base.tendermint.v1beta1.Query.GetSyncingRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.base.tendermint.v1beta1.Query.GetValidatorSetByHeightResponse> tmGetValidatorSetByHeight(long height) {
        return tendermintServiceStub.getValidatorSetByHeight(cosmos.base.tendermint.v1beta1.Query.GetValidatorSetByHeightRequest.newBuilder().setHeight(height).build());
    }

    /********************************************************
     ***** Module 'gravity' : QUERY
     *******************************************************/

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> gravityQueryBatchConfirm(String address, String contractAddress, long nonce) {
        return this.gravityQueryBatchConfirm(address, contractAddress, nonce, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmResponse> gravityQueryBatchConfirm(String address, String contractAddress, long nonce, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.batchConfirm(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmRequest.newBuilder().setAddress(address).setContractAddress(contractAddress).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> gravityQueryBatchConfirms(String contractAddress, long nonce) {
        return this.gravityQueryBatchConfirms(contractAddress, nonce, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsResponse> gravityQueryBatchConfirms(String contractAddress, long nonce, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.batchConfirms(fx.gravity.v1.QueryOuterClass.QueryBatchConfirmsRequest.newBuilder().setContractAddress(contractAddress).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> gravityQueryBatchFees() {
        return this.gravityQueryBatchFees(null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchFeeResponse> gravityQueryBatchFees(BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.batchFees(fx.gravity.v1.QueryOuterClass.QueryBatchFeeRequest.newBuilder().build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryParamsResponse> gravityQueryParams() {
        return this.gravityQueryParams(null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryParamsResponse> gravityQueryParams(BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.params(fx.gravity.v1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> gravityQueryBatchRequestByNonce(String contractAddress, long nonce) {
        return this.gravityQueryBatchRequestByNonce(contractAddress, nonce, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> gravityQueryBatchRequestByNonce(String contractAddress, long nonce, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.batchRequestByNonce(fx.gravity.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.newBuilder().setContractAddress(contractAddress).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> gravityQueryCurrentValset() {
        return this.gravityQueryCurrentValset(null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryCurrentValsetResponse> gravityQueryCurrentValset(BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.currentValset(fx.gravity.v1.QueryOuterClass.QueryCurrentValsetRequest.newBuilder().build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> gravityQueryDenomToERC20(String denom) {
        return this.gravityQueryDenomToERC20(denom, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Response> gravityQueryDenomToERC20(String denom, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.denomToERC20(fx.gravity.v1.QueryOuterClass.QueryDenomToERC20Request.newBuilder().setDenom(denom).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> gravityQueryERC20ToDenom(String erc20) {
        return this.gravityQueryERC20ToDenom(erc20, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomResponse> gravityQueryERC20ToDenom(String erc20, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.eRC20ToDenom(fx.gravity.v1.QueryOuterClass.QueryERC20ToDenomRequest.newBuilder().setErc20(erc20).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> gravityQueryDelegateKeyByEth(String ethAddress) {
        return this.gravityQueryDelegateKeyByEth(ethAddress, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthResponse> gravityQueryDelegateKeyByEth(String ethAddress, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.getDelegateKeyByEth(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByEthRequest.newBuilder().setEthAddress(ethAddress).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> gravityQueryDelegateKeyByOrchestrator(String orchestratorAddress) {
        return this.gravityQueryDelegateKeyByOrchestrator(orchestratorAddress, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorResponse> gravityQueryDelegateKeyByOrchestrator(String orchestratorAddress, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.getDelegateKeyByOrchestrator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByOrchestratorRequest.newBuilder().setOrchestratorAddress(orchestratorAddress).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> gravityQueryDelegateKeyByValidator(String validatorAddress) {
        return this.gravityQueryDelegateKeyByValidator(validatorAddress, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorResponse> gravityQueryDelegateKeyByValidator(String validatorAddress, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.getDelegateKeyByValidator(fx.gravity.v1.QueryOuterClass.QueryDelegateKeyByValidatorRequest.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> gravityQueryIbcSequenceHeight(String sourceChannel, String sourcePort, long sequence) {
        return this.gravityQueryIbcSequenceHeight(sourceChannel, sourcePort, sequence, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> gravityQueryIbcSequenceHeight(String sourceChannel, String sourcePort, long sequence, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.getIbcSequenceHeightByChannel(fx.gravity.v1.QueryOuterClass.QueryIbcSequenceHeightRequest.newBuilder().setSourceChannel(sourceChannel).setSourcePort(sourcePort).setSequence(sequence).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> gravityQueryPendingSendToEth(String senderAddress) {
        return this.gravityQueryPendingSendToEth(senderAddress, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthResponse> gravityQueryPendingSendToEth(String senderAddress, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.getPendingSendToEth(fx.gravity.v1.QueryOuterClass.QueryPendingSendToEthRequest.newBuilder().setSenderAddress(senderAddress).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> gravityQueryLastEventBlockHeightByAddr(String address) {
        return this.gravityQueryLastEventBlockHeightByAddr(address);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> gravityQueryLastEventBlockHeightByAddr(String address, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.lastEventBlockHeightByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> gravityQueryLastEventNonceByAddr(String address) {
        return this.gravityQueryLastEventNonceByAddr(address, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> gravityQueryLastEventNonceByAddr(String address, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.lastEventNonceByAddr(fx.gravity.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastObservedEthBlockHeightResponse> gravityQueryLastObservedEthBlockHeight() {
        return this.gravityQueryLastObservedEthBlockHeight(null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastObservedEthBlockHeightResponse> gravityQueryLastObservedEthBlockHeight(BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.lastObservedEthBlockHeight(fx.gravity.v1.QueryOuterClass.QueryLastObservedEthBlockHeightRequest.newBuilder().build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> gravityQueryLastPendingBatchRequestByAddr(String address) {
        return this.gravityQueryLastPendingBatchRequestByAddr(address, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> gravityQueryLastPendingBatchRequestByAddr(String address, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.lastPendingBatchRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> gravityQueryLastPendingValsetRequestByAddr(String address) {
        return this.gravityQueryLastPendingValsetRequestByAddr(address, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrResponse> gravityQueryLastPendingValsetRequestByAddr(String address, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.lastPendingValsetRequestByAddr(fx.gravity.v1.QueryOuterClass.QueryLastPendingValsetRequestByAddrRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> gravityQueryLastValsetRequests() {
        return this.gravityQueryLastValsetRequests(null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsResponse> gravityQueryLastValsetRequests(BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.lastValsetRequests(fx.gravity.v1.QueryOuterClass.QueryLastValsetRequestsRequest.newBuilder().build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> gravityQueryOutgoingTxBatches() {
        return this.gravityQueryOutgoingTxBatches(null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> gravityQueryOutgoingTxBatches(BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.outgoingTxBatches(fx.gravity.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.newBuilder().build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> gravityQueryValsetConfirm(String address, long nonce) {
        return this.gravityQueryValsetConfirm(address, nonce, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmResponse> gravityQueryValsetConfirm(String address, long nonce, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.valsetConfirm(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmRequest.newBuilder().setAddress(address).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> gravityQueryValsetConfirmsByNonce(long nonce) {
        return this.gravityQueryValsetConfirmsByNonce(nonce, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceResponse> gravityQueryValsetConfirmsByNonce(long nonce, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.valsetConfirmsByNonce(fx.gravity.v1.QueryOuterClass.QueryValsetConfirmsByNonceRequest.newBuilder().setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> gravityQueryValsetRequest(long nonce) {
        return this.gravityQueryValsetRequest(nonce, null);
    }

    public ListenableFuture<fx.gravity.v1.QueryOuterClass.QueryValsetRequestResponse> gravityQueryValsetRequest(long nonce, BigInteger height) {
        fx.gravity.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = gravityQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = gravityQueryStub;
        }
        return stub.valsetRequest(fx.gravity.v1.QueryOuterClass.QueryValsetRequestRequest.newBuilder().setNonce(nonce).build());
    }

    /********************************************************
     ***** Module 'gravity' : MESSAGE
     *******************************************************/

    /**
     * This call allows the sender (and only the sender) to cancel a given MsgSendToEth and receive a refund of the tokens
     * @param sender The sender address
     * @param transactionId The transaction ID
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgCancelSendToEthResponse> gravityMsgCancelSendToEth(String sender, long transactionId) {
        return gravityMsgStub.cancelSendToEth(fx.gravity.v1.Msgs.MsgCancelSendToEth.newBuilder().setSender(sender).setTransactionId(transactionId).build());
    }

    /**
     * When validators observe a MsgRequestBatch, they form a batch by ordering transactions currently in the txqueue in order of highest to lowest fee,
     * cutting off when the batch either reaches a hardcoded maximum size (to be decided, probably around 100) or when transactions stop being profitable
     * (TODO determine this without nondeterminism)
     * This message includes the batch as well as an Ethereum signature over this batch by the validator
     * @param ethSigner
     * @param nonce
     * @param orchestrator
     * @param signature
     * @param tokenContract
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgConfirmBatchResponse> gravityMsgConfirmBatch(String ethSigner, long nonce, String orchestrator, String signature, String tokenContract) {
        return gravityMsgStub.confirmBatch(fx.gravity.v1.Msgs.MsgConfirmBatch.newBuilder().setEthSigner(ethSigner).setNonce(nonce).setOrchestrator(orchestrator).setSignature(signature).setTokenContract(tokenContract).build());
    }

    /**
     * EthereumBridgeDepositClaim : When more than 66% of the active validator set has claimed to have seen the deposit enter the ethereum blockchain,
     * coins are issued to the Cosmos address in question.
     * @param amount
     * @param ethSender
     * @param blockHeight
     * @param eventNonce
     * @param orchestrator
     * @param fxReceiver
     * @param tokenContract
     * @param targetIbc
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgDepositClaimResponse> gravityMsgDespositClaim(String amount, String ethSender, long blockHeight, long eventNonce, String orchestrator, String fxReceiver, String tokenContract, String targetIbc) {
        return gravityMsgStub.depositClaim(fx.gravity.v1.Msgs.MsgDepositClaim.newBuilder().setAmount(amount).setTokenContract(tokenContract).setBlockHeight(blockHeight).setEthSender(ethSender).setEventNonce(eventNonce).setFxReceiver(fxReceiver).setOrchestrator(orchestrator).setTargetIbc(targetIbc).build());
    }

    /**
     *
     * @param blockHeight
     * @param decimals
     * @param eventNonce
     * @param orchestrator
     * @param name
     * @param symbol
     * @param tokenContract
     * @param targetIbc
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgFxOriginatedTokenClaimResponse> gravityMsgDespositClaim(long blockHeight, long decimals, long eventNonce, String orchestrator, String name, String symbol, String tokenContract, String targetIbc) {
        return gravityMsgStub.fxOriginatedTokenClaim(fx.gravity.v1.Msgs.MsgFxOriginatedTokenClaim.newBuilder().setBlockHeight(blockHeight).setDecimals(decimals).setEventNonce(eventNonce).setName(name).setOrchestrator(orchestrator).setSymbol(symbol).setTokenContract(tokenContract).build());
    }

    /**
     * This is a message anyone can send that requests a batch of transactions to send across the bridge be created for whatever block height this message is
     * included in. This acts as a coordination point, the handler for this message looks at the AddToOutgoingPool tx's in the store and generates a batch, also
     * available in the store tied to this message. The validators then grab this batch, sign it, submit the signatures with a MsgConfirmBatch before a relayer
     * can finally submit the batch.
     * @param denom
     * @param feeReceive
     * @param minimumFee
     * @param sender
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgRequestBatchResponse> gravityMsgRequestBatch(String denom, String feeReceive, String minimumFee, String sender) {
        return gravityMsgStub.requestBatch(fx.gravity.v1.Msgs.MsgRequestBatch.newBuilder().setDenom(denom).setFeeReceive(feeReceive).setMinimumFee(minimumFee).setSender(sender).build());
    }

    /**
     * This is the message that a user calls when they want to bridge an asset. It will later be removed when it is included in a batch and successfully
     * submitted tokens are removed from the users balance immediately
     * @param sender The FX sender address
     * @param ethDest The ERC20 destination address
     * @param amount the coin to send across the bridge (note the restriction that this is a single coin not a set of coins that is normal in other Cosmos messages)
     * @param bridgeFee the fee paid for the bridge, distinct from the fee paid to the chain to actually send this message in the first place. So a successful send has two layers of fees for the user.
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgSendToEthResponse> gravityMsgSendToEth(String sender, String ethDest, cosmos.base.v1beta1.CoinOuterClass.Coin amount, cosmos.base.v1beta1.CoinOuterClass.Coin bridgeFee) {
        return gravityMsgStub.sendToEth(fx.gravity.v1.Msgs.MsgSendToEth.newBuilder().setSender(sender).setEthDest(ethDest).setAmount(amount).setBridgeFee(bridgeFee).build());
    }

    /**
     * This message allows validators to delegate their voting responsibilities to a given key. This key is then used as an optional authentication method
     * for signing oracle claims
     * @param ethAddress This is a hex encoded 0x Ethereum public key that will be used by this validator on Ethereum
     * @param orchestrator The orchestrator field is a fx1... string  (i.e. sdk.AccAddress) that references the key that is being delegated to
     * @param validator The validator field is a fxvaloper1... string (i.e. sdk.ValAddress) that references a validator in the active set
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgSetOrchestratorAddressResponse> gravityMsgSendToEth(String ethAddress, String orchestrator, String validator) {
        return gravityMsgStub.setOrchestratorAddress(fx.gravity.v1.Msgs.MsgSetOrchestratorAddress.newBuilder().setEthAddress(ethAddress).setOrchestrator(orchestrator).setValidator(validator).build());
    }

    /**
     * This is the message sent by the validators when they wish to submit their signatures over the validator set at a given block height. A validator must
     * first call MsgSetEthAddress to set their Ethereum address to be used for signing.
     * Then someone (anyone) must make a ValsetRequest, the request is essentially a messaging mechanism to determine which block all validators should
     * submit signatures over.
     * Finally, validators sign the validator set, powers, and Ethereum addresses of the entire validator set at the height of a ValsetRequest and submit
     * that signature with this message.
     * If a sufficient number of validators (66% of voting power)
     * (A) have set Ethereum addresses and
     * (B) submit ValsetConfirm messages with their signatures
     * it is then possible for anyone to view these signatures in the chain store and submit them to Ethereum to update the validator set
     * @param ethAddress
     * @param orchestrator
     * @param nonce
     * @param signature
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgValsetConfirmResponse> gravityMsgValsetConfirm(String ethAddress, String orchestrator, long nonce, String signature) {
        return gravityMsgStub.valsetConfirm(fx.gravity.v1.Msgs.MsgValsetConfirm.newBuilder().setEthAddress(ethAddress).setOrchestrator(orchestrator).setNonce(nonce).setSignature(signature).build());
    }

    /**
     * This informs the Cosmos module that a validator set has been updated.
     * @param orchestrator
     * @param blockHeight
     * @param eventNonce
     * @param valsetNonce
     * @param members
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgValsetUpdatedClaimResponse> gravityMsgValsetUpdatedClaim(String orchestrator, long blockHeight, long eventNonce, long valsetNonce, Iterable<? extends fx.gravity.v1.Types.BridgeValidator> members) {
        return gravityMsgStub.valsetUpdateClaim(fx.gravity.v1.Msgs.MsgValsetUpdatedClaim.newBuilder().setOrchestrator(orchestrator).setBlockHeight(blockHeight).setEventNonce(eventNonce).setValsetNonce(valsetNonce).addAllMembers(members).build());
    }

    /**
     * WithdrawClaim claims that a batch of withdrawal operations on the bridge contract was executed.
     * @param orchestrator
     * @param blockHeight
     * @param eventNonce
     * @param batchNonce
     * @param tokenContract
     * @return
     */
    public ListenableFuture<fx.gravity.v1.Msgs.MsgWithdrawClaimResponse> gravityMsgWithdrawClaim(String orchestrator, long blockHeight, long eventNonce, long batchNonce, String tokenContract) {
        return gravityMsgStub.withdrawClaim(fx.gravity.v1.Msgs.MsgWithdrawClaim.newBuilder().setOrchestrator(orchestrator).setBatchNonce(batchNonce).setBlockHeight(blockHeight).setEventNonce(eventNonce).setTokenContract(tokenContract).build());
    }

    /********************************************************
     ***** Module 'crosschain' : QUERY
     *******************************************************/

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> crosschainQueryBatchConfirm(String chainName, String orchestratorAddress, String tokenContract, long nonce) {
        return this.crosschainQueryBatchConfirm(chainName, orchestratorAddress, tokenContract, nonce, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmResponse> crosschainQueryBatchConfirm(String chainName, String orchestratorAddress, String tokenContract, long nonce, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.batchConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmRequest.newBuilder().setChainName(chainName).setOrchestratorAddress(orchestratorAddress).setTokenContract(tokenContract).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> crosschainQueryBatchConfirms(String chainName, String tokenContract, long nonce) {
        return this.crosschainQueryBatchConfirms(chainName, tokenContract, nonce, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsResponse> crosschainQueryBatchConfirms(String chainName, String tokenContract, long nonce, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.batchConfirms(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchConfirmsRequest.newBuilder().setChainName(chainName).setTokenContract(tokenContract).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> crosschainQueryBatchFees(String chainName) {
        return this.crosschainQueryBatchFees(chainName, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeResponse> crosschainQueryBatchFees(String chainName, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.batchFees(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchFeeRequest.newBuilder().setChainName(chainName).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> crosschainQueryBatchRequestByNonce(String chainName, long nonce, String tokenContract) {
        return this.crosschainQueryBatchRequestByNonce(chainName, nonce, tokenContract, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceResponse> crosschainQueryBatchRequestByNonce(String chainName, long nonce, String tokenContract, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.batchRequestByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryBatchRequestByNonceRequest.newBuilder().setChainName(chainName).setNonce(nonce).setTokenContract(tokenContract).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> crosschainQueryCurrentOracleSet(String chainName) {
        return this.crosschainQueryCurrentOracleSet(chainName, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetResponse> crosschainQueryCurrentOracleSet(String chainName, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.currentOracleSet(fx.gravity.crosschain.v1.QueryOuterClass.QueryCurrentOracleSetRequest.newBuilder().setChainName(chainName).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> crosschainQueryDenomToToken(String chainName, String denom) {
        return this.crosschainQueryDenomToToken(chainName, denom, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenResponse> crosschainQueryDenomToToken(String chainName, String denom, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.denomToToken(fx.gravity.crosschain.v1.QueryOuterClass.QueryDenomToTokenRequest.newBuilder().setDenom(denom).setChainName(chainName).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> crosschainQueryGetIbcSequenceHeightByChannel(String chainName, long sequence, String sourceChannel, String sourcePort) {
        return this.crosschainQueryGetIbcSequenceHeightByChannel(chainName, sequence, sourceChannel, sourcePort, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryIbcSequenceHeightResponse> crosschainQueryGetIbcSequenceHeightByChannel(String chainName, long sequence, String sourceChannel, String sourcePort, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.getIbcSequenceHeightByChannel(fx.gravity.crosschain.v1.QueryOuterClass.QueryIbcSequenceHeightRequest.newBuilder().setChainName(chainName).setSequence(sequence).setSourceChannel(sourceChannel).setSourcePort(sourcePort).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByAddr(String chainName, String oracleAddress) {
        return this.crosschainQueryGetOracleByAddr(chainName, oracleAddress);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByAddr(String chainName, String oracleAddress, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.getOracleByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByAddrRequest.newBuilder().setChainName(chainName).setOracleAddress(oracleAddress).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByExternalAddr(String chainName, String externalAddress) {
        return this.crosschainQueryGetOracleByExternalAddr(chainName, externalAddress, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByExternalAddr(String chainName, String externalAddress, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.getOracleByExternalAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByExternalAddrRequest.newBuilder().setChainName(chainName).setExternalAddress(externalAddress).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByOrchestrator(String chainName, String orchestratorAddress) {
        return this.crosschainQueryGetOracleByOrchestrator(chainName, orchestratorAddress, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleResponse> crosschainQueryGetOracleByOrchestrator(String chainName, String orchestratorAddress, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.getOracleByOrchestrator(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleByOrchestratorRequest.newBuilder().setChainName(chainName).setOrchestratorAddress(orchestratorAddress).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> crosschainQueryGetPendingSendToExternal(String chainName, String senderAddress) {
        return this.crosschainQueryGetPendingSendToExternal(chainName, senderAddress, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalResponse> crosschainQueryGetPendingSendToExternal(String chainName, String senderAddress, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.getPendingSendToExternal(fx.gravity.crosschain.v1.QueryOuterClass.QueryPendingSendToExternalRequest.newBuilder().setChainName(chainName).setSenderAddress(senderAddress).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> crosschainQueryLastEventBlockHeightByAddr(String chainName, String orchestratorAddress) {
        return this.crosschainQueryLastEventBlockHeightByAddr(chainName, orchestratorAddress, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrResponse> crosschainQueryLastEventBlockHeightByAddr(String chainName, String orchestratorAddress, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.lastEventBlockHeightByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventBlockHeightByAddrRequest.newBuilder().setChainName(chainName).setOrchestratorAddress(orchestratorAddress).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> crosschainQueryLastEventNonceByAddr(String chainName, String orchestratorAddress) {
        return this.crosschainQueryLastEventNonceByAddr(chainName, orchestratorAddress, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrResponse> crosschainQueryLastEventNonceByAddr(String chainName, String orchestratorAddress, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.lastEventNonceByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastEventNonceByAddrRequest.newBuilder().setChainName(chainName).setOrchestratorAddress(orchestratorAddress).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> crosschainQueryLastObservedBlockHeight(String chainName) {
        return this.crosschainQueryLastObservedBlockHeight(chainName, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightResponse> crosschainQueryLastObservedBlockHeight(String chainName, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.lastObservedBlockHeight(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastObservedBlockHeightRequest.newBuilder().setChainName(chainName).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> crosschainQueryLastOracleSetRequests(String chainName) {
        return this.crosschainQueryLastOracleSetRequests(chainName, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsResponse> crosschainQueryLastOracleSetRequests(String chainName, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.lastOracleSetRequests(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastOracleSetRequestsRequest.newBuilder().setChainName(chainName).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> crosschainQueryLastPendingBatchRequestByAddr(String chainName, String orchestratorAddress) {
        return this.crosschainQueryLastPendingBatchRequestByAddr(chainName, orchestratorAddress, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrResponse> crosschainQueryLastPendingBatchRequestByAddr(String chainName, String orchestratorAddress, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.lastPendingBatchRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingBatchRequestByAddrRequest.newBuilder().setChainName(chainName).setOrchestratorAddress(orchestratorAddress).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> crosschainQueryLastPendingOracleSetRequestByAddr(String chainName, String orchestratorAddress) {
        return this.crosschainQueryLastPendingOracleSetRequestByAddr(chainName, orchestratorAddress, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrResponse> crosschainQueryLastPendingOracleSetRequestByAddr(String chainName, String orchestratorAddress, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.lastPendingOracleSetRequestByAddr(fx.gravity.crosschain.v1.QueryOuterClass.QueryLastPendingOracleSetRequestByAddrRequest.newBuilder().setChainName(chainName).setOrchestratorAddress(orchestratorAddress).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> crosschainQueryOracles(String chainName) {
        return this.crosschainQueryOracles(chainName, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesResponse> crosschainQueryOracles(String chainName, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.oracles(fx.gravity.crosschain.v1.QueryOuterClass.QueryOraclesRequest.newBuilder().setChainName(chainName).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> crosschainQueryOracleSetConfirm(String chainName, String orchestratorAddress, long nonce) {
        return this.crosschainQueryOracleSetConfirm(chainName, orchestratorAddress, nonce, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmResponse> crosschainQueryOracleSetConfirm(String chainName, String orchestratorAddress, long nonce, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.oracleSetConfirm(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmRequest.newBuilder().setChainName(chainName).setOrchestratorAddress(orchestratorAddress).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> crosschainQueryOracleSetConfirmsByNonce(String chainName, long nonce) {
        return this.crosschainQueryOracleSetConfirmsByNonce(chainName, nonce, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceResponse> crosschainQueryOracleSetConfirmsByNonce(String chainName, long nonce, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.oracleSetConfirmsByNonce(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetConfirmsByNonceRequest.newBuilder().setChainName(chainName).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> crosschainQueryOracleSetRequest(String chainName, long nonce) {
        return this.crosschainQueryOracleSetRequest(chainName, nonce, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestResponse> crosschainQueryOracleSetRequest(String chainName, long nonce, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.oracleSetRequest(fx.gravity.crosschain.v1.QueryOuterClass.QueryOracleSetRequestRequest.newBuilder().setChainName(chainName).setNonce(nonce).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> crosschainQueryOutgoingTxBatches(String chainName) {
        return this.crosschainQueryOutgoingTxBatches(chainName, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesResponse> crosschainQueryOutgoingTxBatches(String chainName, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.outgoingTxBatches(fx.gravity.crosschain.v1.QueryOuterClass.QueryOutgoingTxBatchesRequest.newBuilder().setChainName(chainName).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> crosschainQueryParams(String chainName) {
        return this.crosschainQueryParams(chainName, null);
    }

    public ListenableFuture<fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsResponse> crosschainQueryParams(String chainName, BigInteger height) {
        fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = crosschainQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = crosschainQueryStub;
        }
        return stub.params(fx.gravity.crosschain.v1.QueryOuterClass.QueryParamsRequest.newBuilder().setChainName(chainName).build());
    }

    /********************************************************
     ***** Module 'crosschain' : MESSAGE
     *******************************************************/

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgAddOracleDepositResponse> crosschainMsgAddOracleDeposit(String chainName, String oracle, cosmos.base.v1beta1.CoinOuterClass.Coin amount) {
        return crosschainMsgStub.addOracleDeposit(fx.gravity.crosschain.v1.Tx.MsgAddOracleDeposit.newBuilder().setChainName(chainName).setOracle(oracle).setAmount(amount).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaimResponse> crosschainMsgBridgeTokenClaim(String chainName, String name, String tokenContract, long blockHeight, String symbol, long decimals, String channelIBC, long eventNonce, String orchestrator) {
        return crosschainMsgStub.bridgeTokenClaim(fx.gravity.crosschain.v1.Tx.MsgBridgeTokenClaim.newBuilder().setChainName(chainName).setName(name).setTokenContract(tokenContract).setBlockHeight(blockHeight).setSymbol(symbol).setDecimals(decimals).setChannelIbc(channelIBC).setEventNonce(eventNonce).setOrchestrator(orchestrator).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternalResponse> crosschainMsgCancelSendToExternal(String chainName, String sender, long transactionId) {
        return crosschainMsgStub.cancelSendToExternal(fx.gravity.crosschain.v1.Tx.MsgCancelSendToExternal.newBuilder().setChainName(chainName).setSender(sender).setTransactionId(transactionId).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgConfirmBatchResponse> crosschainMsgConfirmBatch(String chainName, String externalAddress, long nonce, String orchestratorAddress, String signature, String tokenContract) {
        return crosschainMsgStub.confirmBatch(fx.gravity.crosschain.v1.Tx.MsgConfirmBatch.newBuilder().setChainName(chainName).setExternalAddress(externalAddress).setNonce(nonce).setOrchestratorAddress(orchestratorAddress).setSignature(signature).setTokenContract(tokenContract).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirmResponse> crosschainMsgOracleSetConfirm(String chainName, String externalAddress, long nonce, String orchestratorAddress, String signature) {
        return crosschainMsgStub.oracleSetConfirm(fx.gravity.crosschain.v1.Tx.MsgOracleSetConfirm.newBuilder().setChainName(chainName).setExternalAddress(externalAddress).setNonce(nonce).setOrchestratorAddress(orchestratorAddress).setSignature(signature).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaimResponse> crosschainMsgOracleSetUpdateClaim(String chainName, long blockHeight, long oracleSetNonce, long eventNonce, String orchestrator, Iterable<? extends fx.gravity.crosschain.v1.Crosschain.BridgeValidator> members) {
        return crosschainMsgStub.oracleSetUpdateClaim(fx.gravity.crosschain.v1.Tx.MsgOracleSetUpdatedClaim.newBuilder().setChainName(chainName).setBlockHeight(blockHeight).setOracleSetNonce(oracleSetNonce).setEventNonce(eventNonce).setOrchestrator(orchestrator).addAllMembers(members).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgRequestBatchResponse> crosschainMsgRequestBatch(String chainName, String sender, String denom, String feeReceive, String minimumFee) {
        return crosschainMsgStub.requestBatch(fx.gravity.crosschain.v1.Tx.MsgRequestBatch.newBuilder().setChainName(chainName).setSender(sender).setDenom(denom).setFeeReceive(feeReceive).setMinimumFee(minimumFee).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> crosschainMsgSendToExternal(String chainName, String sender, String dest, cosmos.base.v1beta1.CoinOuterClass.Coin amount, cosmos.base.v1beta1.CoinOuterClass.Coin bridgeFee) {
        return crosschainMsgStub.sendToExternal(fx.gravity.crosschain.v1.Tx.MsgSendToExternal.newBuilder().setChainName(chainName).setSender(sender).setDest(dest).setAmount(amount).setBridgeFee(bridgeFee).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> crosschainMsgSendToExternalClaim(String chainName, long batchNonce, long eventNonce, String orchestrator, long blockHeight, String tokenContract) {
        return crosschainMsgStub.sendToExternalClaim(fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim.newBuilder().setChainName(chainName).setBatchNonce(batchNonce).setEventNonce(eventNonce).setOrchestrator(orchestrator).setBlockHeight(blockHeight).setTokenContract(tokenContract).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> crosschainMsgSendToExternalClaim(String chainName, String sender, String receiver, String orchestrator, long eventNonce, String amount, long blockHeight, String tokenContract, String targetIBC) {
        return crosschainMsgStub.sendToFxClaim(fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim.newBuilder().setChainName(chainName).setSender(sender).setReceiver(receiver).setOrchestrator(orchestrator).setEventNonce(eventNonce).setAmount(amount).setBlockHeight(blockHeight).setTokenContract(tokenContract).setTargetIbc(targetIBC).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSetOrchestratorAddressResponse> crosschainMsgSetOrchestratorAddress(String chainName, String orchestrator, String oracle, String externalAddress, cosmos.base.v1beta1.CoinOuterClass.Coin deposit) {
        return crosschainMsgStub.setOrchestratorAddress(fx.gravity.crosschain.v1.Tx.MsgSetOrchestratorAddress.newBuilder().setChainName(chainName).setOrchestrator(orchestrator).setOracle(oracle).setExternalAddress(externalAddress).setDeposit(deposit).build());
    }

    /********************************************************
     ***** Module 'ibc' : QUERY
     *******************************************************/

    public ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> ibcQueryDenomTrace(String hash) {
        return this.ibcQueryDenomTrace(hash, null);
    }

    public ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceResponse> ibcQueryDenomTrace(String hash, BigInteger height) {
        fx.ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = ibcQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = ibcQueryStub;
        }
        return stub.denomTrace(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTraceRequest.newBuilder().setHash(hash).build());
    }

    public ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> ibcQueryDenomTraces(Pagination.PageRequest pagination) {
        return this.ibcQueryDenomTraces(pagination, null);
    }

    public ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesResponse> ibcQueryDenomTraces(Pagination.PageRequest pagination, BigInteger height) {
        fx.ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = ibcQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = ibcQueryStub;
        }
        return stub.denomTraces(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryDenomTracesRequest.newBuilder().setPagination(pagination).build());
    }

    public ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> ibcQueryParams() {
        return this.ibcQueryParams(null);
    }

    public ListenableFuture<fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsResponse> ibcQueryParams(BigInteger height) {
        fx.ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = ibcQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = ibcQueryStub;
        }
        return stub.params(fx.ibc.applications.transfer.v1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    /********************************************************
     ***** Module 'ibc' : MESSAGE
     *******************************************************/

    public ListenableFuture<fx.ibc.applications.transfer.v1.Tx.MsgTransferResponse> ibcMsgTransfer(String sender, String receiver, String router, cosmos.base.v1beta1.CoinOuterClass.Coin token, cosmos.base.v1beta1.CoinOuterClass.Coin fee, String sourceChannel, String sourcePort, ibc.core.client.v1.Client.Height timeoutHeight) {
        return ibcMsgStub.transfer(fx.ibc.applications.transfer.v1.Tx.MsgTransfer.newBuilder().setSender(sender).setReceiver(receiver).setRouter(router).setToken(token).setFee(fee).setSourceChannel(sourceChannel).setSourcePort(sourcePort).setTimeoutHeight(timeoutHeight).build());
    }

    /********************************************************
     ***** Module 'tendermint.base' : SERVICE
     *******************************************************/

}