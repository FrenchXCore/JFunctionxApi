package eu.frenchxcore.cosmossdk.types.feegrant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllowedMsgAllowance {

    /**
     * allowance can be any of basic and filtered fee allowance.
     */
    @JsonProperty("allowance")
    public Object allowance;
    
    /**
     * allowed_messages are the messages for which the grantee has the access.
     */
    @JsonProperty("allowed_messages")
    public List<String> allowedMessages;
    
}
