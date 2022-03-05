package eu.frenchxcore.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * VotingParams defines the params for voting on governance proposals.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VotingParams {

    /**
     * Length of the voting period.
     */
    @JsonProperty("voting_period")
    public String votingPeriod;         // "voting_period": "1209600s"
    
}
