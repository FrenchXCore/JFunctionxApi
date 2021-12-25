package eu.frenchxcore.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;
import eu.frenchxcore.cosmossdk.types._base.Coin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAllBalancesResponse {

    /**
     * balances is the balances of all the coins.
     */
    @JsonProperty("balances")
    public List<Coin> balances;

    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}
