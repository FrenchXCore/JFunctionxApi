package eu.frenchxcore.ibc.types.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

/**
 * Params defines the set of Connection parameters.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Params {

    /**
     * maximum expected time per block (in nanoseconds), used to enforce block
     * delay. This parameter should reflect the largest amount of time that the
     * chain might reasonably take to produce the next block under normal
     * operating conditions. A safe choice is 3-5x the expected time per block.
     */
    @JsonProperty("max_expected_time_per_block")
    public BigInteger maxExpectedTimePerBlock;

}