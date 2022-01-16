package eu.frenchxcore.ibc.types.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.util.List;

/**
 * ConnectionEnd defines a stateful object on a chain connected to another
 * separate one. NOTE: there must only be 2 defined ConnectionEnds to establish
 * a connection between two chains.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionEnd {

    /**
     * client associated with this connection.
     */
    @JsonProperty("client_id")
    public String clientId;

    /**
     * IBC version which can be utilised to determine encodings or protocols for
     * channels or packets utilising this connection.
     */
    @JsonProperty("versions")
    public List<Version> versions;

    /**
     * current state of the connection end.
     */
    @JsonProperty("state")
    public State state;

    /**
     * counterparty chain associated with this connection.
     */
    @JsonProperty("counterparty")
    public Counterparty counterparty;

    /**
     *  delay period that must pass before a consensus state can be used for packet-verification NOTE: delay period logic is only implemented by some clients.
     */
    @JsonProperty("delay_period")
    public BigInteger delayPeriod;

}