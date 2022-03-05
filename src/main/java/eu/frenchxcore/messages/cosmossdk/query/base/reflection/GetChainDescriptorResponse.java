package eu.frenchxcore.messages.cosmossdk.query.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.reflection.ChainDescriptor;

/**
 * GetChainDescriptorResponse is the response returned by the GetChainDescriptor RPC
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetChainDescriptorResponse {

    /**
     * chain describes application chain information
     */
    @JsonProperty("chain")
    public ChainDescriptor chain;
    
}
