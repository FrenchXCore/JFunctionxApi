package eu.frenchxcore.model.cosmossdk.types.gov.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * VoteOption enumerates the valid vote options for a given governance proposal.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum VoteOption {

    /**
     * VOTE_OPTION_UNSPECIFIED defines a no-op vote option.
     */
    VOTE_OPTION_UNSPECIFIED     (0, "0-Unspecified"),

    /**
     * VOTE_OPTION_YES defines a yes vote option.
     */
    VOTE_OPTION_YES             (1, "1-Yes"),

    /**
     * VOTE_OPTION_ABSTAIN defines an abstain vote option.
     */
    VOTE_OPTION_ABSTAIN         (2, "2-Abstain"),

    /**
     * VOTE_OPTION_NO defines a no vote option.
     */
    VOTE_OPTION_NO              (3, "3-No"),

    /**
     * VOTE_OPTION_NO_WITH_VETO defines a no with veto vote option.
     */
    VOTE_OPTION_NO_WITH_VETO    (4, "4-No with veto");
    
    public final int iVal;
    public final String sVal;

    @JsonValue
    public final String name;
    
    VoteOption(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }
    
}
