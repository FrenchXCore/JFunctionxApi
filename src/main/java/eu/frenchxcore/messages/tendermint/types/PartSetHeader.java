package eu.frenchxcore.messages.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PartSetHeader {

    /**
     * 
     */
    @JsonProperty("total")
    public Long total;
    
    /**
     * 
     */
    @JsonProperty("hash")
    public String hash;
    
}
