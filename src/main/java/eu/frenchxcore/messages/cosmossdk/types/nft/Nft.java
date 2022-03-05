package eu.frenchxcore.messages.cosmossdk.types.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Nft {

    @JsonProperty("classId")
    public String classId;

    @JsonProperty("id")
    public String id;

    @JsonProperty("uri")
    public String uri;

    @JsonProperty("uri_hash")
    public String uriHash;

    @JsonProperty("data")
    public Object data;

}
