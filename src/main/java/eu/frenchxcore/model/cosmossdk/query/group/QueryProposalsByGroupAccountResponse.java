package eu.frenchxcore.model.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.group.Proposal;
import eu.frenchxcore.model.cosmossdk.types.query.PageResponse;

import java.util.List;

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
