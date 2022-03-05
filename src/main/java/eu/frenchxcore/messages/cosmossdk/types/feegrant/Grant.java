package eu.frenchxcore.messages.cosmossdk.types.feegrant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Grant is stored in the KVStore to record a grant with full context
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Grant {

    /**
     * granter is the address of the user granting an allowance of their funds.
     */
    @JsonProperty("granter")
    public String granter;

    /**
     * grantee is the address of the user being granted an allowance of another user's funds.
     */
    @JsonProperty("grantee")
    public String grantee;

    /**
     * allowance can be any of basic and filtered fee allowance.
     */
    @JsonProperty("allowance")
    public Object allowance;

}