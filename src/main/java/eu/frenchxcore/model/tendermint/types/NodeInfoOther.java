package eu.frenchxcore.model.tendermint.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NodeInfoOther {

    /**
     * 
     */
    @JsonProperty("tx_index")
    public String txIndex;
    
    /**
     * 
     */
    @JsonProperty("rpc_address")
    public String rpcAddress;
    
}
