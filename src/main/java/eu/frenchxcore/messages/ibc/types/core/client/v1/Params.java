package eu.frenchxcore.messages.ibc.types.core.client.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Params defines the set of IBC light client parameters.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Params {

    /**
     * allowed_clients defines the list of allowed client state types.
     */
    @JsonProperty("allowed_clients")
    public List<String> allowedClients;

}
