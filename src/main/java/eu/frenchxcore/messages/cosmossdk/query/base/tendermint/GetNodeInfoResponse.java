package eu.frenchxcore.messages.cosmossdk.query.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.base.tendermint.VersionInfo;
import eu.frenchxcore.messages.tendermint.types.p2p.DefaultNodeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetNodeInfoResponse {

    /**
     * 
     */
    @JsonProperty("default_node_info")
    public DefaultNodeInfo defaultNodeInfo;
    
    /**
     * 
     */
    @JsonProperty("application_version")
    public VersionInfo applicationVersion;
    
}
