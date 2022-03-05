package eu.frenchxcore.messages.cosmossdk.types.upgrade.v1beta1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Date;

/**
 * Plan specifies information about a planned upgrade and when it should occur.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {

    /**
     * Sets the name for the upgrade. This name will be used by the upgraded
     * version of the software to apply any special "on-upgrade" commands during
     * the first BeginBlock method after the upgrade is applied. It is also used
     * to detect whether a software version can handle a given upgrade. If no
     * upgrade handler with this name has been set in the software, it will be
     * assumed that the software is out-of-date when the upgrade Time or Height
     * is reached and the software will exit.
     */
    @JsonProperty("name")
    public String name;

    /**
     * Deprecated. Deprecated: Time based upgrades have been deprecated. Time based upgrade logic has been removed from the SDK. If this field is not empty, an error will be thrown.
     */
    @JsonProperty("time")
    public Date time;

    /**
     * The height at which the upgrade must be performed. Only used if Time is not set.
     */
    @JsonProperty("height")
    public BigInteger height;

    /**
     * Any application specific upgrade info to be included on-chain such as a git commit that validators could automatically upgrade to
     */
    @JsonProperty("info")
    public String info;

    /**
     * Deprecated. Deprecated: UpgradedClientState field has been deprecated. IBC upgrade logic has been moved to the IBC module in the sub module 02-client. If this field is not empty, an error will be thrown.
     */
    @JsonProperty("upgraded_client_state")
    public String upgradedClientState;

}
