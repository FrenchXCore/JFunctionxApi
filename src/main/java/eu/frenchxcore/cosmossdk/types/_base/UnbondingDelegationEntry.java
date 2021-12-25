package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnbondingDelegationEntry {

    /**
     * creation_height is the height which the unbonding took place.
     */
    @JsonProperty("creation_height")
    public BigInteger creationHeight;
    
    /**
     * completion_time is the unix time for unbonding completion.
     */
    @JsonProperty("completion_time")
    public Instant completionTime;
    
    /**
     * initial_balance defines the tokens initially scheduled to receive at completion.
     */
    @JsonProperty("initial_balance")
    public String initialBalance;
    
    /**
     * balance defines the tokens to receive at completion.
     */
    @JsonProperty("balance")
    public String balance;
    
}
