package eu.frenchxcore.model.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ProposalStatus enumerates the valid statuses of a proposal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum ProposalStatus {

    /**
     * PROPOSAL_STATUS_UNSPECIFIED defines the default proposal status.
     */
    PROPOSAL_STATUS_UNSPECIFIED         (0, "0-Unspecified"),

    /**
     * PROPOSAL_STATUS_DEPOSIT_PERIOD defines a proposal status during the deposit period.
     */
    PROPOSAL_STATUS_DEPOSIT_PERIOD      (1, "1-Deposit period"),

    /**
     * PROPOSAL_STATUS_VOTING_PERIOD defines a proposal status during the voting period.
     */
    PROPOSAL_STATUS_VOTING_PERIOD       (2, "2-Voting period"),

    /**
     * PROPOSAL_STATUS_PASSED defines a proposal status of a proposal that has passed.
     */
    PROPOSAL_STATUS_PASSED              (3, "3-Passed"),

    /**
     * PROPOSAL_STATUS_REJECTED defines a proposal status of a proposal that has been rejected.
     */
    PROPOSAL_STATUS_REJECTED            (4, "4-Rejected"),

    /**
     * PROPOSAL_STATUS_FAILED defines a proposal status of a proposal that has failed.
     */
    PROPOSAL_STATUS_FAILED              (5, "5-Failed");
    
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
