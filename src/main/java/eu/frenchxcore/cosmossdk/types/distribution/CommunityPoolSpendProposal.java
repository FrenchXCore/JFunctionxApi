package eu.frenchxcore.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.v1beta1.Coin;

import java.util.List;

/**
 * CommunityPoolSpendProposal details a proposal for use of community funds,
 * together with how many coins are proposed to be spent, and to which
 * recipient account.
 * "@type":"/cosmos.distribution.v1beta1.CommunityPoolSpendProposal"
 */
public class CommunityPoolSpendProposal {

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
