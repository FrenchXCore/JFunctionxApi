package eu.frenchxcore.messages.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.gov.v1beta1.Vote;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

import java.util.List;

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
