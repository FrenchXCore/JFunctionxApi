package eu.frenchxcore.messages.ibc.types.core.commitment.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.ibc.types.thirdparty.proofs.CommitmentProof;
import java.util.List;

/**
 * MerkleProof is a wrapper type over a chain of CommitmentProofs. It
 * demonstrates membership or non-membership for an element or set of elements,
 * verifiable in conjunction with a known commitment root. Proofs should be
 * succinct. MerkleProofs are ordered from leaf-to-root
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerkleProof {

    /**
     *
     */
    @JsonProperty("proofs")
    public List<CommitmentProof> proofs;

}
