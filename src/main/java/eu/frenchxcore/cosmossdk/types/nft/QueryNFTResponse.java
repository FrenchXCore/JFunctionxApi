package eu.frenchxcore.cosmossdk.types.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.NFT;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryNFTResponse {

    @JsonProperty("nft")
    public NFT nft;

}
