package eu.frenchxcore.cosmossdk.query.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.tx.GasInfo;
import eu.frenchxcore.cosmossdk.types.tx.Result;

/**
 * SimulateResponse is the response type for the Service.SimulateRPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimulateResponse {

    /**
     * gasInfo is the information about gas used in the simulation.
     */
    @JsonProperty("gas_info")
    public GasInfo gasInfo;

    /**
     * result is the result of the simulation.
     */
    @JsonProperty("result")
    public Result result;

}
