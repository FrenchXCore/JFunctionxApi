package eu.frenchxcore.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;
import cosmos.base.query.v1beta1.Pagination;
import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.gov.v1beta2.Gov;
import cosmos.staking.v1beta1.Staking;
import cosmos.vesting.v1beta1.Vesting;
import fx.gravity.v1.Types;
import ibc.core.client.v1.Client;
import org.jetbrains.annotations.NotNull;
import tendermint.abci.ABCIApplicationGrpc;
import tendermint.types.Types.Header;
import eu.frenchxcore.tools.LocalExecutor;
import eu.frenchxcore.tools.XLogger;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class CosmosGrpcApi {

    /** To grab Cosmos events :
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

    private static final Metadata.Key<String> BLOCK_HEIGHT_KEY = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);

    private final ManagedChannel cosmosChannel;
    private final ManagedChannel tendermintChannel;

    private final cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub authQueryStub;
    private final cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub authzQueryStub;
    private final cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub bankQueryStub;
    private final fx.gravity.crosschain.v1.QueryGrpc.QueryFutureStub crosschainQueryStub;
    private final cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub distributionQueryStub;
    private final cosmos.evidence.v1beta1.QueryGrpc.QueryFutureStub evidenceQueryStub;
    private final cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub feeGrantQueryStub;
    private final fx.other.QueryGrpc.QueryFutureStub fxOtherQueryStub;
    private final cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub govQueryStubV1;
    private final cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub govQueryStubV2;
    private final fx.gravity.v1.QueryGrpc.QueryFutureStub gravityQueryStub;
    private final cosmos.group.v1beta1.QueryGrpc.QueryFutureStub groupQueryStub;
    private final fx.ibc.applications.transfer.v1.QueryGrpc.QueryFutureStub ibcQueryStub;
    private final cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub mintQueryStub;
    private final cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub nftQueryStub;
    private final cosmos.params.v1beta1.QueryGrpc.QueryFutureStub paramsQueryStub;
    private final cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub slashingQueryStub;
    private final cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stakingQueryStub;
    private final cosmos.upgrade.v1beta1.QueryGrpc.QueryFutureStub upgradeQueryStub;

    private final cosmos.authz.v1beta1.MsgGrpc.MsgFutureStub authzMsgStub;
    private final cosmos.bank.v1beta1.MsgGrpc.MsgFutureStub bankMsgStub;
    private final cosmos.crisis.v1beta1.MsgGrpc.MsgFutureStub crisisMsgStub;
    private final fx.gravity.crosschain.v1.MsgGrpc.MsgFutureStub crosschainMsgStub;
    private final cosmos.distribution.v1beta1.MsgGrpc.MsgFutureStub distributionMsgStub;
    private final cosmos.evidence.v1beta1.MsgGrpc.MsgFutureStub evidenceMsgStub;
    private final cosmos.feegrant.v1beta1.MsgGrpc.MsgFutureStub feegrantMsgStub;
    private final cosmos.gov.v1beta1.MsgGrpc.MsgFutureStub govMsgStubV1;
    private final cosmos.gov.v1beta2.MsgGrpc.MsgFutureStub govMsgStubV2;
    private final fx.gravity.v1.MsgGrpc.MsgFutureStub gravityMsgStub;
    private final cosmos.group.v1beta1.MsgGrpc.MsgFutureStub groupMsgStub;
    private final fx.ibc.applications.transfer.v1.MsgGrpc.MsgFutureStub ibcMsgStub;
    private final cosmos.nft.v1beta1.MsgGrpc.MsgFutureStub nftMsgStub;
    private final cosmos.slashing.v1beta1.MsgGrpc.MsgFutureStub slashingMsgStub;
    private final cosmos.staking.v1beta1.MsgGrpc.MsgFutureStub stakingMsgStub;
    private final cosmos.vesting.v1beta1.MsgGrpc.MsgFutureStub vestingMsgStub;

    private final cosmos.base.tendermint.v1beta1.ServiceGrpc.ServiceFutureStub tendermintServiceStub;
    private final cosmos.tx.v1beta1.ServiceGrpc.ServiceFutureStub txServiceStub;

    private final tendermint.abci.ABCIApplicationGrpc.ABCIApplicationFutureStub abciStub;

    private final static Map<String, CosmosGrpcApi> instances = new HashMap<>();

    public static CosmosGrpcApi getInstance(String ip, int cosmosPort, int tendermintPort) {
        return getInstance(ip, cosmosPort, tendermintPort, null);
    }

    public static CosmosGrpcApi getInstance(String ip, int cosmosPort, int tendermintPort, Executor executor) {
        XLogger.getMainLogger().trace("Creating new CosmosGrpcApi instance on " + ip + ":" + cosmosPort);
        CosmosGrpcApi ret = null;
        try {
            InetAddress ia = InetAddress.getByName(ip);
            String key = ia.getHostAddress() + ":" + cosmosPort + ":" + tendermintPort;
            if ((ret = (instances.get(key))) == null) {
                ret = new CosmosGrpcApi(ip, cosmosPort, tendermintPort, executor);
                instances.put(key, ret);
            }
        } catch (UnknownHostException ex) {
            XLogger.getMainLogger().error(ex.getMessage());
        }
        return ret;
    }

    private CosmosGrpcApi(String ip, int cosmosPort, int tendermintPort, Executor _executor) {
        Executor executor = (_executor == null ? LocalExecutor.getInstance().get() : _executor);
        this.cosmosChannel =
                ManagedChannelBuilder
                        .forAddress(ip, cosmosPort)
                        .executor(executor)
                        .enableRetry()
                        .maxRetryAttempts(10)
                        .keepAliveWithoutCalls(true)
                        .usePlaintext()
                        .build();
        this.tendermintChannel =
                ManagedChannelBuilder
                        .forAddress(ip, tendermintPort)
                        .executor(executor)
                        .enableRetry()
                        .maxRetryAttempts(10)
                        .keepAliveWithoutCalls(true)
                        .usePlaintext()
                        .build();
        authQueryStub = cosmos.auth.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        authzQueryStub = cosmos.authz.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        bankQueryStub = cosmos.bank.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        distributionQueryStub = cosmos.distribution.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        evidenceQueryStub = cosmos.evidence.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        feeGrantQueryStub = cosmos.feegrant.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        govQueryStubV1 = cosmos.gov.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        govQueryStubV2 = cosmos.gov.v1beta2.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        groupQueryStub = cosmos.group.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        mintQueryStub = cosmos.mint.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        nftQueryStub = cosmos.nft.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        paramsQueryStub = cosmos.params.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        slashingQueryStub = cosmos.slashing.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        stakingQueryStub = cosmos.staking.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        upgradeQueryStub = cosmos.upgrade.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);

        txServiceStub = cosmos.tx.v1beta1.ServiceGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        tendermintServiceStub = cosmos.base.tendermint.v1beta1.ServiceGrpc.newFutureStub(cosmosChannel).withExecutor(executor);

        authzMsgStub = cosmos.authz.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        bankMsgStub = cosmos.bank.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        crisisMsgStub = cosmos.crisis.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        distributionMsgStub = cosmos.distribution.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        evidenceMsgStub = cosmos.evidence.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        feegrantMsgStub = cosmos.feegrant.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        govMsgStubV1 = cosmos.gov.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        govMsgStubV2 = cosmos.gov.v1beta2.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        groupMsgStub = cosmos.group.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        nftMsgStub = cosmos.nft.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        slashingMsgStub = cosmos.slashing.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        stakingMsgStub = cosmos.staking.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        vestingMsgStub = cosmos.vesting.v1beta1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);

        gravityMsgStub = fx.gravity.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        gravityQueryStub = fx.gravity.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        crosschainMsgStub = fx.gravity.crosschain.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        crosschainQueryStub = fx.gravity.crosschain.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        ibcMsgStub = fx.ibc.applications.transfer.v1.MsgGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        ibcQueryStub = fx.ibc.applications.transfer.v1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        fxOtherQueryStub = fx.other.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);

        abciStub = ABCIApplicationGrpc.newFutureStub(tendermintChannel).withExecutor(executor);
    }

    public void close() {
        if (this.cosmosChannel != null) {
            try {
                this.cosmosChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                XLogger.getMainLogger().error(ex);
            }
        }
        if (this.tendermintChannel != null) {
            try {
                this.tendermintChannel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
                XLogger.getMainLogger().error(ex);
            }
        }
    }

    /********************************************************
     ***** Cosmos Module 'auth' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authQueryAccounts() {
        return this.authQueryAccounts(null, null);
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authQueryAccounts(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return authQueryAccounts(pageRequest, null);
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authQueryAccounts(BigInteger height) {
        return authQueryAccounts(null, height);
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authQueryAccounts(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.auth.v1beta1.QueryAccountsRequest.Builder builder = cosmos.auth.v1beta1.QueryAccountsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authQueryStub;
        }
        return stub.accounts(builder.build());
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountResponse> authQueryAccount(String address) {
        return authQueryStub.account(cosmos.auth.v1beta1.QueryAccountRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<cosmos.auth.v1beta1.Bech32PrefixResponse> authQueryBech32Prefix() {
        return authQueryStub.bech32Prefix(cosmos.auth.v1beta1.Bech32PrefixRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryModuleAccountsResponse> authQueryModuleAccounts() {
        return authQueryStub.moduleAccounts(cosmos.auth.v1beta1.QueryModuleAccountsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryParamsResponse> authQueryParams() {
        return authQueryStub.params(cosmos.auth.v1beta1.QueryParamsRequest.newBuilder().build());
    }

    /********************************************************
     ***** Cosmos Module 'authz' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzQueryGranterGrants(String granter) {
        return this.authzQueryGranterGrants(granter, null, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzQueryGranterGrants(String granter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.authzQueryGranterGrants(granter, pageRequest, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzQueryGranterGrants(String granter, BigInteger height) {
        return this.authzQueryGranterGrants(granter, null, height);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzQueryGranterGrants(String granter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest.Builder builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authzQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authzQueryStub;
        }
        return stub.granterGrants(builder.setGranter(granter).build());
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzQueryGrants(String granter, String grantee) {
        return this.authzQueryGrants(granter, grantee, null, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzQueryGrants(String granter, String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.authzQueryGrants(granter, grantee, pageRequest, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzQueryGrants(String granter, String grantee, BigInteger height) {
        return this.authzQueryGrants(granter, grantee, null, height);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzQueryGrants(String granter, String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsRequest.Builder builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = authzQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authzQueryStub;
        }
        return stub.grants(builder.setGranter(granter).setGrantee(grantee).build());
    }

    /********************************************************
     ***** Cosmos Module 'bank' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances() {
        return this.bankQueryAllBalances(null, null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances(String address) {
        return this.bankQueryAllBalances(address, null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances(String address, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.bankQueryAllBalances(address, pageRequest, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances(String address, BigInteger height) {
        return this.bankQueryAllBalances(address, null, height);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankQueryAllBalances(String address, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.bank.v1beta1.QueryAllBalancesRequest.Builder builder = cosmos.bank.v1beta1.QueryAllBalancesRequest.newBuilder();
        if (address != null) {
            builder.setAddress(address);
        }
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.allBalances(builder.build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryBalanceResponse> bankQueryBalance(String address, String denom) {
        return this.bankQueryBalance(address, denom, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryBalanceResponse> bankQueryBalance(String address, String denom, BigInteger height) {
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

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> bankQueryDenomsMetadata() {
        return this.bankQueryDenomsMetadata(null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> bankQueryDenomsMetadata(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.bank.v1beta1.QueryDenomsMetadataRequest.Builder builder = cosmos.bank.v1beta1.QueryDenomsMetadataRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return bankQueryStub.denomsMetadata(cosmos.bank.v1beta1.QueryDenomsMetadataRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomMetadataResponse> bankQueryDenomMetadata(String denom) {
        return bankQueryStub.denomMetadata(cosmos.bank.v1beta1.QueryDenomMetadataRequest.newBuilder().setDenom(denom).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankQueryDenomOwners(String denom) {
        return this.bankQueryDenomOwners(denom, null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankQueryDenomOwners(String denom, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.bankQueryDenomOwners(denom, pageRequest, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankQueryDenomOwners(String denom, BigInteger height) {
        return this.bankQueryDenomOwners(denom, null, height);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankQueryDenomOwners(String denom, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.bank.v1beta1.QueryDenomOwnersRequest.Builder builder = cosmos.bank.v1beta1.QueryDenomOwnersRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.denomOwners(builder.setDenom(denom).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryParamsResponse> bankQueryParams() {
        return this.bankQueryParams(null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryParamsResponse> bankQueryParams(BigInteger height) {
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

    public ListenableFuture<cosmos.bank.v1beta1.QuerySupplyOfResponse> bankQuerySupplyOf(String denom) {
        return this.bankQuerySupplyOf(denom, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QuerySupplyOfResponse> bankQuerySupplyOf(String denom, BigInteger height) {
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

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankQueryTotalSupply() {
        return this.bankQueryTotalSupply(null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankQueryTotalSupply(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.bankQueryTotalSupply(pageRequest, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankQueryTotalSupply(BigInteger height) {
        return this.bankQueryTotalSupply(null, height);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankQueryTotalSupply(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.bank.v1beta1.QueryTotalSupplyRequest.Builder builder = cosmos.bank.v1beta1.QueryTotalSupplyRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = bankQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankQueryStub;
        }
        return stub.totalSupply(builder.build());
    }

    /********************************************************
     ***** Cosmos Module 'distribution' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolResponse> distributionQueryCommunityPool() {
        return this.distributionQueryCommunityPool();
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolResponse> distributionQueryCommunityPool(BigInteger height) {
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

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionQueryDelegationRewards(String delegatorAddress) {
        return this.distributionQueryDelegationRewards(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionQueryDelegationRewards(String delegatorAddress, BigInteger height) {
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

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsResponse> distributionQueryDelegationTotalRewardsAddress(String delegatorAddress) {
        return this.distributionQueryDelegationTotalRewardsAddress(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsResponse> distributionQueryDelegationTotalRewardsAddress(String delegatorAddress, BigInteger height) {
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

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> distributionQueryDelegatorValidators(String delegatorAddress) {
        return this.distributionQueryDelegatorValidators(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> distributionQueryDelegatorValidators(String delegatorAddress, BigInteger height) {
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

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressResponse> distributionQueryDelegatorWithdrawAddress(String delegatorAddress) {
        return this.distributionQueryDelegatorWithdrawAddress(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressResponse> distributionQueryDelegatorWithdrawAddress(String delegatorAddress, BigInteger height) {
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

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryParamsResponse> distributionQueryParams(BigInteger height) {
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

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionResponse> distributionQueryValidatorCommission(String validatorAddress) {
        return this.distributionQueryValidatorCommission(validatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionResponse> distributionQueryValidatorCommission(String validatorAddress, BigInteger height) {
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

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsResponse> distributionQueryValidatorOutstandingRewards(String validatorAddress) {
        return this.distributionQueryValidatorOutstandingRewards(validatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsResponse> distributionQueryValidatorOutstandingRewards(String validatorAddress, BigInteger height) {
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

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionQueryValidatorSlashes(String validatorAddress) {
        return this.distributionQueryValidatorSlashes(validatorAddress, null, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionQueryValidatorSlashes(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.distributionQueryValidatorSlashes(validatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionQueryValidatorSlashes(String validatorAddress, BigInteger height) {
        return this.distributionQueryValidatorSlashes(validatorAddress, null, height);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionQueryValidatorSlashes(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesRequest.Builder builder = cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = distributionQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionQueryStub;
        }
        return stub.validatorSlashes(builder.setValidatorAddress(validatorAddress).build());
    }

    /********************************************************
     ***** Cosmos Module 'evidence' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceQueryAllEvidence(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.evidenceQueryAllEvidence(pageRequest, null);
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceQueryAllEvidence(BigInteger height) {
        return this.evidenceQueryAllEvidence(null, height);
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceQueryAllEvidence(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceRequest.Builder builder = cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.evidence.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = evidenceQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = evidenceQueryStub;
        }
        return stub.allEvidence(builder.build());
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceResponse> evidenceQueryEvidence(ByteString evidenceHash) {
        return evidenceQueryStub.evidence(cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceRequest.newBuilder().setEvidenceHash(evidenceHash).build());
    }

    /********************************************************
     ***** Cosmos Module 'feegrant' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantQueryAllowances(String grantee) {
        return this.feegrantQueryAllowances(grantee, null, null);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantQueryAllowances(String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.feegrantQueryAllowances(grantee, pageRequest, null);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantQueryAllowances(String grantee, BigInteger height) {
        return this.feegrantQueryAllowances(grantee, null, height);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantQueryAllowances(String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesRequest.Builder builder = cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = feeGrantQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = feeGrantQueryStub;
        }
        return stub.allowances(builder.setGrantee(grantee).build());
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceResponse> feegrantQueryAllowance(String grantee, String granter) {
        return this.feegrantQueryAllowance(grantee, granter, null);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceResponse> feegrantQueryAllowance(String grantee, String granter, BigInteger height) {
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
     ***** Cosmos Module 'gov' : QUERY (v1beta1 and v1beta2)
     *******************************************************/

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositResponse> govQueryDepositV1(String depositor, Long proposalId) {
        return this.govQueryDepositV1(depositor, proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositResponse> govQueryDepositV1(String depositor, Long proposalId, BigInteger height) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.deposit(cosmos.gov.v1beta1.QueryDepositRequest.newBuilder().setDepositor(depositor).setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govQueryDepositsV1(Long proposalId) {
        return this.govQueryDepositsV1(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govQueryDepositsV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govQueryDepositsV1(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govQueryDepositsV1(Long proposalId, BigInteger height) {
        return this.govQueryDepositsV1(proposalId, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govQueryDepositsV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.gov.v1beta1.QueryDepositsRequest.Builder builder = cosmos.gov.v1beta1.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.deposits(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryParamsResponse> govQueryParamsV1(String paramsType) {
        return this.govQueryParamsV1(paramsType, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryParamsResponse> govQueryParamsV1(String paramsType, BigInteger height) {
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

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalResponse> govQueryProposalV1(Long proposalId) {
        return this.govQueryProposalV1(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalResponse> govQueryProposalV1(Long proposalId, BigInteger height) {
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

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1() {
        return this.govQueryProposalsV1(null, null, null, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govQueryProposalsV1(null, null, null, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryProposalsV1(depositor, voter, proposalStatus, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            BigInteger height
    ) {
        return this.govQueryProposalsV1(depositor, voter, proposalStatus, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govQueryProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta1.QueryProposalsRequest.Builder builder = cosmos.gov.v1beta1.QueryProposalsRequest.newBuilder();
        if (depositor != null) {
            builder.setDepositor(depositor);
        }
        if (voter != null) {
            builder.setVoter(voter);
        }
        if (proposalStatus != null) {
            builder.setProposalStatus(proposalStatus);
        }
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.proposals(builder.build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryTallyResultResponse> govQueryTallyResultV1(Long proposalId) {
        return this.govQueryTallyResultV1(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryTallyResultResponse> govQueryTallyResultV1(Long proposalId, BigInteger height) {
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

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govQueryVotesV1(Long proposalId) {
        return this.govQueryVotesV1(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govQueryVotesV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govQueryVotesV1(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govQueryVotesV1(Long proposalId, BigInteger height) {
        return this.govQueryVotesV1(proposalId, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govQueryVotesV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.gov.v1beta1.QueryVotesRequest.Builder builder = cosmos.gov.v1beta1.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.votes(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govQueryVoteV1(Long proposalId) {
        return this.govQueryVoteV1(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govQueryVoteV1(Long proposalId, String voter) {
        return this.govQueryVoteV1(proposalId, voter, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govQueryVoteV1(Long proposalId, String voter, BigInteger height) {
        cosmos.gov.v1beta1.QueryVoteRequest.Builder builder = cosmos.gov.v1beta1.QueryVoteRequest.newBuilder();
        if (voter != null) {
            builder.setVoter(voter);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV1;
        }
        return stub.vote(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> govQueryDepositV2(String depositor, Long proposalId) {
        return this.govQueryDepositV2(depositor, proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> govQueryDepositV2(String depositor, Long proposalId, BigInteger height) {
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

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govQueryDepositsV2(Long proposalId) {
        return this.govQueryDepositsV2(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govQueryDepositsV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govQueryDepositsV2(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govQueryDepositsV2(Long proposalId, BigInteger height) {
        return this.govQueryDepositsV2(proposalId, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govQueryDepositsV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.deposits(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> govQueryParamsV2(String paramsType) {
        return this.govQueryParamsV2(paramsType, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> govQueryParamsV2(String paramsType, BigInteger height) {
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

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> govQueryProposalV2(Long proposalId) {
        return this.govQueryProposalV2(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> govQueryProposalV2(Long proposalId, BigInteger height) {
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

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2() {
        return this.govQueryProposalsV2(null, null, null, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govQueryProposalsV2(null, null, null, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govQueryProposalsV2(depositor, voter, proposalStatus, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            BigInteger height
    ) {
        return this.govQueryProposalsV2(depositor, voter, proposalStatus, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govQueryProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest,
            BigInteger height
    ) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest.newBuilder();
        if (depositor != null) {
            builder.setDepositor(depositor);
        }
        if (voter != null) {
            builder.setVoter(voter);
        }
        if (proposalStatus != null) {
            builder.setProposalStatus(proposalStatus);
        }
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.proposals(builder.build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> govQueryTallyResultV2(Long proposalId) {
        return this.govQueryTallyResultV2(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> govQueryTallyResultV2(Long proposalId, BigInteger height) {
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

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govQueryVotesV2(Long proposalId) {
        return this.govQueryVotesV2(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govQueryVotesV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govQueryVotesV2(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govQueryVotesV2(Long proposalId, BigInteger height) {
        return this.govQueryVotesV2(proposalId, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govQueryVotesV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.votes(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govQueryVoteV2(Long proposalId) {
        return this.govQueryVoteV2(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govQueryVoteV2(Long proposalId, String voter) {
        return this.govQueryVoteV2(proposalId, voter, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govQueryVoteV2(Long proposalId, String voter, BigInteger height) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.newBuilder();
        if (voter != null) {
            builder.setVoter(voter);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = govQueryStubV2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govQueryStubV2;
        }
        return stub.vote(builder.setProposalId(proposalId).build());
    }

    /********************************************************
     ***** Cosmos Module 'group' : QUERY
     *******************************************************/

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> groupQueryGroupAccountInfo(String address) {
        return this.groupQueryGroupAccountInfo(address, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> groupQueryGroupAccountInfo(String address, BigInteger height) {
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

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupQueryGroupAccountsByGroup(Long groupId) {
        return this.groupQueryGroupAccountsByGroup(groupId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupQueryGroupAccountsByGroup(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupQueryGroupAccountsByGroup(groupId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupQueryGroupAccountsByGroup(Long groupId, BigInteger height) {
        return this.groupQueryGroupAccountsByGroup(groupId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupQueryGroupAccountsByGroup(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupAccountsByGroup(builder.setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupQueryGroupAccountsByAdmin(String admin) {
        return this.groupQueryGroupAccountsByAdmin(admin, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupQueryGroupAccountsByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupQueryGroupAccountsByAdmin(admin, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupQueryGroupAccountsByAdmin(String admin, BigInteger height) {
        return this.groupQueryGroupAccountsByAdmin(admin, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupQueryGroupAccountsByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupAccountsByAdmin(builder.setAdmin(admin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupQueryGroupByAdmin(String admin) {
        return this.groupQueryGroupByAdmin(admin, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupQueryGroupByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupQueryGroupByAdmin(admin, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupQueryGroupByAdmin(String admin, BigInteger height) {
        return this.groupQueryGroupByAdmin(admin, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupQueryGroupByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupsByAdmin(builder.setAdmin(admin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupQueryGroupByMember(String memberAddress) {
        return this.groupQueryGroupByMember(memberAddress, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupQueryGroupByMember(String memberAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupQueryGroupByMember(memberAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupQueryGroupByMember(String memberAddress, BigInteger height) {
        return this.groupQueryGroupByMember(memberAddress, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupQueryGroupByMember(String memberAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupsByMember(builder.setAddress(memberAddress).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> groupQueryGroupAccountsByAdmin(Long groupId) {
        return groupQueryStub.groupInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest.newBuilder().setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupQueryGroupMembers(Long groupId) {
        return this.groupQueryGroupMembers(groupId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupQueryGroupMembers(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupQueryGroupMembers(groupId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupQueryGroupMembers(Long groupId, BigInteger height) {
        return this.groupQueryGroupMembers(groupId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupQueryGroupMembers(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.groupMembers(builder.setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> groupQueryProposal(Long proposalId) {
        return this.groupQueryProposal(proposalId, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> groupQueryProposal(Long proposalId, BigInteger height) {
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

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupQueryProposalsByGroupAccount(String groupAddress) {
        return this.groupQueryProposalsByGroupAccount(groupAddress, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupQueryProposalsByGroupAccount(String groupAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupQueryProposalsByGroupAccount(groupAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupQueryProposalsByGroupAccount(String groupAddress, BigInteger height) {
        return this.groupQueryProposalsByGroupAccount(groupAddress, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupQueryProposalsByGroupAccount(String groupAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.proposalsByGroupAccount(builder.setAddress(groupAddress).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> groupQueryVoteByProposalVoter(Long proposalId, String voter) {
        return this.groupQueryVoteByProposalVoter(proposalId, voter, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> groupQueryVoteByProposalVoter(Long proposalId, String voter, BigInteger height) {
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

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupQueryVotesByProposal(Long proposalId) {
        return this.groupQueryVotesByProposal(proposalId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupQueryVotesByProposal(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupQueryVotesByProposal(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupQueryVotesByProposal(Long proposalId, BigInteger height) {
        return this.groupQueryVotesByProposal(proposalId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupQueryVotesByProposal(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.votesByProposal(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupQueryVotesByVoter(String voter) {
        return this.groupQueryVotesByVoter(voter, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupQueryVotesByVoter(String voter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupQueryVotesByVoter(voter, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupQueryVotesByVoter(String voter, BigInteger height) {
        return this.groupQueryVotesByVoter(voter, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupQueryVotesByVoter(String voter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = groupQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupQueryStub;
        }
        return stub.votesByVoter(builder.setVoter(voter).build());
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
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.balance(builder.setOwner(owner).build());
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
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.classes(builder.build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTResponse> nftQueryNft(String id, String classId) {
        return this.nftQueryNft(id, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTResponse> nftQueryNft(String id, String classId, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.nFT(builder.setId(id).build());
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
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.nFTsOfClass(builder.setOwner(owner).setClassId(classId).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerResponse> nftQueryOwner(String id, String classId) {
        return this.nftQueryOwner(id, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerResponse> nftQueryOwner(String id, String classId, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = nftQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftQueryStub;
        }
        return stub.owner(builder.setId(id).build());
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
        cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.Builder builder = cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder();
        if (subspace != null) {
            builder.setSubspace(subspace);
        }
        cosmos.params.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = paramsQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = paramsQueryStub;
        }
        return stub.params(builder.build());
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
        cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.Builder builder = cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = slashingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingQueryStub;
        }
        return stub.signingInfos(builder.build());
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
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorDelegations(builder.setDelegatorAddr(delegatorAddress).build());
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
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorUnbondingDelegations(builder.setDelegatorAddr(delegatorAddress).build());
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
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.delegatorValidators(builder.setDelegatorAddr(delegatorAddress).build());
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
        cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.redelegations(builder.setDelegatorAddr(delegatorAddress).build());
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
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest.newBuilder();
        if (status != null) {
            builder.setStatus(status);
        }
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validators(builder.build());
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
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsRequest.newBuilder()
                .setValidatorAddr(validatorAddress);
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
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
        return stub.validatorDelegations(builder.build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingQueryValidatorUnbondingDelegations(validatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(String validatorAddress, BigInteger height) {
        return this.stakingQueryValidatorUnbondingDelegations(validatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingQueryValidatorUnbondingDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            header.put(BLOCK_HEIGHT_KEY, height.toString());
            stub = stakingQueryStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingQueryStub;
        }
        return stub.validatorUnbondingDelegations(builder.setValidatorAddr(validatorAddress).build());
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
     ***** Cosmos Module 'authz' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.authz.v1beta1.Tx.MsgExecResponse> authzMsgExec(String grantee, Iterable<? extends Any> messages) {
        return authzMsgStub.exec(cosmos.authz.v1beta1.Tx.MsgExec.newBuilder().setGrantee(grantee).addAllMsgs(messages).build());
    }

    public ListenableFuture<cosmos.authz.v1beta1.Tx.MsgGrantResponse> authzMsgGrant(String granter, String grantee, cosmos.authz.v1beta1.Authz.Grant grant) {
        return authzMsgStub.grant(cosmos.authz.v1beta1.Tx.MsgGrant.newBuilder().setGranter(granter).setGrantee(grantee).setGrant(grant).build());
    }

    public ListenableFuture<cosmos.authz.v1beta1.Tx.MsgRevokeResponse> authzMsgRevoke(String granter, String grantee, String msgTypeUrl) {
        return authzMsgStub.revoke(cosmos.authz.v1beta1.Tx.MsgRevoke.newBuilder().setGranter(granter).setGrantee(grantee).setMsgTypeUrl(msgTypeUrl).build());
    }

    /********************************************************
     ***** Cosmos Module 'bank' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.bank.v1beta1.MsgMultiSendResponse> bankMsgMultiSend(Iterable<? extends cosmos.bank.v1beta1.Bank.Input> inputs, Iterable<? extends cosmos.bank.v1beta1.Bank.Output> outputs) {
        cosmos.bank.v1beta1.MsgMultiSend.Builder _builder = cosmos.bank.v1beta1.MsgMultiSend.newBuilder();
        if (inputs != null) {
            _builder.addAllInputs(inputs);
        }
        if (outputs != null) {
            _builder.addAllOutputs(outputs);
        }
        return bankMsgStub.multiSend(_builder.build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.MsgSendResponse> bankMsgSend(String from, String to, cosmos.base.v1beta1.CoinOuterClass.Coin value) {
        return bankMsgStub.send(cosmos.bank.v1beta1.MsgSend.newBuilder().setFromAddress(from).setToAddress(to).addAmount(value).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.MsgSendResponse> bankMsgSend(String from, String to, Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> values) {
        return bankMsgStub.send(cosmos.bank.v1beta1.MsgSend.newBuilder().setFromAddress(from).setToAddress(to).addAllAmount(values).build());
    }

    /********************************************************
     ***** Cosmos Module 'crisis' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.crisis.v1beta1.Tx.MsgVerifyInvariantResponse> bankMsgSend(String sender, String invariantModuleName, String invariantRoute) {
        return crisisMsgStub.verifyInvariant(cosmos.crisis.v1beta1.Tx.MsgVerifyInvariant.newBuilder().setSender(sender).setInvariantModuleName(invariantModuleName).setInvariantRoute(invariantRoute).build());
    }

    /********************************************************
     ***** Cosmos Module 'distribution' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgFundCommunityPoolResponse> distributionMsgFundCommunityPool(String depositor, Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> values) {
        return distributionMsgStub.fundCommunityPool(cosmos.distribution.v1beta1.Tx.MsgFundCommunityPool.newBuilder().setDepositor(depositor).addAllAmount(values).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgFundCommunityPoolResponse> distributionMsgFundCommunityPool(String depositor, cosmos.base.v1beta1.CoinOuterClass.Coin value) {
        return distributionMsgStub.fundCommunityPool(cosmos.distribution.v1beta1.Tx.MsgFundCommunityPool.newBuilder().setDepositor(depositor).addAmount(value).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddressResponse> distributionMsgSetWithdrawAddress(String delegatorAddress, String withdrawAddress) {
        return distributionMsgStub.setWithdrawAddress(cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress.newBuilder().setDelegatorAddress(delegatorAddress).setWithdrawAddress(withdrawAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorRewardResponse> distributionMsgWithdrawDelegatorReward(String delegatorAddress, String validatorAddress) {
        return distributionMsgStub.withdrawDelegatorReward(cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.newBuilder().setDelegatorAddress(delegatorAddress).setValidatorAddress(validatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.Tx.MsgWithdrawValidatorCommissionResponse> distributionMsgWithdrawValidatorCommission(String validatorAddress) {
        return distributionMsgStub.withdrawValidatorCommission(cosmos.distribution.v1beta1.Tx.MsgWithdrawValidatorCommission.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    /********************************************************
     ***** Cosmos Module 'evidence' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.evidence.v1beta1.Tx.MsgSubmitEvidenceResponse> distributionMsgSubmitEvidence(String submitter, Any evidence) {
        return evidenceMsgStub.submitEvidence(cosmos.evidence.v1beta1.Tx.MsgSubmitEvidence.newBuilder().setSubmitter(submitter).setEvidence(evidence).build());
    }

    /********************************************************
     ***** Cosmos Module 'feegrant' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.feegrant.v1beta1.Tx.MsgGrantAllowanceResponse> feegrantMsgGrantAllowance(String granter, String grantee, Any allowance) {
        return feegrantMsgStub.grantAllowance(cosmos.feegrant.v1beta1.Tx.MsgGrantAllowance.newBuilder().setGranter(granter).setGrantee(grantee).setAllowance(allowance).build());
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.Tx.MsgRevokeAllowanceResponse> feegrantMsgRevokeAllowance(String granter, String grantee) {
        return feegrantMsgStub.revokeAllowance(cosmos.feegrant.v1beta1.Tx.MsgRevokeAllowance.newBuilder().setGranter(granter).setGrantee(grantee).build());
    }

    /********************************************************
     ***** Cosmos Module 'gov' : MESSAGE (v1beta1 and v1beta2)
     *******************************************************/

    public ListenableFuture<cosmos.gov.v1beta1.MsgSubmitProposalResponse> govMsgSubmitProposalV1(String proposer, Any content, Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits) {
        return govMsgStubV1.submitProposal(cosmos.gov.v1beta1.MsgSubmitProposal.newBuilder().setProposer(proposer).setContent(content).addAllInitialDeposit(deposits).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.MsgSubmitProposalResponse> govMsgSubmitProposalV1(String proposer, Any content, cosmos.base.v1beta1.CoinOuterClass.Coin deposit) {
        return govMsgStubV1.submitProposal(cosmos.gov.v1beta1.MsgSubmitProposal.newBuilder().setProposer(proposer).setContent(content).addInitialDeposit(deposit).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.MsgDepositResponse> govMsgDepositV1(long proposalId, String depositor, Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits) {
        return govMsgStubV1.deposit(cosmos.gov.v1beta1.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAllAmount(deposits).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.MsgDepositResponse> govMsgDepositV1(long proposalId, String depositor, cosmos.base.v1beta1.CoinOuterClass.Coin deposit) {
        return govMsgStubV1.deposit(cosmos.gov.v1beta1.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAmount(deposit).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.MsgVoteResponse> govMsgVoteV1(long proposalId, String voter, cosmos.gov.v1beta1.Gov.VoteOption option) {
        return govMsgStubV1.vote(cosmos.gov.v1beta1.MsgVote.newBuilder().setProposalId(proposalId).setVoter(voter).setOption(option).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.MsgVoteWeightedResponse> govMsgVoteWeightedV1(long proposalId, String voter, Iterable<? extends cosmos.gov.v1beta1.Gov.WeightedVoteOption> options) {
        return govMsgStubV1.voteWeighted(cosmos.gov.v1beta1.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addAllOptions(options).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.MsgVoteWeightedResponse> govMsgVoteWeightedV1(long proposalId, String voter, cosmos.gov.v1beta1.Gov.WeightedVoteOption option) {
        return govMsgStubV1.voteWeighted(cosmos.gov.v1beta1.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addOptions(option).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposalResponse> govMsgSubmitProposalV2(String proposer, Iterable<? extends Any> messages, Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits) {
        return govMsgStubV2.submitProposal(cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposal.newBuilder().setProposer(proposer).addAllMessages(messages).addAllInitialDeposit(deposits).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposalResponse> govMsgSubmitProposalV2(String proposer, Iterable<? extends Any> messages, cosmos.base.v1beta1.CoinOuterClass.Coin deposit) {
        return govMsgStubV2.submitProposal(cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposal.newBuilder().setProposer(proposer).addAllMessages(messages).addInitialDeposit(deposit).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposalResponse> govMsgSubmitProposalV2(String proposer, Any message, Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits) {
        return govMsgStubV2.submitProposal(cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposal.newBuilder().setProposer(proposer).addMessages(message).addAllInitialDeposit(deposits).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposalResponse> govMsgSubmitProposalV2(String proposer, Any message, cosmos.base.v1beta1.CoinOuterClass.Coin deposit) {
        return govMsgStubV2.submitProposal(cosmos.gov.v1beta2.CosmosGovTxProto.MsgSubmitProposal.newBuilder().setProposer(proposer).addMessages(message).addInitialDeposit(deposit).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgDepositResponse> govMsgDepositV2(long proposalId, String depositor, Iterable<? extends cosmos.base.v1beta1.CoinOuterClass.Coin> deposits) {
        return govMsgStubV2.deposit(cosmos.gov.v1beta2.CosmosGovTxProto.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAllAmount(deposits).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgDepositResponse> govMsgDepositV2(long proposalId, String depositor, cosmos.base.v1beta1.CoinOuterClass.Coin deposit) {
        return govMsgStubV2.deposit(cosmos.gov.v1beta2.CosmosGovTxProto.MsgDeposit.newBuilder().setProposalId(proposalId).setDepositor(depositor).addAmount(deposit).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteResponse> govMsgVoteV2(long proposalId, String voter, Gov.VoteOption option) {
        return govMsgStubV2.vote(cosmos.gov.v1beta2.CosmosGovTxProto.MsgVote.newBuilder().setProposalId(proposalId).setVoter(voter).setOption(option).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteWeightedResponse> govMsgVoteWeightedV2(long proposalId, String voter, Iterable<? extends cosmos.gov.v1beta2.Gov.WeightedVoteOption> options) {
        return govMsgStubV2.voteWeighted(cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addAllOptions(options).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteWeightedResponse> govMsgVoteWeightedV2(long proposalId, String voter, cosmos.gov.v1beta2.Gov.WeightedVoteOption option) {
        return govMsgStubV2.voteWeighted(cosmos.gov.v1beta2.CosmosGovTxProto.MsgVoteWeighted.newBuilder().setProposalId(proposalId).setVoter(voter).addOptions(option).build());
    }

    /********************************************************
     ***** Cosmos Module 'group' : MESSAGE
     *******************************************************/

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateGroupResponse> groupMsgCreateGroup(String admin, byte[] metadata, Iterable<? extends cosmos.group.v1beta1.Types.Member> members) {
        return groupMsgStub.createGroup(cosmos.group.v1beta1.Tx.MsgCreateGroup.newBuilder().setAdmin(admin).setMetadata(ByteString.copyFrom(metadata)).addAllMembers(members).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateGroupResponse> groupMsgCreateGroup(String admin, byte[] metadata, cosmos.group.v1beta1.Types.Member member) {
        return groupMsgStub.createGroup(cosmos.group.v1beta1.Tx.MsgCreateGroup.newBuilder().setAdmin(admin).setMetadata(ByteString.copyFrom(metadata)).addMembers(member).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateGroupAccountResponse> groupMsgCreateGroupAccount(String admin, long groupId, byte[] metadata, Any decisionPolicy) {
        return groupMsgStub.createGroupAccount(cosmos.group.v1beta1.Tx.MsgCreateGroupAccount.newBuilder().setAdmin(admin).setGroupId(groupId).setMetadata(ByteString.copyFrom(metadata)).setDecisionPolicy(decisionPolicy).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateProposalResponse> groupMsgCreateProposal(String address, Iterable<String> proposers, Iterable<? extends Any> messages) {
        return groupMsgStub.createProposal(cosmos.group.v1beta1.Tx.MsgCreateProposal.newBuilder().setAddress(address).addAllProposers(proposers).addAllMsgs(messages).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateProposalResponse> groupMsgCreateProposal(String address, Iterable<String> proposers, Any message) {
        return groupMsgStub.createProposal(cosmos.group.v1beta1.Tx.MsgCreateProposal.newBuilder().setAddress(address).addAllProposers(proposers).addMsgs(message).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateProposalResponse> groupMsgCreateProposal(String address, String proposer, Iterable<? extends Any> messages) {
        return groupMsgStub.createProposal(cosmos.group.v1beta1.Tx.MsgCreateProposal.newBuilder().setAddress(address).addProposers(proposer).addAllMsgs(messages).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgCreateProposalResponse> groupMsgCreateProposal(String address, String proposer, Any message) {
        return groupMsgStub.createProposal(cosmos.group.v1beta1.Tx.MsgCreateProposal.newBuilder().setAddress(address).addProposers(proposer).addMsgs(message).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgVoteResponse> groupMsgVote(long proposalId, String voter, cosmos.group.v1beta1.Types.Choice choice, cosmos.group.v1beta1.Tx.Exec exec, byte[] metadata) {
        return groupMsgStub.vote(cosmos.group.v1beta1.Tx.MsgVote.newBuilder().setProposalId(proposalId).setVoter(voter).setChoice(choice).setExec(exec).setMetadata(ByteString.copyFrom(metadata)).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgExecResponse> groupMsgExec(long proposalId, String signer) {
        return groupMsgStub.exec(cosmos.group.v1beta1.Tx.MsgExec.newBuilder().setProposalId(proposalId).setSigner(signer).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountAdminResponse> groupMsgUpdateGroupAccountAdmin(String address, String admin, String newAdmin) {
        return groupMsgStub.updateGroupAccountAdmin(cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountAdmin.newBuilder().setAdmin(admin).setNewAdmin(newAdmin).setAddress(address).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountDecisionPolicyResponse> groupMsgUpdateGroupAccountDecisionPolicy(String address, String admin, Any decisionPolicy) {
        return groupMsgStub.updateGroupAccountDecisionPolicy(cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountDecisionPolicy.newBuilder().setAdmin(admin).setAddress(address).setDecisionPolicy(decisionPolicy).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountMetadataResponse> groupMsgUpdateGroupAccountMetadata(String address, String admin, byte[] metadata) {
        return groupMsgStub.updateGroupAccountMetadata(cosmos.group.v1beta1.Tx.MsgUpdateGroupAccountMetadata.newBuilder().setAdmin(admin).setAddress(address).setMetadata(ByteString.copyFrom(metadata)).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupAdminResponse> groupMsgUpdateGroupAdmin(long groupId, String admin, String newAdmin) {
        return groupMsgStub.updateGroupAdmin(cosmos.group.v1beta1.Tx.MsgUpdateGroupAdmin.newBuilder().setGroupId(groupId).setAdmin(admin).setNewAdmin(newAdmin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupMembersResponse> groupMsgUpdateGroupMembers(long groupId, String admin, Iterable<? extends cosmos.group.v1beta1.Types.Member> members) {
        return groupMsgStub.updateGroupMembers(cosmos.group.v1beta1.Tx.MsgUpdateGroupMembers.newBuilder().setGroupId(groupId).setAdmin(admin).addAllMemberUpdates(members).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.Tx.MsgUpdateGroupMetadataResponse> groupMsgUpdateGroupMetadata(long groupId, String admin, byte[] metadata) {
        return groupMsgStub.updateGroupMetadata(cosmos.group.v1beta1.Tx.MsgUpdateGroupMetadata.newBuilder().setGroupId(groupId).setAdmin(admin).setMetadata(ByteString.copyFrom(metadata)).build());
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

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgCreateValidatorResponse> stakingMsgCreateValidator(@NotNull Staking.CommissionRates commissionRates, String validatorAddress, String delegatorAddress, @NotNull Staking.Description description, @NotNull String minSelfDelegation, Any publicKey, @NotNull CoinOuterClass.Coin value) {
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

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgEditValidatorResponse> stakingMsgEditValidator(String commissionRate, @NotNull String validatorAddress, String delegatorAddress, @NotNull Staking.Description description, @NotNull String minSelfDelegation, Any publicKey, @NotNull CoinOuterClass.Coin value) {
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

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgDelegateResponse> stakingMsgDelegate(@NotNull String validatorAddress, @NotNull String delegatorAddress, @NotNull CoinOuterClass.Coin amount) {
        return stakingMsgStub.delegate(cosmos.staking.v1beta1.Tx.MsgDelegate.newBuilder()
                .setDelegatorAddress(delegatorAddress)
                .setValidatorAddress(validatorAddress)
                .setAmount(amount)
                .build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgUndelegateResponse> stakingMsgUndelegate(@NotNull String validatorAddress, @NotNull String delegatorAddress, @NotNull CoinOuterClass.Coin amount) {
        return stakingMsgStub.undelegate(cosmos.staking.v1beta1.Tx.MsgUndelegate.newBuilder()
                .setDelegatorAddress(delegatorAddress)
                .setValidatorAddress(validatorAddress)
                .setAmount(amount)
                .build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.Tx.MsgBeginRedelegateResponse> stakingMsgBeginRedelegate(@NotNull String validatorSrcAddress, @NotNull String validatorDstAddress, @NotNull String delegatorAddress, @NotNull CoinOuterClass.Coin amount) {
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

    public ListenableFuture<cosmos.vesting.v1beta1.Tx.MsgCreateVestingAccountResponse> vestingMsgCreateVestingAccount(boolean delayed, String fromAddress, String toAddress, long endTime, Iterable<? extends CoinOuterClass.Coin> amounts) {
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

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventResponse> txGetTxsEvent(String events, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return txServiceStub.getTxsEvent(_builder.addEvents(events).build());
    }

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
     ***** TENDERMINT Module 'abci'
     *******************************************************/

    /**
     * Echo a string to test an abci client/server implementation
     *
     * @param message A string to echo back
     */
    public ListenableFuture<tendermint.abci.Types.ResponseEcho> abciEcho(String message) {
        return abciStub.echo(tendermint.abci.Types.RequestEcho.newBuilder().setMessage(message).build());
    }

    /**
     * Signals that messages queued on the client should be flushed to the server.
     * It is called periodically by the client implementation to ensure asynchronous requests are actually sent,
     * and is called immediately to make a synchronous request, which returns when the Flush response comes back.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseFlush> abciFlush() {
        return abciStub.flush(tendermint.abci.Types.RequestFlush.newBuilder().build());
    }

    /**
     * Return information about the application state.
     * Used to sync Tendermint with the application during a handshake that happens on startup.
     * The returned app_version will be included in the Header of every block.
     * Tendermint expects last_block_app_hash and last_block_height to be updated during Commit, ensuring that Commit is never called twice for the same block height.
     * Note: Semantic version is a reference to semantic versioning (opens new window). Semantic versions in info will be displayed as X.X.x.
     *
     * @param version      The Tendermint software semantic version
     * @param blockVersion The Tendermint Block Protocol version
     * @param p2pVersion   The Tendermint P2P Protocol version
     */
    public ListenableFuture<tendermint.abci.Types.ResponseInfo> abciInfo(String version, long blockVersion, long p2pVersion) {
        return abciStub.info(tendermint.abci.Types.RequestInfo.newBuilder().setVersion(version).setBlockVersion(blockVersion).setP2PVersion(p2pVersion).build());
    }

    /**
     * Called once upon genesis.
     * If ResponseInitChain.Validators is empty, the initial validator set will be the RequestInitChain.Validators
     * If ResponseInitChain.Validators is not empty, it will be the initial validator set (regardless of what is in RequestInitChain.Validators).
     * This allows the app to decide if it wants to accept the initial validator set proposed by tendermint (i.e. in the genesis file), or if it
     * wants to use a different one (perhaps computed based on some application specific information in the genesis file).
     * Both ResponseInitChain.Validators and ResponseInitChain.Validators are ValidatorUpdate structs. So, technically, they both are updating
     * the set of validators from the empty set.
     *
     * @param time            Genesis time
     * @param chainId         ID of the blockchain.
     * @param consensusParams Initial consensus-critical parameters.
     * @param validators      Initial genesis validators, sorted by voting power.
     * @param appStateBytes   Serialized initial application state. JSON bytes.
     * @param initialHeight   Height of the initial block (typically 1).
     */
    public ListenableFuture<tendermint.abci.Types.ResponseInitChain> abciInitChain(Timestamp time, String chainId, tendermint.abci.Types.ConsensusParams consensusParams, Iterable<tendermint.abci.Types.ValidatorUpdate> validators, ByteString appStateBytes, long initialHeight) {
        return abciStub.initChain(tendermint.abci.Types.RequestInitChain.newBuilder()
                .setTime(time)
                .setChainId(chainId)
                .setConsensusParams(consensusParams)
                .addAllValidators(validators)
                .setAppStateBytes(appStateBytes)
                .setInitialHeight(initialHeight)
                .build());
    }

    /**
     * Query for data from the application at current or past height.
     * Optionally return Merkle proof.
     * Merkle proof includes self-describing type field to support many types of Merkle trees and encoding formats.
     *
     * @param path   Path field of the request URI. Can be used with or in lieu of data. Apps MUST interpret /store as a query by key on the underlying store.
     *               The key SHOULD be specified in the data field. Apps SHOULD allow queries over specific types like /accounts/... or /votes/...
     * @param data   Raw query bytes. Can be used with or in lieu of Path.
     * @param prove  Return Merkle proof with response if possible
     * @param height The block height for which you want the query (default=0 returns data for the latest committed block).
     *               Note that this is the height of the block containing the application's Merkle root hash, which represents the state as
     *               it was after committing the block at Height-1
     */
    public ListenableFuture<tendermint.abci.Types.ResponseQuery> abciQuery(String path, ByteString data, Boolean prove, Long height) {
        tendermint.abci.Types.RequestQuery.Builder rq = tendermint.abci.Types.RequestQuery.newBuilder();
        if (path != null) {
            rq.setPath(path);
        }
        if (data != null && !data.isEmpty()) {
            rq.setData(data);
        }
        rq.setHeight(height != null ? height : 0L);
        rq.setProve(prove != null ? prove : true);
        return abciStub.query(rq.build());
    }

    /**
     * Technically optional - not involved in processing blocks.
     * Guardian of the mempool: every node runs CheckTx before letting a transaction into its local mempool.
     * The transaction may come from an external user or another node
     * CheckTx validates the transaction against the current state of the application, for example, checking signatures and account balances, but does not apply any of the state changes described in the transaction. not running code in a virtual machine.
     * Transactions where ResponseCheckTx.Code != 0 will be rejected - they will not be broadcast to other nodes or included in a proposal block.
     * Tendermint attributes no other value to the response code
     *
     * @param tx   The request transaction bytes
     * @param type One of CheckTx_New or CheckTx_Recheck.
     *             CheckTx_New is the default and means that a full check of the transaction is required.
     *             CheckTx_Recheck types are used when the mempool is initiating a normal recheck of a transaction.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseCheckTx> abciCheckTx(ByteString tx, tendermint.abci.Types.CheckTxType type) {
        tendermint.abci.Types.RequestCheckTx.Builder rq = tendermint.abci.Types.RequestCheckTx.newBuilder();
        if (tx != null && !tx.isEmpty()) {
            rq.setTx(tx);
        }
        rq.setType(type);
        return abciStub.checkTx(rq.build());
    }

    /**
     * Empty request asking the application for a list of snapshots.
     * Used during state sync to discover available snapshots on peers.
     * See Snapshot data type for details.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseListSnapshots> abciListSnapshots() {
        return abciStub.listSnapshots(tendermint.abci.Types.RequestListSnapshots.newBuilder().build());
    }

    /**
     * Used during state sync to retrieve snapshot chunks from peers.
     *
     * @param height The height of the snapshot the chunk belongs to.
     * @param format The application-specific format of the snapshot the chunk belongs to.
     * @param chunk  The chunk index, starting from 0 for the initial chunk.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseLoadSnapshotChunk> abciLoadSnapshotChunk(Long height, Integer format, Integer chunk) {
        return abciStub.loadSnapshotChunk(
                tendermint.abci.Types.RequestLoadSnapshotChunk.newBuilder()
                        .setHeight(height)
                        .setFormat(format)
                        .setChunk(chunk)
                        .build());
    }

    /**
     * OfferSnapshot is called when bootstrapping a node using state sync. The application may accept or reject snapshots as appropriate.
     * Upon accepting, Tendermint will retrieve and apply snapshot chunks via ApplySnapshotChunk. The application may also choose to reject
     * a snapshot in the chunk response, in which case it should be prepared to accept further OfferSnapshot calls.
     * Only AppHash can be trusted, as it has been verified by the light client. Any other data can be spoofed by adversaries, so applications
     * should employ additional verification schemes to avoid denial-of-service attacks. The verified AppHash is automatically checked against
     * the restored application at the end of snapshot restoration.
     * For more information, see the Snapshot data type or the state sync section.
     *
     * @param snapshot The snapshot offered for restoration.
     * @param appHash  The light client-verified app hash for this height, from the blockchain.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseOfferSnapshot> abciOfferSnapshot(tendermint.abci.Types.Snapshot snapshot, ByteString appHash) {
        return abciStub.offerSnapshot(
                tendermint.abci.Types.RequestOfferSnapshot.newBuilder()
                        .setSnapshot(snapshot)
                        .setAppHash(appHash)
                        .build());
    }

    /**
     * The application can choose to refetch chunks and/or ban P2P peers as appropriate. Tendermint will not do this unless instructed by the application.
     * The application may want to verify each chunk, e.g. by attaching chunk hashes in Snapshot.Metadata and/or incrementally verifying contents against AppHash.
     * When all chunks have been accepted, Tendermint will make an ABCI Info call to verify that LastBlockAppHash and LastBlockHeight matches the expected values,
     *  and record the AppVersion in the node state. It then switches to fast sync or consensus and joins the network.
     * If Tendermint is unable to retrieve the next chunk after some time (e.g. because no suitable peers are available), it will reject the snapshot and try
     *  a different one via OfferSnapshot. The application should be prepared to reset and accept it or abort as appropriate.
     *
     * @param index The chunk index, starting from 0. Tendermint applies chunks sequentially.
     * @param chunk The binary chunk contents, as returned by LoadSnapshotChunk.
     * @param sender The P2P ID of the node who sent this chunk.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseApplySnapshotChunk> abciApplySnapshotChunk(Integer index, ByteString chunk, String sender) {
        return abciStub.applySnapshotChunk(
                tendermint.abci.Types.RequestApplySnapshotChunk.newBuilder()
                        .setIndex(index)
                        .setChunk(chunk)
                        .setSender(sender)
                        .build());
    }

    /**
     * Signals the beginning of a new block.
     * Called prior to any DeliverTx method calls.
     * The header contains the height, timestamp, and more - it exactly matches the Tendermint block header. We may seek to generalize this in the future.
     * The LastCommitInfo and ByzantineValidators can be used to determine rewards and punishments for the validators.
     *
     * @param hash The block's hash. This can be derived from the block header.
     * @param header The block header.
     * @param lastCommitInfo Info about the last commit, including the round, and the list of validators and which ones signed the last block.
     * @param byzantineValidators List of evidence of validators that acted maliciously.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseBeginBlock> abciBeginBlock(ByteString hash, Header header, tendermint.abci.Types.LastCommitInfo lastCommitInfo, Collection<tendermint.abci.Types.Evidence> byzantineValidators) {
        return abciStub.beginBlock(
                tendermint.abci.Types.RequestBeginBlock.newBuilder()
                        .setHash(hash)
                        .setHeader(header)
                        .setLastCommitInfo(lastCommitInfo)
                        .addAllByzantineValidators(byzantineValidators)
                        .build());
    }

    /**
     * The core method of the application.
     * When DeliverTx is called, the application must execute the transaction in full before returning control to Tendermint.
     * ResponseDeliverTx.Code == 0 only if the transaction is fully valid.
     *
     * @param tx The request transaction bytes.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseDeliverTx> abciDeliverTx(ByteString tx) {
        return abciStub.deliverTx(
                tendermint.abci.Types.RequestDeliverTx.newBuilder()
                        .setTx(tx)
                        .build());
    }

    /**
     * Signals the end of a block.
     * Called after all the transactions for the current block have been delivered, prior to the block's Commit message.
     * Optional validator_updates triggered by block H. These updates affect validation for blocks H+1, H+2, and H+3.
     * Heights following a validator update are affected in the following way:
     * H+1: NextValidatorsHash includes the new validator_updates value.
     * H+2: The validator set change takes effect and ValidatorsHash is updated.
     * H+3: LastCommitInfo is changed to include the altered validator set.
     * consensus_param_updates returned for block H apply to the consensus params for block H+1. For more information on the consensus parameters, see the application spec entry on consensus parameters.
     *
     * @param height Height of the block just executed.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseEndBlock> abciEndBlock(long height) {
        return abciStub.endBlock(
                tendermint.abci.Types.RequestEndBlock.newBuilder()
                        .setHeight(height)
                        .build());
    }

    /**
     * Commit signals the application to persist application state. It takes no parameters.
     * Signal the application to persist the application state.
     * Return an (optional) Merkle root hash of the application state
     * ResponseCommit.Data is included as the Header.AppHash in the next block
     * it may be empty
     * Later calls to Query can return proofs about the application state anchored in this Merkle root hash
     * Note developers can return whatever they want here (could be nothing, or a constant string, etc.), so long as it is deterministic - it must not be a function of anything that did not come from the BeginBlock/DeliverTx/EndBlock methods.
     * Use RetainHeight with caution! If all nodes in the network remove historical blocks then this data is permanently lost, and no new nodes will be able to join the network and bootstrap. Historical blocks may also be required for other purposes, e.g. auditing, replay of non-persisted heights, light client verification, and so on.
     */
    public ListenableFuture<tendermint.abci.Types.ResponseCommit> abciCommit() {
        return abciStub.commit(tendermint.abci.Types.RequestCommit.newBuilder().build());
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
    public ListenableFuture<fx.gravity.v1.Msgs.MsgSendToEthResponse> gravityMsgSendToEth(String sender, String ethDest, CoinOuterClass.Coin amount, CoinOuterClass.Coin bridgeFee) {
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
    public ListenableFuture<fx.gravity.v1.Msgs.MsgValsetUpdatedClaimResponse> gravityMsgValsetUpdatedClaim(String orchestrator, long blockHeight, long eventNonce, long valsetNonce, Iterable<? extends Types.BridgeValidator> members) {
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

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgAddOracleDepositResponse> crosschainMsgAddOracleDeposit(String chainName, String oracle, CoinOuterClass.Coin amount) {
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

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToExternalResponse> crosschainMsgSendToExternal(String chainName, String sender, String dest, CoinOuterClass.Coin amount, CoinOuterClass.Coin bridgeFee) {
        return crosschainMsgStub.sendToExternal(fx.gravity.crosschain.v1.Tx.MsgSendToExternal.newBuilder().setChainName(chainName).setSender(sender).setDest(dest).setAmount(amount).setBridgeFee(bridgeFee).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaimResponse> crosschainMsgSendToExternalClaim(String chainName, long batchNonce, long eventNonce, String orchestrator, long blockHeight, String tokenContract) {
        return crosschainMsgStub.sendToExternalClaim(fx.gravity.crosschain.v1.Tx.MsgSendToExternalClaim.newBuilder().setChainName(chainName).setBatchNonce(batchNonce).setEventNonce(eventNonce).setOrchestrator(orchestrator).setBlockHeight(blockHeight).setTokenContract(tokenContract).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSendToFxClaimResponse> crosschainMsgSendToExternalClaim(String chainName, String sender, String receiver, String orchestrator, long eventNonce, String amount, long blockHeight, String tokenContract, String targetIBC) {
        return crosschainMsgStub.sendToFxClaim(fx.gravity.crosschain.v1.Tx.MsgSendToFxClaim.newBuilder().setChainName(chainName).setSender(sender).setReceiver(receiver).setOrchestrator(orchestrator).setEventNonce(eventNonce).setAmount(amount).setBlockHeight(blockHeight).setTokenContract(tokenContract).setTargetIbc(targetIBC).build());
    }

    public ListenableFuture<fx.gravity.crosschain.v1.Tx.MsgSetOrchestratorAddressResponse> crosschainMsgSetOrchestratorAddress(String chainName, String orchestrator, String oracle, String externalAddress, CoinOuterClass.Coin deposit) {
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

    public ListenableFuture<fx.ibc.applications.transfer.v1.Tx.MsgTransferResponse> ibcMsgTransfer(String sender, String receiver, String router, CoinOuterClass.Coin token, CoinOuterClass.Coin fee, String sourceChannel, String sourcePort, Client.Height timeoutHeight) {
        return ibcMsgStub.transfer(fx.ibc.applications.transfer.v1.Tx.MsgTransfer.newBuilder().setSender(sender).setReceiver(receiver).setRouter(router).setToken(token).setFee(fee).setSourceChannel(sourceChannel).setSourcePort(sourcePort).setTimeoutHeight(timeoutHeight).build());
    }

    /********************************************************
     ***** Module 'tendermint.base' : SERVICE
     *******************************************************/

}