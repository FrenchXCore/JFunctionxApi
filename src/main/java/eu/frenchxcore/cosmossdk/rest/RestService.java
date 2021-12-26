package eu.frenchxcore.cosmossdk.rest;

import eu.frenchxcore.cosmossdk.types.tx.BroadcastMode;
import eu.frenchxcore.cosmossdk.types.tx.OrderBy;
import eu.frenchxcore.cosmossdk.types.tx.Tx;
import java.math.BigInteger;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {

    ///////////////////////////////////////////////////////
    ////////// authz
    ///////////////////////////////////////////////////////

    /**
     * Returns list of Authorization, granted to the grantee by the granter.
     *
     * @param granter
     * @param grantee
     * @param msgTypeUrl (Optional) when set, will query only grants matching
     * given msg type.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/authz/v1beta1/grants")
    Call<eu.frenchxcore.cosmossdk.query.authz.QueryGrantsResponse> authzGrants(
            @Path(value = "granter", encoded = false) String granter,
            @Path(value = "grantee", encoded = false) String grantee,
            @Path(value = "msg_type_url", encoded = false) String msgTypeUrl,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * GranterGrants returns list of Authorization, granted by granter.
     *
     * @param granter
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/authz/v1beta1/grants/{granter}")
    Call<eu.frenchxcore.cosmossdk.query.authz.QueryGrantsResponse> authzGranterGrants(
            @Path(value = "granter", encoded = false) String granter,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    ///////////////////////////////////////////////////////
    ////////// bank
    ///////////////////////////////////////////////////////

    /**
     * bankBalance queries the balance of a single coin for a single account..
     *
     * @param address is the address to query balances for.
     * @param denom is the coin denom to query balances for.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/balances/{address}/{denom}")
    Call<eu.frenchxcore.cosmossdk.query.bank.QueryBalanceResponse> bankBalance(
            @Path(value = "address", encoded = false) String address,
            @Path(value = "denom", encoded = false) String denom
    );

    /**
     * bankAllBalances queries the balance of all coins for a single account.
     *
     * @param address is the address to query balances for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/balances/{address}")
    Call<eu.frenchxcore.cosmossdk.query.bank.QueryAllBalancesResponse> bankAllBalances(
            @Path(value = "address", encoded = false) String address,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * bankTotalSupply queries the total supply of all coins.
     *
     * @return
     */
    @GET("/cosmos/bank/v1beta1/supply")
    Call<eu.frenchxcore.cosmossdk.query.bank.QueryTotalSupplyResponse> bankTotalSupply(
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * bankSupplyOf queries the supply of a single coin.
     *
     * @param denom is the coin denom to query balances for.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/supply/{denom}")
    Call<eu.frenchxcore.cosmossdk.query.bank.QuerySupplyOfResponse> bankSupplyOf(
            @Path(value = "denom", encoded = false) String denom
    );

    /**
     * bankParams queries the parameters of x/bank module.
     *
     * @return
     */
    @GET("/cosmos/bank/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.query.bank.QueryParamsResponse> bankParams();

    /**
     * bankDenomsMetadata queries the client metadata of a given coin
     * denomination.
     *
     * @param denom is the coin denom to query the metadata for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/denoms_metadata/{denom}")
    Call<eu.frenchxcore.cosmossdk.query.bank.QueryDenomMetadataResponse> bankDenomMetadata(
            @Path(value = "denom", encoded = false) String denom,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * bankDenomsMetadata queries the client metadata for all registered coin
     * denominations.
     *
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/denoms_metadata")
    Call<eu.frenchxcore.cosmossdk.query.bank.QueryDenomsMetadataResponse> bankDenomsMetadata();

    /**
     * bankDenomOwners queries for all account addresses that own a particular
     * token denomination.
     *
     * @param denom defines the coin denomination to query all account holders
     * for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/denoms_owners/{denom}")
    Call<eu.frenchxcore.cosmossdk.query.bank.QueryDenomOwnersResponse> bankDenomOwners(
            @Path(value = "denom", encoded = false) String denom,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    ///////////////////////////////////////////////////////
    ////////// base.reflection
    ///////////////////////////////////////////////////////

    /**
     * baseReflectionListAllInterfaces lists all the interfaces registered in
     * the interface registry.
     *
     * @param granter
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/base/reflection/v1beta1/interfaces")
    Call<eu.frenchxcore.cosmossdk.query.base.reflection.ListAllInterfacesResponse> baseReflectionListAllInterfaces();

    /**
     * baseReflectionListImplementations list all the concrete types that
     * implement a given interface.
     *
     * @param interfaceName interface_name defines the interface to query the
     * implementations for.
     * @return
     */
    @GET("/cosmos/base/reflection/v1beta1/interfaces/{interface_name}/implementations")
    Call<eu.frenchxcore.cosmossdk.query.base.reflection.ListImplementationsResponse> baseReflectionListImplementations(
            @Path(value = "interface_name", encoded = false) String interfaceName
    );

    /**
     * baseReflectionGetAuthnDescriptor returns information on how to
     * authenticate transactions in the application. NOTE: this RPC is still
     * experimental and might be subject to breaking changes or removal in
     * future releases of the cosmos-sdk.
     *
     * @return
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/authn")
    Call<eu.frenchxcore.cosmossdk.query.base.reflection.GetAuthnDescriptorResponse> baseReflectionGetAuthnDescriptor();

    /**
     * baseReflectionGetChainDescriptor returns the description of the chain
     *
     * @return
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/chain")
    Call<eu.frenchxcore.cosmossdk.query.base.reflection.GetChainDescriptorResponse> baseReflectionGetChainDescriptor();

    /**
     * baseReflectionGetCodecDescriptor returns the descriptor of the codec of
     * the application
     *
     * @return
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/codec")
    Call<eu.frenchxcore.cosmossdk.query.base.reflection.GetCodecDescriptorResponse> baseReflectionGetCodecDescriptor();

    /**
     * baseReflectionGetConfigurationDescriptor returns the descriptor for the
     * sdk.Config of the application
     *
     * @return
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/configuration")
    Call<eu.frenchxcore.cosmossdk.query.base.reflection.GetConfigurationDescriptorResponse> baseReflectionGetConfigurationDescriptor();

    /**
     * baseReflectionGetQueryServicesDescriptor returns the available gRPC
     * queryable services of the application
     *
     * @return
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/query_services")
    Call<eu.frenchxcore.cosmossdk.query.base.reflection.GetQueryServicesDescriptorResponse> baseReflectionGetQueryServicesDescriptor();

    /**
     * GetTxDescriptor returns information on the used transaction object and
     * available msgs that can be used
     *
     * @return
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/tx_descriptor")
    Call<eu.frenchxcore.cosmossdk.query.base.reflection.GetTxDescriptorResponse> baseReflectionGetTxDescriptor();

    ///////////////////////////////////////////////////////
    ////////// base.tendermint
    ///////////////////////////////////////////////////////

    /**
     * baseTendermintGetNodeInfo queries the current node info.
     *
     * @return
     */
    @GET("/cosmos/base/tendermint/v1beta1/node_info")
    Call<eu.frenchxcore.cosmossdk.query.base.tendermint.GetNodeInfoResponse> baseTendermintGetNodeInfo();

    /**
     * baseTendermintGetSyncing queries node syncing.
     *
     * @return
     */
    @GET("/cosmos/base/tendermint/v1beta1/syncing")
    Call<eu.frenchxcore.cosmossdk.query.base.tendermint.GetSyncingResponse> baseTendermintGetSyncing();

    /**
     * baseTendermintGetLatestBlock returns the latest block.
     *
     * @return
     */
    @GET("/cosmos/base/tendermint/v1beta1/blocks/latest")
    Call<eu.frenchxcore.cosmossdk.query.base.tendermint.GetLatestBlockResponse> baseTendermintGetLatestBlock();

    /**
     * baseTendermintGetBlockByHeight queries block for given height.
     *
     * @param height
     * @return
     */
    @GET("/cosmos/base/tendermint/v1beta1/blocks/{height}")
    Call<eu.frenchxcore.cosmossdk.query.base.tendermint.GetBlockByHeightResponse> baseTendermintGetBlockByHeight(
            @Path(value = "height", encoded = false) BigInteger height
    );

    /**
     * baseTendermintGetLatestValidatorSet queries latest validator-set.
     *
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/base/tendermint/v1beta1/validatorsets/latest")
    Call<eu.frenchxcore.cosmossdk.query.base.tendermint.GetLatestValidatorSetResponse> baseTendermintGetLatestValidatorSet(
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * baseTendermintGetValidatorSetByHeight queries validator-set at a given
     * height.
     *
     * @param height
     * @return
     */
    @GET("/cosmos/base/tendermint/v1beta1/validatorsets/{height}")
    Call<eu.frenchxcore.cosmossdk.query.base.tendermint.GetValidatorSetByHeightResponse> baseTendermintGetValidatorSetByHeight(
            @Path(value = "height", encoded = false) BigInteger height,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    ///////////////////////////////////////////////////////
    ////////// distribution
    ///////////////////////////////////////////////////////

    /**
     * distributionParams queries params of the distribution module.
     *
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryParamsResponse> distributionParams();

    /**
     * distributionValidatorOutstandingRewards queries rewards of a validator
     * address.
     *
     * @param validatorAddress defines the validator address to query for.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/validators/{validator_address}/outstanding_rewards")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryValidatorOutstandingRewardsResponse> distributionValidatorOutstandingRewards(
            @Path(value = "validator_address", encoded = false) String validatorAddress
    );

    /**
     * distributionValidatorCommission queries accumulated commission for a
     * validator.
     *
     * @param validatorAddress defines the validator address to query for.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/validators/{validator_address}/commission")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryValidatorCommissionResponse> distributionValidatorCommission(
            @Path(value = "validator_address", encoded = false) String validatorAddress
    );

    /**
     * distributionValidatorSlashes queries slash events of a validator.
     *
     * @param validatorAddress defines the validator address to query for.
     * @param startingHeight defines the optional starting height to query the
     * slashes.
     * @param endingHeight defines the optional ending height to query the
     * slashes.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/validators/{validator_address}/slashes")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryValidatorSlashesResponse> distributionValidatorSlashes(
            @Path(value = "validator_address", encoded = false) String validatorAddress,
            @Query(value = "starting_height", encoded = false) String startingHeight,
            @Query(value = "ending_height", encoded = false) String endingHeight,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * distributionDelegationRewards queries the total rewards accrued by a
     * delegation.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param validatorAddress defines the validator address to query for.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/rewards/{validator_address}")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryDelegationRewardsResponse> distributionDelegationRewards(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Path(value = "validator_address", encoded = false) String validatorAddress
    );

    /**
     * distributionDelegationTotalRewards queries the total rewards accrued by a
     * each validator.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/rewards")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryDelegationTotalRewardsResponse> distributionDelegationTotalRewards(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress
    );

    /**
     * distributionDelegatorValidators queries the validators of a delegator.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/validators")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryDelegatorValidatorsResponse> distributionDelegatorValidators(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress
    );

    /**
     * distributionDelegatorWithdrawAddress queries withdraw address of a
     * delegator.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/withdraw_address")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryDelegatorWithdrawAddressResponse> distributionDelegatorWithdrawAddress(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress
    );

    /**
     * distributionCommunityPool queries the community pool coins.
     *
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/community_pool")
    Call<eu.frenchxcore.cosmossdk.query.distribution.QueryCommunityPoolResponse> distributionCommunityPool();

    ///////////////////////////////////////////////////////
    ////////// evidence
    ///////////////////////////////////////////////////////

    /**
     * evidenceEvidence queries evidence based on evidence hash.
     * 
     * @param evidenceHash defines the hash of the requested evidence.
     * @return 
     */
    @GET("/cosmos/evidence/v1beta1/evidence/{evidence_hash}")
    Call<eu.frenchxcore.cosmossdk.query.evidence.QueryEvidenceResponse> evidenceEvidence(
            @Path(value = "evidence_hash", encoded = false) String evidenceHash
    );

    /**
     * evidenceAllEvidence queries all evidence.
     * 
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/evidence/v1beta1/evidence")
    Call<eu.frenchxcore.cosmossdk.query.evidence.QueryAllEvidenceResponse> evidenceAllEvidence(
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    ///////////////////////////////////////////////////////
    ////////// feegrant
    ///////////////////////////////////////////////////////

    /**
     * Allowance returns fee granted to the grantee by the granter.
     * 
     * @param granter is the address of the user granting an allowance of their funds.
     * @param grantee is the address of the user being granted an allowance of another user's funds.
     * @return 
     */
    @GET("/cosmos/feegrant/v1beta1/allowance/{granter}/{grantee}")
    Call<eu.frenchxcore.cosmossdk.query.feegrant.QueryAllowanceResponse> feegrantAllowance(
            @Path(value = "granter", encoded = false) String granter,
            @Path(value = "grantee", encoded = false) String grantee
    );

    /**
     * Allowances returns all the grants for address.
     * 
     * @param grantee
     * @param pagination
     * @return 
     */
    @GET("/cosmos/feegrant/v1beta1/allowances/{grantee}")
    Call<eu.frenchxcore.cosmossdk.query.feegrant.QueryAllowancesResponse> feegrantAllowances(
            @Path(value = "grantee", encoded = false) String grantee,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    ///////////////////////////////////////////////////////
    ////////// gov
    ///////////////////////////////////////////////////////

    /**
     * govProposal queries proposal details based on ProposalID.
     *
     * @param proposalId defines the unique id of the proposal.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}")
    Call<eu.frenchxcore.cosmossdk.query.gov.QueryProposalResponse> govProposal(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId
    );

    /**
     * govProposals queries all proposals based on given status.
     *
     * @param proposalStatus defines the status of the proposals.
     * @param voter defines the voter address for the proposals.
     * @param depositor defines the deposit addresses from the proposals.
     * @param pagination an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals}")
    Call<eu.frenchxcore.cosmossdk.query.gov.QueryProposalsResponse> govProposals(
            @Query(value = "proposal_status", encoded = false) eu.frenchxcore.cosmossdk.types.gov.ProposalStatus proposalStatus,
            @Query(value = "voter", encoded = false) String voter,
            @Query(value = "depositor", encoded = false) String depositor,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * govVote queries voted information based on proposalID, voterAddr.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param voter defines the oter address for the proposals.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/votes/{voter}")
    Call<eu.frenchxcore.cosmossdk.query.gov.QueryVoteResponse> govVote(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId,
            @Path(value = "voter", encoded = false) String voter
    );

    /**
     * govVotes queries votes of a given proposal.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/votes")
    Call<eu.frenchxcore.cosmossdk.query.gov.QueryVotesResponse> govVotes(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * govParams queries all parameters of the gov module.
     *
     * @param paramsType defines which parameters to query for : can be one of
     * "voting", "tallying" or "deposit".
     * @return
     */
    @GET("/cosmos/gov/v1beta1/params/{params_type}")
    Call<eu.frenchxcore.cosmossdk.query.gov.QueryParamsResponse> govParams(
            @Path(value = "params_type", encoded = false) eu.frenchxcore.cosmossdk.types.gov.ParamsType paramsType
    );

    /**
     * govDeposit queries single deposit information based proposalID,
     * depositAddr.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param depositor defines the deposit addresses from the proposals.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/deposits/{depositor}")
    Call<eu.frenchxcore.cosmossdk.query.gov.QueryDepositResponse> govDeposit(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId,
            @Path(value = "depositor", encoded = false) String depositor
    );

    /**
     * govDeposits queries all deposits of a single proposal.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/deposits")
    Call<eu.frenchxcore.cosmossdk.query.gov.QueryDepositsResponse> govDeposits(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * govTallyResult queries the tally of a proposal vote.
     *
     * @param proposalId defines the unique id of the proposal.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/tally")
    Call<eu.frenchxcore.cosmossdk.query.gov.QueryTallyResultResponse> govTallyResult(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId
    );

    ///////////////////////////////////////////////////////
    ////////// group
    ///////////////////////////////////////////////////////

    /**
     * groupGroupInfo queries group info based on group id.
     * 
     * @param groupId is the unique ID of the group.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/group_info/{group_id}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryGroupInfoResponse> groupGroupInfo(
            @Path(value = "group_id", encoded = false) BigInteger groupId
    );

    /**
     * groupGroupAccountInfo queries group account info based on group account address.
     * 
     * @param address is the account address of the group account.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/group_account_info/{address}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryGroupAccountInfoResponse> groupGroupAccountInfo(
            @Path(value = "address", encoded = false) String address
    );

    /**
     * groupGroupMembers queries members of a group
     * 
     * @param groupId is the unique ID of the group.
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/group_members/{group_id}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryGroupMembersResponse> groupGroupMembers(
            @Path(value = "group_id", encoded = false) BigInteger groupId,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * groupGroupsByAdmin queries groups by admin address.
     * 
     * @param admin is the account address of a group's admin.
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/groups_by_admin/{admin}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryGroupsByAdminResponse> groupGroupsByAdmin(
            @Path(value = "admin", encoded = false) String admin,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * groupGroupAccountsByGroup queries group accounts by group id.
     * 
     * @param groupId is the unique ID of the group account's group.
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/group_accounts_by_group/{group_id}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryGroupAccountsByGroupResponse> groupGroupAccountsByGroup(
            @Path(value = "group_id", encoded = false) BigInteger groupId,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * groupGroupsByAdmin queries group accounts by admin address.
     * 
     * @param admin is the admin address of the group account.
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/group_accounts_by_admin/{admin}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryGroupAccountsByAdminResponse> groupGroupAccountsByAdmin(
            @Path(value = "admin", encoded = false) String admin,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * groupProposal queries a proposal based on proposal id.
     * 
     * @param proposalId is the unique ID of a proposal.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/proposal/{proposal_id}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryProposalResponse> groupProposal(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId
    );

    /**
     * groupProposalsByGroupAccount queries proposals based on group account address.
     * 
     * @param address is the group account address related to proposals.
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/proposals_by_group_account/{address}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryProposalsByGroupAccountResponse> groupProposalsByGroupAccount(
            @Path(value = "address", encoded = false) String address,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * groupVoteByProposalVoter queries a vote by proposal id and voter.
     * 
     * @param proposalId is the unique ID of a proposal.
     * @param voter is a proposal voter account address.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/vote_by_proposal_voter/{proposal_id}/{voter}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryVoteByProposalVoterResponse> groupVoteByProposalVoter(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId,
            @Path(value = "voter", encoded = false) String voter
    );

    /**
     * groupVotesByProposal queries a vote by proposal.
     * 
     * @param proposalId is the unique ID of a proposal.
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/votes_by_proposal/{proposal_id}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryVotesByProposalResponse> groupVotesByProposal(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * groupVotesByVoter queries a vote by voter.
     * 
     * @param voter is a proposal voter account address.
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/votes_by_voter/{voter}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryVotesByVoterResponse> groupVotesByVoter(
            @Path(value = "voter", encoded = false) String voter,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * groupGroupsByMember queries groups by member address.
     * 
     * @param address is the group member address.
     * @param pagination defines an optional pagination for the request.
     * @return 
     */
    @GET("/cosmos/group/v1beta1/groups_by_member/{address}")
    Call<eu.frenchxcore.cosmossdk.query.group.QueryGroupsByMemberResponse> groupGroupsByMember(
            @Path(value = "address", encoded = false) String address,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    ///////////////////////////////////////////////////////
    ////////// mint
    ///////////////////////////////////////////////////////

    /**
     * mintParams returns the total set of minting parameters.
     *
     * @return
     */
    @GET("/cosmos/mint/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.query.mint.QueryParamsResponse> mintParams();

    /**
     * mintInflation returns the current minting inflation value.
     *
     * @return
     */
    @GET("/cosmos/mint/v1beta1/inflation")
    Call<eu.frenchxcore.cosmossdk.query.mint.QueryInflationResponse> mintInflation();

    /**
     * mintAnnualProvisions current minting annual provisions value.
     *
     * @return
     */
    @GET("/cosmos/mint/v1beta1/annual_provisions")
    Call<eu.frenchxcore.cosmossdk.query.mint.QueryAnnualProvisionsResponse> mintAnnualProvisions();

    ///////////////////////////////////////////////////////
    ////////// nft
    ///////////////////////////////////////////////////////

    /**
     * nftBalance queries the number of NFTs of a given class owned by the
     * owner, same as balanceOf in ERC721
     *
     * @param owner
     * @param classId
     * @return
     */
    @GET("/cosmos/nft/v1beta1/balance/{owner]/{class_id}")
    Call<eu.frenchxcore.cosmossdk.query.nft.QueryBalanceResponse> nftBalance(
            @Path(value = "owner", encoded = false) String owner,
            @Path(value = "class_id", encoded = false) String classId
    );

    /**
     * nftOwner queries the owner of the NFT based on its class and id, same as
     * ownerOf in ERC721
     *
     * @param classId
     * @param id
     * @return
     */
    @GET("/cosmos/nft/v1beta1/owner/{class_id}/{id}")
    Call<eu.frenchxcore.cosmossdk.query.nft.QueryOwnerResponse> nftOwner(
            @Path(value = "class_id", encoded = false) String classId,
            @Path(value = "id", encoded = false) String id
    );

    /**
     * nftSupply queries the number of NFTs from the given class, same as
     * totalSupply of ERC721.
     *
     * @param classId
     * @return
     */
    @GET("/cosmos/nft/v1beta1/supply/{class_id}")
    Call<eu.frenchxcore.cosmossdk.query.nft.QuerySupplyResponse> nftSupply(
            @Path(value = "class_id", encoded = false) String classId
    );

    /**
     * nftNFTsOfClass queries all NFTs of a given class or optional owner,
     * similar to tokenByIndex in ERC721Enumerable
     *
     * @param classId
     * @return
     */
    @GET("/cosmos/nft/v1beta1/nfts/{class_id}")
    Call<eu.frenchxcore.cosmossdk.query.nft.QueryNFTsOfClassResponse> nftNFTsOfClass(
            @Path(value = "class_id", encoded = false) String classId
    );

    /**
     * nftNFT queries an NFT based on its class and id.
     *
     * @param classId
     * @param id
     * @return
     */
    @GET("/cosmos/nft/v1beta1/nfts/[class_id}/{id}")
    Call<eu.frenchxcore.cosmossdk.query.nft.QueryNFTResponse> nftNFT(
            @Path(value = "class_id", encoded = false) String classId,
            @Path(value = "id", encoded = false) String id
    );

    /**
     * nftClass queries an NFT class based on its id
     *
     * @param classId
     * @return
     */
    @GET("/cosmos/nft/v1beta1/classes/[class_id}")
    Call<eu.frenchxcore.cosmossdk.query.nft.QueryClassResponse> nftClass(
            @Path(value = "class_id", encoded = false) String classId
    );

    /**
     * nftClasses queries all NFT classes
     *
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/nft/v1beta1/classes")
    Call<eu.frenchxcore.cosmossdk.query.nft.QueryClassesResponse> nftClasses(
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    ///////////////////////////////////////////////////////
    ////////// params
    ///////////////////////////////////////////////////////

    /**
     * paramsParams queries a specific parameter of a module, given its subspace
     * and key.
     *
     * @param subspace defines the module to query the parameter for.
     * @param key defines the key of the parameter in the subspace.
     * @return
     */
    @GET("/cosmos/params/v1beta1/params/{subspace}/{key}")
    Call<eu.frenchxcore.cosmossdk.query.params.QueryParamsResponse> paramsParams(
            @Path(value = "subspace", encoded = false) String subspace,
            @Path(value = "key", encoded = false) String key
    );

    /**
     * paramsSubspaces queries for all registered subspaces and all keys for a
     * subspace.
     *
     * @return
     */
    @GET("/cosmos/params/v1beta1/subspaces")
    Call<eu.frenchxcore.cosmossdk.query.params.QuerySubspacesResponse> paramsSubspaces();

    ///////////////////////////////////////////////////////
    ////////// slashing
    ///////////////////////////////////////////////////////

    /**
     * slashingParams queries the parameters of slashing module
     *
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.query.slashing.QueryParamsResponse> slashingParams();

    /**
     * slashingSigningInfo queries the signing info of given cons address
     *
     * @param consAddress is the address to query signing info of
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/signing_infos/{cons_address}")
    Call<eu.frenchxcore.cosmossdk.query.slashing.QuerySigningInfoResponse> slashingSigningInfo(
            @Path(value = "cons_address", encoded = false) String consAddress
    );

    /**
     * slashingSigningInfos queries signing info of all validators
     *
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/signing_infos")
    Call<eu.frenchxcore.cosmossdk.query.slashing.QuerySigningInfosResponse> slashingSigningInfos(
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    ///////////////////////////////////////////////////////
    ////////// staking
    ///////////////////////////////////////////////////////

    /**
     * stakingValidators queries all validators that match the given status.
     *
     * @param status enables to query for validators matching a given status.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryValidatorsResponse> stakingValidators(
            @Query(value = "status", encoded = false) eu.frenchxcore.cosmossdk.types.staking.BondStatus status,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * stakingValidator queries validator info for given validator address.
     *
     * @param validatorAddress defines the validator address to query for.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_addr}")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryValidatorResponse> stakingValidator(
            @Query(value = "validator_addr", encoded = false) String validatorAddress
    );

    /**
     * stakingValidatorDelegations queries delegate info for given validator.
     *
     * @param validatorAddress defines the validator address to query for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_address}/delegations")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryValidatorDelegationsResponse> stakingValidatorDelegations(
            @Path(value = "validator_address", encoded = false) String validatorAddress,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * stakingValidatorUnbondingDelegations queries unbonding delegations of a
     * validator.
     *
     * @param validatorAddress defines the validator address to query for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_address}/unbonding_delegations")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryValidatorUnbondingDelegationsResponse> stakingValidatorUnbondingDelegations(
            @Path(value = "validator_address", encoded = false) String validatorAddress,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * stakingDelegation queries delegate info for given validator delegator
     * pair.
     *
     * @param validatorAddress defines the validator address to query for.
     * @param delegatorAddress defines the delegator address to query for.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_address}/delegations/{delegator_address}")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryDelegationResponse> stakingDelegation(
            @Path(value = "validator_address", encoded = false) String validatorAddress,
            @Path(value = "delegator_address", encoded = false) String delegatorAddress
    );

    /**
     * stakingUnbondingDelegation queries unbonding info for given validator
     * delegator pair.
     *
     * @param validatorAddress defines the validator address to query for.
     * @param delegatorAddress defines the delegator address to query for.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_address}/delegations/{delegator_address}/unbonding_delegation")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryUnbondingDelegationResponse> stakingUnbondingDelegation(
            @Path(value = "validator_address", encoded = false) String validatorAddress,
            @Path(value = "delegator_address", encoded = false) String delegatorAddress
    );

    /**
     * stakingDelegatorDelegations queries all delegations of a given delegator
     * address.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegations/{delegator_address}")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * stakingDelegatorUnbondingDelegations queries all unbonding delegations of
     * a given delegator address.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegators/{delegator_address}/unbonding_delegations")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * stakingRedelegations queries redelegations of given address.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param sourceValidatorAddress defines the validator address to redelegate
     * from.
     * @param destinationValidatorAddress defines the validator address to
     * redelegate to.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegators/{delegator_address}/redelegations")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryRedelegationsResponse> stakingRedelegations(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Query(value = "src_validator_address", encoded = false) String sourceValidatorAddress,
            @Query(value = "dst_validator_address", encoded = false) String destinationValidatorAddress,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * stakingDelegatorValidators queries all validators info for given
     * delegator address.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegators/{delegator_address}/validators")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination
    );

    /**
     * stakingDelegatorValidator queries validator info for given delegator
     * validator pair.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param validatorAddress defines the validator address to query for.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegators/{delegator_address}/validators/{validator_address}")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryDelegatorValidatorResponse> stakingDelegatorValidator(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Path(value = "validator_address", encoded = false) String validatorAddress
    );

    /**
     * stakingHistoricalInfo queries the historical info for given height.
     *
     * @param height defines at which height to query the historical info.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/historical_info/{height}")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryHistoricalInfoResponse> stakingHistoricalInfo(
            @Path(value = "height", encoded = false) BigInteger height
    );

    /**
     * stakingPool queries the pool info.
     *
     * @return
     */
    @GET("/cosmos/staking/v1beta1/pool")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryPoolResponse> stakingPool();

    /**
     * stakingParameters queries the staking parameters.
     *
     * @return
     */
    @GET("/cosmos/staking/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.query.staking.QueryParamsResponse> stakingParams();

    ///////////////////////////////////////////////////////
    ////////// tx
    ///////////////////////////////////////////////////////

    /**
     * txSimulate simulates executing a transaction for estimating gas usage.
     * 
     * @param tx Deprecated. tx is the transaction to simulate. Deprecated. Send raw tx bytes instead.
     * @param tx_bytes is the raw transaction.
     * @return 
     */
    @POST("/cosmos/tx/v1beta1/simulate")
    Call<eu.frenchxcore.cosmossdk.query.tx.SimulateResponse> txSimulate(
        @Query(value = "tx", encoded = false) Tx tx,
        @Query(value = "tx_bytes", encoded = false) String txBytes
    );

    /**
     * txGetTx fetches a tx by hash.
     * 
     * @param hash is the tx hash to query, encoded as a hex string.
     * @return 
     */
    @GET("/cosmos/tx/v1beta1/txs/{hash}")
    Call<eu.frenchxcore.cosmossdk.query.tx.GetTxResponse> txGetTx(
        @Query(value = "hash", encoded = false) String hash
    );

    /**
     * txBroadcastTx broadcast transaction.
     * 
     * @param tx_bytes is the raw transaction.
     * @param mode
     * @return 
     */
    @POST("/cosmos/tx/v1beta1/txs")
    Call<eu.frenchxcore.cosmossdk.query.tx.BroadcastTxResponse> txBroadcastTx(
        @Query(value = "tx_bytes", encoded = false) String txBytes,
        @Query(value = "mode", encoded = false) BroadcastMode mode
    );

    /**
     * txGetTxsEvent fetches txs by event.
     * 
     * @return 
     */
    @GET("/cosmos/tx/v1beta1/txs")
    Call<eu.frenchxcore.cosmossdk.query.tx.GetTxsEventResponse> txGetTxsEvent(
        @Query(value = "events", encoded = false) List<String> events,
        @Query(value = "pagination", encoded = false) eu.frenchxcore.cosmossdk.types.query.PageRequest pagination,
        @Query(value = "order_by", encoded = false) OrderBy order_by
    );

    ///////////////////////////////////////////////////////
    ////////// upgrade
    ///////////////////////////////////////////////////////

    /**
     * upgradeCurrentPlan queries the current upgrade plan.
     * 
     * @return 
     */
    @GET("/cosmos/upgrade/v1beta1/current_plan")
    Call<eu.frenchxcore.cosmossdk.query.upgrade.QueryCurrentPlanResponse> upgradeCurrentPlan();

    /**
     * upgradeAppliedPlan queries a previously applied upgrade plan by its name.
     * 
     * @param name name is the name of the applied plan to query for.
     * @return 
     */
    @GET("/cosmos/upgrade/v1beta1/applied_plan/{name}")
    Call<eu.frenchxcore.cosmossdk.query.upgrade.QueryAppliedPlanResponse> upgradeAppliedPlan(
        @Path(value = "name", encoded = false) String name
    );

    /**
     * upgradeUpgradedConsensusState queries the consensus state that will serve as a trusted kernel for the next version of this chain. It will only be stored at the last height of this chain. UpgradedConsensusState RPC not supported with legacy querier This rpc is deprecated now that IBC has its own replacement (https://github.com/cosmos/ibc-go/blob/2c880a22e9f9cc75f62b527ca94aa75ce1106001/proto/ibc/core/client/v1/query.proto#L54)
     * 
     * @param last_height
     * @return 
     */
    @GET("/cosmos/upgrade/v1beta1/upgraded_consensus_state/{last_height}")
    Call<eu.frenchxcore.cosmossdk.query.upgrade.QueryUpgradedConsensusStateResponse> upgradeUpgradedConsensusState(
        @Path(value = "last_height", encoded = false) BigInteger lastHeight
    );

}
