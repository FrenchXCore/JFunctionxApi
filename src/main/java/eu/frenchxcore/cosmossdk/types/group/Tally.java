package eu.frenchxcore.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Tally represents the sum of weighted votes.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tally {

    /**
     * yesCount is the weighted sum of yes votes.
     */
    @JsonProperty("yes_count")
    public String yesCount;

    /**
     * noCount is the weighted sum of no votes.
     */
    @JsonProperty("no_count")
    public String noCount;

    /**
     * abstainCount is the weighted sum of abstainers
     */
    @JsonProperty("abstain_count")
    public String abstainCount;

    /**
     * vetoCount is the weighted sum of vetoes.
     */
    @JsonProperty("veto_count")
    public String vetoCount;

}
