package eu.frenchxcore.cosmossdk.query.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.cosmossdk.types.base.v1beta1.Coin;

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
