package eu.frenchxcore.model.cosmossdk.types.crypto.multisig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * MultiSignature wraps the signatures from a multisig.LegacyAminoPubKey. See
 * cosmos.tx.v1betata1.ModeInfo.Multi for how to specify which signers signed
 * and with which modes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MultiSignature {

    /**
     *
     */
    @JsonProperty("signatures")
    public List<String> signatures;

}
