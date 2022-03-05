package eu.frenchxcore.messages.cosmossdk.types.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ProposalExecutorResult {

    EXECUTOR_RESULT_UNSPECIFIED (0, "0-An empty value is not allowed."),

    EXECUTOR_RESULT_NOT_RUN     (1, "1-We have not yet run the executor."),

    EXECUTOR_RESULT_SUCCESS     (2, "2-The executor was successful and proposed action updated state."),

    EXECUTOR_RESULT_FAILURE     (3, "3-The executor returned an error and proposed action didn't update state.");
    
    public final int iVal;
    public final String sVal;

    @JsonValue
    public final String name;
    
    ProposalExecutorResult(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }
    
}
