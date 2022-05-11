package eu.frenchxcore.model.cosmossdk.query.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.cosmossdk.types.tx.Tx;
import eu.frenchxcore.model.cosmossdk.types.tx.TxResponse;

/**
 * GetTxResponse is the response type for the Service.GetTx method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTxResponse {

    /**
     * tx is the queried transaction.
     */
    @JsonProperty("tx")
    public Tx tx;
    
    /**
     * txResponse is the queried TxResponses.
     */
    @JsonProperty("tx_response")
    public TxResponse txResponse;
    
}