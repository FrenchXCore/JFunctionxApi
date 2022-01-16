package eu.frenchxcore.ibc.types.applications.transfer.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * FungibleTokenPacketData defines a struct for the packet payload See
 * FungibleTokenPacketData spec:
 * https://github.com/cosmos/ibc/tree/master/spec/app/ics-020-fungible-token-transfer#data-structures
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FungibleTokenPacketData {

    /**
     * the token denomination to be transferred
     */
    @JsonProperty("denom")
    public String denom;

    /**
     * the token amount to be transferred
     */
    @JsonProperty("amount")
    public String amount;

    /**
     * the sender address
     */
    @JsonProperty("sender")
    public String sender;

    /**
     * the recipient address on the destination chain
     */
    @JsonProperty("receiver")
    public String receiver;

}
