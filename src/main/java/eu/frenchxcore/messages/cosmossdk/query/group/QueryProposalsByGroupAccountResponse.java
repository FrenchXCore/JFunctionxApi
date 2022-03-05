package eu.frenchxcore.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.group.Proposal;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;

/**
 * QueryProposalsByGroupAccountResponse is the Query/ProposalByGroupAccount response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryProposalsByGroupAccountResponse {

    /**
     * proposals are the proposals with given group account.
     */
    @JsonProperty("proposals")
    public List<Proposal> proposals;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
