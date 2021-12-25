package eu.frenchxcore.cosmossdk.types.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.DepositParams;
import eu.frenchxcore.cosmossdk.types._base.TallyParams;
import eu.frenchxcore.cosmossdk.types._base.VotingParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamsResponse {

    /**
     * voting_params defines the parameters related to voting.
     */
    @JsonProperty("voting_params")
    public VotingParams votingParams;

    /**
     * deposit_params defines the parameters related to deposit.
     */
    @JsonProperty("deposit_params")
    public DepositParams depositParams;

    /**
     * tally_params defines the parameters related to tally.
     */
    @JsonProperty("tally_params")
    public TallyParams tallyParams;

}