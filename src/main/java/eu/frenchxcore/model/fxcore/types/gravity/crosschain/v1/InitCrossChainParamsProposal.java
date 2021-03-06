package eu.frenchxcore.model.fxcore.types.gravity.crosschain.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.ProposalContent;

/**
 * "@type": "/fx.gravity.crosschain.v1.InitCrossChainParamsProposal"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
