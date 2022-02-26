package eu.frenchxcore.cosmossdk.query.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.authz.Grant;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;

import java.util.List;

/**
 * QueryAccountRequest is the request type for the Query/Account RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccountRequest {

    /**
     * address defines the address to query for.
     */
    @JsonProperty("account")
    public String address;

}
