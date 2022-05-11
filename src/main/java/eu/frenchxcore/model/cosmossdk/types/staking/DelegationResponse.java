package eu.frenchxcore.model.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.Coin;

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
