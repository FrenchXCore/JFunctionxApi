package eu.frenchxcore.messages.ibc.types.core.channel.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.ibc.types.core.connection.v1.Counterparty;
import java.util.List;

/**
 * Channel defines pipeline for exactly-once packet delivery between specific
 * modules on separate blockchains, which has at least one end capable of
 * sending packets and one end capable of receiving packets.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

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

}
