package eu.frenchxcore.messages.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WeightedVoteOption defines a unit of vote for vote split.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeightedVoteOption {

    @JsonProperty("option")
    public VoteOption option;
    
    @JsonProperty("weight")
    public String weight;
    
}
