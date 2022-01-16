package eu.frenchxcore.ibc.types.core.commitment.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * MerklePath is the path used to verify commitment proofs, which can be an arbitrary structured object (defined by a commitment type).
 * MerklePath is represented from root-to-leaf
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerklePath {

    /**
     * 
     */
    @JsonProperty("key_path")
    public List<String> keyPath;

}
