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
import eu.frenchxcore.model.tendermint.openapi.NodeInfoOther;
import eu.frenchxcore.model.tendermint.openapi.ProtocolVersion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * NodeInfo
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class NodeInfo {
  public static final String SERIALIZED_NAME_PROTOCOL_VERSION = "protocol_version";
  @SerializedName(SERIALIZED_NAME_PROTOCOL_VERSION)
  private ProtocolVersion protocolVersion;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private String id;

  public static final String SERIALIZED_NAME_LISTEN_ADDR = "listen_addr";
  @SerializedName(SERIALIZED_NAME_LISTEN_ADDR)
  private String listenAddr;

  public static final String SERIALIZED_NAME_NETWORK = "network";
  @SerializedName(SERIALIZED_NAME_NETWORK)
  private String network;

  public static final String SERIALIZED_NAME_VERSION = "version";
  @SerializedName(SERIALIZED_NAME_VERSION)
  private String version;

  public static final String SERIALIZED_NAME_CHANNELS = "channels";
  @SerializedName(SERIALIZED_NAME_CHANNELS)
  private String channels;

  public static final String SERIALIZED_NAME_MONIKER = "moniker";
  @SerializedName(SERIALIZED_NAME_MONIKER)
  private String moniker;

  public static final String SERIALIZED_NAME_OTHER = "other";
  @SerializedName(SERIALIZED_NAME_OTHER)
  private NodeInfoOther other;


  public NodeInfo protocolVersion(ProtocolVersion protocolVersion) {
    
    this.protocolVersion = protocolVersion;
    return this;
  }

   /**
   * Get protocolVersion
   * @return protocolVersion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ProtocolVersion getProtocolVersion() {
    return protocolVersion;
  }


  public void setProtocolVersion(ProtocolVersion protocolVersion) {
    this.protocolVersion = protocolVersion;
  }


  public NodeInfo id(String id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "5576458aef205977e18fd50b274e9b5d9014525a", value = "")

  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public NodeInfo listenAddr(String listenAddr) {
    
    this.listenAddr = listenAddr;
    return this;
  }

   /**
   * Get listenAddr
   * @return listenAddr
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "tcp://0.0.0.0:26656", value = "")

  public String getListenAddr() {
    return listenAddr;
  }


  public void setListenAddr(String listenAddr) {
    this.listenAddr = listenAddr;
  }


  public NodeInfo network(String network) {
    
    this.network = network;
    return this;
  }

   /**
   * Get network
   * @return network
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "cosmoshub-2", value = "")

  public String getNetwork() {
    return network;
  }


  public void setNetwork(String network) {
    this.network = network;
  }


  public NodeInfo version(String version) {
    
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "0.32.1", value = "")

  public String getVersion() {
    return version;
  }


  public void setVersion(String version) {
    this.version = version;
  }


  public NodeInfo channels(String channels) {
    
    this.channels = channels;
    return this;
  }

   /**
   * Get channels
   * @return channels
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "4020212223303800", value = "")

  public String getChannels() {
    return channels;
  }


  public void setChannels(String channels) {
    this.channels = channels;
  }


  public NodeInfo moniker(String moniker) {
    
    this.moniker = moniker;
    return this;
  }

   /**
   * Get moniker
   * @return moniker
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "moniker-node", value = "")

  public String getMoniker() {
    return moniker;
  }


  public void setMoniker(String moniker) {
    this.moniker = moniker;
  }


  public NodeInfo other(NodeInfoOther other) {
    
    this.other = other;
    return this;
  }

   /**
   * Get other
   * @return other
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public NodeInfoOther getOther() {
    return other;
  }


  public void setOther(NodeInfoOther other) {
    this.other = other;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NodeInfo nodeInfo = (NodeInfo) o;
    return Objects.equals(this.protocolVersion, nodeInfo.protocolVersion) &&
        Objects.equals(this.id, nodeInfo.id) &&
        Objects.equals(this.listenAddr, nodeInfo.listenAddr) &&
        Objects.equals(this.network, nodeInfo.network) &&
        Objects.equals(this.version, nodeInfo.version) &&
        Objects.equals(this.channels, nodeInfo.channels) &&
        Objects.equals(this.moniker, nodeInfo.moniker) &&
        Objects.equals(this.other, nodeInfo.other);
  }

  @Override
  public int hashCode() {
    return Objects.hash(protocolVersion, id, listenAddr, network, version, channels, moniker, other);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NodeInfo {\n");
    sb.append("    protocolVersion: ").append(toIndentedString(protocolVersion)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    listenAddr: ").append(toIndentedString(listenAddr)).append("\n");
    sb.append("    network: ").append(toIndentedString(network)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    channels: ").append(toIndentedString(channels)).append("\n");
    sb.append("    moniker: ").append(toIndentedString(moniker)).append("\n");
    sb.append("    other: ").append(toIndentedString(other)).append("\n");
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

