package eu.frenchxcore.cosmossdk.query.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.reflection.QueryServicesDescriptor;

/**
 * GetQueryServicesDescriptorResponse is the response returned by the GetQueryServicesDescriptor RPC
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetQueryServicesDescriptorResponse {

    /**
     * queries provides information on the available queryable services
     */
    @JsonProperty("queries")
    public QueryServicesDescriptor queries;
    
}
