package eu.frenchxcore.cosmossdk.types.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ProposalStatus {

    PROPOSAL_STATUS_UNSPECIFIED         (0, "0-Unspecified"),

    PROPOSAL_STATUS_DEPOSIT_PERIOD      (1, "1-Deposit period"),

    PROPOSAL_STATUS_VOTING_PERIOD       (2, "2-Voting period"),

    PROPOSAL_STATUS_PASSED              (3, "3-Passed"),
    
    PROPOSAL_STATUS_REJECTED            (4, "4-Rejected"),
    
    PROPOSAL_STATUS_FAILED              (5, "5-Failed");
    
    public int iVal;
    public String sVal;

    @JsonValue
    public String name;
    
    ProposalStatus(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }
    
}
