package eu.frenchxcore.messages.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * TxDescriptor describes the accepted transaction type
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxDescriptor {

    /**
     * fullname is the protobuf fullname of the raw transaction type (for
     * instance the tx.Tx type) it is not meant to support polymorphism of
     * transaction types, it is supposed to be used by reflection clients to
     * understand if they can handle a specific transaction type in an
     * application.
     */
    @JsonProperty("fullname")
    public String fullname;

    /**
     * msgs lists the accepted application messages (sdk.Msg)
     */
    @JsonProperty("msgs")
    public List<MsgDescriptor> msgs;

}
