package eu.frenchxcore.cosmossdk.query.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.params.ParamChange;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamsResponse {

    /**
     * param defines the queried parameter.
     */
    @JsonProperty("param")
    public ParamChange param;

}
