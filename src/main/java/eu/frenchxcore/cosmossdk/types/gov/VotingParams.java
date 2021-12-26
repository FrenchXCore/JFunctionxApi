package eu.frenchxcore.cosmossdk.types.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VotingParams {

    @JsonProperty("voting_period")
    public String votingPeriod;         // "voting_period": "1209600s"
    
}
