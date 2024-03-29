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
import eu.frenchxcore.model.tendermint.openapi.NodeInfo;
import eu.frenchxcore.model.tendermint.openapi.SyncInfo;
import eu.frenchxcore.model.tendermint.openapi.ValidatorInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Status Response
 */
@ApiModel(description = "Status Response")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class Status {
  public static final String SERIALIZED_NAME_NODE_INFO = "node_info";
  @SerializedName(SERIALIZED_NAME_NODE_INFO)
  private NodeInfo nodeInfo;

  public static final String SERIALIZED_NAME_SYNC_INFO = "sync_info";
  @SerializedName(SERIALIZED_NAME_SYNC_INFO)
  private SyncInfo syncInfo;

  public static final String SERIALIZED_NAME_VALIDATOR_INFO = "validator_info";
  @SerializedName(SERIALIZED_NAME_VALIDATOR_INFO)
  private ValidatorInfo validatorInfo;


  public Status nodeInfo(NodeInfo nodeInfo) {
    
    this.nodeInfo = nodeInfo;
    return this;
  }

   /**
   * Get nodeInfo
   * @return nodeInfo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public NodeInfo getNodeInfo() {
    return nodeInfo;
  }


  public void setNodeInfo(NodeInfo nodeInfo) {
    this.nodeInfo = nodeInfo;
  }


  public Status syncInfo(SyncInfo syncInfo) {
    
    this.syncInfo = syncInfo;
    return this;
  }

   /**
   * Get syncInfo
   * @return syncInfo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public SyncInfo getSyncInfo() {
    return syncInfo;
  }


  public void setSyncInfo(SyncInfo syncInfo) {
    this.syncInfo = syncInfo;
  }


  public Status validatorInfo(ValidatorInfo validatorInfo) {
    
    this.validatorInfo = validatorInfo;
    return this;
  }

   /**
   * Get validatorInfo
   * @return validatorInfo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ValidatorInfo getValidatorInfo() {
    return validatorInfo;
  }


  public void setValidatorInfo(ValidatorInfo validatorInfo) {
    this.validatorInfo = validatorInfo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Status status = (Status) o;
    return Objects.equals(this.nodeInfo, status.nodeInfo) &&
        Objects.equals(this.syncInfo, status.syncInfo) &&
        Objects.equals(this.validatorInfo, status.validatorInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeInfo, syncInfo, validatorInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Status {\n");
    sb.append("    nodeInfo: ").append(toIndentedString(nodeInfo)).append("\n");
    sb.append("    syncInfo: ").append(toIndentedString(syncInfo)).append("\n");
    sb.append("    validatorInfo: ").append(toIndentedString(validatorInfo)).append("\n");
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

