package eu.frenchxcore.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.group.Vote;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;
import java.util.List;

/**
 * QueryVotesByVoterResponse is the Query/VotesByVoter response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryVotesByVoterResponse {

    /**
     * votes are the list of votes by given voter.
     */
    @JsonProperty("votes")
    public List<Vote> votes;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
