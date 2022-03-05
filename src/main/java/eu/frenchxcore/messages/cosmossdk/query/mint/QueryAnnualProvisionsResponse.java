package eu.frenchxcore.messages.cosmossdk.query.mint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryAnnualProvisionsResponse {

    /**
     * annual_provisions is the current minting annual provisions value.
     */
    @JsonProperty("annual_provisions")
    public BigDecimal annualProvisions;

}
