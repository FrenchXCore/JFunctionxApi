package eu.frenchxcore.model.ibc.types.core.commitment.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MerkleRoot defines a merkle root hash.
 * In the Cosmos SDK, the AppHash of a block header becomes the root.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerkleRoot {

    /**
     *
     */
    @JsonProperty("hash")
    public String hash;

}