package eu.frenchxcore.messages.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommissionRates {

    /**
     * rate is the commission rate charged to delegators, as a fraction.
     */
    @JsonProperty("rate")
    public String rate;
    
    /**
     * max_rate defines the maximum commission rate which validator can ever charge, as a fraction.
     */
    @JsonProperty("max_rate")
    public String maxRate;
    
    /**
     * max_change_rate defines the maximum daily increase of the validator commission, as a fraction.
     */
    @JsonProperty("max_change_rate")
    public String maxChangeRate;
    
}
