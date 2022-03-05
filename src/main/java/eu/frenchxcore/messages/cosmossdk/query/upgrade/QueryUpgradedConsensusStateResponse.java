package eu.frenchxcore.messages.cosmossdk.query.upgrade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * QueryUpgradedConsensusStateResponse is the response type for the Query/UpgradedConsensusState RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryUpgradedConsensusStateResponse {

    /**
     * 
     */
    @JsonProperty("upgraded_consensus_state")
    public String upgradedConsensusState;
    
}