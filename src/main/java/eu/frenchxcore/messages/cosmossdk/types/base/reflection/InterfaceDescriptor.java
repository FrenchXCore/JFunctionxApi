package eu.frenchxcore.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * InterfaceDescriptor describes the implementation of an interface
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterfaceDescriptor {

    /**
     * fullname is the name of the interface
     */
    @JsonProperty("fullname")
    public String fullname;

    /**
     * interface_accepting_messages contains information regarding the proto
     * messages which contain the interface as google.protobuf.Any field
     */
    @JsonProperty("interface_accepting_messages")
    public List<InterfaceAcceptingMessageDescriptor> interfaceAcceptingMessages;

    /**
     * interface_implementers is a list of the descriptors of the interface implementers
     */
    @JsonProperty("interface_implementers")
    public List<InterfaceImplementerDescriptor> interfaceImplementers;

}