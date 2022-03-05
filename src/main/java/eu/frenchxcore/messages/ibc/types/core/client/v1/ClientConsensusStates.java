package eu.frenchxcore.messages.ibc.types.core.client.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * ClientConsensusStates defines all the stored consensus states for a given
 * client.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientConsensusStates {

    /**
     * client identifier
     */
    @JsonProperty("client_id")
    public String clientId;

    /**
     * consensus states and their heights associated with the client
     */
    @JsonProperty("consensus_states")
    public List<ConsensusStateWithHeight> consensusStates;

}