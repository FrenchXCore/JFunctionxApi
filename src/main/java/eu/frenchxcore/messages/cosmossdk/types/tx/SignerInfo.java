package eu.frenchxcore.messages.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Map;

/**
 * SignerInfo describes the public key and signing mode of a single top-level
 * signer.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignerInfo {

    /**
     * publicKey is the public key of the signer. It is optional for accounts
     * that already exist in state. If unset, the verifier can use the required
     * \ signer address for this position and lookup the public key.
     */
    @JsonProperty("public_key")
    public Map<String,Object> publicKey;

    /**
     * modeInfo describes the signing mode of the signer and is a nested
     * structure to support nested multisig pubkey's
     */
    @JsonProperty("mode_info")
    public ModeInfo mode_info;

    /**
     * sequence is the sequence of the account, which describes the number of
     * committed transactions signed by a given address. It is used to prevent
     * replay attacks.
     */
    @JsonProperty("sequence")
    public BigInteger sequence;

}
