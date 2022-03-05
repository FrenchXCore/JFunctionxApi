package eu.frenchxcore.messages.ibc.types.lightclients.tendermint.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

/**
 * Fraction defines the protobuf message type for tmmath.Fraction that only
 * supports positive values.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Fraction {

    /**
     *
     */
    @JsonProperty("numerator")
    public BigInteger numerator;

    /**
     *
     */
    @JsonProperty("denominator")
    public BigInteger denominator;

}
