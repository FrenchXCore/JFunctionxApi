package eu.frenchxcore.model.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ProposalContent {

    /**
     *  "@type":"/cosmos.distribution.v1beta1.CommunityPoolSpendProposal"
     *  "@type":"/cosmos.distribution.v1beta1.CommunityPoolSpendProposalWithDeposit"
     *  "@type":"/cosmos.gov.v1beta1.TextProposal"
     *  "@type":"/cosmos.params.v1beta1.ParameterChangeProposal"
     *  "@type":"/cosmos.upgrade.v1beta1.SoftwareUpgradeProposal"
     *  "@type":"/cosmos.upgrade.v1beta1.CancelSoftwareUpgradeProposal"
     *  "@type": "/fx.gravity.crosschain.v1.InitCrossChainParamsProposal"
     *  "@type": "/fx.gravity.crosschain.v1.UpdateChainOraclesProposal"
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

}