package eu.frenchxcore.cosmossdk.query.bank;

import eu.frenchxcore.cosmossdk.types.Coin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryTotalSupplyResponse {

    /**
     * supply is the supply of the coins
     */
    @JsonProperty("supply")
    public List<Coin> supply;
    
    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
