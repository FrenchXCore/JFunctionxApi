package eu.frenchxcore.messages.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * ExistenceProof takes a key and a value and a set of steps to perform on it.
 * The result of peforming all these steps will provide a "root hash", which can
 * be compared to the value in a header.
 *
 * Since it is computationally infeasible to produce a hash collission for any
 * of the used cryptographic hash functions, if someone can provide a series of
 * operations to transform a given key and value into a root hash that matches
 * some trusted root, these key and values must be in the referenced merkle
 * tree.
 *
 * The only possible issue is maliablity in LeafOp, such as providing extra
 * prefix data, which should be controlled by a spec. Eg. with lengthOp as NONE,
 * prefix = FOO, key = BAR, value = CHOICE and prefix = F, key = OOBAR, value =
 * CHOICE would produce the same value.
 *
 * With LengthOp this is tricker but not impossible. Which is why the
 * "leafPrefixEqual" field in the ProofSpec is valuable to prevent this
 * mutability. And why all trees should length-prefix the data before hashing
 * it.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExistenceProof {

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
     *
     */
    @JsonProperty("path")
    public List<InnerOp> path;

}