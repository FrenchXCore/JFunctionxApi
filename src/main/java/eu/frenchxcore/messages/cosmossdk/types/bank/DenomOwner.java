package eu.frenchxcore.messages.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.Coin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DenomOwner {

    /**
     * address defines the address that owns a particular denomination.
     */
    @JsonProperty("address")
    public String address;
    
    /**
     * balance is the balance of the denominated coin for an account.
     */
    @JsonProperty("balance")
    public Coin balance;
    
}
