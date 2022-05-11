package eu.frenchxcore.model.cosmossdk.types.params.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.gov.v1beta1.ProposalContent;

import java.util.List;

/**
 * ParameterChangeProposal defines a proposal to change one or more parameters.
 * "@type":"/cosmos.params.v1beta1.ParameterChangeProposal"
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterChangeProposal extends ProposalContent {

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

    @JsonProperty("changes")
    public List<ParamChange> changes;

}
