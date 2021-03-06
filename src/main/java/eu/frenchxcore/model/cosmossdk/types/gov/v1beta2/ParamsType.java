package eu.frenchxcore.model.cosmossdk.types.gov.v1beta2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ParamsType {
    voting,
    tallying,
    deposit;

    @JsonValue
    public String name;
    
}
