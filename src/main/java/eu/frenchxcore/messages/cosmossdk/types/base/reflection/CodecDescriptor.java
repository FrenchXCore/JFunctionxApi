package eu.frenchxcore.messages.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * CodecDescriptor describes the registered interfaces and provides metadata information on the types
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CodecDescriptor {

    /**
     * interfaces is a list of the registerted interfaces descriptors
     */
    @JsonProperty("interfaces")
    public List<InterfaceDescriptor> interfaces;

}
