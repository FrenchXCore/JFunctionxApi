package eu.frenchxcore.messages.cosmossdk.types.base.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DecCoin {

    @JsonProperty("denom")
    public String denom;
    
    @JsonProperty("amount")
    public BigDecimal amount;
    
}
