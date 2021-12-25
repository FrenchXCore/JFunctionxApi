package eu.frenchxcore.cosmossdk.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OutstandingRewards {

    @JsonProperty("denom")
    public String denom;
    
    @JsonProperty("amount")
    public BigDecimal amount;
    
}
