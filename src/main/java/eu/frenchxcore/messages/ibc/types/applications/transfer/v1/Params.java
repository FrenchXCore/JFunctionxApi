package eu.frenchxcore.ibc.types.applications.transfer.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Params defines the set of IBC transfer parameters.
 *
 * NOTE: To prevent a single token from being transferred, set the
 * TransfersEnabled parameter to true and then set the bank module's SendEnabled
 * parameter for the denomination to false.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Params {

    /**
     * send_enabled enables or disables all cross-chain token transfers from
     * this chain.
     */
    @JsonProperty("send_enabled")
    public Boolean sendEnabled;

    /**
     * receive_enabled enables or disables all cross-chain token transfers to
     * this chain.
     */
    @JsonProperty("receive_enabled")
    public Boolean receiveEnabled;

}
