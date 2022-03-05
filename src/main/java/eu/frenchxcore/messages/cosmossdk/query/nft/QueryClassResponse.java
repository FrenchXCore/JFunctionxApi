package eu.frenchxcore.cosmossdk.query.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.nft.NftClass;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryClassResponse {

    @JsonProperty("class")
    public NftClass _class;

}
