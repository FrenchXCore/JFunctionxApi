package eu.frenchxcore.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum Choice {

    CHOICE_UNSPECIFIED  (0, "0-CHOICE_UNSPECIFIED defines a no-op voting choice."),

    CHOICE_NO           (1, "1-CHOICE_NO defines a no voting choice."),

    CHOICE_YES          (2, "2-CHOICE_YES defines a yes voting choice."),

    CHOICE_ABSTAIN      (3, "3-CHOICE_ABSTAIN defines an abstaining voting choice."),

    CHOICE_VETO         (4, "4-CHOICE_VETO defines a voting choice with veto.");
    
    public int iVal;
    public String sVal;

    @JsonValue
    public String name;
    
    Choice(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }
    
}
