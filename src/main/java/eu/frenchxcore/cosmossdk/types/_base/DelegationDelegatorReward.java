package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DelegationDelegatorReward {

    @JsonProperty("validator_address")
    public String validatorAddress;
    
    @JsonProperty("reward")
    public List<DecCoin> reward;
    
}
