package eu.frenchxcore.messages.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.group.Vote;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

import java.util.List;

/**
 * QueryVotesByProposalResponse is the Query/VotesByProposal response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryVotesByProposalResponse {

    /**
     * votes are the list of votes for given proposalId.
     */
    @JsonProperty("votes")
    public List<Vote> votes;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
