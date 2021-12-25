package eu.frenchxcore.cosmossdk.types.bank;

import eu.frenchxcore.cosmossdk.types._base.Coin;
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
