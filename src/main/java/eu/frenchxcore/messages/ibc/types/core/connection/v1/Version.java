package eu.frenchxcore.messages.ibc.types.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.ibc.types.core.commitment.v1.MerklePrefix;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Version {

    /**
     * 
     */
    @JsonProperty("identifier")
    public String identifier;
    
    /**
     * 
     */
    @JsonProperty("features")
    public List<String> features;
    
    /**
     * 
     */
    @JsonProperty("prefix")
    public MerklePrefix prefix;

}