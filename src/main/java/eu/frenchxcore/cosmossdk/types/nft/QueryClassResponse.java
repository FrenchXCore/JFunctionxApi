package eu.frenchxcore.cosmossdk.types.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.NftClass;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryClassResponse {

    @JsonProperty("class")
    public NftClass _class;

}
