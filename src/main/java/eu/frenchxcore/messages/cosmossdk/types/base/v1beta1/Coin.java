package eu.frenchxcore.messages.cosmossdk.types.base.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coin {

    @JsonProperty("denom")
    public String denom;
    
    @JsonProperty("amount")
    public BigInteger amount;
    
}
