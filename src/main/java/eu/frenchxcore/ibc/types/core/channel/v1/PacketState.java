package eu.frenchxcore.ibc.types.core.channel.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

/**
 * PacketState defines the generic type necessary to retrieve and store packet
 * commitments, acknowledgements, and receipts. Caller is responsible for
 * knowing the context necessary to interpret this state as a commitment,
 * acknowledgement, or a receipt.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketState {

    /**
     * channel port identifier.
     */
    @JsonProperty("port_id")
    public String portId;

    /**
     * channel unique identifier.
     */
    @JsonProperty("channel_id")
    public String channeId;

    /**
     * packet sequence.
     */
    @JsonProperty("sequence")
    public BigInteger sequence;

    /**
     * embedded data that represents packet state.
     */
    @JsonProperty("data")
    public String data;

}