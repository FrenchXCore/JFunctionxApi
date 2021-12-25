package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedelegationEntry {

    /**
     * creation_height defines the height which the redelegation took place.
     */
    @JsonProperty("creation_height")
    public BigInteger creationHeight;
    
    /**
     * completion_time defines the unix time for redelegation completion.
     */
    @JsonProperty("completion_time")
    public Instant completionTime;
    
    /**
     * initial_balance defines the initial balance when redelegation started.
     */
    @JsonProperty("initial_balance")
    public String initialBalance;
    
    /**
     * shares_dst is the amount of destination-validator shares created by redelegation.

     */
    @JsonProperty("shares_dst")
    public String sharesDst;
    
}
