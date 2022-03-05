package eu.frenchxcore.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.group.Vote;

/**
 * QueryVoteByProposalVoterResponse is the Query/VoteByProposalVoter response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryVoteByProposalVoterResponse {

    /**
     * vote is the vote with given proposal_id and voter.
     */
    @JsonProperty("vote")
    public Vote vote;

}
