package eu.frenchxcore.model.ibc.types.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * ConnectionPaths define all the connection paths for a given client state.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionPaths {

    /**
     * client state unique identifier
     */
    @JsonProperty("client_id")
    public String clientId;

    /**
     * list of connection paths
     */
    @JsonProperty("paths")
    public List<String> paths;

}