package eu.frenchxcore.messages.cosmossdk.query.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * QueryAccountRequest is the request type for the Query/Account RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccountsRequest {

    /**
     * address defines the address to query for.
     */
    @JsonProperty("account")
    public String address;

}
