package eu.frenchxcore.cosmossdk.types.mint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryInflationResponse {

    /**
     * inflation is the current minting inflation value.
     */
    @JsonProperty("inflation")
    public BigDecimal inflation;

}
