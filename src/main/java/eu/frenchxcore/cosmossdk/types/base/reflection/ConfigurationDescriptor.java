package eu.frenchxcore.cosmossdk.types.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * ConfigurationDescriptor contains metadata information on the sdk.Config
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigurationDescriptor {

    /**
     * bech32_account_address_prefix is the account address prefix
     */
    @JsonProperty("bech32_account_address_prefix")
    public String bech32AccountAddressPrefix;

}
