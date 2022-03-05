package eu.frenchxcore.messages.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * GasInfo defines tx execution gas context.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    /**
     * Deprecated. data is any data returned from message or handler execution.
     * It MUST be length prefixed in order to separate data from multiple
     * message executions. Deprecated. This field is still populated, but prefer
     * msg_response instead because it also contains the Msg response typeURL.
     */
    @JsonProperty("data")
    public String data;

    /**
     * log contains the log information from message or handler execution.
     */
    @JsonProperty("log")
    public String log;

    /**
     * events contains a slice of Event objects that were emitted during message
     * or handler execution.
     */
    @JsonProperty("events")
    public List<String> events;

    /**
     * msg_responses contains the Msg handler responses type packed in Anys.
     */
    @JsonProperty("msg_responses")
    public List<String> msgResponses;

}
