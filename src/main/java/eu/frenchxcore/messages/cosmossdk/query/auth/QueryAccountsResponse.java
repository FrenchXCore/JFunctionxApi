package eu.frenchxcore.cosmossdk.query.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.auth.Account;

/**
 * QueryAccountResponse is the response type for the Query/Account RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccountsResponse {

    /**
     * account defines the account of the corresponding address.
     */
    @JsonProperty("account")
    public Account account;

}
