package eu.frenchxcore.model.cosmossdk.types.feegrant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.Coin;

import java.util.Date;
import java.util.List;

/**
 * PeriodicAllowance extends Allowance to allow for both a maximum cap, as well as a limit per time period.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PeriodicAllowance {

    /**
     * basic specifies a struct of BasicAllowance
     */
    @JsonProperty("basic")
    public BasicAllowance basic;

    /**
     * period specifies the time duration in which period_spend_limit coins can be spent before that allowance is reset
     */
    @JsonProperty("duration")
    public String duration; //Duration

    /**
     * period_spend_limit specifies the maximum number of coins that can be spent in the period
     */
    @JsonProperty("period_spend_limit")
    public List<Coin> periodSpendLimit;

    /**
     * period_can_spend is the number of coins left to be spent before the period_reset time
     */
    @JsonProperty("period_can_spend")
    public List<Coin> periodCanSpend;

    /**
     * period_reset is the time at which this period resets and a new one begins, it is calculated from the start time of the first transaction after the last period ended
     */
    @JsonProperty("period_reset")
    public Date periodReset;

}