package eu.frenchxcore.model.cosmossdk.query.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.Coin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryBalanceResponse {

    /**
     * balance is the balance of the coin.
     */
    @JsonProperty("balance")
    public Coin balance;

}
