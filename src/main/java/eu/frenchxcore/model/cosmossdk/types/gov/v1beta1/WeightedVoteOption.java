package eu.frenchxcore.model.cosmossdk.types.gov.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WeightedVoteOption defines a unit of vote for vote split.
 * @Since: cosmos-sdk 0.43
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeightedVoteOption {

    @JsonProperty("option")
    public VoteOption option;
    
    @JsonProperty("weight")
    public String weight;
    
}
