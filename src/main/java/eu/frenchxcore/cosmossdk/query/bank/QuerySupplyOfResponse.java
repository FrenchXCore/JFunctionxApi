package eu.frenchxcore.cosmossdk.query.bank;

import eu.frenchxcore.cosmossdk.types.Coin;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerySupplyOfResponse {

    /**
     * amount is the supply of the coin.
     */
    @JsonProperty("amount")
    public Coin amount;
    
}
