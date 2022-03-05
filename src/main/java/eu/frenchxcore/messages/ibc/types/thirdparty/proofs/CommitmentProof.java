package eu.frenchxcore.messages.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CommitmentProof is either an ExistenceProof or a NonExistenceProof, or a
 * Batch of such messages.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommitmentProof {

    /**
     * 
     */
    @JsonProperty("exist")
    public ExistenceProof exist;

    /**
     * 
     */
    @JsonProperty("nonexist")
    public NonExistenceProof nonexist;

    /**
     * 
     */
    @JsonProperty("batch")
    public BatchProof batch;

    /**
     * 
     */
    @JsonProperty("compressed")
    public CompressedBatchProof compressed;

}