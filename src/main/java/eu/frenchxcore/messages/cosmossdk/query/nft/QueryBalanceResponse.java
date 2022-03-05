package eu.frenchxcore.messages.cosmossdk.query.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryBalanceResponse {

    @JsonProperty("amount")
    public BigInteger amount;

}
