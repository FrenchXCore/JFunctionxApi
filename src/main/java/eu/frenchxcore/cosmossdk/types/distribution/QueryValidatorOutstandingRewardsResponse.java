package eu.frenchxcore.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.ValidatorOutstandingRewards;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorOutstandingRewardsResponse {

    /**
     * 
     */
    @JsonProperty("rewards")
    public ValidatorOutstandingRewards rewards;

}