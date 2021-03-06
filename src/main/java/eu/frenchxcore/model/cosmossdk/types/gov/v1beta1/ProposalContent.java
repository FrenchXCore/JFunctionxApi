package eu.frenchxcore.model.cosmossdk.types.gov.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.gson.JsonObject;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "@type",
        visible = true,
        defaultImpl = JsonObject.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.types.distribution.CommunityPoolSpendProposal.class, name = "/cosmos.distribution.v1beta1.CommunityPoolSpendProposal"),
        @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.types.distribution.CommunityPoolSpendProposalWithDeposit.class, name = "/cosmos.distribution.v1beta1.CommunityPoolSpendProposalWithDeposit"),
        @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.TextProposal.class, name = "/cosmos.gov.v1beta1.TextProposal"),
        @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.types.params.v1beta1.ParameterChangeProposal.class, name = "/cosmos.params.v1beta1.ParameterChangeProposal"),
        @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.types.upgrade.v1beta1.SoftwareUpgradeProposal.class, name = "/cosmos.upgrade.v1beta1.SoftwareUpgradeProposal"),
        @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.types.upgrade.v1beta1.CancelSoftwareUpgradeProposal.class, name = "/cosmos.upgrade.v1beta1.CancelSoftwareUpgradeProposal"),
        @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.types.gravity.crosschain.v1.InitCrossChainParamsProposal.class, name = "/fx.gravity.crosschain.v1.InitCrossChainParamsProposal"),
        @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.types.gravity.crosschain.v1.UpdateChainOraclesProposal.class, name = "/fx.gravity.crosschain.v1.UpdateChainOraclesProposal")
})
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

}