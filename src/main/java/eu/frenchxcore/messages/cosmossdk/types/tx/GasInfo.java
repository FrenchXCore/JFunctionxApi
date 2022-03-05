package eu.frenchxcore.messages.cosmossdk.types.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;

/**
 * GasInfo defines tx execution gas context.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GasInfo {

    /**
     * gasWanted is the maximum units of work we allow this tx to perform.
     */
    @JsonProperty("gas_wanted")
    public BigInteger gasWanted;

    /**
     * gasUsed is the amount of gas actually consumed.
     */
    @JsonProperty("gas_used")
    public BigInteger gasUsed;

}
