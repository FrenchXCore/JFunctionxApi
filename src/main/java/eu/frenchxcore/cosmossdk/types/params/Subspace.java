package eu.frenchxcore.cosmossdk.types.params;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subspace {

    @JsonProperty("subspace")
    public String subspace;
    
    @JsonProperty("keys")
    public List<String> keys;
    
}
