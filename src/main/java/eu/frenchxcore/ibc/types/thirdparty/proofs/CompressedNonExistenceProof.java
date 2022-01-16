package eu.frenchxcore.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompressedNonExistenceProof {

    /**
     *
     */
    @JsonProperty("key")
    public String key;

    /**
     *
     */
    @JsonProperty("left")
    public CompressedExistenceProof left;

    /**
     *
     */
    @JsonProperty("right")
    public CompressedExistenceProof right;

}