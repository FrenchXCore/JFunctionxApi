package eu.frenchxcore.messages.cosmossdk.query.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.Coin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerySupplyOfResponse {

    /**
     * amount is the supply of the coin.
     */
    @JsonProperty("amount")
    public Coin amount;
    
}
