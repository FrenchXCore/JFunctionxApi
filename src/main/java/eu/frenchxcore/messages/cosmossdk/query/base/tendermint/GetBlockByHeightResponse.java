package eu.frenchxcore.messages.cosmossdk.query.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.tendermint.types.Block;
import eu.frenchxcore.tendermint.types.BlockID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetBlockByHeightResponse {

    /**
     * 
     */
    @JsonProperty("block_id")
    public BlockID blockID;
    
    /**
     * 
     */
    @JsonProperty("block")
    public Block block;
    
}
