package eu.frenchxcore.cosmossdk.query.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.gov.v1beta1.Deposit;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDepositResponse {

    /**
     * deposit defines the requested deposit.
     */
    @JsonProperty("deposit")
    public Deposit deposit;

}
