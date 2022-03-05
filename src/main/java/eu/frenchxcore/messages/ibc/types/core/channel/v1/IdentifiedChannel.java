package eu.frenchxcore.ibc.types.core.channel.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.ibc.types.core.connection.v1.Counterparty;
import java.util.List;

/**
 * IdentifiedChannel defines a channel with additional port and channel
 * identifier fields.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifiedChannel {

    /**
     * current state of the channel end
     */
    @JsonProperty("state")
    public State state;

    /**
     * whether the channel is ordered or unordered
     */
    @JsonProperty("ordering")
    public Order ordering;

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

    /**
     * port identifier
     */
    @JsonProperty("port_id")
    public String portId;

    /**
     * channel identifier
     */
    @JsonProperty("channel_id")
    public String channelId;

}