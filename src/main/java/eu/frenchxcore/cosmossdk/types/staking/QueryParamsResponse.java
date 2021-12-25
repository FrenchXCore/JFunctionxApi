package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.StakingParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamsResponse {

    /**
     * params holds all the parameters of this module.
     */
    @JsonProperty("params")
    public StakingParams params;
    
}
