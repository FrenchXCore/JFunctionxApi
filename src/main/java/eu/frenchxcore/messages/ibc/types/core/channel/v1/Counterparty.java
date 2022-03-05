package eu.frenchxcore.messages.ibc.types.core.channel.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Counterparty defines a channel end counterparty
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Counterparty {

    /**
     * port on the counterparty chain which owns the other end of the channel.
     */
    @JsonProperty("port_id")
    public String portId;

    /**
     * channel end on the counterparty chain
     */
    @JsonProperty("channel_id")
    public String channeId;

    /**
     * counterparty channel end
     */
    @JsonProperty("counterparty")
    public Counterparty counterparty;

    /**
     * list of connection identifiers, in order, along which packets sent on
     * this channel will travel
     */
    @JsonProperty("connection_hops")
    public List<String> connectionHops;

    /**
     * opaque channel version, which is agreed upon during the handshake
     */
    @JsonProperty("version")
    public String version;

}