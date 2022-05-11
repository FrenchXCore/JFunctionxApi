package eu.frenchxcore.model.cosmossdk.query.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.staking.Validator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryValidatorResponse {

    /**
     * validator defines the the validator info.
     */
    @JsonProperty("validator")
    public Validator validator;
    
}
