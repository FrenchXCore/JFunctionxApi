package eu.frenchxcore.model.cosmossdk.query.slashing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.slashing.ValidatorSigningInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuerySigningInfoResponse {

    /**
     * val_signing_info is the signing info of requested val cons address
     */
    @JsonProperty("val_signing_info")
    public ValidatorSigningInfo valSigningInfo;

}
