package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DecCoin {

    @JsonProperty("denom")
    public String denom;
    
    @JsonProperty("amount")
    public BigDecimal amount;
    
}
