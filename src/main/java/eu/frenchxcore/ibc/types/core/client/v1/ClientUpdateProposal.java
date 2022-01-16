package eu.frenchxcore.ibc.types.core.client.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ClientUpdateProposal is a governance proposal. If it passes, the substitute
 * client's latest consensus state is copied over to the subject client. The
 * proposal handler may fail if the subject and the substitute do not match in
 * client and chain parameters (with exception to latest height, frozen height,
 * and chain-id).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientUpdateProposal {

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

    /**
     * the client identifier for the client to be updated if the proposal passes
     */
    @JsonProperty("subject_client_id")
    public String subjectClientId;

    /**
     * the substitute client identifier for the client standing in for the
     * subject client
     */
    @JsonProperty("substitute_client_id")
    public String substituteClientId;

}