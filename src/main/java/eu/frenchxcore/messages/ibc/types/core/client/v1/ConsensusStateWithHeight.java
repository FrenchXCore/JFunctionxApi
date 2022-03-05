package eu.frenchxcore.ibc.types.core.client.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.ibc.types.lightclients.tendermint.v1.ConsensusState;

/**
 * ConsensusStateWithHeight defines a consensus state with an additional height
 * field.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsensusStateWithHeight {

    /**
     * consensus state height
     */
    @JsonProperty("height")
    public Height height;

    /**
     * consensus state
     */
    @JsonProperty("consensus_state")
    public ConsensusState consensusState;

}