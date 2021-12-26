package eu.frenchxcore.cosmossdk.types.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NftClass {

    @JsonProperty("id")
    public String id;
    
    @JsonProperty("name")
    public String name;
    
    @JsonProperty("symbol")
    public String symbol;
    
    @JsonProperty("description")
    public String description;
    
    @JsonProperty("uri")
    public String uri;
    
    @JsonProperty("uri_hash")
    public String uriHash;
    
    @JsonProperty("data")
    public Object data;
    
}
