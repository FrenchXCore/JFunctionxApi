package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
