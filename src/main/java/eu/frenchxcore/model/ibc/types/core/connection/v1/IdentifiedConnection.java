package eu.frenchxcore.model.ibc.types.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;

/**
 * IdentifiedConnection defines a connection with additional connection identifier field.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifiedConnection {

    /**
     * connection identifier.
     */
    @JsonProperty("id")
    public String id;

    /**
     * client associated with this connection.
     */
    @JsonProperty("client_id")
    public String clientId;

    /**
     * IBC version which can be utilised to determine encodings or protocols for channels or packets utilising this connection
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
     * delay period associated with this connection.
     */
    @JsonProperty("delay_period")
    public BigInteger delayPeriod;

}