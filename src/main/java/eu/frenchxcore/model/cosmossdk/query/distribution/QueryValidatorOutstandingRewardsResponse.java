package eu.frenchxcore.model.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.distribution.ValidatorOutstandingRewards;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorOutstandingRewardsResponse {

    /**
     * 
     */
    @JsonProperty("rewards")
    public ValidatorOutstandingRewards rewards;

}