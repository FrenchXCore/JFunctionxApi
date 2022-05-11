package eu.frenchxcore.model.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * MsgDescriptor describes a cosmos-sdk message that can be delivered with a transaction
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgDescriptor {

    /**
     * msg_type_url contains the TypeURL of a sdk.Msg.
     */
    @JsonProperty("msg_type_url	")
    public String msgTypeUrl;

}
