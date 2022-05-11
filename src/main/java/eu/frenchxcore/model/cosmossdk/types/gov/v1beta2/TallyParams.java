package eu.frenchxcore.model.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TallyParams {

    /**
     * Minimum percentage of total stake needed to vote for a result to be considered valid.
     */
    @JsonProperty("quorum")
    public String quorum;           // "quorum": "0.400000000000000000"

    /**
     * Minimum proportion of Yes votes for proposal to pass. Default value: 0.5.
     */
    @JsonProperty("threshold")
    public String threshold;        // "threshold": "0.500000000000000000"

    /**
     * Minimum value of Veto votes to Total votes ratio for proposal to be vetoed. Default value: 1/3.
     */
    @JsonProperty("veto_threshold")
    public String vetoThreshold;    // "veto_threshold": "0.334000000000000000"
    
}
