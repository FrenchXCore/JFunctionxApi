package eu.frenchxcore.messages.tendermint.types.crypto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PubKey {

    /**
     * 
     */
    @JsonProperty("ed25519")
    public String ed25519;
    
}
