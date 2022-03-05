package eu.frenchxcore.messages.cosmossdk.query.authz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.authz.Grant;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGranterGrantsResponse {

    /**
     * authorizations is a list of grants granted for grantee by granter.
     */
    @JsonProperty("grants")
    public List<Grant> grants;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
