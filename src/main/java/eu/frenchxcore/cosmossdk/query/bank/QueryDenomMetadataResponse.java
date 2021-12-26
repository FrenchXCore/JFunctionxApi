package eu.frenchxcore.cosmossdk.query.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.bank.Metadata;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryDenomMetadataResponse {

    /**
     * metadata describes and provides all the client information for the requested token.
     */
    @JsonProperty("metadata")
    public Metadata metadata;
    
}