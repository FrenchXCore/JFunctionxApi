package eu.frenchxcore.messages.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ThresholdDecisionPolicy implements the DecisionPolicy interface
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThresholdDecisionPolicy {

    /**
     * threshold is the minimum weighted sum of yes votes that must be met or
     * exceeded for a proposal to succeed.
     */
    @JsonProperty("threshold")
    public String threshold;

    /**
     * timeout is the duration from submission of a proposal to the end of
     * voting period Within this times votes and exec messages can be submitted.
     */
    @JsonProperty("timeout")
    public String timeout;    //Duration

}
