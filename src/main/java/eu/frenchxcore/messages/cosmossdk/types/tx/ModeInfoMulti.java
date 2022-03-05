package eu.frenchxcore.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.crypto.multisig.CompactBitArray;
import java.util.List;

/**
 * ModeInfoMulti is the mode info for a multisig public key
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeInfoMulti {

    /**
     * bitarray specifies which keys within the multisig are signing
     */
    @JsonProperty("bitarray")
    public CompactBitArray bitarray;

    /**
     * modeInfos is the corresponding modes of the signers of the multisig which
     * could include nested multisig public keys
     */
    @JsonProperty("mode_infos")
    public List<ModeInfo> mode_infos;

}
