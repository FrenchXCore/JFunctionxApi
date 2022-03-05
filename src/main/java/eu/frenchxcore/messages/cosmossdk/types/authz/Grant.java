package eu.frenchxcore.messages.cosmossdk.types.authz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;

/**
 * Params defines the parameters for the bank module.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Grant {

    /**
     * 
     */
    @JsonProperty("authorization")
    public Map<String, String> authorization;
    
    /**
     * 
     */
    @JsonProperty("expiration")
    public Date expiration;

}
