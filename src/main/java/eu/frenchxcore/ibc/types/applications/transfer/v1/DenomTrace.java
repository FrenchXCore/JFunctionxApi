package eu.frenchxcore.ibc.types.applications.transfer.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DenomTrace contains the base denomination for ICS20 fungible tokens and the
 * source tracing information path.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DenomTrace {

    /**
     * path defines the chain of port/channel identifiers used for tracing the
     * source of the fungible token.
     */
    @JsonProperty("path")
    public String path;

    /**
     * base denomination of the relayed fungible token.
     */
    @JsonProperty("base_denom")
    public String baseDenom;

}
