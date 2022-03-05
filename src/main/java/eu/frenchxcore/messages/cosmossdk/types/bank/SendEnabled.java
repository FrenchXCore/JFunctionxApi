package eu.frenchxcore.messages.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SendEnabled maps coin denom to a send_enabled status (whether a denom is sendable).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SendEnabled {

    @JsonProperty("denom")
    public String denom;
    
    @JsonProperty("enabled")
    public Boolean enabled;
    
}
