package eu.frenchxcore.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.staking.Validator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorResponse {

    /**
     * validator defines the the validator info.
     */
    @JsonProperty("validator")
    public Validator validator;
    
}
