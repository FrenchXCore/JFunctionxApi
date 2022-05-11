package eu.frenchxcore.model.cosmossdk.query.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.bank.DenomOwner;
import eu.frenchxcore.model.cosmossdk.types.query.PageResponse;

import java.util.List;

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