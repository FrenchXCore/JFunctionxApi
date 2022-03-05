package eu.frenchxcore.ibc.types.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.ibc.types.core.commitment.v1.MerklePrefix;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Counterparty {

    /**
     * 
     */
    @JsonProperty("client_id")
    public String clientId;
    
    /**
     * 
     */
    @JsonProperty("connection_id")
    public String connectionId;
    
    /**
     * 
     */
    @JsonProperty("prefix")
    public MerklePrefix prefix;

}