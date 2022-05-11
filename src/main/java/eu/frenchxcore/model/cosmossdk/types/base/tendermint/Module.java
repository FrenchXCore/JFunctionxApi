package eu.frenchxcore.model.cosmossdk.types.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Module {

    /**
     * module path
     */
    @JsonProperty("path")
    public String path;
    
    /**
     * module version
     */
    @JsonProperty("version")
    public String version;
    
    /**
     * checksum
     */
    @JsonProperty("sum")
    public String sum;
    
}
