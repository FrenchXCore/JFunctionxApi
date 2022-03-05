package eu.frenchxcore.messages.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.distribution.ValidatorOutstandingRewards;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorOutstandingRewardsResponse {

    /**
     * 
     */
    @JsonProperty("rewards")
    public ValidatorOutstandingRewards rewards;

}