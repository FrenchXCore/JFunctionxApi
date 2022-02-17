package eu.frenchxcore.fxcore.types.gravity.crosschain.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.gov.v1beta1.ProposalContent;

import java.util.List;

/**
 * "@type": "/fx.gravity.crosschain.v1.UpdateChainOraclesProposal"
 */
public class UpdateChainOraclesProposal extends ProposalContent {

    @JsonProperty("@type")
    public String type;

    /**
     * the title of the update proposal
     */
    @JsonProperty("title")
    public String title;

    /**
     * the description of the proposal
     */
    @JsonProperty("description")
    public String description;

    @JsonProperty("oracles")
    public List<String> oracles;

    @JsonProperty("chain_name")
    public String chainName;

}
