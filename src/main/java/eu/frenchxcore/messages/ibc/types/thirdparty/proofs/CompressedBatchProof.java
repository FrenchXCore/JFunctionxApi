package eu.frenchxcore.messages.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompressedBatchProof {

    /**
     *
     */
    @JsonProperty("entries")
    public List<CompressedBatchEntry> entries;

    /**
     *
     */
    @JsonProperty("lookup_inners")
    public List<InnerOp> lookupInners;

}
