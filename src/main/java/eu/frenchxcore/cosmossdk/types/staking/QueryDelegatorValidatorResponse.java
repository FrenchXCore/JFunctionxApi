package eu.frenchxcore.cosmossdk.types.staking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.Validator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDelegatorValidatorResponse {

    /**
     * validator defines the the validator info.
     */
    @JsonProperty("validator")
    public Validator validator;
    
}
