package eu.frenchxcore.cosmossdk.types.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TallyParams {

    @JsonProperty("quorum")
    public BigDecimal quorum;           // "quorum": "0.400000000000000000"
    
    @JsonProperty("threshold")
    public BigDecimal threshold;        // "threshold": "0.500000000000000000"
    
    @JsonProperty("veto_threshold")
    public BigDecimal vetoThreshold;    // "veto_threshold": "0.334000000000000000"
    
}
