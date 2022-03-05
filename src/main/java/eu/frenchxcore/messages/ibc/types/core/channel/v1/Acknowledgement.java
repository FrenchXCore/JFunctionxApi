package eu.frenchxcore.messages.ibc.types.core.channel.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Acknowledgement is the recommended acknowledgement format to be used by
 * app-specific protocols. NOTE: The field numbers 21 and 22 were explicitly
 * chosen to avoid accidental conflicts with other protobuf message formats used
 * for acknowledgements. The first byte of any message with this format will be
 * the non-ASCII values `0xaa` (result) or `0xb2` (error). Implemented as
 * defined by ICS:
 * https://github.com/cosmos/ibc/tree/master/spec/core/ics-004-channel-and-packet-semantics#acknowledgement-envelope
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Acknowledgement {

    /**
     * either result
     */
    @JsonProperty("result")
    public String result;

    /**
     * or error
     */
    @JsonProperty("error")
    public String error;

}