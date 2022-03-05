package eu.frenchxcore.messages.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompressedExistenceProof {

    /**
     *
     */
    @JsonProperty("key")
    public String key;

    /**
     *
     */
    @JsonProperty("value")
    public String value;

    /**
     *
     */
    @JsonProperty("leaf")
    public LeafOp leaf;

    /**
     * these are indexes into the lookup_inners table in CompressedBatchProof
     */
    @JsonProperty("path")
    public List<Integer> path;

}
