package eu.frenchxcore.messages.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * AuthnDescriptor provides information on how to sign transactions without
 * relying on the online RPCs GetTxMetadata and CombineUnsignedTxAndSignatures.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthnDescriptor {

    /**
     * sign_modes defines the supported signature algorithm
     */
    @JsonProperty("sign_modes")
    public List<SigningModeDescriptor> signModes;

}
