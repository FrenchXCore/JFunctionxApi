package eu.frenchxcore.cosmossdk.types.gov;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.Proposal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryProposalResponse {

    /**
     * 
     */
    @JsonProperty("proposal")
    public Proposal proposal;

}
