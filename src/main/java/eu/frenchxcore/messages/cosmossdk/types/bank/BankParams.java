package eu.frenchxcore.messages.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Params defines the parameters for the bank module.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankParams {

    /**
     * 
     */
    @JsonProperty("send_enabled")
    public List<SendEnabled> sendEnabled;
    
    /**
     * 
     */
    @JsonProperty("default_send_enabled")
    public Boolean defaultSendEnabled;

}
