package eu.frenchxcore.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Attribute defines an attribute wrapper where the key and value are strings instead of raw bytes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attribute {

    /**
     * 
     */
    @JsonProperty("key")
    public String key;

    /**
     * 
     */
    @JsonProperty("value")
    public String value;

}
