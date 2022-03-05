package eu.frenchxcore.fxcore.types.gravity.crosschain.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.gov.v1beta1.ProposalContent;

/**
 * "@type": "/fx.gravity.crosschain.v1.InitCrossChainParamsProposal"
 */
public class InitCrossChainParamsProposal extends ProposalContent {

    /**
     *  "@type": "/fx.gravity.crosschain.v1.InitCrossChainParamsProposal"
     */
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

    @JsonProperty("params")
    public InitCrossChainParamsProposalParams params;

    @JsonProperty("chain_name")
    public String chainName;

}
