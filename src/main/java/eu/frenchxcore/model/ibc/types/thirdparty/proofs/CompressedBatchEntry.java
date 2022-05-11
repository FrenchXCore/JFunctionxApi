package eu.frenchxcore.model.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Use CompressedBatchEntry not CommitmentProof, to avoid recursion
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompressedBatchEntry {

    /**
     * One of
     */
    @JsonProperty("exist")
    public CompressedExistenceProof exist;

    /**
     * One of
     */
    @JsonProperty("nonexist")
    public CompressedNonExistenceProof nonexist;

}