package eu.frenchxcore.messages.cosmossdk.types.gov.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TextProposal defines a standard text proposal whose changes need to be
 * manually updated in case of approval.
 * "@type":"/cosmos.gov.v1beta1.TextProposal"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextProposal extends ProposalContent {

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