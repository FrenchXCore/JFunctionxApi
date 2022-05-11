package eu.frenchxcore.model.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {

    @JsonProperty("description")
    public String description;
    
    @JsonProperty("denom_units")
    public List<DenomUnit> denomUnits;
    
    @JsonProperty("base")
    public String base;
    
    @JsonProperty("display")
    public String display;
    
    @JsonProperty("name")
    public String name;
    
}
