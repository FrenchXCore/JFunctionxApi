package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Delegation {

    /**
     * delegator_address is the bech32-encoded address of the delegator.
     */
    @JsonProperty("delegator_address")
    public String delegatorAddress;
    
    /**
     * validator_address is the bech32-encoded address of the validator.
     */
    @JsonProperty("validator_address")
    public String validatorAddress;
    
    /**
     * shares define the delegation shares received.
     */
    @JsonProperty("shares")
    public BigDecimal shares;
    
}
