package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pool {

    @JsonProperty("unbonded_tokens")
    public String unbondedTokens;
    
    @JsonProperty("bonded_tokens")
    public String bondedTokens;

}
