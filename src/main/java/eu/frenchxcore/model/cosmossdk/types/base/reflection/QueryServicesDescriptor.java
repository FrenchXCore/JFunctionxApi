package eu.frenchxcore.model.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * QueryServicesDescriptor contains the list of cosmos-sdk queriable services
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryServicesDescriptor {

    /**
     * query_services is a list of cosmos-sdk QueryServiceDescriptor
     */
    @JsonProperty("query_services")
    public List<QueryServiceDescriptor> queryServices;

}