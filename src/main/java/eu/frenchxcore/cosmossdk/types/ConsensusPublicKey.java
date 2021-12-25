package eu.frenchxcore.cosmossdk.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsensusPublicKey {

    @JsonProperty("@type")
    public String type;
    
    @JsonProperty("key")
    public byte[] key;
    
}
