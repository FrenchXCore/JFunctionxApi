package eu.frenchxcore.tools.grpc;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GrpcClient {

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
    private final cosmos.upgrade.v1beta1.QueryGrpc.QueryFutureStub upgradeStub;

    public GrpcClient(Channel channel) {
        this(channel, null);
    }

    public GrpcClient(Channel channel, Executor _executor) {
        Executor executor = (_executor == null ? Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) : _executor);
        authStub = cosmos.auth.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        authzStub = cosmos.authz.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        bankStub = cosmos.bank.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        distributionStub = cosmos.distribution.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        evidenceStub = cosmos.evidence.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        feeGrantStub = cosmos.feegrant.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        govStub1 = cosmos.gov.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        govStub2 = cosmos.gov.v1beta2.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        groupStub = cosmos.group.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        mintStub = cosmos.mint.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        nftStub = cosmos.nft.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        paramsStub = cosmos.params.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        slashingStub = cosmos.slashing.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        stakingStub = cosmos.staking.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
        upgradeStub = cosmos.upgrade.v1beta1.QueryGrpc.newFutureStub(channel).withExecutor(executor);
    }

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel =
                ManagedChannelBuilder
                        .forAddress("192.168.0.200", 9090)
                        .executor(Executors.newCachedThreadPool())
                        .enableRetry()
                        .maxRetryAttempts(10)
                        .keepAliveWithoutCalls(true)
                        .usePlaintext()
                        .build();
        try {
            GrpcClient client = new GrpcClient(channel);
            cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse resp0 = client.stakingValidators().get();
            int j = 0;
            cosmos.auth.v1beta1.QueryAccountResponse resp1 = client.authAccount("fx1z67rkadwrp2nf4zwxpktpqnw969plely6rfzpt").get();
            if (resp1.getAccount().is(cosmos.auth.v1beta1.BaseAccount.class)) {
                cosmos.auth.v1beta1.BaseAccount account = resp1.getAccount().unpack(cosmos.auth.v1beta1.BaseAccount.class);
                j = 0;
            }
            cosmos.auth.v1beta1.QueryAccountResponse resp2 = client.authAccount("fx1q2lnaudfxm9l06td642jae9kmhwsq6zpt905uj").get();
            if (resp2.getAccount().is(cosmos.auth.v1beta1.BaseAccount.class)) {
                cosmos.auth.v1beta1.BaseAccount account = resp2.getAccount().unpack(cosmos.auth.v1beta1.BaseAccount.class);
                j = 0;
            }
            cosmos.auth.v1beta1.QueryParamsResponse resp3 = client.authParams().get();
            j = 0;
        } catch (InvalidProtocolBufferException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authAccounts() {
        return this.authAccounts(null);
    }

    public ListenableFuture<cosmos.auth.v1beta1.QueryAccountsResponse> authAccounts(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.auth.v1beta1.QueryAccountsRequest.Builder builder = cosmos.auth.v1beta1.QueryAccountsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return authStub.accounts(builder.build());
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
        return this.authzGranterGrants(granter, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsResponse> authzGranterGrants(String granter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest.Builder builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGranterGrantsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return authzStub.granterGrants(builder.setGranter(granter).build());
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzGrants(String granter, String grantee) {
        return this.authzGrants(granter, grantee, null);
    }

    public ListenableFuture<cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsResponse> authzGrants(String granter, String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsRequest.Builder builder = cosmos.authz.v1beta1.QueryOuterClass.QueryGrantsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return authzStub.grants(builder.setGranter(granter).setGrantee(grantee).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankAllBalances() {
        return this.bankAllBalances(null, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankAllBalances(String address) {
        return this.bankAllBalances(address, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryAllBalancesResponse> bankAllBalances(String address, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.bank.v1beta1.QueryAllBalancesRequest.Builder builder = cosmos.bank.v1beta1.QueryAllBalancesRequest.newBuilder();
        if (address != null) {
            builder.setAddress(address);
        }
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return bankStub.allBalances(builder.build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryBalanceResponse> bankBalance(String address, String denom) {
        return bankStub.balance(cosmos.bank.v1beta1.QueryBalanceRequest.newBuilder().setAddress(address).setDenom(denom).build());
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
        return this.bankDenomOwners(denom, null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryDenomOwnersResponse> bankDenomOwners(String denom, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.bank.v1beta1.QueryDenomOwnersRequest.Builder builder = cosmos.bank.v1beta1.QueryDenomOwnersRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return bankStub.denomOwners(builder.setDenom(denom).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryParamsResponse> bankParams() {
        return bankStub.params(cosmos.bank.v1beta1.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QuerySupplyOfResponse> bankSupplyOf(String denom) {
        return bankStub.supplyOf(cosmos.bank.v1beta1.QuerySupplyOfRequest.newBuilder().setDenom(denom).build());
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankTotalSupply() {
        return this.bankTotalSupply(null);
    }

    public ListenableFuture<cosmos.bank.v1beta1.QueryTotalSupplyResponse> bankTotalSupply(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.bank.v1beta1.QueryTotalSupplyRequest.Builder builder = cosmos.bank.v1beta1.QueryTotalSupplyRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return bankStub.totalSupply(builder.build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolResponse> distributionCommunityPool() {
        return distributionStub.communityPool(cosmos.distribution.v1beta1.QueryOuterClass.QueryCommunityPoolRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsResponse> distributionDelegationRewards(String delegatorAddress) {
        return distributionStub.delegationRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationRewardsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsResponse> distributionDelegationTotalRewardsAddress(String delegatorAddress) {
        return distributionStub.delegationTotalRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegationTotalRewardsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> distributionDelegatorValidators(String delegatorAddress) {
        return distributionStub.delegatorValidators(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressResponse> distributionDelegatorWithdrawAddress(String delegatorAddress) {
        return distributionStub.delegatorWithdrawAddress(cosmos.distribution.v1beta1.QueryOuterClass.QueryDelegatorWithdrawAddressRequest.newBuilder().setDelegatorAddress(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryParamsResponse> distributionParams() {
        return distributionStub.params(cosmos.distribution.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionResponse> distributionValidatorCommission(String validatorAddress) {
        return distributionStub.validatorCommission(cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorCommissionRequest.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsResponse> distributionValidatorOutstandingRewards(String validatorAddress) {
        return distributionStub.validatorOutstandingRewards(cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorOutstandingRewardsRequest.newBuilder().setValidatorAddress(validatorAddress).build());
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionValidatorSlashes(String validatorAddress) {
        return this.distributionValidatorSlashes(validatorAddress, null);
    }

    public ListenableFuture<cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesResponse> distributionValidatorSlashes(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesRequest.Builder builder = cosmos.distribution.v1beta1.QueryOuterClass.QueryValidatorSlashesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return distributionStub.validatorSlashes(builder.setValidatorAddress(validatorAddress).build());
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceResponse> evidenceAllEvidence(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceRequest.Builder builder = cosmos.evidence.v1beta1.QueryOuterClass.QueryAllEvidenceRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return evidenceStub.allEvidence(builder.build());
    }

    public ListenableFuture<cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceResponse> evidenceEvidence(ByteString evidenceHash) {
        return evidenceStub.evidence(cosmos.evidence.v1beta1.QueryOuterClass.QueryEvidenceRequest.newBuilder().setEvidenceHash(evidenceHash).build());
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantAllowances(String grantee) {
        return this.feegrantAllowances(grantee, null);
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesResponse> feegrantAllowances(String grantee, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesRequest.Builder builder = cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowancesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return feeGrantStub.allowances(builder.setGrantee(grantee).build());
    }

    public ListenableFuture<cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceResponse> feegrantAllowance(String grantee, String granter) {
        return feeGrantStub.allowance(cosmos.feegrant.v1beta1.QueryOuterClass.QueryAllowanceRequest.newBuilder().setGrantee(grantee).setGranter(granter).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositResponse> govDepositV1(String depositor, Long proposalId) {
        return govStub1.deposit(cosmos.gov.v1beta1.QueryDepositRequest.newBuilder().setDepositor(depositor).setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govDepositsV1(Long proposalId) {
        return this.govDepositsV1(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryDepositsResponse> govDepositsV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.gov.v1beta1.QueryDepositsRequest.Builder builder = cosmos.gov.v1beta1.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return govStub1.deposits(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryParamsResponse> govParamsV1(String paramsType) {
        return govStub1.params(cosmos.gov.v1beta1.QueryParamsRequest.newBuilder().setParamsType(paramsType).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalResponse> govProposalV1(Long proposalId) {
        return govStub1.proposal(cosmos.gov.v1beta1.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govProposalsV1() {
        return this.govProposalsV1(null, null, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryProposalsResponse> govProposalsV1(
            String depositor,
            String voter,
            cosmos.gov.v1beta1.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        cosmos.gov.v1beta1.QueryProposalsRequest.Builder builder = cosmos.gov.v1beta1.QueryProposalsRequest.newBuilder();
        if (depositor != null) {
            builder.setDepositor(depositor);
        }
        if (voter != null) {
            builder.setVoter(voter);
        }
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        if (proposalStatus != null) {
            builder.setProposalStatus(proposalStatus);
        }
        return govStub1.proposals(builder.build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryTallyResultResponse> govTallyResultV1(Long proposalId) {
        return govStub1.tallyResult(cosmos.gov.v1beta1.QueryTallyResultRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govVotesV1(Long proposalId) {
        return this.govVotesV1(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVotesResponse> govVotesV1(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.gov.v1beta1.QueryVotesRequest.Builder builder = cosmos.gov.v1beta1.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return govStub1.votes(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govVoteV1(Long proposalId) {
        return this.govVoteV1(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta1.QueryVoteResponse> govVoteV1(Long proposalId, String voter) {
        cosmos.gov.v1beta1.QueryVoteRequest.Builder builder = cosmos.gov.v1beta1.QueryVoteRequest.newBuilder();
        if (voter != null) {
            builder.setVoter(voter);
        }
        return govStub1.vote(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositResponse> govDepositV2(String depositor, Long proposalId) {
        return govStub2.deposit(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositRequest.newBuilder().setDepositor(depositor).setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govDepositsV2(Long proposalId) {
        return this.govDepositsV2(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsResponse> govDepositsV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryDepositsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return govStub2.deposits(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsResponse> govParamsV2(String paramsType) {
        return govStub2.params(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryParamsRequest.newBuilder().setParamsType(paramsType).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalResponse> govProposalV2(Long proposalId) {
        return govStub2.proposal(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govProposalsV2() {
        return this.govProposalsV2(null, null, null, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsResponse> govProposalsV2(
            String depositor,
            String voter,
            cosmos.gov.v1beta2.Gov.ProposalStatus proposalStatus,
            cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest
    ) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryProposalsRequest.newBuilder();
        if (depositor != null) {
            builder.setDepositor(depositor);
        }
        if (voter != null) {
            builder.setVoter(voter);
        }
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        if (proposalStatus != null) {
            builder.setProposalStatus(proposalStatus);
        }
        return govStub2.proposals(builder.build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultResponse> govTallyResultV2(Long proposalId) {
        return govStub2.tallyResult(cosmos.gov.v1beta2.CosmosGovQueryProto.QueryTallyResultRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govVotesV2(Long proposalId) {
        return this.govVotesV2(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesResponse> govVotesV2(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVotesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return govStub2.votes(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govVoteV2(Long proposalId) {
        return this.govVoteV2(proposalId, null);
    }

    public ListenableFuture<cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteResponse> govVoteV2(Long proposalId, String voter) {
        cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.Builder builder = cosmos.gov.v1beta2.CosmosGovQueryProto.QueryVoteRequest.newBuilder();
        if (voter != null) {
            builder.setVoter(voter);
        }
        return govStub2.vote(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoResponse> groupGroupAccountInfo(String address) {
        return groupStub.groupAccountInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountInfoRequest.newBuilder().setAddress(address).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupGroupAccountsByGroup(Long groupId) {
        return this.groupGroupAccountsByGroup(groupId, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupResponse> groupGroupAccountsByGroup(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByGroupRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return groupStub.groupAccountsByGroup(builder.setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupGroupAccountsByAdmin(String admin) {
        return this.groupGroupAccountsByAdmin(admin, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminResponse> groupGroupAccountsByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupAccountsByAdminRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return groupStub.groupAccountsByAdmin(builder.setAdmin(admin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupGroupByAdmin(String admin) {
        return this.groupGroupByAdmin(admin, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminResponse> groupGroupByAdmin(String admin, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByAdminRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return groupStub.groupsByAdmin(builder.setAdmin(admin).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupGroupByMember(String memberAddress) {
        return this.groupGroupByMember(memberAddress, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberResponse> groupGroupByMember(String memberAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupsByMemberRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return groupStub.groupsByMember(builder.setAddress(memberAddress).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoResponse> groupGroupAccountsByAdmin(Long groupId) {
        return groupStub.groupInfo(cosmos.group.v1beta1.QueryOuterClass.QueryGroupInfoRequest.newBuilder().setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupGroupMembers(Long groupId) {
        return this.groupGroupMembers(groupId, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersResponse> groupGroupMembers(Long groupId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryGroupMembersRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return groupStub.groupMembers(builder.setGroupId(groupId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalResponse> groupProposal(Long proposalId) {
        return groupStub.proposal(cosmos.group.v1beta1.QueryOuterClass.QueryProposalRequest.newBuilder().setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupProposalsByGroupAccount(String groupAddress) {
        return this.groupProposalsByGroupAccount(groupAddress, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountResponse> groupProposalsByGroupAccount(String groupAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryProposalsByGroupAccountRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return groupStub.proposalsByGroupAccount(builder.setAddress(groupAddress).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterResponse> groupVoteByProposalVoter(Long proposalId, String voter) {
        return groupStub.voteByProposalVoter(cosmos.group.v1beta1.QueryOuterClass.QueryVoteByProposalVoterRequest.newBuilder().setProposalId(proposalId).setVoter(voter).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupVotesByProposal(Long proposalId) {
        return this.groupVotesByProposal(proposalId, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalResponse> groupVotesByProposal(Long proposalId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByProposalRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return groupStub.votesByProposal(builder.setProposalId(proposalId).build());
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupVotesByVoter(String voter) {
        return this.groupVotesByVoter(voter, null);
    }

    public ListenableFuture<cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterResponse> groupVotesByVoter(String voter, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.Builder builder = cosmos.group.v1beta1.QueryOuterClass.QueryVotesByVoterRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return groupStub.votesByVoter(builder.setVoter(voter).build());
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsResponse> mintAnnualProvisions() {
        return mintStub.annualProvisions(cosmos.mint.v1beta1.QueryOuterClass.QueryAnnualProvisionsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryInflationResponse> mintInflation() {
        return mintStub.inflation(cosmos.mint.v1beta1.QueryOuterClass.QueryInflationRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.mint.v1beta1.QueryOuterClass.QueryParamsResponse> mintParams() {
        return mintStub.params(cosmos.mint.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceResponse> nftBalance(String owner) {
        return this.nftBalance(owner, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceResponse> nftBalance(String owner, String classId) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryBalanceRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        return nftStub.balance(builder.setOwner(owner).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassResponse> nftClass(String classId) {
        return nftStub.class_(cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassRequest.newBuilder().setClassId(classId).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftClasses() {
        return this.nftClasses(null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesResponse> nftClasses(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryClassesRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return nftStub.classes(builder.build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTResponse> nftNFT(String id, String classId) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        return nftStub.nFT(builder.setId(id).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftClasses(String owner, String classId) {
        return this.nftNftsOfClass(owner, classId, null);
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassResponse> nftNftsOfClass(String owner, String classId, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryNFTsOfClassRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return nftStub.nFTsOfClass(builder.setOwner(owner).setClassId(classId).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerResponse> nftOwner(String id, String classId) {
        cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerRequest.Builder builder = cosmos.nft.v1beta1.CosmosNftQueryProto.QueryOwnerRequest.newBuilder();
        if (classId != null) {
            builder.setClassId(classId);
        }
        return nftStub.owner(builder.setId(id).build());
    }

    public ListenableFuture<cosmos.nft.v1beta1.CosmosNftQueryProto.QuerySupplyResponse> nftSupply(String classId) {
        return nftStub.supply(cosmos.nft.v1beta1.CosmosNftQueryProto.QuerySupplyRequest.newBuilder().setClassId(classId).build());
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsParams() {
        return this.paramsParams(null);
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QueryParamsResponse> paramsParams(String subspace) {
        cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.Builder builder = cosmos.params.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder();
        if (subspace != null) {
            builder.setSubspace(subspace);
        }
        return paramsStub.params(builder.build());
    }

    public ListenableFuture<cosmos.params.v1beta1.QueryOuterClass.QuerySubspacesResponse> paramsSubspaces() {
        return paramsStub.subspaces(cosmos.params.v1beta1.QueryOuterClass.QuerySubspacesRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsResponse> slashingParams() {
        return slashingStub.params(cosmos.slashing.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoResponse> slashingSigningInfo(String consAddress) {
        return slashingStub.signingInfo(cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfoRequest.newBuilder().setConsAddress(consAddress).build());
    }

    public ListenableFuture<cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosResponse> slashingSigningInfos(cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.Builder builder = cosmos.slashing.v1beta1.QueryOuterClass.QuerySigningInfosRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return slashingStub.signingInfos(builder.build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationResponse> stakingDelegation(String delegatorAddress, String validatorAddress) {
        return stakingStub.delegation(cosmos.staking.v1beta1.QueryOuterClass.QueryDelegationRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(String delegatorAddress) {
        return this.stakingDelegatorDelegations(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return stakingStub.delegatorDelegations(builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(String delegatorAddress) {
        return this.stakingDelegatorUnbondingDelegations(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return stakingStub.delegatorUnbondingDelegations(builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(String delegatorAddress) {
        return this.stakingDelegatorValidators(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return stakingStub.delegatorValidators(builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorResponse> stakingDelegatorValidator(String delegatorAddress, String validatorAddress) {
        return stakingStub.delegatorValidator(cosmos.staking.v1beta1.QueryOuterClass.QueryDelegatorValidatorRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoResponse> stakingHistoricalInfo(Long height) {
        return stakingStub.historicalInfo(cosmos.staking.v1beta1.QueryOuterClass.QueryHistoricalInfoRequest.newBuilder().setHeight(height).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryParamsResponse> stakingParams() {
        return stakingStub.params(cosmos.staking.v1beta1.QueryOuterClass.QueryParamsRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryPoolResponse> stakingPool() {
        return stakingStub.pool(cosmos.staking.v1beta1.QueryOuterClass.QueryPoolRequest.newBuilder().build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingRedelegations(String delegatorAddress) {
        return this.stakingRedelegations(delegatorAddress, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsResponse> stakingRedelegations(String delegatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryRedelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return stakingStub.redelegations(builder.setDelegatorAddr(delegatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationResponse> stakingUnbondingDelegation(String delegatorAddress, String validatorAddress) {
        return stakingStub.unbondingDelegation(cosmos.staking.v1beta1.QueryOuterClass.QueryUnbondingDelegationRequest.newBuilder().setDelegatorAddr(delegatorAddress).setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorResponse> stakingValidator(String validatorAddress) {
        return stakingStub.validator(cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorRequest.newBuilder().setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsResponse> stakingValidator(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return stakingStub.validatorDelegations(builder.setValidatorAddr(validatorAddress).build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingValidators() {
        return this.stakingValidators(null, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingValidators(String status) {
        return this.stakingValidators(status, null);
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsResponse> stakingValidators(String status, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorsRequest.newBuilder();
        if (status != null) {
            builder.setStatus(status);
        }
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return stakingStub.validators(builder.build());
    }

    public ListenableFuture<cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsResponse> stakingValidatorUnbondingDelegations(String validatorAddress, cosmos.base.query.v1beta1.Pagination.PageRequest pageRequest) {
        cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.Builder builder = cosmos.staking.v1beta1.QueryOuterClass.QueryValidatorUnbondingDelegationsRequest.newBuilder();
        if (pageRequest != null) {
            builder.setPagination(pageRequest);
        }
        return stakingStub.validatorUnbondingDelegations(builder.setValidatorAddr(validatorAddress).build());
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

}