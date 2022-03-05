package eu.frenchxcore.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistributionCommission {

    @JsonProperty("denom")
    public String denom;
    
    @JsonProperty("amount")
    public BigDecimal amount;
    
}
