package eu.frenchxcore.messages.cosmossdk.query.tx;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.messages.cosmossdk.types.query.PageResponse;
import eu.frenchxcore.messages.cosmossdk.types.tx.Tx;
import eu.frenchxcore.messages.cosmossdk.types.tx.TxResponse;

import java.util.List;

/**
 * GetTxsEventResponse is the response type for the Service.TxsByEvents RPC method.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetTxsEventResponse {

    /**
     * txs is the list of queried transactions.
     */
    @JsonProperty("tx")
    public List<Tx> txs;
    
    /**
     * txResponses is the list of queried TxResponses.
     */
    @JsonProperty("tx_responses")
    public List<TxResponse> txResponses;
    
    /**
     * pagination defines the pagination in the response.
     */
    @JsonProperty("pagination")
    public PageResponse pagination;

}