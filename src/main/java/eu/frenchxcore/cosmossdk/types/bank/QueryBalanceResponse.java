package eu.frenchxcore.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.Coin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryBalanceResponse {

    /**
     * balance is the balance of the coin.
     */
    @JsonProperty("balance")
    public Coin balance;

}
