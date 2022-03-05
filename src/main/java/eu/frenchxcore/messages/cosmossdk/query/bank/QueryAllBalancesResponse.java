package eu.frenchxcore.messages.cosmossdk.query.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.Coin;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

import java.util.List;

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
