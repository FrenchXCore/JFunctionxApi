package eu.frenchxcore.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ProofSpec defines what the expected parameters are for a given proof type.
 * This can be stored in the client and used to validate any incoming proofs.
 *
 * verify(ProofSpec, Proof) -> Proof | Error
 *
 * As demonstrated in tests, if we don't fix the algorithm used to calculate the
 * LeafHash for a given tree, there are many possible key-value pairs that can
 * generate a given hash (by interpretting the preimage differently). We need
 * this for proper security, requires client knows a priori what tree format
 * server uses. But not in code, rather a configuration object.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProofSpec {

    /**
     * any field in the ExistenceProof must be the same as in this spec. except
     * Prefix, which is just the first bytes of prefix (spec can be longer)
     */
    @JsonProperty("leaf_spec")
    public LeafOp leafSpec;

    /**
     *
     */
    @JsonProperty("inner_spec")
    public InnerSpec innerSpec;

    /**
      *max_depth (if > 0) is the maximum number of InnerOps allowed (mainly for fixed-depth tries)
     */
    @JsonProperty("max_depth")
    public Integer maxDepth;

    /**
     * min_depth (if > 0) is the minimum number of InnerOps allowed (mainly for fixed-depth tries)
     */
    @JsonProperty("min_depth")
    public Integer minDepth;

}
