package eu.frenchxcore.model.cosmossdk.query.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.reflection.CodecDescriptor;

/**
 * GetCodecDescriptorResponse is the response returned by the GetCodecDescriptor RPC
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetCodecDescriptorResponse {

    /**
     * codec describes the application codec such as registered interfaces and implementations
     */
    @JsonProperty("codec")
    public CodecDescriptor codec;
    
}
