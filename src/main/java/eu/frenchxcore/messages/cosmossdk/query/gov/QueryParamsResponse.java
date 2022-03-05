package eu.frenchxcore.messages.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.gov.v1beta1.DepositParams;
import eu.frenchxcore.messages.cosmossdk.types.gov.v1beta1.TallyParams;
import eu.frenchxcore.messages.cosmossdk.types.gov.v1beta1.VotingParams;

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