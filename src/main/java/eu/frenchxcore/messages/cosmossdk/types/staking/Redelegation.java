package eu.frenchxcore.messages.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Redelegation {

    /**
     * delegator_address is the bech32-encoded address of the delegator.
     */
    @JsonProperty("delegator_address")
    public String delegatorAddress;
    
    /**
     * validator_src_address is the validator redelegation source operator address.
     */
    @JsonProperty("validator_src_address")
    public String validatorSrcAddress;
    
    /**
     * validator_dst_address is the validator redelegation destination operator address.
     */
    @JsonProperty("validator_dst_address")
    public String validatorDstAddress;
    
    /**
     * entries are the redelegation entries.
     */
    @JsonProperty("entries")
    public List<RedelegationEntry> entries;
    
}