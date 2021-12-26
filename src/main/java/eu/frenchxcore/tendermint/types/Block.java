package eu.frenchxcore.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {

    /**
     * 
     */
    @JsonProperty("header")
    public Header header;
    
    /**
     * 
     */
    @JsonProperty("data")
    public Data data;
    
    /**
     * 
     */
    @JsonProperty("evidence")
    public Evidence evidence;
    
    /**
     * 
     */
    @JsonProperty("last_commit")
    public Commit lastCommit;
    
}
