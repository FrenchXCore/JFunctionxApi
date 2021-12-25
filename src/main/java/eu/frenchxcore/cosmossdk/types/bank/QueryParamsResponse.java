package eu.frenchxcore.cosmossdk.types.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types._base.BankParams;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryParamsResponse {

    /**
     * 
     */
    @JsonProperty("params")
    public BankParams params;

}
