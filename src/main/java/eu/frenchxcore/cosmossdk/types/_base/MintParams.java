package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MintParams {

    @JsonProperty("mint_denom")
    public String mintDenom;                // "mint_denom": "FX"
    
    @JsonProperty("inflation_rate_change")
    public String inflationRateChange;      // "inflation_rate_change": "0.300000000000000000"
    
    @JsonProperty("inflation_max")
    public String inflationMax;             // "inflation_max": "0.416762000000000000"
    
    @JsonProperty("inflation_min")
    public String inflationMin;             // "inflation_min": "0.170000000000000000"
    
    @JsonProperty("goal_bonded")
    public String goalBonded;               // "goal_bonded": "0.510000000000000000"
    
    @JsonProperty("blocks_per_year")
    public String blocksPerYear;            // "blocks_per_year": "6311520"
    
}