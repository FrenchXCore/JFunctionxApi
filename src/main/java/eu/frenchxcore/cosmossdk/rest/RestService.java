package eu.frenchxcore.cosmossdk.rest;

import java.math.BigInteger;
import eu.frenchxcore.cosmossdk.types._base.BondStatus;
import eu.frenchxcore.cosmossdk.types._base.ParamsType;
import eu.frenchxcore.cosmossdk.types._base.ProposalStatus;
import eu.frenchxcore.cosmossdk.types._base.query.PageRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {

    /**
     * bankBalance queries the balance of a single coin for a single account..
     *
     * @param address is the address to query balances for.
     * @param denom is the coin denom to query balances for.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/balances/{address}/{denom}")
    Call<eu.frenchxcore.cosmossdk.types.bank.QueryBalanceResponse> bankBalance(
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
    Call<eu.frenchxcore.cosmossdk.types.bank.QueryAllBalancesResponse> bankAllBalances(
            @Path(value = "address", encoded = false) String address,
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * bankTotalSupply queries the total supply of all coins.
     *
     * @return
     */
    @GET("/cosmos/bank/v1beta1/supply")
    Call<eu.frenchxcore.cosmossdk.types.bank.QueryTotalSupplyResponse> bankTotalSupply(
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * bankSupplyOf queries the supply of a single coin.
     *
     * @param denom is the coin denom to query balances for.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/supply/{denom}")
    Call<eu.frenchxcore.cosmossdk.types.bank.QuerySupplyOfResponse> bankSupplyOf(
            @Path(value = "denom", encoded = false) String denom
    );

    /**
     * bankParams queries the parameters of x/bank module.
     *
     * @return
     */
    @GET("/cosmos/bank/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.types.bank.QueryParamsResponse> bankParams();

    /**
     * bankDenomsMetadata queries the client metadata of a given coin
     * denomination.
     *
     * @param denom is the coin denom to query the metadata for.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/denoms_metadata/{denom}")
    Call<eu.frenchxcore.cosmossdk.types.bank.QueryDenomMetadataResponse> bankDenomMetadata(
            @Path(value = "denom", encoded = false) String denom,
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * bankDenomsMetadata queries the client metadata for all registered coin
     * denominations.
     *
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/bank/v1beta1/denoms_metadata")
    Call<eu.frenchxcore.cosmossdk.types.bank.QueryDenomsMetadataResponse> bankDenomsMetadata();

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
    Call<eu.frenchxcore.cosmossdk.types.bank.QueryDenomOwnersResponse> bankDenomOwners(
            @Path(value = "denom", encoded = false) String denom,
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * distributionParams queries params of the distribution module.
     *
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryParamsResponse> distributionParams();

    /**
     * distributionValidatorOutstandingRewards queries rewards of a validator
     * address.
     *
     * @param validatorAddress defines the validator address to query for.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/validators/{validator_address}/outstanding_rewards")
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryValidatorOutstandingRewardsResponse> distributionValidatorOutstandingRewards(
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
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryValidatorCommissionResponse> distributionValidatorCommission(
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
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryValidatorSlashesResponse> distributionValidatorSlashes(
            @Path(value = "validator_address", encoded = false) String validatorAddress,
            @Query(value = "starting_height", encoded = false) String startingHeight,
            @Query(value = "ending_height", encoded = false) String endingHeight,
            @Query(value = "pagination", encoded = false) PageRequest pagination
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
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryDelegationRewardsResponse> distributionDelegationRewards(
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
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryDelegationTotalRewardsResponse> distributionDelegationTotalRewards(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress
    );

    /**
     * distributionDelegatorValidators queries the validators of a delegator.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/validators")
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryDelegatorValidatorsResponse> distributionDelegatorValidators(
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
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryDelegatorWithdrawAddressResponse> distributionDelegatorWithdrawAddress(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress
    );

    /**
     * distributionCommunityPool queries the community pool coins.
     *
     * @return
     */
    @GET("/cosmos/distribution/v1beta1/community_pool")
    Call<eu.frenchxcore.cosmossdk.types.distribution.QueryCommunityPoolResponse> distributionCommunityPool();

    /**
     * govProposal queries proposal details based on ProposalID.
     *
     * @param proposalId defines the unique id of the proposal.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}")
    Call<eu.frenchxcore.cosmossdk.types.gov.QueryProposalResponse> govProposal(
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
    Call<eu.frenchxcore.cosmossdk.types.gov.QueryProposalsResponse> govProposals(
            @Query(value = "proposal_status", encoded = false) ProposalStatus proposalStatus,
            @Query(value = "voter", encoded = false) String voter,
            @Query(value = "depositor", encoded = false) String depositor,
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * govVote queries voted information based on proposalID, voterAddr.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param voter defines the oter address for the proposals.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/votes/{voter}")
    Call<eu.frenchxcore.cosmossdk.types.gov.QueryVoteResponse> govVote(
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
    Call<eu.frenchxcore.cosmossdk.types.gov.QueryVotesResponse> govVotes(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId,
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * govParams queries all parameters of the gov module.
     *
     * @param paramsType defines which parameters to query for : can be one of
     * "voting", "tallying" or "deposit".
     * @return
     */
    @GET("/cosmos/gov/v1beta1/params/{params_type}")
    Call<eu.frenchxcore.cosmossdk.types.gov.QueryParamsResponse> govParams(
            @Path(value = "params_type", encoded = false) ParamsType paramsType
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
    Call<eu.frenchxcore.cosmossdk.types.gov.QueryDepositResponse> govDeposit(
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
    Call<eu.frenchxcore.cosmossdk.types.gov.QueryDepositsResponse> govDeposits(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId,
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * govTallyResult queries the tally of a proposal vote.
     *
     * @param proposalId defines the unique id of the proposal.
     * @return
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/tally")
    Call<eu.frenchxcore.cosmossdk.types.gov.QueryTallyResultResponse> govTallyResult(
            @Path(value = "proposal_id", encoded = false) BigInteger proposalId
    );

    /**
     * mintParams returns the total set of minting parameters.
     *
     * @return
     */
    @GET("/cosmos/mint/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.types.mint.QueryParamsResponse> mintParams();

    /**
     * mintInflation returns the current minting inflation value.
     *
     * @return
     */
    @GET("/cosmos/mint/v1beta1/inflation")
    Call<eu.frenchxcore.cosmossdk.types.mint.QueryInflationResponse> mintInflation();

    /**
     * mintAnnualProvisions current minting annual provisions value.
     *
     * @return
     */
    @GET("/cosmos/mint/v1beta1/annual_provisions")
    Call<eu.frenchxcore.cosmossdk.types.mint.QueryAnnualProvisionsResponse> mintAnnualProvisions();

    /**
     * nftBalance queries the number of NFTs of a given class owned by the
     * owner, same as balanceOf in ERC721
     *
     * @param owner
     * @param classId
     * @return
     */
    @GET("/cosmos/nft/v1beta1/balance/{owner]/{class_id}")
    Call<eu.frenchxcore.cosmossdk.types.nft.QueryBalanceResponse> nftBalance(
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
    Call<eu.frenchxcore.cosmossdk.types.nft.QueryOwnerResponse> nftOwner(
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
    Call<eu.frenchxcore.cosmossdk.types.nft.QuerySupplyResponse> nftSupply(
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
    Call<eu.frenchxcore.cosmossdk.types.nft.QueryNFTsOfClassResponse> nftNFTsOfClass(
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
    Call<eu.frenchxcore.cosmossdk.types.nft.QueryNFTResponse> nftNFT(
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
    Call<eu.frenchxcore.cosmossdk.types.nft.QueryClassResponse> nftClass(
            @Path(value = "class_id", encoded = false) String classId
    );

    /**
     * nftClasses queries all NFT classes
     *
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/nft/v1beta1/classes")
    Call<eu.frenchxcore.cosmossdk.types.nft.QueryClassesResponse> nftClasses(
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * paramsParams queries a specific parameter of a module, given its subspace
     * and key.
     *
     * @param subspace defines the module to query the parameter for.
     * @param key defines the key of the parameter in the subspace.
     * @return
     */
    @GET("/cosmos/params/v1beta1/params/{subspace}/{key}")
    Call<eu.frenchxcore.cosmossdk.types.params.QueryParamsResponse> paramsParams(
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
    Call<eu.frenchxcore.cosmossdk.types.params.QuerySubspacesResponse> paramsSubspaces();

    /**
     * slashingParams queries the parameters of slashing module
     *
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.types.slashing.QueryParamsResponse> slashingParams();

    /**
     * slashingSigningInfo queries the signing info of given cons address
     *
     * @param consAddress is the address to query signing info of
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/signing_infos/{cons_address}")
    Call<eu.frenchxcore.cosmossdk.types.slashing.QuerySigningInfoResponse> slashingSigningInfo(
            @Path(value = "cons_address", encoded = false) String consAddress
    );

    /**
     * slashingSigningInfos queries signing info of all validators
     *
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/signing_infos")
    Call<eu.frenchxcore.cosmossdk.types.slashing.QuerySigningInfosResponse> slashingSigningInfos(
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * stakingValidators queries all validators that match the given status.
     *
     * @param status enables to query for validators matching a given status.
     * @param pagination defines an optional pagination for the request.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators")
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryValidatorsResponse> stakingValidators(
            @Query(value = "status", encoded = false) BondStatus status,
            @Query(value = "pagination", encoded = false) PageRequest pagination
    );

    /**
     * stakingValidator queries validator info for given validator address.
     *
     * @param validatorAddress defines the validator address to query for.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_addr}")
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryValidatorResponse> stakingValidator(
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryValidatorDelegationsResponse> stakingValidatorDelegations(
            @Path(value = "validator_address", encoded = false) String validatorAddress,
            @Query(value = "pagination", encoded = false) PageRequest pagination
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryValidatorUnbondingDelegationsResponse> stakingValidatorUnbondingDelegations(
            @Path(value = "validator_address", encoded = false) String validatorAddress,
            @Query(value = "pagination", encoded = false) PageRequest pagination
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryDelegationResponse> stakingDelegation(
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryUnbondingDelegationResponse> stakingUnbondingDelegation(
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Query(value = "pagination", encoded = false) PageRequest pagination
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Query(value = "pagination", encoded = false) PageRequest pagination
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryRedelegationsResponse> stakingRedelegations(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Query(value = "src_validator_address", encoded = false) String sourceValidatorAddress,
            @Query(value = "dst_validator_address", encoded = false) String destinationValidatorAddress,
            @Query(value = "pagination", encoded = false) PageRequest pagination
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(
            @Path(value = "delegator_address", encoded = false) String delegatorAddress,
            @Query(value = "pagination", encoded = false) PageRequest pagination
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryDelegatorValidatorResponse> stakingDelegatorValidator(
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
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryHistoricalInfoResponse> stakingHistoricalInfo(
            @Path(value = "height", encoded = false) BigInteger height
    );

    /**
     * stakingPool queries the pool info.
     *
     * @return
     */
    @GET("/cosmos/staking/v1beta1/pool")
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryPoolResponse> stakingPool();

    /**
     * stakingParameters queries the staking parameters.
     *
     * @return
     */
    @GET("/cosmos/staking/v1beta1/params")
    Call<eu.frenchxcore.cosmossdk.types.staking.QueryParamsResponse> stakingParams();

}