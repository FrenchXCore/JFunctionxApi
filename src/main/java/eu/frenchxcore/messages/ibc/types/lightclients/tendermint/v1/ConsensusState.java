package eu.frenchxcore.ibc.types.lightclients.tendermint.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.ibc.types.core.commitment.v1.MerkleRoot;

/**
 * ClientState from Tendermint tracks the current validator set, latest height,
 * and a possible frozen height.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsensusState {

    /**
     * timestamp that corresponds to the block height in which the
     * ConsensusState was stored.
     */
    @JsonProperty("timestamp")
    public String timestamp;

    /**
     * commitment root (i.e app hash)
     */
    @JsonProperty("root")
    public MerkleRoot root;

    /**
     * duration of the period since the LastestTimestamp during which the
     * submitted headers are valid for upgrade
     */
    @JsonProperty("next_validators_hash")
    public String nextValidatorsHassh;

}
