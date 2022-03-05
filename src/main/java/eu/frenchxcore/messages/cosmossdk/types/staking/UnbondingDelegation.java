package eu.frenchxcore.messages.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnbondingDelegation {

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
     * entries are the unbonding delegation entries.
     */
    @JsonProperty("entries")
    public List<UnbondingDelegationEntry> entries;
    
}
