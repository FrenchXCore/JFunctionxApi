package eu.frenchxcore.messages.cosmossdk.query.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import eu.frenchxcore.messages.cosmossdk.types.bank.DenomOwner;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDenomOwnersResponse {

    /**
     * 
     */
    @JsonProperty("denom_owners")
    public List<DenomOwner> denomOwners;
    
    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;
    
}