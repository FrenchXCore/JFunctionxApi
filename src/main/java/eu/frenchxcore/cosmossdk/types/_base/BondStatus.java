package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum BondStatus {
    /**
     * UNSPECIFIED defines an invalid validator status.
     */
    BOND_STATUS_UNSPECIFIED (0, "Unspecified"),

    /**
     * UNBONDED defines a validator that is not bonded.
     */
    BOND_STATUS_UNBONDED    (1, "Unbonded"),

    /**
     * UNBONDING defines a validator that is unbonding.
     */
    BOND_STATUS_UNBONDING   (2, "Unbonding"),

    /**
     * BONDED defines a validator that is bonded.
     */
    BOND_STATUS_BONDED      (3, "Bonded");
    
    public int iVal;
    public String sVal;

    @JsonValue
    public String name;
    
    BondStatus(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.name = this.name();
    }
    
}
