package eu.frenchxcore.model.cosmossdk.query.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.group.Proposal;

/**
 * QueryProposalResponse is the Query/Proposal response type.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryProposalResponse {

    /**
     * proposal is the proposal info.
     */
    @JsonProperty("proposal")
    public Proposal proposal;

}
