package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Description {
    
    /**
     * moniker defines a human-readable name for the validator.
     */
    @JsonProperty("moniker")
    public String moniker;
    
    /**
     * identity defines an optional identity signature (ex. UPort or Keybase).
     */
    @JsonProperty("identity")
    public String identity;
    
    /**
     * website defines an optional website link.
     */
    @JsonProperty("website")
    public String website;
    
    /**
     * security_contact defines an optional email for security contact.
     */
    @JsonProperty("security_contact")
    public String securityContact;
    
    /**
     * details define other optional details.
     */
    @JsonProperty("details")
    public String details;
    
}
