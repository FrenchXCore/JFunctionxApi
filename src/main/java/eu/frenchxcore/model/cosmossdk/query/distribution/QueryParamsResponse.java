package eu.frenchxcore.model.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.distribution.DistributionParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamsResponse {

    /**
     * params defines the parameters of the module.
     */
    @JsonProperty("params")
    public DistributionParams params;

}
