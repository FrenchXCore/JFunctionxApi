package eu.frenchxcore.messages.cosmossdk.types.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.tendermint.types.crypto.PubKey;

import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Validator {

    /**
     * address
     */
    @JsonProperty("address")
    public String address;
    
    /**
     * pubKey
     */
    @JsonProperty("pub_key")
    public PubKey pubKey;
    
    /**
     * votingPower
     */
    @JsonProperty("voting_power")
    public BigInteger votingPower;
    
    /**
     * pubKey
     */
    @JsonProperty("proposer_priority")
    public BigInteger proposerPriority;
    
}
