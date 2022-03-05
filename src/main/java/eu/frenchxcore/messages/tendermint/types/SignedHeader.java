package eu.frenchxcore.messages.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignedHeader {

    /**
     * 
     */
    @JsonProperty("header")
    public Header header;
    
    /**
     * 
     */
    @JsonProperty("commit")
    public Commit commit;
    
}
