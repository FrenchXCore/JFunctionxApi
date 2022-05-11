package eu.frenchxcore.api;

import eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.ParamsType;
import eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.ProposalStatus;
import eu.frenchxcore.model.cosmossdk.types.tx.BroadcastMode;
import eu.frenchxcore.model.cosmossdk.types.tx.OrderBy;
import eu.frenchxcore.model.cosmossdk.types.tx.Tx;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.math.BigInteger;
import java.util.List;

public interface CosmosRestService {

    ///////////////////////////////////////////////////////
    ////////// auth
    ///////////////////////////////////////////////////////
    /**
     * authAccounts returns all the existing accounts
     * Since: cosmos-sdk 0.43
     *
     * @return all existing accounts
     */
    @GET("/cosmos/auth/v1beta1/accounts")
    Call<eu.frenchxcore.model.cosmossdk.query.auth.QueryAccountsResponse> authAccounts();

    /**
     * authAccount returns account details based on address.
     *
     * @param address
     * @return Grants
     */
    @GET("/cosmos/auth/v1beta1/accounts/{address}")
    Call<eu.frenchxcore.model.cosmossdk.query.auth.QueryAccountResponse> authAccount(
            @Path(value = "address") String address
    );


    ///////////////////////////////////////////////////////
    ////////// authz
    ///////////////////////////////////////////////////////
    /**
     * Returns list of Authorization, granted to the grantee by the granter.
     *
     * @param granter granter
     * @param grantee grantee
     * @param msgTypeUrl (Optional) when set, will query only grants matching given msg type.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return Grants
     */
    @GET("/cosmos/authz/v1beta1/grants")
    Call<eu.frenchxcore.model.cosmossdk.query.authz.QueryGrantsResponse> authzGrants(
            @Path(value = "granter") String granter,
            @Path(value = "grantee") String grantee,
            @Path(value = "msg_type_url") String msgTypeUrl,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * GranterGrants returns list of Authorization, granted by granter.
     *
     * @param granter granter
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return Grants
     */
    @GET("/cosmos/authz/v1beta1/grants/{granter}")
    Call<eu.frenchxcore.model.cosmossdk.query.authz.QueryGrantsResponse> authzGranterGrants(
            @Path(value = "granter") String granter,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    ///////////////////////////////////////////////////////
    ////////// bank
    ///////////////////////////////////////////////////////
    /**
     * bankBalance queries the balance of a single coin for a single account..
     *
     * @param address is the address to query balances for.
     * @param denom is the coin denom to query balances for.
     * @return Balance
     */
    @GET("/cosmos/bank/v1beta1/balances/{address}/{denom}")
    Call<eu.frenchxcore.model.cosmossdk.query.bank.QueryBalanceResponse> bankBalance(
            @Path(value = "address") String address,
            @Path(value = "denom") String denom
    );

    /**
     * bankAllBalances queries the balance of all coins for a single account.
     *
     * @param address is the address to query balances for.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return AllBalances
     */
    @GET("/cosmos/bank/v1beta1/balances/{address}")
    Call<eu.frenchxcore.model.cosmossdk.query.bank.QueryAllBalancesResponse> bankAllBalances(
            @Path(value = "address") String address,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * bankTotalSupply queries the total supply of all coins.
     *
     * @return Total supply of all coins
     */
    @GET("/cosmos/bank/v1beta1/supply")
    Call<eu.frenchxcore.model.cosmossdk.query.bank.QueryTotalSupplyResponse> bankTotalSupply(
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * bankSupplyOf queries the supply of a single coin.
     *
     * @param denom is the coin denom to query balances for.
     * @return Supply of the denom coin
     */
    @GET("/cosmos/bank/v1beta1/supply/{denom}")
    Call<eu.frenchxcore.model.cosmossdk.query.bank.QuerySupplyOfResponse> bankSupplyOf(
            @Path(value = "denom") String denom
    );

    /**
     * bankParams queries the parameters of x/bank module.
     *
     * @return Bank module parameters
     */
    @GET("/cosmos/bank/v1beta1/params")
    Call<eu.frenchxcore.model.cosmossdk.query.bank.QueryParamsResponse> bankParams();

    /**
     * bankDenomsMetadata queries the client metadata of a given coin
     * denomination.
     *
     * @param denom is the coin denom to query the metadata for.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return Denom coin metadata
     */
    @GET("/cosmos/bank/v1beta1/denoms_metadata/{denom}")
    Call<eu.frenchxcore.model.cosmossdk.query.bank.QueryDenomMetadataResponse> bankDenomMetadata(
            @Path(value = "denom") String denom,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * bankDenomsMetadata queries the client metadata for all registered coin
     * denominations.
     *
     * @return All denom coins metadata
     */
    @GET("/cosmos/bank/v1beta1/denoms_metadata")
    Call<eu.frenchxcore.model.cosmossdk.query.bank.QueryDenomsMetadataResponse> bankDenomsMetadata();

    /**
     * bankDenomOwners queries for all account addresses that own a particular
     * token denomination (NOT IMPLEMENTED).
     *
     * @param denom defines the coin denomination to query all account holders
     * for.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return All denom coin owners
     */
    @GET("/cosmos/bank/v1beta1/denom_owners/{denom}")
    Call<eu.frenchxcore.model.cosmossdk.query.bank.QueryDenomOwnersResponse> bankDenomOwners(
            @Path(value = "denom") String denom,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    ///////////////////////////////////////////////////////
    ////////// base.reflection
    ///////////////////////////////////////////////////////
    /**
     * baseReflectionListAllInterfaces lists all the interfaces registered in
     * the interface registry.
     *
     * @return all interfaces registered.
     */
    @GET("/cosmos/base/reflection/v1beta1/interfaces")
    Call<eu.frenchxcore.model.cosmossdk.query.base.reflection.ListAllInterfacesResponse> baseReflectionListAllInterfaces();

    /**
     * baseReflectionListImplementations list all the concrete types that
     * implement a given interface.
     *
     * @param interfaceName interface_name defines the interface to query the
     * implementations for.
     * @return All the concrete types that implement the given interface name
     */
    @GET("/cosmos/base/reflection/v1beta1/interfaces/{interface_name}/implementations")
    Call<eu.frenchxcore.model.cosmossdk.query.base.reflection.ListImplementationsResponse> baseReflectionListImplementations(
            @Path(value = "interface_name") String interfaceName
    );

    /**
     * baseReflectionGetAuthnDescriptor returns information on how to
     * authenticate transactions in the application. NOTE: this RPC is still
     * experimental and might be subject to breaking changes or removal in
     * future releases of the cosmos-sdk.
     *
     * @return Information on how to authenticate transactions in the application
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/authn")
    Call<eu.frenchxcore.model.cosmossdk.query.base.reflection.GetAuthnDescriptorResponse> baseReflectionGetAuthnDescriptor();

    /**
     * baseReflectionGetChainDescriptor returns the description of the chain
     *
     * @return The description of the chain
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/chain")
    Call<eu.frenchxcore.model.cosmossdk.query.base.reflection.GetChainDescriptorResponse> baseReflectionGetChainDescriptor();

    /**
     * baseReflectionGetCodecDescriptor returns the descriptor of the codec of
     * the application
     *
     * @return The descriptor of the codec of the application
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/codec")
    Call<eu.frenchxcore.model.cosmossdk.query.base.reflection.GetCodecDescriptorResponse> baseReflectionGetCodecDescriptor();

    /**
     * baseReflectionGetConfigurationDescriptor returns the descriptor for the
     * sdk.Config of the application
     *
     * @return The descriptor for the sdk.Config of the application
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/configuration")
    Call<eu.frenchxcore.model.cosmossdk.query.base.reflection.GetConfigurationDescriptorResponse> baseReflectionGetConfigurationDescriptor();

    /**
     * baseReflectionGetQueryServicesDescriptor returns the available gRPC
     * queryable services of the application
     *
     * @return The available gRPC queryable services of the application
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/query_services")
    Call<eu.frenchxcore.model.cosmossdk.query.base.reflection.GetQueryServicesDescriptorResponse> baseReflectionGetQueryServicesDescriptor();

    /**
     * GetTxDescriptor returns information on the used transaction object and
     * available msgs that can be used
     *
     * @return Information on the used transaction object and available msgs that can be used
     */
    @GET("/cosmos/base/reflection/v1beta1/app_descriptor/tx_descriptor")
    Call<eu.frenchxcore.model.cosmossdk.query.base.reflection.GetTxDescriptorResponse> baseReflectionGetTxDescriptor();

    ///////////////////////////////////////////////////////
    ////////// base.tendermint
    ///////////////////////////////////////////////////////
    /**
     * baseTendermintGetNodeInfo queries the current node info.
     *
     * @return The current node info
     */
    @GET("/cosmos/base/tendermint/v1beta1/node_info")
    Call<eu.frenchxcore.model.cosmossdk.query.base.tendermint.GetNodeInfoResponse> baseTendermintGetNodeInfo();

    /**
     * baseTendermintGetSyncing queries node syncing.
     *
     * @return True if the node is syncing, false if already synced.
     */
    @GET("/cosmos/base/tendermint/v1beta1/syncing")
    Call<eu.frenchxcore.model.cosmossdk.query.base.tendermint.GetSyncingResponse> baseTendermintGetSyncing();

    /**
     * baseTendermintGetLatestBlock returns the latest block.
     *
     * @return The latest block height.
     */
    @GET("/cosmos/base/tendermint/v1beta1/blocks/latest")
    Call<eu.frenchxcore.model.cosmossdk.query.base.tendermint.GetLatestBlockResponse> baseTendermintGetLatestBlock();

    /**
     * baseTendermintGetBlockByHeight queries block for given height.
     *
     * @param height The height of the requested block.
     * @return The block at requested height.
     */
    @GET("/cosmos/base/tendermint/v1beta1/blocks/{height}")
    Call<eu.frenchxcore.model.cosmossdk.query.base.tendermint.GetBlockByHeightResponse> baseTendermintGetBlockByHeight(
            @Path(value = "height") BigInteger height
    );

    /**
     * baseTendermintGetLatestValidatorSet queries latest validator-set.
     *
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return The latest validator set.
     */
    @GET("/cosmos/base/tendermint/v1beta1/validatorsets/latest")
    Call<eu.frenchxcore.model.cosmossdk.query.base.tendermint.GetLatestValidatorSetResponse> baseTendermintGetLatestValidatorSet(
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * baseTendermintGetValidatorSetByHeight queries validator-set at a given
     * height.
     *
     * @param height The block height at which the validator-set is requested.
     * @return The validator-set at that block height.
     */
    @GET("/cosmos/base/tendermint/v1beta1/validatorsets/{height}")
    Call<eu.frenchxcore.model.cosmossdk.query.base.tendermint.GetValidatorSetByHeightResponse> baseTendermintGetValidatorSetByHeight(
            @Path(value = "height") BigInteger height,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    ///////////////////////////////////////////////////////
    ////////// distribution
    ///////////////////////////////////////////////////////
    /**
     * distributionParams queries params of the distribution module.
     *
     * @return The distribution module parameters.
     */
    @GET("/cosmos/distribution/v1beta1/params")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryParamsResponse> distributionParams();

    /**
     * distributionValidatorOutstandingRewards queries rewards of a validator
     * address.
     *
     * @param validatorAddress defines the validator address to query for.
     * @return All outstanding rewards to be delivered to all this validator's delegators.
     */
    @GET("/cosmos/distribution/v1beta1/validators/{validator_address}/outstanding_rewards")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryValidatorOutstandingRewardsResponse> distributionValidatorOutstandingRewards(
            @Path(value = "validator_address") String validatorAddress
    );

    /**
     * distributionValidatorCommission queries accumulated commission for a
     * validator.
     *
     * @param validatorAddress defines the validator address to query for.
     * @return The accumulated commission for the given validator
     */
    @GET("/cosmos/distribution/v1beta1/validators/{validator_address}/commission")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryValidatorCommissionResponse> distributionValidatorCommission(
            @Path(value = "validator_address") String validatorAddress
    );

    /**
     * distributionValidatorSlashes queries slash events of a validator.
     *
     * @param validatorAddress defines the validator address to query for.
     * @param startingHeight defines the optional starting height to query the slashes.
     * @param endingHeight defines the optional ending height to query the slashes.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return The slashes applied to the given validator.
     */
    @GET("/cosmos/distribution/v1beta1/validators/{validator_address}/slashes")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryValidatorSlashesResponse> distributionValidatorSlashes(
            @Path(value = "validator_address") String validatorAddress,
            @Query(value = "starting_height") String startingHeight,
            @Query(value = "ending_height") String endingHeight,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * distributionDelegationRewards queries the total rewards accrued by a
     * delegation.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param validatorAddress defines the validator address to query for.
     * @return The rewards accrued by the given delegator with the given validator.
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/rewards/{validator_address}")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryDelegationRewardsResponse> distributionDelegationRewards(
            @Path(value = "delegator_address") String delegatorAddress,
            @Path(value = "validator_address") String validatorAddress
    );

    /**
     * distributionDelegationTotalRewards queries the total rewards accrued by a delegator.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @return The total rewards accrued by a delegator with all validators.
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/rewards")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryDelegationTotalRewardsResponse> distributionDelegationTotalRewards(
            @Path(value = "delegator_address") String delegatorAddress
    );

    /**
     * distributionDelegatorValidators queries the validators of a delegator.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @return The validators of the given delegator
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/validators")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryDelegatorValidatorsResponse> distributionDelegatorValidators(
            @Path(value = "delegator_address") String delegatorAddress
    );

    /**
     * distributionDelegatorWithdrawAddress queries withdraw address of a
     * delegator.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @return The withdraw address of the given delegator
     */
    @GET("/cosmos/distribution/v1beta1/delegators/{delegator_address}/withdraw_address")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryDelegatorWithdrawAddressResponse> distributionDelegatorWithdrawAddress(
            @Path(value = "delegator_address") String delegatorAddress
    );

    /**
     * distributionCommunityPool queries the community pool coins.
     *
     * @return The community pool coins.
     */
    @GET("/cosmos/distribution/v1beta1/community_pool")
    Call<eu.frenchxcore.model.cosmossdk.query.distribution.QueryCommunityPoolResponse> distributionCommunityPool();

    ///////////////////////////////////////////////////////
    ////////// evidence
    ///////////////////////////////////////////////////////
    /**
     * evidenceEvidence queries evidence based on evidence hash.
     *
     * @param evidenceHash defines the hash of the requested evidence.
     * @return evidence based on the given evidence hash.
     */
    @GET("/cosmos/evidence/v1beta1/evidence/{evidence_hash}")
    Call<eu.frenchxcore.model.cosmossdk.query.evidence.QueryEvidenceResponse> evidenceEvidence(
            @Path(value = "evidence_hash") String evidenceHash
    );

    /**
     * evidenceAllEvidence queries all evidence.
     *
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return all evidence.
     */
    @GET("/cosmos/evidence/v1beta1/evidence")
    Call<eu.frenchxcore.model.cosmossdk.query.evidence.QueryAllEvidenceResponse> evidenceAllEvidence(
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    ///////////////////////////////////////////////////////
    ////////// feegrant
    ///////////////////////////////////////////////////////
    /**
     * Allowance returns fee granted to the grantee by the granter.
     *
     * @param granter is the address of the user granting an allowance of their funds.
     * @param grantee is the address of the user being granted an allowance of another user's funds.
     * @return the fee granted to the grantee by the granter.
     */
    @GET("/cosmos/feegrant/v1beta1/allowance/{granter}/{grantee}")
    Call<eu.frenchxcore.model.cosmossdk.query.feegrant.QueryAllowanceResponse> feegrantAllowance(
            @Path(value = "granter") String granter,
            @Path(value = "grantee") String grantee
    );

    /**
     * Allowances returns all the grants for address.
     *
     * @param grantee the address of the user being granted an allowance of another user's funds.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return all the grants for address.
     */
    @GET("/cosmos/feegrant/v1beta1/allowances/{grantee}")
    Call<eu.frenchxcore.model.cosmossdk.query.feegrant.QueryAllowancesResponse> feegrantAllowances(
            @Path(value = "grantee") String grantee,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    ///////////////////////////////////////////////////////
    ////////// gov
    ///////////////////////////////////////////////////////
    /**
     * govProposal queries proposal details based on a proposal ID.
     *
     * @param proposalId defines the unique id of the proposal.
     * @return the proposal details based on the given proposal ID.
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.gov.QueryProposalResponse> govProposal(
            @Path(value = "proposal_id") BigInteger proposalId
    );

    /**
     * govProposals queries all proposals based on given status.
     *
     * @param proposalStatus defines the status of the proposals.
     * @param voter defines the voter address for the proposals.
     * @param depositor defines the deposit addresses from the proposals.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return all proposals based on given status.
     */
    @GET("/cosmos/gov/v1beta1/proposals")
    Call<eu.frenchxcore.model.cosmossdk.query.gov.QueryProposalsResponse> govProposals(
            @Query(value = "proposal_status") ProposalStatus proposalStatus,
            @Query(value = "voter") String voter,
            @Query(value = "depositor") String depositor,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * govVote queries vote information based on proposal ID and a voter address.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param voter defines the voter address for the proposal.
     * @return vote information based on proposal ID and a voter address.
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/votes/{voter}")
    Call<eu.frenchxcore.model.cosmossdk.query.gov.QueryVoteResponse> govVote(
            @Path(value = "proposal_id") BigInteger proposalId,
            @Path(value = "voter") String voter
    );

    /**
     * govVotes queries votes of a given proposal.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return votes for the given proposal ID.
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/votes")
    Call<eu.frenchxcore.model.cosmossdk.query.gov.QueryVotesResponse> govVotes(
            @Path(value = "proposal_id") BigInteger proposalId,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * govParams queries all parameters of the gov module.
     *
     * @param paramsType defines which parameters to query for : can be one of
     * "voting", "tallying" or "deposit".
     * @return all parameters of the given gov module.
     */
    @GET("/cosmos/gov/v1beta1/params/{params_type}")
    Call<eu.frenchxcore.model.cosmossdk.query.gov.QueryParamsResponse> govParams(
            @Path(value = "params_type") ParamsType paramsType
    );

    /**
     * govDeposit queries single deposit information based proposalID,
     * depositAddr.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param depositor defines the depositor address for the proposal.
     * @return single deposit information based on the given proposal ID and depositor address.
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/deposits/{depositor}")
    Call<eu.frenchxcore.model.cosmossdk.query.gov.QueryDepositResponse> govDeposit(
            @Path(value = "proposal_id") BigInteger proposalId,
            @Path(value = "depositor") String depositor
    );

    /**
     * govDeposits queries all deposits of a single proposal.
     *
     * @param proposalId defines the unique id of the proposal.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return all deposits of the given proposal.
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/deposits")
    Call<eu.frenchxcore.model.cosmossdk.query.gov.QueryDepositsResponse> govDeposits(
            @Path(value = "proposal_id") BigInteger proposalId,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * govTallyResult queries the tally of a proposal vote.
     *
     * @param proposalId defines the unique id of the proposal.
     * @return the tally for the given proposal ID.
     */
    @GET("/cosmos/gov/v1beta1/proposals/{proposal_id}/tally")
    Call<eu.frenchxcore.model.cosmossdk.query.gov.QueryTallyResultResponse> govTallyResult(
            @Path(value = "proposal_id") BigInteger proposalId
    );

    ///////////////////////////////////////////////////////
    ////////// group
    ///////////////////////////////////////////////////////
    /**
     * groupGroupInfo queries group info based on group ID.
     *
     * @param groupId is the unique ID of the group.
     * @return  group info for the given group ID.
     */
    @GET("/cosmos/group/v1beta1/group_info/{group_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryGroupInfoResponse> groupGroupInfo(
            @Path(value = "group_id") BigInteger groupId
    );

    /**
     * groupGroupAccountInfo queries group account info based on group account
     * address.
     *
     * @param address is the account address of the group account.
     * @return group account info for the given group account address.
     */
    @GET("/cosmos/group/v1beta1/group_account_info/{address}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryGroupAccountInfoResponse> groupGroupAccountInfo(
            @Path(value = "address") String address
    );

    /**
     * groupGroupMembers queries members of a group
     *
     * @param groupId is the unique ID of the group.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return members of the given group ID.
     */
    @GET("/cosmos/group/v1beta1/group_members/{group_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryGroupMembersResponse> groupGroupMembers(
            @Path(value = "group_id") BigInteger groupId,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * groupGroupsByAdmin queries groups by admin address.
     *
     * @param admin is the account address of a group's admin.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return groups for a given admin address.
     */
    @GET("/cosmos/group/v1beta1/groups_by_admin/{admin}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryGroupsByAdminResponse> groupGroupsByAdmin(
            @Path(value = "admin") String admin,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * groupGroupAccountsByGroup queries group accounts by group id.
     *
     * @param groupId is the unique ID of the group account's group.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return group accounts for the given group ID.
     */
    @GET("/cosmos/group/v1beta1/group_accounts_by_group/{group_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryGroupAccountsByGroupResponse> groupGroupAccountsByGroup(
            @Path(value = "group_id") BigInteger groupId,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * groupGroupsByAdmin queries group accounts by admin address.
     *
     * @param admin is the admin address of the group account.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/group/v1beta1/group_accounts_by_admin/{admin}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryGroupAccountsByAdminResponse> groupGroupAccountsByAdmin(
            @Path(value = "admin") String admin,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * groupProposal queries a proposal based on proposal id.
     *
     * @param proposalId is the unique ID of a proposal.
     * @return
     */
    @GET("/cosmos/group/v1beta1/proposal/{proposal_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryProposalResponse> groupProposal(
            @Path(value = "proposal_id") BigInteger proposalId
    );

    /**
     * groupProposalsByGroupAccount queries proposals based on group account
     * address.
     *
     * @param address is the group account address related to proposals.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/group/v1beta1/proposals_by_group_account/{address}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryProposalsByGroupAccountResponse> groupProposalsByGroupAccount(
            @Path(value = "address") String address,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * groupVoteByProposalVoter queries a vote by proposal id and voter.
     *
     * @param proposalId is the unique ID of a proposal.
     * @param voter is a proposal voter account address.
     * @return
     */
    @GET("/cosmos/group/v1beta1/vote_by_proposal_voter/{proposal_id}/{voter}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryVoteByProposalVoterResponse> groupVoteByProposalVoter(
            @Path(value = "proposal_id") BigInteger proposalId,
            @Path(value = "voter") String voter
    );

    /**
     * groupVotesByProposal queries a vote by proposal.
     *
     * @param proposalId is the unique ID of a proposal.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/group/v1beta1/votes_by_proposal/{proposal_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryVotesByProposalResponse> groupVotesByProposal(
            @Path(value = "proposal_id") BigInteger proposalId,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * groupVotesByVoter queries a vote by voter.
     *
     * @param voter is a proposal voter account address.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/group/v1beta1/votes_by_voter/{voter}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryVotesByVoterResponse> groupVotesByVoter(
            @Path(value = "voter") String voter,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * groupGroupsByMember queries groups by member address.
     *
     * @param address is the group member address.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/group/v1beta1/groups_by_member/{address}")
    Call<eu.frenchxcore.model.cosmossdk.query.group.QueryGroupsByMemberResponse> groupGroupsByMember(
            @Path(value = "address") String address,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
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
    Call<eu.frenchxcore.model.cosmossdk.query.mint.QueryParamsResponse> mintParams();

    /**
     * mintInflation returns the current minting inflation value.
     *
     * @return
     */
    @GET("/cosmos/mint/v1beta1/inflation")
    Call<eu.frenchxcore.model.cosmossdk.query.mint.QueryInflationResponse> mintInflation();

    /**
     * mintAnnualProvisions current minting annual provisions value.
     *
     * @return
     */
    @GET("/cosmos/mint/v1beta1/annual_provisions")
    Call<eu.frenchxcore.model.cosmossdk.query.mint.QueryAnnualProvisionsResponse> mintAnnualProvisions();

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
    Call<eu.frenchxcore.model.cosmossdk.query.nft.QueryBalanceResponse> nftBalance(
            @Path(value = "owner") String owner,
            @Path(value = "class_id") String classId
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
    Call<eu.frenchxcore.model.cosmossdk.query.nft.QueryOwnerResponse> nftOwner(
            @Path(value = "class_id") String classId,
            @Path(value = "id") String id
    );

    /**
     * nftSupply queries the number of NFTs from the given class, same as
     * totalSupply of ERC721.
     *
     * @param classId
     * @return
     */
    @GET("/cosmos/nft/v1beta1/supply/{class_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.nft.QuerySupplyResponse> nftSupply(
            @Path(value = "class_id") String classId
    );

    /**
     * nftNFTsOfClass queries all NFTs of a given class or optional owner,
     * similar to tokenByIndex in ERC721Enumerable
     *
     * @param classId
     * @return
     */
    @GET("/cosmos/nft/v1beta1/nfts/{class_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.nft.QueryNFTsOfClassResponse> nftNFTsOfClass(
            @Path(value = "class_id") String classId
    );

    /**
     * nftNFT queries an NFT based on its class and id.
     *
     * @param classId
     * @param id
     * @return
     */
    @GET("/cosmos/nft/v1beta1/nfts/[class_id}/{id}")
    Call<eu.frenchxcore.model.cosmossdk.query.nft.QueryNFTResponse> nftNFT(
            @Path(value = "class_id") String classId,
            @Path(value = "id") String id
    );

    /**
     * nftClass queries an NFT class based on its id
     *
     * @param classId
     * @return
     */
    @GET("/cosmos/nft/v1beta1/classes/[class_id}")
    Call<eu.frenchxcore.model.cosmossdk.query.nft.QueryClassResponse> nftClass(
            @Path(value = "class_id") String classId
    );

    /**
     * nftClasses queries all NFT classes
     *
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/nft/v1beta1/classes")
    Call<eu.frenchxcore.model.cosmossdk.query.nft.QueryClassesResponse> nftClasses(
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    ///////////////////////////////////////////////////////
    ////////// other
    ///////////////////////////////////////////////////////

    /**
     * otherGasPrice queries the node's gas price.
     *
     * @return node's gas price.
     */
    @GET("/other/v1/gas_price")
    Call<eu.frenchxcore.model.fxcore.query.QueryGasPriceResponse> otherGasPrice();

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
    Call<eu.frenchxcore.model.cosmossdk.query.params.QueryParamsResponse> paramsParams(
            @Path(value = "subspace") String subspace,
            @Path(value = "key") String key
    );

    /**
     * paramsSubspaces queries for all registered subspaces and all keys for a
     * subspace.
     *
     * @return
     */
    @GET("/cosmos/params/v1beta1/subspaces")
    Call<eu.frenchxcore.model.cosmossdk.query.params.QuerySubspacesResponse> paramsSubspaces();

    ///////////////////////////////////////////////////////
    ////////// slashing
    ///////////////////////////////////////////////////////
    /**
     * slashingParams queries the parameters of slashing module
     *
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/params")
    Call<eu.frenchxcore.model.cosmossdk.query.slashing.QueryParamsResponse> slashingParams();

    /**
     * slashingSigningInfo queries the signing info of given cons address
     *
     * @param consAddress is the address to query signing info of
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/signing_infos/{cons_address}")
    Call<eu.frenchxcore.model.cosmossdk.query.slashing.QuerySigningInfoResponse> slashingSigningInfo(
            @Path(value = "cons_address") String consAddress
    );

    /**
     * slashingSigningInfos queries signing info of all validators
     *
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/slashing/v1beta1/signing_infos")
    Call<eu.frenchxcore.model.cosmossdk.query.slashing.QuerySigningInfosResponse> slashingSigningInfos(
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    ///////////////////////////////////////////////////////
    ////////// staking
    ///////////////////////////////////////////////////////
    /**
     * stakingValidators queries all validators that match the given optional
     * status.
     *
     * @param status enables to query for validators matching a given status.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryValidatorsResponse> stakingValidators(
            @Query(value = "status") eu.frenchxcore.model.cosmossdk.types.staking.BondStatus status,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * stakingValidator queries validator info for given validator address.
     *
     * @param validatorAddress defines the validator address to query for.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_addr}")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryValidatorResponse> stakingValidator(
            @Query(value = "validator_addr") String validatorAddress
    );

    /**
     * stakingValidatorDelegations queries delegate info for given validator.
     *
     * @param validatorAddress defines the validator address to query for.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_address}/delegations")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryValidatorDelegationsResponse> stakingValidatorDelegations(
            @Path(value = "validator_address") String validatorAddress,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * stakingValidatorUnbondingDelegations queries unbonding delegations of a
     * validator.
     *
     * @param validatorAddress defines the validator address to query for.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/validators/{validator_address}/unbonding_delegations")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryValidatorUnbondingDelegationsResponse> stakingValidatorUnbondingDelegations(
            @Path(value = "validator_address") String validatorAddress,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
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
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryDelegationResponse> stakingDelegation(
            @Path(value = "validator_address") String validatorAddress,
            @Path(value = "delegator_address") String delegatorAddress
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
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryUnbondingDelegationResponse> stakingUnbondingDelegation(
            @Path(value = "validator_address") String validatorAddress,
            @Path(value = "delegator_address") String delegatorAddress
    );

    /**
     * stakingDelegatorDelegations queries all delegations of a given delegator
     * address.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegations/{delegator_address}")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryDelegatorDelegationsResponse> stakingDelegatorDelegations(
            @Path(value = "delegator_address") String delegatorAddress,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * stakingDelegatorUnbondingDelegations queries all unbonding delegations of
     * a given delegator address.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegators/{delegator_address}/unbonding_delegations")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryDelegatorUnbondingDelegationsResponse> stakingDelegatorUnbondingDelegations(
            @Path(value = "delegator_address") String delegatorAddress,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * stakingRedelegations queries redelegations of given address.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param sourceValidatorAddress defines the validator address to redelegate
     * from.
     * @param destinationValidatorAddress defines the validator address to
     * redelegate to.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegators/{delegator_address}/redelegations")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryRedelegationsResponse> stakingRedelegations(
            @Path(value = "delegator_address") String delegatorAddress,
            @Query(value = "src_validator_address") String sourceValidatorAddress,
            @Query(value = "dst_validator_address") String destinationValidatorAddress,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
    );

    /**
     * stakingDelegatorValidators queries all validators info for given
     * delegator address.
     *
     * @param delegatorAddress defines the delegator address to query for.
     * @param paginationKey is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of paginationOffset or paginationKey should be set.
     * @param paginationOffset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
     * @param paginationLimit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
     * @param paginationCountTotal is set to true to indicate that the result set should include a count of the total number of items available for pagination in UIs. It is only respected when paginationOffset is used. It is ignored when paginationKey is set.
     * @param paginationReverse is set to true if results are to be returned in the descending order.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/delegators/{delegator_address}/validators")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryDelegatorValidatorsResponse> stakingDelegatorValidators(
            @Path(value = "delegator_address") String delegatorAddress,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse
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
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryDelegatorValidatorResponse> stakingDelegatorValidator(
            @Path(value = "delegator_address") String delegatorAddress,
            @Path(value = "validator_address") String validatorAddress
    );

    /**
     * stakingHistoricalInfo queries the historical info for given height.
     *
     * @param height defines at which height to query the historical info.
     * @return
     */
    @GET("/cosmos/staking/v1beta1/historical_info/{height}")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryHistoricalInfoResponse> stakingHistoricalInfo(
            @Path(value = "height") BigInteger height
    );

    /**
     * stakingPool queries the pool info.
     *
     * @return
     */
    @GET("/cosmos/staking/v1beta1/pool")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryPoolResponse> stakingPool();

    /**
     * stakingParameters queries the staking parameters.
     *
     * @return
     */
    @GET("/cosmos/staking/v1beta1/params")
    Call<eu.frenchxcore.model.cosmossdk.query.staking.QueryParamsResponse> stakingParams();

    ///////////////////////////////////////////////////////
    ////////// tx
    ///////////////////////////////////////////////////////
    /**
     * txSimulate simulates executing a transaction for estimating gas usage.
     *
     * @param tx Deprecated. tx is the transaction to simulate. Deprecated. Send
     * raw tx bytes instead.
     * @param txBytes is the raw transaction.
     * @return
     */
    @POST("/cosmos/tx/v1beta1/simulate")
    Call<eu.frenchxcore.model.cosmossdk.query.tx.SimulateResponse> txSimulate(
            @Query(value = "tx") Tx tx,
            @Query(value = "tx_bytes") String txBytes
    );

    /**
     * txGetTx fetches a tx by hash.
     *
     * @param hash is the tx hash to query, encoded as a hex string.
     * @return
     */
    @GET("/cosmos/tx/v1beta1/txs/{hash}")
    Call<eu.frenchxcore.model.cosmossdk.query.tx.GetTxResponse> txGetTx(
            @Path(value = "hash") String hash
    );

    /**
     * txBroadcastTx broadcast transaction.
     *
     * @param txBytes is the raw transaction.
     * @param mode
     * @return
     */
    @POST("/cosmos/tx/v1beta1/txs")
    Call<eu.frenchxcore.model.cosmossdk.query.tx.BroadcastTxResponse> txBroadcastTx(
            @Query(value = "tx_bytes") String txBytes,
            @Query(value = "mode") BroadcastMode mode
    );

    /**
     * txGetTxsEvent fetches txs by event.
     *
     * @return
     */
    @GET("/cosmos/tx/v1beta1/txs")
    Call<eu.frenchxcore.model.cosmossdk.query.tx.GetTxsEventResponse> txGetTxsEvent(
            @Query(value = "events") List<String> events,
            @Query(value = "pagination.key") String paginationKey,
            @Query(value = "pagination.offset") BigInteger paginationOffset,
            @Query(value = "pagination.limit") BigInteger paginationLimit,
            @Query(value = "pagination.count_total") Boolean paginationCountTotal,
            @Query(value = "pagination.reverse") Boolean paginationReverse,
            @Query(value = "order_by") OrderBy order_by
    );

    ///////////////////////////////////////////////////////
    ////////// upgrade
    ///////////////////////////////////////////////////////
    /**
     * upgradeCurrentPlan queries the current upgrade plan.
     *
     * @return the current upgrade plan.
     */
    @GET("/cosmos/upgrade/v1beta1/current_plan")
    Call<eu.frenchxcore.model.cosmossdk.query.upgrade.QueryCurrentPlanResponse> upgradeCurrentPlan();

    /**
     * upgradeAppliedPlan queries a previously applied upgrade plan by its name.
     *
     * @param name defines the name of the applied plan to query for.
     * @return a previously applied upgrade plan with the given name.
     */
    @GET("/cosmos/upgrade/v1beta1/applied_plan/{name}")
    Call<eu.frenchxcore.model.cosmossdk.query.upgrade.QueryAppliedPlanResponse> upgradeAppliedPlan(
            @Path(value = "name") String name
    );

    /**
     * upgradeUpgradedConsensusState queries the consensus state that will serve
     * as a trusted kernel for the next version of this chain. It will only be
     * stored at the last height of this chain. UpgradedConsensusState RPC not
     * supported with legacy querier This rpc is deprecated now that IBC has its
     * own replacement
     * (https://github.com/cosmos/ibc-go/blob/2c880a22e9f9cc75f62b527ca94aa75ce1106001/proto/ibc/core/client/v1/query.proto#L54)
     *
     * @param lastHeight defines the block height at which the consensus state will be stored.
     * @return The consensus state that will serve as a trusted kernel for the next version of this chain.
     */
    @GET("/cosmos/upgrade/v1beta1/upgraded_consensus_state/{last_height}")
    Call<eu.frenchxcore.model.cosmossdk.query.upgrade.QueryUpgradedConsensusStateResponse> upgradeUpgradedConsensusState(
            @Path(value = "last_height") BigInteger lastHeight
    );

}
