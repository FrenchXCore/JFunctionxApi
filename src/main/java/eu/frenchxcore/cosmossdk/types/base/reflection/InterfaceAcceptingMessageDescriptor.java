package eu.frenchxcore.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * InterfaceAcceptingMessageDescriptor describes a protobuf message which
 * contains an interface represented as a google.protobuf.Any
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterfaceAcceptingMessageDescriptor {

    /**
     * fullname is the protobuf fullname of the type containing the interface
     */
    @JsonProperty("fullname")
    public String fullname;

    /**
     * field_descriptor_names is a list of the protobuf name (not fullname) of
     * the field which contains the interface as google.protobuf.Any (the
     * interface is the same, but it can be in multiple fields of the same proto
     * message)
     */
    @JsonProperty("field_descriptor_names")
    public List<String> fieldDescriptorNames;

}