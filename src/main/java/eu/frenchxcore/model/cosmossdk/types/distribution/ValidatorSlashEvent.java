package eu.frenchxcore.model.cosmossdk.types.distribution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * ValidatorSlashEvent represents a validator slash event.
 * Height is implicit within the store key. This is needed to calculate appropriate amount of staking tokens 
 * for delegations which are withdrawn after a slash has occurred.
 */
public class ValidatorSlashEvent {
    
    @JsonProperty("validator_period")
    public BigInteger validator_period;

    @JsonProperty("pagination")
    public String fraction;

}
