package eu.frenchxcore.model.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ChainDescriptor describes chain information of the application
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChainDescriptor {

    /**
     * id is the chain id
     */
    @JsonProperty("id")
    public String id;

}
