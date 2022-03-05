package eu.frenchxcore.messages.cosmossdk.query.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.bank.Metadata;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

import java.util.List;

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
