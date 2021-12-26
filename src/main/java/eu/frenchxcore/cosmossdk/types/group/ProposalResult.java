package eu.frenchxcore.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ProposalResult {

    RESULT_UNSPECIFIED  (0, "0-An empty value is invalid and not allowed."),

    RESULT_UNFINALIZED  (1, "1-Until a final tally has happened the status is unfinalized."),

    RESULT_ACCEPTED     (2, "2-Final result of the tally."),

    RESULT_REJECTED     (3, "3-Final result of the tally.");
    
    public int iVal;
    public String sVal;

    @JsonValue
    public String name;
    
    ProposalResult(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }
    
}
