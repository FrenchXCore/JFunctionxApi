package eu.frenchxcore.model.cosmossdk.query.base.reflection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.base.reflection.TxDescriptor;

/**
 * GetTxDescriptorResponse is the response returned by the GetTxDescriptor RPC
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTxDescriptorResponse {

    /**
     * tx provides information on msgs that can be forwarded to the application alongside the accepted transaction protobuf type
     */
    @JsonProperty("tx")
    public TxDescriptor tx;
    
}
