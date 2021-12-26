package eu.frenchxcore.cosmossdk.query.authz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.authz.Grant;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGrantsResponse {

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
