package eu.frenchxcore.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * InnerSpec contains all store-specific structure info to determine if two
 * proofs from a given store are neighbors.
 *
 * This enables:
 *
 * isLeftMost(spec: InnerSpec, op: InnerOp)
 * isRightMost(spec: InnerSpec, op: InnerOp)
 * isLeftNeighbor(spec: InnerSpec, left: InnerOp, right: InnerOp)
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InnerSpec {

    /**
     * Child order is the ordering of the children node, must count from 0 iavl tree is [0, 1] (left then right) merk is [0, 2, 1] (left, right, here)
     */
    @JsonProperty("child_order")
    public List<Integer> childOrder;

    /**
     *
     */
    @JsonProperty("child_size")
    public Integer childSize;

    /**
     * 
     */
    @JsonProperty("min_prefix_length")
    public Integer minPrefixLength;

    /**
     * 
     */
    @JsonProperty("max_prefix_length")
    public Integer maxPrefixLength;

    /**
     * empty child is the prehash image that is used when one child is nil (eg. 20 bytes of 0)
     */
    @JsonProperty("empty_child")
    public String emptyChild;

    /**
     * hash is the algorithm that must be used for each InnerOp
     */
    @JsonProperty("hash")
    public HashOp hash;

}