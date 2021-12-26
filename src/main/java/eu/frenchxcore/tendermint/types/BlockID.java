package eu.frenchxcore.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlockID {

    /**
     * 
     */
    @JsonProperty("hash")
    public String hash;
    
    /**
     * 
     */
    @JsonProperty("part_set_header")
    public PartSetHeader partSetHeader;
    
}
