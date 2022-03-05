package eu.frenchxcore.cosmossdk.query.evidence;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;
import java.util.List;

/**
 * QueryAllEvidenceResponse is the response type for the Query/AllEvidence RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAllEvidenceResponse {

    /**
     * evidence returns all evidences.
     */
    @JsonProperty("evidence")
    public List<Object> evidence;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
