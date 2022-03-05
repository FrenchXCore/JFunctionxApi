package eu.frenchxcore.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProtocolVersion {

    /**
     * 
     */
    @JsonProperty("p2p")
    public String p2p;
    
    /**
     * 
     */
    @JsonProperty("block")
    public String block;
    
    /**
     * 
     */
    @JsonProperty("app")
    public String app;
    
}
