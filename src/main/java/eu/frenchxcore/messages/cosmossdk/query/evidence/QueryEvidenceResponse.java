package eu.frenchxcore.messages.cosmossdk.query.evidence;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * QueryEvidenceResponse is the response type for the Query/Evidence RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryEvidenceResponse {

    /**
     * evidence returns the requested evidence.
     */
    @JsonProperty("evidence")
    public Object evidence;

}
