package eu.frenchxcore.model.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * NonExistenceProof takes a proof of two neighbors, one left of the desired
 * key, one right of the desired key. If both proofs are valid AND they are
 * neighbors, then there is no valid proof for the given key.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NonExistenceProof {

    /**
     *
     */
    @JsonProperty("key")
    public String key;

    /**
     *
     */
    @JsonProperty("left")
    public ExistenceProof left;

    /**
     *
     */
    @JsonProperty("right")
    public ExistenceProof right;

    /**
     *
     */
    @JsonProperty("compressed")
    public CompressedBatchProof compressed;

}