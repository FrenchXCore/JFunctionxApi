package eu.frenchxcore.model.cosmossdk.query.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryOwnerResponse {

    @JsonProperty("owner")
    public String owner;

}
