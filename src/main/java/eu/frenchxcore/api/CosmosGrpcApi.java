package eu.frenchxcore.api;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import cosmos.gov.v1beta1.QueryGrpc;
import eu.frenchxcore.tools.LocalExecutor;
import eu.frenchxcore.tools.XLogger;
import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import tendermint.abci.Types;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CosmosGrpcApi {

    private final ManagedChannel cosmosChannel;
    private final ManagedChannel tendermintChannel;

    private final cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub authStub;
    private final cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub authzStub;
    private final cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub bankStub;
    private final cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub distributionStub;
    private final cosmos.evidence.v1beta1.QueryGrpc.QueryFutureStub evidenceStub;
    private final cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub feeGrantStub;
    private final cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub govStub1;
    private final cosmos.group.v1beta1.QueryGrpc.QueryFutureStub groupStub;
    private final cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub govStub2;
    private final cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub mintStub;
    private final cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub nftStub;
    private final cosmos.params.v1beta1.QueryGrpc.QueryFutureStub paramsStub;
    private final cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub slashingStub;
    private final cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stakingStub;
    private final cosmos.tx.v1beta1.ServiceGrpc.ServiceFutureStub txStub;
    private final cosmos.upgrade.v1beta1.QueryGrpc.QueryFutureStub upgradeStub;

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
            String key = ia.getHostAddress()+":"+cosmosPort+":"+tendermintPort;
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
        authStub = cosmos.auth.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        authzStub = cosmos.authz.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        bankStub = cosmos.bank.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        distributionStub = cosmos.distribution.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        evidenceStub = cosmos.evidence.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        feeGrantStub = cosmos.feegrant.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        govStub1 = cosmos.gov.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        govStub2 = cosmos.gov.v1beta2.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        groupStub = cosmos.group.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        mintStub = cosmos.mint.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        nftStub = cosmos.nft.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        paramsStub = cosmos.params.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        slashingStub = cosmos.slashing.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        stakingStub = cosmos.staking.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        txStub = cosmos.tx.v1beta1.ServiceGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        upgradeStub = cosmos.upgrade.v1beta1.QueryGrpc.newFutureStub(cosmosChannel).withExecutor(executor);
        abciStub = tendermint.abci.ABCIApplicationGrpc.newFutureStub(tendermintChannel).withExecutor(executor);
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

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authAccounts() {
        return this.authAccounts(null, null);
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authAccounts(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return authAccounts(pageRequest, null);
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authAccounts(BigInteger height) {
        return authAccounts(null, height);
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authAccounts(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.auth.v1beta1.QueryAccountsRequest.Builder builder = cosmos.auth.v1beta1.QueryAccountsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.auth.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = authStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authStub;
        }
        return stub.accounts(builder.build());
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountResponse> authAccount(String address) {
        return authStub.account(cosmos.auth.v1beta1.QueryAccountRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<cosmos.auth.v1beta1.Bech32PrefixResponse> authBech32Prefix(String address) {
        return authStub.bech32Prefix(cosmos.auth.v1beta1.Bech32PrefixRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryModuleAccountsResponse> authModuleAccounts() {
        return authStub.moduleAccounts(cosmos.auth.v1beta1.QueryModuleAccountsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryParamsResponse> authParams() {
        return authStub.params(cosmos.auth.v1beta1.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzGranterGrants(String granter) {
        return this.authzGranterGrants(granter, null, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzGranterGrants(String granter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.authzGranterGrants(granter, pageRequest, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzGranterGrants(String granter, BigInteger height) {
        return this.authzGranterGrants(granter, height);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzGranterGrants(String granter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest.Builder builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = authzStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authzStub;
        }
        return stub.granterGrants(builder.setGranter(granter).build());
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzGrants(String granter, String grantee) {
        return this.authzGrants(granter, grantee, null, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzGrants(String granter, String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.authzGrants(granter, grantee, pageRequest, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzGrants(String granter, String grantee, BigInteger height) {
        return this.authzGrants(granter, grantee, null, height);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzGrants(String granter, String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsRequest.Builder builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.authz.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = authzStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = authzStub;
        }
        return authzStub.grants(builder.setGranter(granter).setGrantee(grantee).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankAllBalances() {
        return this.bankAllBalances(null, null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankAllBalances(String address) {
        return this.bankAllBalances(address, null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankAllBalances(String address, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.bankAllBalances(address, pageRequest, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankAllBalances(String address, BigInteger height) {
        return this.bankAllBalances(address, null, height);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankAllBalances(String address, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
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
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = bankStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankStub;
        }
        return stub.allBalances(builder.build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryBalanceResponse> bankBalance(String address, String denom) {
        return this.bankBalance(address, denom, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryBalanceResponse> bankBalance(String address, String denom, BigInteger height) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = bankStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankStub;
        }
        return stub.balance(cosmos.bank.v1beta1.QueryBalanceRequest.newBuilder().setAddress(address).setDenom(denom).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> bankDenomsMetadata() {
        return this.bankDenomsMetadata(null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomsMetadataResponse> bankDenomsMetadata(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.bank.v1beta1.QueryDenomsMetadataRequest.Builder builder = cosmos.bank.v1beta1.QueryDenomsMetadataRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return bankStub.denomsMetadata(cosmos.bank.v1beta1.QueryDenomsMetadataRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomMetadataResponse> bankDenomMetadata(String denom) {
        return bankStub.denomMetadata(cosmos.bank.v1beta1.QueryDenomMetadataRequest.newBuilder().setDenom(denom).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankDenomOwners(String denom) {
        return this.bankDenomOwners(denom, null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankDenomOwners(String denom, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.bankDenomOwners(denom, pageRequest, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankDenomOwners(String denom, BigInteger height) {
        return this.bankDenomOwners(denom, null, height);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankDenomOwners(String denom, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.bank.v1beta1.QueryDenomOwnersRequest.Builder builder = cosmos.bank.v1beta1.QueryDenomOwnersRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = bankStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankStub;
        }
        return stub.denomOwners(builder.setDenom(denom).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryParamsResponse> bankParams() {
        return this.bankParams(null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryParamsResponse> bankParams(BigInteger height) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = bankStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankStub;
        }
        return stub.params(cosmos.bank.v1beta1.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QuerySupplyOfResponse> bankSupplyOf(String denom) {
        return this.bankSupplyOf(null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QuerySupplyOfResponse> bankSupplyOf(String denom, BigInteger height) {
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = bankStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankStub;
        }
        return stub.supplyOf(cosmos.bank.v1beta1.QuerySupplyOfRequest.newBuilder().setDenom(denom).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankTotalSupply() {
        return this.bankTotalSupply(null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankTotalSupply(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.bankTotalSupply(pageRequest, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankTotalSupply(BigInteger height) {
        return this.bankTotalSupply(null, height);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankTotalSupply(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.bank.v1beta1.QueryTotalSupplyRequest.Builder builder = cosmos.bank.v1beta1.QueryTotalSupplyRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.bank.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = bankStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = bankStub;
        }
        return stub.totalSupply(builder.build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolResponse> distributionCommunityPool() {
        return this.distributionCommunityPool();
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolResponse> distributionCommunityPool(BigInteger height) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.communityPool(cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionDelegationRewards(String delegatorAddress) {
        return this.distributionDelegationRewards(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionDelegationRewards(String delegatorAddress, BigInteger height) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.delegationRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsResponse> distributionDelegationTotalRewardsAddress(String delegatorAddress) {
        return this.distributionDelegationTotalRewardsAddress(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsResponse> distributionDelegationTotalRewardsAddress(String delegatorAddress, BigInteger height) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.delegationTotalRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> distributionDelegatorValidators(String delegatorAddress) {
        return this.distributionDelegatorValidators(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> distributionDelegatorValidators(String delegatorAddress, BigInteger height) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.delegatorValidators(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressResponse> distributionDelegatorWithdrawAddress(String delegatorAddress) {
        return this.distributionDelegatorWithdrawAddress(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressResponse> distributionDelegatorWithdrawAddress(String delegatorAddress, BigInteger height) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.delegatorWithdrawAddress(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryParamsResponse> distributionParams(BigInteger height) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.params(cosmos.distribution.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionResponse> distributionValidatorCommission(String validatorAddress) {
        return this.distributionValidatorCommission(validatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionResponse> distributionValidatorCommission(String validatorAddress, BigInteger height) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.validatorCommission(cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionRequest.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsResponse> distributionValidatorOutstandingRewards(String validatorAddress) {
        return this.distributionValidatorOutstandingRewards(validatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsResponse> distributionValidatorOutstandingRewards(String validatorAddress, BigInteger height) {
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.validatorOutstandingRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsRequest.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionValidatorSlashes(String validatorAddress) {
        return this.distributionValidatorSlashes(validatorAddress, null, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionValidatorSlashes(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.distributionValidatorSlashes(validatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionValidatorSlashes(String validatorAddress, BigInteger height) {
        return this.distributionValidatorSlashes(validatorAddress, null, height);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionValidatorSlashes(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesRequest.Builder builder = cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.distribution.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = distributionStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = distributionStub;
        }
        return stub.validatorSlashes(builder.setValidatorAddress(validatorAddress).build());
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceAllEvidence(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.evidenceAllEvidence(pageRequest, null);
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceAllEvidence(BigInteger height) {
        return this.evidenceAllEvidence(null, height);
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceAllEvidence(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceRequest.Builder builder = cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.evidence.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = evidenceStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = evidenceStub;
        }
        return stub.allEvidence(builder.build());
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceResponse> evidenceEvidence(ByteString evidenceHash) {
        return evidenceStub.evidence(cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceRequest.newBuilder().setEvidenceHash(evidenceHash).build());
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantAllowances(String grantee) {
        return this.feegrantAllowances(grantee, null, null);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantAllowances(String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.feegrantAllowances(grantee, pageRequest, null);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantAllowances(String grantee, BigInteger height) {
        return this.feegrantAllowances(grantee, null, height);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantAllowances(String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesRequest.Builder builder = cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = feeGrantStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = feeGrantStub;
        }
        return stub.allowances(builder.setGrantee(grantee).build());
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceResponse> feegrantAllowance(String grantee, String granter) {
        return this.feegrantAllowance(grantee, granter, null);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceResponse> feegrantAllowance(String grantee, String granter, BigInteger height) {
        cosmos.feegrant.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = feeGrantStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = feeGrantStub;
        }
        return stub.allowance(cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceRequest.newBuilder().setGrantee(grantee).setGranter(granter).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositResponse> govDepositV1(String depositor, Long proposalId) {
        return this.govDepositV1(depositor, proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositResponse> govDepositV1(String depositor, Long proposalId, BigInteger height) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub1;
        }
        return stub.deposit(cosmos.gov.v1beta1.QueryDepositRequest.newBuilder().setDepositor(depositor).setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govDepositsV1(Long proposalId) {
        return this.govDepositsV1(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govDepositsV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govDepositsV1(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govDepositsV1(Long proposalId, BigInteger height) {
        return this.govDepositsV1(proposalId, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govDepositsV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.gov.v1beta1.QueryDepositsRequest.Builder builder = cosmos.gov.v1beta1.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub1;
        }
        return stub.deposits(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryParamsResponse> govParamsV1(String paramsType) {
        return this.govParamsV1(paramsType, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryParamsResponse> govParamsV1(String paramsType, BigInteger height) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub1;
        }
        return stub.params(cosmos.gov.v1beta1.QueryParamsRequest.newBuilder().setParamsType(paramsType).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalResponse> govProposalV1(Long proposalId) {
        return this.govProposalV1(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalResponse> govProposalV1(Long proposalId, BigInteger height) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub1;
        }
        return stub.proposal(cosmos.gov.v1beta1.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govProposalsV1() {
        return this.govProposalsV1(null, null, null, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govProposalsV1(depositor, voter, proposalStatus, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            BigInteger height
    ) {
        return this.govProposalsV1(depositor, voter, proposalStatus, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govProposalsV1(
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
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub1;
        }
        return stub.proposals(builder.build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryTallyResultResponse> govTallyResultV1(Long proposalId) {
        return this.govTallyResultV1(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryTallyResultResponse> govTallyResultV1(Long proposalId, BigInteger height) {
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub1;
        }
        return stub.tallyResult(cosmos.gov.v1beta1.QueryTallyResultRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govVotesV1(Long proposalId) {
        return this.govVotesV1(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govVotesV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govVotesV1(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govVotesV1(Long proposalId, BigInteger height) {
        return this.govVotesV1(proposalId, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govVotesV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.gov.v1beta1.QueryVotesRequest.Builder builder = cosmos.gov.v1beta1.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub1;
        }
        return stub.votes(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govVoteV1(Long proposalId) {
        return this.govVoteV1(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govVoteV1(Long proposalId, String voter) {
        return this.govVoteV1(proposalId, voter, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govVoteV1(Long proposalId, String voter, BigInteger height) {
        cosmos.gov.v1beta1.QueryVoteRequest.Builder builder = cosmos.gov.v1beta1.QueryVoteRequest.newBuilder();
        if (voter != null) {
            builder.setVoter(voter);
        }
        cosmos.gov.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub1.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub1;
        }
        return stub.vote(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> govDepositV2(String depositor, Long proposalId) {
        return this.govDepositV2(depositor, proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> govDepositV2(String depositor, Long proposalId, BigInteger height) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub2;
        }
        return stub.deposit(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest.newBuilder().setDepositor(depositor).setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govDepositsV2(Long proposalId) {
        return this.govDepositsV2(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govDepositsV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govDepositsV2(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govDepositsV2(Long proposalId, BigInteger height) {
        return this.govDepositsV2(proposalId, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govDepositsV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub2;
        }
        return stub.deposits(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> govParamsV2(String paramsType) {
        return this.govParamsV2(paramsType, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> govParamsV2(String paramsType, BigInteger height) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub2;
        }
        return stub.params(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest.newBuilder().setParamsType(paramsType).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> govProposalV2(Long proposalId) {
        return this.govProposalV2(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> govProposalV2(Long proposalId, BigInteger height) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub2;
        }
        return stub.proposal(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govProposalsV2() {
        return this.govProposalsV2(null, null, null, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        return this.govProposalsV2(depositor, voter, proposalStatus, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            BigInteger height
    ) {
        return this.govProposalsV2(depositor, voter, proposalStatus, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govProposalsV2(
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
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub2;
        }
        return stub.proposals(builder.build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> govTallyResultV2(Long proposalId) {
        return this.govTallyResultV2(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> govTallyResultV2(Long proposalId, BigInteger height) {
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub2;
        }
        return stub.tallyResult(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govVotesV2(Long proposalId) {
        return this.govVotesV2(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govVotesV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.govVotesV2(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govVotesV2(Long proposalId, BigInteger height) {
        return this.govVotesV2(proposalId, null, height);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govVotesV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub2;
        }
        return stub.votes(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govVoteV2(Long proposalId) {
        return this.govVoteV2(proposalId, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govVoteV2(Long proposalId, String voter) {
        return this.govVoteV2(proposalId, voter, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govVoteV2(Long proposalId, String voter, BigInteger height) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.newBuilder();
        if (voter != null) {
            builder.setVoter(voter);
        }
        cosmos.gov.v1beta2.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = govStub2.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = govStub2;
        }
        return stub.vote(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> groupGroupAccountInfo(String address) {
        return this.groupGroupAccountInfo(address, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> groupGroupAccountInfo(String address, BigInteger height) {
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.groupAccountInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupGroupAccountsByGroup(Long groupId) {
        return this.groupGroupAccountsByGroup(groupId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupGroupAccountsByGroup(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupGroupAccountsByGroup(groupId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupGroupAccountsByGroup(Long groupId, BigInteger height) {
        return this.groupGroupAccountsByGroup(groupId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupGroupAccountsByGroup(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.groupAccountsByGroup(builder.setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupGroupAccountsByAdmin(String admin) {
        return this.groupGroupAccountsByAdmin(admin, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupGroupAccountsByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupGroupAccountsByAdmin(admin, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupGroupAccountsByAdmin(String admin, BigInteger height) {
        return this.groupGroupAccountsByAdmin(admin, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupGroupAccountsByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.groupAccountsByAdmin(builder.setAdmin(admin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupGroupByAdmin(String admin) {
        return this.groupGroupByAdmin(admin, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupGroupByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupGroupByAdmin(admin, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupGroupByAdmin(String admin, BigInteger height) {
        return this.groupGroupByAdmin(admin, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupGroupByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.groupsByAdmin(builder.setAdmin(admin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupGroupByMember(String memberAddress) {
        return this.groupGroupByMember(memberAddress, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupGroupByMember(String memberAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupGroupByMember(memberAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupGroupByMember(String memberAddress, BigInteger height) {
        return this.groupGroupByMember(memberAddress, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupGroupByMember(String memberAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.groupsByMember(builder.setAddress(memberAddress).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> groupGroupAccountsByAdmin(Long groupId) {
        return groupStub.groupInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest.newBuilder().setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupGroupMembers(Long groupId) {
        return this.groupGroupMembers(groupId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupGroupMembers(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupGroupMembers(groupId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupGroupMembers(Long groupId, BigInteger height) {
        return this.groupGroupMembers(groupId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupGroupMembers(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.groupMembers(builder.setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> groupProposal(Long proposalId) {
        return this.groupProposal(proposalId, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> groupProposal(Long proposalId, BigInteger height) {
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.proposal(cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupProposalsByGroupAccount(String groupAddress) {
        return this.groupProposalsByGroupAccount(groupAddress, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupProposalsByGroupAccount(String groupAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupProposalsByGroupAccount(groupAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupProposalsByGroupAccount(String groupAddress, BigInteger height) {
        return this.groupProposalsByGroupAccount(groupAddress, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupProposalsByGroupAccount(String groupAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.proposalsByGroupAccount(builder.setAddress(groupAddress).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> groupVoteByProposalVoter(Long proposalId, String voter) {
        return this.groupVoteByProposalVoter(proposalId, voter, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> groupVoteByProposalVoter(Long proposalId, String voter, BigInteger height) {
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.voteByProposalVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest.newBuilder().setProposalId(proposalId).setVoter(voter).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupVotesByProposal(Long proposalId) {
        return this.groupVotesByProposal(proposalId, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupVotesByProposal(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupVotesByProposal(proposalId, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupVotesByProposal(Long proposalId, BigInteger height) {
        return this.groupVotesByProposal(proposalId, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupVotesByProposal(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.votesByProposal(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupVotesByVoter(String voter) {
        return this.groupVotesByVoter(voter, null, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupVotesByVoter(String voter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.groupVotesByVoter(voter, pageRequest, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupVotesByVoter(String voter, BigInteger height) {
        return this.groupVotesByVoter(voter, null, height);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupVotesByVoter(String voter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.group.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = groupStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = groupStub;
        }
        return stub.votesByVoter(builder.setVoter(voter).build());
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsResponse> mintAnnualProvisions() {
        return this.mintAnnualProvisions(null);
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsResponse> mintAnnualProvisions(BigInteger height) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = mintStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintStub;
        }
        return stub.annualProvisions(cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryInflationResponse> mintInflation() {
        return this.mintInflation(null);
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryInflationResponse> mintInflation(BigInteger height) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = mintStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintStub;
        }
        return stub.inflation(cosmos.mint.v1beta1.QueryOuterClass.QueryInflationRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryParamsResponse> mintParams() {
        return this.mintParams(null);
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryParamsResponse> mintParams(BigInteger height) {
        cosmos.mint.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = mintStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = mintStub;
        }
        return stub.params(cosmos.mint.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceResponse> nftBalance(String owner) {
        return this.nftBalance(owner, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceResponse> nftBalance(String owner, String classId) {
        return this.nftBalance(owner, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceResponse> nftBalance(String owner, String classId, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = nftStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftStub;
        }
        return stub.balance(builder.setOwner(owner).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassResponse> nftClass(String classId) {
        return this.nftClass(classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassResponse> nftClass(String classId, BigInteger height) {
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = nftStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftStub;
        }
        return stub.class_(cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassRequest.newBuilder().setClassId(classId).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftClasses() {
        return this.nftClasses(null, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftClasses(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.nftClasses(pageRequest, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftClasses(BigInteger height) {
        return this.nftClasses(null, height);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftClasses(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = nftStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftStub;
        }
        return stub.classes(builder.build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTResponse> nftNFT(String id, String classId) {
        return this.nftNFT(id, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTResponse> nftNFT(String id, String classId, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = nftStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftStub;
        }
        return stub.nFT(builder.setId(id).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftNftsOfClass(String owner, String classId) {
        return this.nftNftsOfClass(owner, classId, null, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftNftsOfClass(String owner, String classId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.nftNftsOfClass(owner, classId, pageRequest, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftNftsOfClass(String owner, String classId, BigInteger height) {
        return this.nftNftsOfClass(owner, classId, null, height);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftNftsOfClass(String owner, String classId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = nftStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftStub;
        }
        return stub.nFTsOfClass(builder.setOwner(owner).setClassId(classId).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerResponse> nftOwner(String id, String classId) {
        return this.nftOwner(id, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerResponse> nftOwner(String id, String classId, BigInteger height) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = nftStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftStub;
        }
        return stub.owner(builder.setId(id).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QuerySupplyResponse> nftSupply(String classId) {
        return this.nftSupply(classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QuerySupplyResponse> nftSupply(String classId, BigInteger height) {
        cosmos.nft.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = nftStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = nftStub;
        }
        return stub.supply(cosmos.nft.v1beta1.CosmosNftQueryProto.QuerySupplyRequest.newBuilder().setClassId(classId).build());
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsParams() {
        return this.paramsParams(null, null);
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsParams(String subspace) {
        return this.paramsParams(subspace, null);
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsParams(String subspace, Integer height) {
        cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.Builder builder = cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder();
        if (subspace != null) {
            builder.setSubspace(subspace);
        }
        cosmos.params.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = paramsStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = paramsStub;
        }
        return stub.params(builder.build());
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QuerySubspacesResponse> paramsSubspaces() {
        return this.paramsSubspaces(null);
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QuerySubspacesResponse> paramsSubspaces(BigInteger height) {
        cosmos.params.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = paramsStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = paramsStub;
        }
        return stub.subspaces(cosmos.params.v1beta1.QueryOuterClass.QuerySubspacesRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsResponse> slashingParams() {
        return this.slashingParams(null);
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsResponse> slashingParams(BigInteger height) {
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = slashingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingStub;
        }
        return stub.params(cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoResponse> slashingSigningInfo(String consAddress) {
        return this.slashingSigningInfo(consAddress, null);
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoResponse> slashingSigningInfo(String consAddress, BigInteger height) {
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = slashingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingStub;
        }
        return stub.signingInfo(cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoRequest.newBuilder().setConsAddress(consAddress).build());
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingSigningInfos(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.slashingSigningInfos(pageRequest, null);
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingSigningInfos(BigInteger height) {
        return this.slashingSigningInfos(null, height);
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingSigningInfos(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.Builder builder = cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.slashing.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = slashingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = slashingStub;
        }
        return stub.signingInfos(builder.build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationResponse> stakingDelegation(String delegatorAddress, String validatorAddress) {
        return this.stakingDelegation(delegatorAddress, validatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationResponse> stakingDelegation(String delegatorAddress, String validatorAddress, BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.delegation(cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(String delegatorAddress) {
        return this.stakingDelegatorDelegations(delegatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingDelegatorDelegations(delegatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(String delegatorAddress, BigInteger height) {
        return this.stakingDelegatorDelegations(delegatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.delegatorDelegations(builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(String delegatorAddress) {
        return this.stakingDelegatorUnbondingDelegations(delegatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingDelegatorUnbondingDelegations(delegatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(String delegatorAddress, BigInteger height) {
        return this.stakingDelegatorUnbondingDelegations(delegatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.delegatorUnbondingDelegations(builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(String delegatorAddress) {
        return this.stakingDelegatorValidators(delegatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingDelegatorValidators(delegatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(String delegatorAddress, BigInteger height) {
        return this.stakingDelegatorValidators(delegatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.delegatorValidators(builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorResponse> stakingDelegatorValidator(String delegatorAddress, String validatorAddress) {
        return this.stakingDelegatorValidator(delegatorAddress, validatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorResponse> stakingDelegatorValidator(String delegatorAddress, String validatorAddress, BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.delegatorValidator(cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> stakingHistoricalInfo(Long height) {
        return stakingStub.historicalInfo(cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest.newBuilder().setHeight(height).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryParamsResponse> stakingParams() {
        return this.stakingParams(null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryParamsResponse> stakingParams(BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.params(cosmos.staking.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryPoolResponse> stakingPool() {
        return this.stakingPool(null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryPoolResponse> stakingPool(BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.pool(cosmos.staking.v1beta1.QueryOuterClass.QueryPoolRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingRedelegations(String delegatorAddress) {
        return this.stakingRedelegations(delegatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingRedelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingRedelegations(delegatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingRedelegations(String delegatorAddress, BigInteger height) {
        return this.stakingRedelegations(delegatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingRedelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.redelegations(builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationResponse> stakingUnbondingDelegation(String delegatorAddress, String validatorAddress) {
        return this.stakingUnbondingDelegation(delegatorAddress, validatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationResponse> stakingUnbondingDelegation(String delegatorAddress, String validatorAddress, BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.unbondingDelegation(cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> stakingValidator(String validatorAddress) {
        return this.stakingValidator(validatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> stakingValidator(String validatorAddress, BigInteger height) {
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.validator(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest.newBuilder().setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingValidators() {
        return this.stakingValidators(null, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingValidators(String status) {
        return this.stakingValidators(status, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingValidators(String status, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingValidators(status, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingValidators(String status, BigInteger height) {
        return this.stakingValidators(status, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingValidators(String status, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
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
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.validators(builder.build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingValidatorDelegations(String validatorAddress) {
        return this.stakingValidatorDelegations(validatorAddress, null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingValidatorDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingValidatorDelegations(validatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingValidatorDelegations(String validatorAddress, BigInteger height) {
        return this.stakingValidatorDelegations(validatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingValidatorDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger heightValue) {
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
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.validatorDelegations(builder.build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingValidatorUnbondingDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        return this.stakingValidatorUnbondingDelegations(validatorAddress, pageRequest, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingValidatorUnbondingDelegations(String validatorAddress, BigInteger height) {
        return this.stakingValidatorUnbondingDelegations(validatorAddress, null, height);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingValidatorUnbondingDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest, BigInteger height) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        cosmos.staking.v1beta1.QueryGrpc.QueryFutureStub stub;
        if (height != null) {
            Metadata header = new Metadata();
            Metadata.Key<String> key = Metadata.Key.of("x-cosmos-block-height", Metadata.ASCII_STRING_MARSHALLER);
            header.put(key, height.toString());
            stub = stakingStub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(header));
        } else {
            stub = stakingStub;
        }
        return stub.validatorUnbondingDelegations(builder.setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxResponse> txBroadcastTx(cosmos.tx.v1beta1.ServiceOuterClass.BroadcastMode mode, byte[] txBytes) {
        cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.BroadcastTxRequest.newBuilder();
        return txStub.broadcastTx(_builder.setMode(mode).setTxBytes(ByteString.copyFrom(txBytes)).build());
    }

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxResponse> txGetTx(String txHash) {
        cosmos.tx.v1beta1.ServiceOuterClass.GetTxRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.GetTxRequest.newBuilder();
        return txStub.getTx(_builder.setHash(txHash).build());
    }

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventResponse> txGetTxsEvent(String events, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return txStub.getTxsEvent(_builder.addEvents(events).build());
    }

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventResponse> txGetTxsEvent(Iterable<String> events, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.GetTxsEventRequest.newBuilder();
        if (pageRequest != null) {
            _builder.setPagination(pageRequest);
        }
        return txStub.getTxsEvent(_builder.addAllEvents(events).build());
    }

    public ListenableFuture<cosmos.tx.v1beta1.ServiceOuterClass.SimulateResponse> txSimulate(cosmos.tx.v1beta1.TxOuterClass.Tx tx) {
        cosmos.tx.v1beta1.ServiceOuterClass.SimulateRequest.Builder _builder = cosmos.tx.v1beta1.ServiceOuterClass.SimulateRequest.newBuilder();
        return txStub.simulate(_builder.setTx(tx).build());
    }

    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryAppliedPlanResponse> upgradeAppliedPlan(String name) {
        return upgradeStub.appliedPlan(cosmos.upgrade.v1beta1.QueryOuterClass.QueryAppliedPlanRequest.newBuilder().setName(name).build());
    }

    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryCurrentPlanResponse> upgradeCurrentPlan() {
        return upgradeStub.currentPlan(cosmos.upgrade.v1beta1.QueryOuterClass.QueryCurrentPlanRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.upgrade.v1beta1.QueryOuterClass.QueryModuleVersionsResponse> upgradeModuleVersions(String moduleName) {
        return upgradeStub.moduleVersions(cosmos.upgrade.v1beta1.QueryOuterClass.QueryModuleVersionsRequest.newBuilder().setModuleName(moduleName).build());
    }

    public ListenableFuture<tendermint.abci.Types.ResponseQuery> abciQuery(String path, ByteString data) {
        Types.RequestQuery.Builder rq = tendermint.abci.Types.RequestQuery.newBuilder();
        if (path != null) {
            rq.setPath(path);
        }
        if (data !=null && !data.isEmpty()) {
            rq.setData(data);
        }
        return abciStub.query(rq.build());
    }

}