package eu.frenchxcore.model.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.DecCoin;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegationRewardsResponse {

    /**
     * rewards defines the rewards accrued by a delegation.
     */
    @JsonProperty("rewards")
    public List<DecCoin> rewards;
    
}
