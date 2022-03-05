package eu.frenchxcore.messages.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ModeInfoSingle is the mode info for a single signer. It is structured as a
 * message to allow for additional fields such as locale for SIGN_MODE_TEXTUAL
 * in the future
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeInfoSingle {

    /**
     * mode is the signing mode of the single signer
     */
    @JsonProperty("mode")
    public SignMode mode;

}
