package eu.frenchxcore.cosmossdk.types.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.PublicKey;

import java.math.BigInteger;

/**
 * BaseAccount defines a base account type.
 * It contains all the necessary fields for basic account functionality.
 * Any custom account type should extend this type for additional functionality (e.g. vesting).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseAccount extends Account {

    /**
     *
     */
    @JsonProperty("address")
    public String address;

    /**
     *
     */
    @JsonProperty("pub_key")
    public PublicKey pubKey;

    /**
     *
     */
    @JsonProperty("account_number")
    public BigInteger accountNumber;

    /**
     *
     */
    @JsonProperty("account_sequence")
    public BigInteger accountSequence;

}
