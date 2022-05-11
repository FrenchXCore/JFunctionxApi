package eu.frenchxcore.model.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum BroadcastMode {

    BROADCAST_MODE_UNSPECIFIED(0, "0-zero-value for mode ordering"),
    BROADCAST_MODE_BLOCK(1, "1-BROADCAST_MODE_BLOCK defines a tx broadcasting mode where the client waits for the tx to be committed in a block."),
    BROADCAST_MODE_SYNC(2, "2-BROADCAST_MODE_SYNC defines a tx broadcasting mode where the client waits for a CheckTx execution response only."),
    BROADCAST_MODE_ASYNC(3, "3-BROADCAST_MODE_ASYNC defines a tx broadcasting mode where the client returns immediately.");

    public final int iVal;
    public final String sVal;

    @JsonValue
    public final String name;

    BroadcastMode(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }

}
