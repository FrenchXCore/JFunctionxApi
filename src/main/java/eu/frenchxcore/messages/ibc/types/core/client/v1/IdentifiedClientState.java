package eu.frenchxcore.messages.ibc.types.core.client.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.ibc.types.lightclients.tendermint.v1.ClientState;

/**
 * IdentifiedClientState defines a client state with an additional client
 * identifier field.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifiedClientState {

    /**
     * client identifier
     */
    @JsonProperty("client_id")
    public String clientId;

    /**
     * client state
     */
    @JsonProperty("client_state")
    public ClientState clientState;

}