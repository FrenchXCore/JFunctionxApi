package eu.frenchxcore.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.cosmossdk.types._base.Metadata;
import eu.frenchxcore.cosmossdk.types._base.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDenomsMetadataResponse {

    /**
     * metadata provides the client information for all the registered tokens.
     */
    @JsonProperty("metadatas")
    public List<Metadata> metadatas;
    
    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}
