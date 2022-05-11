package eu.frenchxcore.model.cosmossdk.query.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.tendermint.VersionInfo;
import eu.frenchxcore.model.tendermint.types.p2p.DefaultNodeInfo;

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
