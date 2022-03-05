package eu.frenchxcore.messages.cosmossdk.types.params.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ParamChange defines an individual parameter change, for use in
 * ParameterChangeProposal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParamChange {

    @JsonProperty("subspace")
    public String subspace;
    
    @JsonProperty("key")
    public String key;
    
    @JsonProperty("value")
    public String value;
    
}
