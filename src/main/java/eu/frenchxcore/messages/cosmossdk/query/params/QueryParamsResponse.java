package eu.frenchxcore.messages.cosmossdk.query.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.params.v1beta1.ParamChange;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamsResponse {

    /**
     * param defines the queried parameter.
     */
    @JsonProperty("param")
    public ParamChange param;

}
