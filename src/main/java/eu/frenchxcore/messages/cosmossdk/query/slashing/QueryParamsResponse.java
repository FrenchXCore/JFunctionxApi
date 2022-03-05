package eu.frenchxcore.cosmossdk.query.slashing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.slashing.SlashingParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamsResponse {

    @JsonProperty("params")
    public SlashingParams params;

}
