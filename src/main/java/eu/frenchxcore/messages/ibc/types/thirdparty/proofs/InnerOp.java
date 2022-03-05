package eu.frenchxcore.messages.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * InnerOp represents a merkle-proof step that is not a leaf. It represents
 * concatenating two children and hashing them to provide the next result.
 *
 * The result of the previous step is passed in, so the signature of this op is:
 * innerOp(child) -> output
 *
 * The result of applying InnerOp should be: output = op.hash(op.prefix || child
 * || op.suffix)
 *
 * where the || operator is concatenation of binary data, and child is the
 * result of hashing all the tree below this step.
 *
 * Any special data, like prepending child with the length, or prepending the
 * entire operation with some value to differentiate from leaf nodes, should be
 * included in prefix and suffix. If either of prefix or suffix is empty, we
 * just treat it as an empty string
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InnerOp {

    /**
     *
     */
    @JsonProperty("hash")
    public HashOp hash;

    /**
     * 
     */
    @JsonProperty("prefix")
    public String prefix;

    /**
     * 
     */
    @JsonProperty("suffix")
    public String suffix;

}
