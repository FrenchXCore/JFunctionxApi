package eu.frenchxcore.model.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum SignMode {

    SIGN_MODE_UNSPECIFIED(0),
    SIGN_MODE_DIRECT(1),
    SIGN_MODE_TEXTUAL(2),
    SIGN_MODE_DIRECT_AUX(3),
    SIGN_MODE_LEGACY_AMINO_JSON(127);

    public final int iVal;

    @JsonValue
    public final String name;

    SignMode(int iVal) {
        this.iVal = iVal;
        this.name = this.name();
    }

}
