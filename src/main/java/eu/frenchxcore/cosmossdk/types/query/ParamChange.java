package eu.frenchxcore.cosmossdk.types.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParamChange {

    @JsonProperty("subspace")
    public String subspace;
    
    @JsonProperty("key")
    public String key;
    
    @JsonProperty("value")
    public String value;
    
}
