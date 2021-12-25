package eu.frenchxcore.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.DistributionParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamsResponse {

    /**
     * params defines the parameters of the module.
     */
    @JsonProperty("params")
    public DistributionParams params;

}
