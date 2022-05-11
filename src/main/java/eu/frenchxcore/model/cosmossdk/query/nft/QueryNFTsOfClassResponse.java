package eu.frenchxcore.model.cosmossdk.query.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.nft.Nft;
import eu.frenchxcore.model.cosmossdk.types.query.PageResponse;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryNFTsOfClassResponse {

    @JsonProperty("nfts")
    public List<Nft> nfts;

    @JsonProperty("pagination")
    public PageResponse pagination;

}
