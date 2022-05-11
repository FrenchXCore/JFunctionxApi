package eu.frenchxcore.model.ibc.types.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * ClientPaths define all the connection paths for a client state.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientPaths {

    /**
     * list of connection paths
     */
    @JsonProperty("paths")
    public List<String> paths;

}