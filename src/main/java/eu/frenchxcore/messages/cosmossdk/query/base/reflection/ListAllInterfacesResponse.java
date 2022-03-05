package eu.frenchxcore.messages.cosmossdk.query.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListAllInterfacesResponse {

    /**
     * interfaceNames is an array of all the registered interfaces.
     */
    @JsonProperty("interface_names")
    public List<String> interfaceNames;
    
}
