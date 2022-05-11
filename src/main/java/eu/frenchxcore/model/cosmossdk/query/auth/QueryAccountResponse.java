package eu.frenchxcore.model.cosmossdk.query.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.auth.Account;

/**
 * QueryAccountResponse is the response type for the Query/Account RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAccountResponse {

    /**
     * account defines the account of the corresponding address.
     */
    @JsonProperty("account")
    public Account account;

}
