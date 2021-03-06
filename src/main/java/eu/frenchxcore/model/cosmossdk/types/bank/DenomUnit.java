package eu.frenchxcore.model.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DenomUnit {

    @JsonProperty("denom")
    public String denom;
    
    @JsonProperty("exponent")
    public int exponent;
    
    @JsonProperty("aliases")
    public List<String> aliases;
    
}
