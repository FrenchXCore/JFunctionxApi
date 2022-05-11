package eu.frenchxcore.model.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * LeafOp represents the raw key-value data we wish to prove, and must be
 * flexible to represent the internal transformation from the original key-value
 * pairs into the basis hash, for many existing merkle trees.
 *
 * key and value are passed in. So that the signature of this operation is:
 * leafOp(key, value) -> output
 *
 * To process this, first prehash the keys and values if needed (ANY means no
 * hash in this case): hkey = prehashKey(key) hvalue = prehashValue(value)
 *
 * Then combine the bytes, and hash it output = hash(prefix || length(hkey) ||
 * hkey || length(hvalue) || hvalue)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeafOp {

    /**
     *
     */
    @JsonProperty("hash")
    public HashOp hash;

    /**
     *
     */
    @JsonProperty("prehash_key")
    public HashOp prehashKey;

    /**
     *
     */
    @JsonProperty("prehash_value")
    public HashOp prehashValue;

    /**
     *
     */
    @JsonProperty("length")
    public LengthOp length;

    /**
     * prefix is a fixed bytes that may optionally be included at the beginning
     * to differentiate a leaf node from an inner node.
     */
    @JsonProperty("prefix")
    public String prefix;

}