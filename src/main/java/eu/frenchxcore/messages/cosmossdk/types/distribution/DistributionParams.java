package eu.frenchxcore.messages.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistributionParams {

    @JsonProperty("community_tax")
    public BigDecimal communityTax;
    
    @JsonProperty("base_proposer_reward")
    public BigDecimal baseProposerReward;
    
    @JsonProperty("bonus_proposer_reward")
    public BigDecimal bonusProposerReward;
    
    @JsonProperty("withdraw_addr_enabled")
    public boolean withdrawAddrEnabled;
    
}
