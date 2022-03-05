package eu.frenchxcore.messages.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ModeInfo describes the signing mode of a single or nested multisig signer.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModeInfo {

    /**
     * single represents a single signer
     */
    @JsonProperty("single")
    public ModeInfoSingle single;

    /**
     * multi represents a nested multisig signer
     */
    @JsonProperty("multi")
    public ModeInfoMulti multi;

}
