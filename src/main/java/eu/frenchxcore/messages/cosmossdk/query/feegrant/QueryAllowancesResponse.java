package eu.frenchxcore.cosmossdk.query.feegrant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.feegrant.Grant;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;
import java.util.List;

/**
 * QueryAllowancesResponse is the response type for the Query/Allowances RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAllowancesResponse {

    /**
     * allowances are allowance's granted for grantee by granter.
     */
    @JsonProperty("allowances")
    public List<Grant> allowances;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
