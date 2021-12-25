package eu.frenchxcore.cosmossdk.types.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types._base.NFT;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryNFTsOfClassResponse {

    @JsonProperty("nfts")
    public List<NFT> nfts;

    @JsonProperty("pagination")
    public PageResponse pagination;

}
