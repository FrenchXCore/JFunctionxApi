package eu.frenchxcore.model.cosmossdk.query.base.tendermint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.tendermint.types.Block;
import eu.frenchxcore.model.tendermint.types.BlockID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetLatestBlockResponse {

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
