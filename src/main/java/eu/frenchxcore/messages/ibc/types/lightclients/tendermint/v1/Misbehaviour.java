package eu.frenchxcore.ibc.types.lightclients.tendermint.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Misbehaviour is a wrapper over two conflicting Headers
 * that implements Misbehaviour interface expected by ICS-02
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Misbehaviour {

    /**
     * 
     */
    @JsonProperty("client_id")
    public String clientId;
    
    /**
     * 
     */
    @JsonProperty("header1")
    public Header header1;
    
    /**
     * 
     */
    @JsonProperty("header2")
    public Header header2;
    
}