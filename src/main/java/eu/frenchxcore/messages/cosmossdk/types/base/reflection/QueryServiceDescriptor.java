package eu.frenchxcore.messages.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * QueryServiceDescriptor describes a cosmos-sdk queryable service
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryServiceDescriptor {

    /**
     * fullname is the protobuf fullname of the service descriptor
     */
    @JsonProperty("fullname")
    public String fullname;

    /**
     * is_module describes if this service is actually exposed by an application's module
     */
    @JsonProperty("is_module")
    public Boolean isModule;

    /**
     * methods provides a list of query service methods
     */
    @JsonProperty("methods")
    public List<QueryMethodDescriptor> methods;

}