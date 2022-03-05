package eu.frenchxcore.messages.fxcore.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.v1beta1.Coin;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryGasPriceResponse {

    /**
     * gasPrices is a list of gas prices by token type.
     */
    @JsonProperty("gas_prices")
    public List<Coin> gasPrices;

}
