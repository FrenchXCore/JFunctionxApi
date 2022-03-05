package eu.frenchxcore.messages.cosmossdk.types.params.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Subspace defines a parameter subspace name and all the keys that exist for
 * the subspace.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subspace {

    @JsonProperty("subspace")
    public String subspace;
    
    @JsonProperty("keys")
    public List<String> keys;
    
}
