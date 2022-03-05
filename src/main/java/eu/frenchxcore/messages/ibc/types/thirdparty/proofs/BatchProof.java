package eu.frenchxcore.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * BatchProof is a group of multiple proof types than can be compressed
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchProof {

    /**
     *
     */
    @JsonProperty("entries")
    public List<BatchEntry> entries;

}