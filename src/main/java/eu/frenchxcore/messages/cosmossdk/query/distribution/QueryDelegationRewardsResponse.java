package eu.frenchxcore.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.base.v1beta1.DecCoin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegationRewardsResponse {

    /**
     * rewards defines the rewards accrued by a delegation.
     */
    @JsonProperty("rewards")
    public List<DecCoin> rewards;
    
}
