package eu.frenchxcore.messages.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.Coin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DelegationResponse {

    /**
     * 
     */
    @JsonProperty("delegation")
    public Delegation delegation;
    
    /**
     * 
     */
    @JsonProperty("balance")
    public Coin balance;
    
}
