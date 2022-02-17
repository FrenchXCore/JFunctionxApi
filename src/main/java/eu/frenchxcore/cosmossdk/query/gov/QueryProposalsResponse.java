package eu.frenchxcore.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.gov.v1beta1.Proposal;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryProposalsResponse {

    /**
     * 
     */
    @JsonProperty("proposals")
    public List<Proposal> proposals;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
