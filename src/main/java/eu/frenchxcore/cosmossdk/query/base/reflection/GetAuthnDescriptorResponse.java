package eu.frenchxcore.cosmossdk.query.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.reflection.AuthnDescriptor;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAuthnDescriptorResponse {

    /**
     * authn describes how to authenticate to the application when sending transactions
     */
    @JsonProperty("authn")
    public AuthnDescriptor authn;
    
}
