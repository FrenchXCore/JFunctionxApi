package eu.frenchxcore.model.cosmossdk.query.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.reflection.ConfigurationDescriptor;

/**
 * GetConfigurationDescriptorResponse is the response returned by the GetConfigurationDescriptor RPC
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetConfigurationDescriptorResponse {

    /**
     * config describes the application's sdk.Config
     */
    @JsonProperty("config")
    public ConfigurationDescriptor config;
    
}
