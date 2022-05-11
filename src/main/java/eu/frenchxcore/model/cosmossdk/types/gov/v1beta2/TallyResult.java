package eu.frenchxcore.model.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TallyResult defines a standard tally for a governance proposal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TallyResult {

    @JsonProperty("yes")
    public String yes;
    
    @JsonProperty("abstain")
    public String abstain;
    
    @JsonProperty("no")
    public String no;
    
    @JsonProperty("no_with_veto")
    public String noWithVeto;
    
}
