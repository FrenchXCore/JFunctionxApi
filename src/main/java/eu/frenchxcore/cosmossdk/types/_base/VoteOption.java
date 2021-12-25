package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum VoteOption {

    VOTE_OPTION_UNSPECIFIED     (0, "0-Unspecified"),
    VOTE_OPTION_YES             (1, "1-Yes"),
    VOTE_OPTION_ABSTAIN         (2, "2-Abstain"),
    VOTE_OPTION_NO              (3, "3-No"),
    VOTE_OPTION_NO_WITH_VETO    (4, "4-No with veto");
    
    public int iVal;
    public String sVal;

    @JsonValue
    public String name;
    
    VoteOption(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }
    
}
