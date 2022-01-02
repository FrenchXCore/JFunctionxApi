package eu.frenchxcore.ibc.types.core.client.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Height {

    /**
     * 
     */
    @JsonProperty("revision_number")
    public BigInteger revisionNumber;
    
    /**
     * 
     */
    @JsonProperty("revision_height")
    public BigInteger revisionHeight;

}