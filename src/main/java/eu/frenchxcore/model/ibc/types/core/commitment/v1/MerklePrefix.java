package eu.frenchxcore.model.ibc.types.core.commitment.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MerklePrefix is merkle path prefixed to the key.
 * 
 * The constructed key from the Path and the key will be append(Path.KeyPath,
 * append(Path.KeyPrefix, key...))
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerklePrefix {

    /**
     * 
     */
    @JsonProperty("key_prefix")
    public String keyPrefix;

}
