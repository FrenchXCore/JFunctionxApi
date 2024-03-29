/*
 * Tendermint RPC
 * Tendermint supports the following RPC protocols:  * URI over HTTP * JSON-RPC 2.0 over HTTP * JSON-RPC 2.0 over websockets (deprecated)  ## Configuration  RPC can be configured by tuning parameters under `[rpc]` table in the `$TMHOME/config/config.toml` file or by using the `--rpc.X` command-line flags.  Default rpc listen address is `tcp://0.0.0.0:26657`. To set another address, set the `laddr` config parameter to desired value. CORS (Cross-Origin Resource Sharing) can be enabled by setting `cors_allowed_origins`, `cors_allowed_methods`, `cors_allowed_headers` config parameters.  ## Arguments  Arguments which expect strings or byte arrays may be passed as quoted strings, like `\"abc\"` or as `0x`-prefixed strings, like `0x616263`.  ## URI/HTTP  A GET request with arguments encoded as query parameters:      curl localhost:26657/block?height=5  ## JSONRPC/HTTP  JSONRPC requests can be POST'd to the root RPC endpoint via HTTP.      curl --header \"Content-Type: application/json\" --request POST --data '{\"method\": \"block\", \"params\": [\"5\"], \"id\": 1}' localhost:26657  ## JSONRPC/websockets  In Tendermint v0.35 and earlier, JSONRPC requests can be also made via websocket.  The websocket interface is deprecated in Tendermint v0.36, and will be removed in v0.37.  The websocket endpoint is at `/websocket`, e.g. `localhost:26657/websocket`. The RPC methods for event subscription (`subscribe`, `unsubscribe`, and `unsubscribe_all`) are only available via websockets.  Example using https://github.com/hashrocket/ws:      ws ws://localhost:26657/websocket     > { \"jsonrpc\": \"2.0\", \"method\": \"subscribe\", \"params\": [\"tm.event='NewBlock'\"], \"id\": 1 } 
 *
 * The version of the OpenAPI document: Master
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package eu.frenchxcore.model.tendermint.openapi;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * UnconfirmedTransactionsResponseResult
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class UnconfirmedTransactionsResponseResult {
  public static final String SERIALIZED_NAME_N_TXS = "n_txs";
  @SerializedName(SERIALIZED_NAME_N_TXS)
  private String nTxs;

  public static final String SERIALIZED_NAME_TOTAL = "total";
  @SerializedName(SERIALIZED_NAME_TOTAL)
  private String total;

  public static final String SERIALIZED_NAME_TOTAL_BYTES = "total_bytes";
  @SerializedName(SERIALIZED_NAME_TOTAL_BYTES)
  private String totalBytes;

  public static final String SERIALIZED_NAME_TXS = "txs";
  @SerializedName(SERIALIZED_NAME_TXS)
  private List<String> txs = new ArrayList<String>();


  public UnconfirmedTransactionsResponseResult nTxs(String nTxs) {
    
    this.nTxs = nTxs;
    return this;
  }

   /**
   * Get nTxs
   * @return nTxs
  **/
  @ApiModelProperty(example = "82", required = true, value = "")

  public String getnTxs() {
    return nTxs;
  }


  public void setnTxs(String nTxs) {
    this.nTxs = nTxs;
  }


  public UnconfirmedTransactionsResponseResult total(String total) {
    
    this.total = total;
    return this;
  }

   /**
   * Get total
   * @return total
  **/
  @ApiModelProperty(example = "82", required = true, value = "")

  public String getTotal() {
    return total;
  }


  public void setTotal(String total) {
    this.total = total;
  }


  public UnconfirmedTransactionsResponseResult totalBytes(String totalBytes) {
    
    this.totalBytes = totalBytes;
    return this;
  }

   /**
   * Get totalBytes
   * @return totalBytes
  **/
  @ApiModelProperty(example = "19974", required = true, value = "")

  public String getTotalBytes() {
    return totalBytes;
  }


  public void setTotalBytes(String totalBytes) {
    this.totalBytes = totalBytes;
  }


  public UnconfirmedTransactionsResponseResult txs(List<String> txs) {
    
    this.txs = txs;
    return this;
  }

  public UnconfirmedTransactionsResponseResult addTxsItem(String txsItem) {
    this.txs.add(txsItem);
    return this;
  }

   /**
   * Get txs
   * @return txs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "[\"gAPwYl3uCjCMTXENChSMnIkb5ZpYHBKIZqecFEV2tuZr7xIUA75/FmYq9WymsOBJ0XSJ8yV8zmQKMIxNcQ0KFIyciRvlmlgcEohmp5wURXa25mvvEhQbrvwbvlNiT+Yjr86G+YQNx7kRVgowjE1xDQoUjJyJG+WaWBwSiGannBRFdrbma+8SFK2m+1oxgILuQLO55n8mWfnbIzyPCjCMTXENChSMnIkb5ZpYHBKIZqecFEV2tuZr7xIUQNGfkmhTNMis4j+dyMDIWXdIPiYKMIxNcQ0KFIyciRvlmlgcEohmp5wURXa25mvvEhS8sL0D0wwgGCItQwVowak5YB38KRIUCg4KBXVhdG9tEgUxMDA1NBDoxRgaagom61rphyECn8x7emhhKdRCB2io7aS/6Cpuq5NbVqbODmqOT3jWw6kSQKUresk+d+Gw0BhjiggTsu8+1voW+VlDCQ1GRYnMaFOHXhyFv7BCLhFWxLxHSAYT8a5XqoMayosZf9mANKdXArA=\"]", required = true, value = "")

  public List<String> getTxs() {
    return txs;
  }


  public void setTxs(List<String> txs) {
    this.txs = txs;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnconfirmedTransactionsResponseResult unconfirmedTransactionsResponseResult = (UnconfirmedTransactionsResponseResult) o;
    return Objects.equals(this.nTxs, unconfirmedTransactionsResponseResult.nTxs) &&
        Objects.equals(this.total, unconfirmedTransactionsResponseResult.total) &&
        Objects.equals(this.totalBytes, unconfirmedTransactionsResponseResult.totalBytes) &&
        Objects.equals(this.txs, unconfirmedTransactionsResponseResult.txs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nTxs, total, totalBytes, txs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnconfirmedTransactionsResponseResult {\n");
    sb.append("    nTxs: ").append(toIndentedString(nTxs)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    totalBytes: ").append(toIndentedString(totalBytes)).append("\n");
    sb.append("    txs: ").append(toIndentedString(txs)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

