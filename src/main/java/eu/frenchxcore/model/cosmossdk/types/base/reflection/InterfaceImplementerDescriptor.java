package eu.frenchxcore.model.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * InterfaceImplementerDescriptor describes an interface implementer
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InterfaceImplementerDescriptor {

    /**
     * fullname is the protobuf queryable name of the interface implementer
     */
    @JsonProperty("fullname")
    public String fullname;

    /**
     * type_url defines the type URL used when marshalling the type as any this
     * is required so we can provide type safe google.protobuf.Any marshalling
     * and unmarshalling, making sure that we don't accept just 'any' type in
     * our interface fields
     */
    @JsonProperty("type_url")
    public String typeUrl;

}