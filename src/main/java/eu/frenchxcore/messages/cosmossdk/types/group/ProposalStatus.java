package eu.frenchxcore.messages.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ProposalStatus {

    STATUS_UNSPECIFIED  (0, "0-An empty value is invalid and not allowed."),

    STATUS_SUBMITTED    (1, "1-Initial status of a proposal when persisted."),

    STATUS_CLOSED       (2, "2-Final status of a proposal when the final tally was executed."),

    STATUS_ABORTED      (3, "3-Final status of a proposal when the group was modified before the final tally.");
    
    public final int iVal;
    public final String sVal;

    @JsonValue
    public final String name;
    
    ProposalStatus(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }
    
}
