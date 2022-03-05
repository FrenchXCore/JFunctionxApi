package eu.frenchxcore.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * ABCIMessageLog defines a structure containing an indexed tx ABCI message log.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ABCIMessageLog {

    /**
     * 
     */
    @JsonProperty("msg_index")
    public Long msgIndex;

    /**
     * 
     */
    @JsonProperty("log")
    public String log;

    /**
     * Events contains a slice of Event objects that were emitted during some execution.
     */
    @JsonProperty("events")
    public List<StringEvent> events;

}
