package eu.frenchxcore.cosmossdk.types.crypto.multisig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CompactBitArray is an implementation of a space efficient bit array. This is used to ensure that the encoded data takes up a minimal amount of space after proto encoding. This is not thread safe, and is not intended for concurrent usage.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompactBitArray {

    /**
     * 
     */
    @JsonProperty("extra_bits_stored")
    public Long extraBitsStored;

    /**
     * 
     */
    @JsonProperty("elems")
    public String elems;

}
