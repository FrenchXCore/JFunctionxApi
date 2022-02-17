package eu.frenchxcore.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.gov.v1beta1.Vote;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryVotesResponse {

    /**
     * votes defined the queried votes.
     */
    @JsonProperty("votes")
    public List<Vote> votes;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
