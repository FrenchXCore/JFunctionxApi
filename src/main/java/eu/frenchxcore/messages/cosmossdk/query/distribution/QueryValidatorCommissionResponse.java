package eu.frenchxcore.messages.cosmossdk.query.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.distribution.ValidatorAccumulatedCommission;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorCommissionResponse {

    /**
     * commission defines the commision the validator received.
     */
    @JsonProperty("commission")
    public ValidatorAccumulatedCommission commission;
    
}
