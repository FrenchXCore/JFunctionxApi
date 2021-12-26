package eu.frenchxcore.cosmossdk.query.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.nft.Nft;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryNFTsOfClassResponse {

    @JsonProperty("nfts")
    public List<Nft> nfts;

    @JsonProperty("pagination")
    public PageResponse pagination;

}
