package eu.frenchxcore.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Evidence {

    /**
     * 
     */
    @JsonProperty("evidence")
    public List<String> evidence;
    
}