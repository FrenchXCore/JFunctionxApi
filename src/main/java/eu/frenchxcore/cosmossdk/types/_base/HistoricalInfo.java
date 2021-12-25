package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalInfo {

    @JsonProperty("header")
    public String header;
    
    @JsonProperty("valset")
    public List<Validator> valset;
    
}
