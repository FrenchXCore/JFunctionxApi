package eu.frenchxcore.messages.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedelegationEntryResponse {

    @JsonProperty("redelegation_entry")
    public RedelegationEntry redelegationEntry;
    
    @JsonProperty("balance")
    public String balance;
    
}
