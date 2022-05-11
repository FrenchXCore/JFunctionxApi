package eu.frenchxcore.model.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.Coin;
import eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.ProposalContent;

import java.util.List;

/**
 * CommunityPoolSpendProposal details a proposal for use of community funds,
 * together with how many coins are proposed to be spent, and to which
 * recipient account.
 *
 * "@type":"/cosmos.distribution.v1beta1.CommunityPoolSpendProposal"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommunityPoolSpendProposal extends ProposalContent {

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
    public List<Coin> amount;

}
