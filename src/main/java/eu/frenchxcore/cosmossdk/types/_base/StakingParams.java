package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StakingParams {

    /**
     * unbonding_time is the time duration of unbonding.
     */
    @JsonProperty("unbonding_time")
    public Instant unbondingTime;

    /**
     * max_validators is the maximum number of validators.
     */
    @JsonProperty("max_validators")
    public Long maxValidators;

    /**
     * max_entries is the max entries for either unbonding delegation or redelegation (per pair/trio).
     */
    @JsonProperty("max_entries")
    public Long maxEntries;

    /**
     * historical_entries is the number of historical entries to persist.
     */
    @JsonProperty("historical_entries")
    public Long historicalEntries;

    /**
     * bond_denom defines the bondable coin denomination.
     */
    @JsonProperty("bond_denom")
    public String bondDenom;

    /**
     * min_commission_rate is the chain-wide minimum commission rate that a validator can charge their delegators
     */
    @JsonProperty("min_commission_rate")
    public String minCommissionRate;

}