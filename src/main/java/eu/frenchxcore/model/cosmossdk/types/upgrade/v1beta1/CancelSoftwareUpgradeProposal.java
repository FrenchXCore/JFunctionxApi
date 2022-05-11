package eu.frenchxcore.model.cosmossdk.types.upgrade.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.ProposalContent;

/**
 * CancelSoftwareUpgradeProposal is a gov Content type for cancelling a software upgrade.
 * "@type":"/cosmos.upgrade.v1beta1.CancelSoftwareUpgradeProposal"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelSoftwareUpgradeProposal extends ProposalContent {

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

}
