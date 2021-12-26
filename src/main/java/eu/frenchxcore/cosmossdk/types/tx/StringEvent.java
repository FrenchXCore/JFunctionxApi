package eu.frenchxcore.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.List;

/**
 * StringEvent defines en Event object wrapper where all the attributes contain key/value pairs that are strings instead of raw bytes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StringEvent {

    /**
     * 
     */
    @JsonProperty("type")
    public String type;

    /**
     * 
     */
    @JsonProperty("attributes")
    public List<Attribute> attributes;

}
