package eu.frenchxcore.cosmossdk.types.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    public String pubKey;
    
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
