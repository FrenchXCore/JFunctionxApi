package eu.frenchxcore.messages.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.DecCoin;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DelegationDelegatorReward {

    @JsonProperty("validator_address")
    public String validatorAddress;
    
    @JsonProperty("reward")
    public List<DecCoin> reward;
    
}
