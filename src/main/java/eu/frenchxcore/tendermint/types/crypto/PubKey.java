package eu.frenchxcore.tendermint.types.crypto;

import eu.frenchxcore.tendermint.types.*;
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
