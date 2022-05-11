package eu.frenchxcore.model.cosmossdk.query.feegrant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.feegrant.Grant;

/**
 * QueryAllowanceResponse is the response type for the Query/Allowance RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAllowanceResponse {

    /**
     * allowance is a allowance granted for grantee by granter.
     */
    @JsonProperty("allowance")
    public Grant allowance;

}
