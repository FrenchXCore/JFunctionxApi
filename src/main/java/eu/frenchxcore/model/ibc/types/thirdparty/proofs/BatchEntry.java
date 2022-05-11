package eu.frenchxcore.model.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Use BatchEntry not CommitmentProof, to avoid recursion
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchEntry {

    /**
     * One of
     */
    @JsonProperty("exist")
    public ExistenceProof exist;

    /**
     * One of
     */
    @JsonProperty("nonexist")
    public NonExistenceProof nonexist;

}