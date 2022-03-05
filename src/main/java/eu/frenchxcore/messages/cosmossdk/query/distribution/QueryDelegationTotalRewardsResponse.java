package eu.frenchxcore.messages.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.DecCoin;
import eu.frenchxcore.messages.cosmossdk.types.distribution.DelegationDelegatorReward;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegationTotalRewardsResponse {

    /**
     * rewards defines all the rewards accrued by a delegator.
     */
    @JsonProperty("rewards")
    public List<DelegationDelegatorReward> rewards;
    
    /**
     * total defines the sum of all the rewards.
     */
    @JsonProperty("total")
    public List<DecCoin> total;
    
}
