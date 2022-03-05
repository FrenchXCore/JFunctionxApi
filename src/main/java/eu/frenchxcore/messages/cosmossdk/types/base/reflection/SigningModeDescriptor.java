package eu.frenchxcore.messages.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * SigningModeDescriptor provides information on a signing flow of the
 * application NOTE(fdymylja): here we could go as far as providing an entire
 * flow on how to sign a message given a SigningModeDescriptor, but it's better
 * to think about this another time
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SigningModeDescriptor {

    /**
     * name defines the unique name of the signing mode
     */
    @JsonProperty("name")
    public String name;

    /**
     * number is the unique int32 identifier for the sign_mode enum
     */
    @JsonProperty("number")
    public Long number;

    /**
     * authn_info_provider_method_fullname defines the fullname of the method to
     * call to get the metadata required to authenticate using the provided
     * sign_modes
     */
    @JsonProperty("authn_info_provider_method_fullname")
    public String authn_info_provider_method_fullname;

}
