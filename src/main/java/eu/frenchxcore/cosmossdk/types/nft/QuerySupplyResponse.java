package eu.frenchxcore.cosmossdk.types.nft;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerySupplyResponse {

    @JsonProperty("amount")
    public BigInteger amount;

}
