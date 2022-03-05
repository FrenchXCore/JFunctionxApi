package eu.frenchxcore.messages.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedelegationResponse {

    @JsonProperty("redelegation")
    public Redelegation redelegation;
    
    @JsonProperty("entries")
    public List<RedelegationEntryResponse> entries;
    
}
