package eu.frenchxcore.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CommunityPoolSpendProposalWithDeposit defines a CommunityPoolSpendProposal
 * with a deposit
 * "@type":"/cosmos.distribution.v1beta1.CommunityPoolSpendProposalWithDeposit"
 */
public class CommunityPoolSpendProposalWithDeposit {

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
