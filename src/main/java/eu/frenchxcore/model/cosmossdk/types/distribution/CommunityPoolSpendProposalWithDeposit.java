package eu.frenchxcore.model.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.ProposalContent;

/**
 * CommunityPoolSpendProposalWithDeposit defines a CommunityPoolSpendProposal
 * with a deposit
 * "@type":"/cosmos.distribution.v1beta1.CommunityPoolSpendProposalWithDeposit"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommunityPoolSpendProposalWithDeposit extends ProposalContent {

    @JsonProperty("@type")
    public String type;

    /**
     * the title of the proposal
     */
    @JsonProperty("title")
    public String title;

    /**
     * the description of the proposal
     */
    @JsonProperty("description")
    public String description;

    @JsonProperty("recipient")
    public String recipient;

    @JsonProperty("amount")
    public String amount;

    @JsonProperty("deposit")
    public String deposit;

}
