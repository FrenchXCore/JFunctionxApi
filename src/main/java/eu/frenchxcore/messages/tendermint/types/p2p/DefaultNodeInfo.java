package eu.frenchxcore.messages.tendermint.types.p2p;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.tendermint.types.NodeInfoOther;
import eu.frenchxcore.messages.tendermint.types.ProtocolVersion;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultNodeInfo {

    /**
     * 
     */
    @JsonProperty("protocol_version")
    public ProtocolVersion protocolVersion;
    
    /**
     * 
     */
    @JsonProperty("default_node_id")
    public String defaultNodeID;
    
    /**
     * 
     */
    @JsonProperty("listen_addr")
    public String listenAddr;
    
    /**
     * 
     */
    @JsonProperty("network")
    public String network;
    
    /**
     * 
     */
    @JsonProperty("version")
    public String version;
    
    /**
     * 
     */
    @JsonProperty("channels")
    public String channels;
    
    /**
     * 
     */
    @JsonProperty("moniker")
    public String moniker;
    
    /**
     * 
     */
    @JsonProperty("other")
    public NodeInfoOther other;
    
}
