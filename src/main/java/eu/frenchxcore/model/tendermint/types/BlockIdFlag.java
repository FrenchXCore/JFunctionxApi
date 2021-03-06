package eu.frenchxcore.model.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum BlockIdFlag {
    BlockIDFlagUnknown  (0, "BLOCK_ID_FLAG_UNKNOWN"),
    BlockIDFlagAbsent   (1, "BLOCK_ID_FLAG_ABSENT"),
    BlockIDFlagCommit   (2, "BLOCK_ID_FLAG_COMMIT"),
    BlockIDFlagNil      (3, "BLOCK_ID_FLAG_NIL");
    
    public final int iVal;
    
    @JsonValue
    public final String sVal;

    BlockIdFlag(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
    }
    
}
