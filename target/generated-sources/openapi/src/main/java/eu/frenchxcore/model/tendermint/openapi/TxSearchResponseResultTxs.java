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
import eu.frenchxcore.model.tendermint.openapi.TxSearchResponseResultProof;
import eu.frenchxcore.model.tendermint.openapi.TxSearchResponseResultTxResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * TxSearchResponseResultTxs
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:08.597986700+02:00[Europe/Paris]")
public class TxSearchResponseResultTxs {
  public static final String SERIALIZED_NAME_HASH = "hash";
  @SerializedName(SERIALIZED_NAME_HASH)
  private String hash;

  public static final String SERIALIZED_NAME_HEIGHT = "height";
  @SerializedName(SERIALIZED_NAME_HEIGHT)
  private String height;

  public static final String SERIALIZED_NAME_INDEX = "index";
  @SerializedName(SERIALIZED_NAME_INDEX)
  private Integer index;

  public static final String SERIALIZED_NAME_TX_RESULT = "tx_result";
  @SerializedName(SERIALIZED_NAME_TX_RESULT)
  private TxSearchResponseResultTxResult txResult;

  public static final String SERIALIZED_NAME_TX = "tx";
  @SerializedName(SERIALIZED_NAME_TX)
  private String tx;

  public static final String SERIALIZED_NAME_PROOF = "proof";
  @SerializedName(SERIALIZED_NAME_PROOF)
  private TxSearchResponseResultProof proof;


  public TxSearchResponseResultTxs hash(String hash) {
    
    this.hash = hash;
    return this;
  }

   /**
   * Get hash
   * @return hash
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "D70952032620CC4E2737EB8AC379806359D8E0B17B0488F627997A0B043ABDED", value = "")

  public String getHash() {
    return hash;
  }


  public void setHash(String hash) {
    this.hash = hash;
  }


  public TxSearchResponseResultTxs height(String height) {
    
    this.height = height;
    return this;
  }

   /**
   * Get height
   * @return height
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1000", value = "")

  public String getHeight() {
    return height;
  }


  public void setHeight(String height) {
    this.height = height;
  }


  public TxSearchResponseResultTxs index(Integer index) {
    
    this.index = index;
    return this;
  }

   /**
   * Get index
   * @return index
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "0", value = "")

  public Integer getIndex() {
    return index;
  }


  public void setIndex(Integer index) {
    this.index = index;
  }


  public TxSearchResponseResultTxs txResult(TxSearchResponseResultTxResult txResult) {
    
    this.txResult = txResult;
    return this;
  }

   /**
   * Get txResult
   * @return txResult
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public TxSearchResponseResultTxResult getTxResult() {
    return txResult;
  }


  public void setTxResult(TxSearchResponseResultTxResult txResult) {
    this.txResult = txResult;
  }


  public TxSearchResponseResultTxs tx(String tx) {
    
    this.tx = tx;
    return this;
  }

   /**
   * Get tx
   * @return tx
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "5wHwYl3uCkaoo2GaChQmSIu8hxpJxLcCuIi8fiHN4TMwrRIU/Af1cEG7Rcs/6LjTl7YjRSymJfYaFAoFdWF0b20SCzE0OTk5OTk1MDAwEhMKDQoFdWF0b20SBDUwMDAQwJoMGmoKJuta6YchAwswBShaB1wkZBctLIhYqBC3JrAI28XGzxP+rVEticGEEkAc+khTkKL9CDE47aDvjEHvUNt+izJfT4KVF2v2JkC+bmlH9K08q3PqHeMI9Z5up+XMusnTqlP985KF+SI5J3ZOIhhNYWRlIGJ5IENpcmNsZSB3aXRoIGxvdmU=", value = "")

  public String getTx() {
    return tx;
  }


  public void setTx(String tx) {
    this.tx = tx;
  }


  public TxSearchResponseResultTxs proof(TxSearchResponseResultProof proof) {
    
    this.proof = proof;
    return this;
  }

   /**
   * Get proof
   * @return proof
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public TxSearchResponseResultProof getProof() {
    return proof;
  }


  public void setProof(TxSearchResponseResultProof proof) {
    this.proof = proof;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TxSearchResponseResultTxs txSearchResponseResultTxs = (TxSearchResponseResultTxs) o;
    return Objects.equals(this.hash, txSearchResponseResultTxs.hash) &&
        Objects.equals(this.height, txSearchResponseResultTxs.height) &&
        Objects.equals(this.index, txSearchResponseResultTxs.index) &&
        Objects.equals(this.txResult, txSearchResponseResultTxs.txResult) &&
        Objects.equals(this.tx, txSearchResponseResultTxs.tx) &&
        Objects.equals(this.proof, txSearchResponseResultTxs.proof);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hash, height, index, txResult, tx, proof);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TxSearchResponseResultTxs {\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("    height: ").append(toIndentedString(height)).append("\n");
    sb.append("    index: ").append(toIndentedString(index)).append("\n");
    sb.append("    txResult: ").append(toIndentedString(txResult)).append("\n");
    sb.append("    tx: ").append(toIndentedString(tx)).append("\n");
    sb.append("    proof: ").append(toIndentedString(proof)).append("\n");
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

