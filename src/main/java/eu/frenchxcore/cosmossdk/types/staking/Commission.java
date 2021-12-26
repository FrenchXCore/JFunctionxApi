package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commission {

    /**
     * commission_rates defines the initial commission rates to be used for creating a validator.
     */
    @JsonProperty("commission_rates")
    public CommissionRates commissionRates;

    /**
     * update_time is the last time the commission rate was changed.
     */
    @JsonProperty("update_time")
    public Date updateTime;
    
}
