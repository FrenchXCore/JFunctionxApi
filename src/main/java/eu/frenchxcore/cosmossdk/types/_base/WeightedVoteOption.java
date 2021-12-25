package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeightedVoteOption {

    @JsonProperty("option")
    public VoteOption option;
    
    @JsonProperty("weight")
    public String weight;
    
}
