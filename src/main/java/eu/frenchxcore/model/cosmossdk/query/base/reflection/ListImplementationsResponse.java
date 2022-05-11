package eu.frenchxcore.model.cosmossdk.query.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListImplementationsResponse {

    /**
     * 
     */
    @JsonProperty("implementation_message_names")
    public List<String> interfaceNames;
    
}
