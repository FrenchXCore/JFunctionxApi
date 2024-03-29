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

/**
 * ProtocolVersion
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class ProtocolVersion {
  public static final String SERIALIZED_NAME_P2P = "p2p";
  @SerializedName(SERIALIZED_NAME_P2P)
  private String p2p;

  public static final String SERIALIZED_NAME_BLOCK = "block";
  @SerializedName(SERIALIZED_NAME_BLOCK)
  private String block;

  public static final String SERIALIZED_NAME_APP = "app";
  @SerializedName(SERIALIZED_NAME_APP)
  private String app;


  public ProtocolVersion p2p(String p2p) {
    
    this.p2p = p2p;
    return this;
  }

   /**
   * Get p2p
   * @return p2p
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "7", value = "")

  public String getP2p() {
    return p2p;
  }


  public void setP2p(String p2p) {
    this.p2p = p2p;
  }


  public ProtocolVersion block(String block) {
    
    this.block = block;
    return this;
  }

   /**
   * Get block
   * @return block
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "10", value = "")

  public String getBlock() {
    return block;
  }


  public void setBlock(String block) {
    this.block = block;
  }


  public ProtocolVersion app(String app) {
    
    this.app = app;
    return this;
  }

   /**
   * Get app
   * @return app
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "0", value = "")

  public String getApp() {
    return app;
  }


  public void setApp(String app) {
    this.app = app;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProtocolVersion protocolVersion = (ProtocolVersion) o;
    return Objects.equals(this.p2p, protocolVersion.p2p) &&
        Objects.equals(this.block, protocolVersion.block) &&
        Objects.equals(this.app, protocolVersion.app);
  }

  @Override
  public int hashCode() {
    return Objects.hash(p2p, block, app);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProtocolVersion {\n");
    sb.append("    p2p: ").append(toIndentedString(p2p)).append("\n");
    sb.append("    block: ").append(toIndentedString(block)).append("\n");
    sb.append("    app: ").append(toIndentedString(app)).append("\n");
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

