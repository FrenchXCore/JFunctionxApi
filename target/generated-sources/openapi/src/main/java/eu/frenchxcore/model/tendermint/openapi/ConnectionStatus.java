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
import eu.frenchxcore.model.tendermint.openapi.Channel;
import eu.frenchxcore.model.tendermint.openapi.Monitor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ConnectionStatus
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class ConnectionStatus {
  public static final String SERIALIZED_NAME_DURATION = "Duration";
  @SerializedName(SERIALIZED_NAME_DURATION)
  private String duration;

  public static final String SERIALIZED_NAME_SEND_MONITOR = "SendMonitor";
  @SerializedName(SERIALIZED_NAME_SEND_MONITOR)
  private Monitor sendMonitor;

  public static final String SERIALIZED_NAME_RECV_MONITOR = "RecvMonitor";
  @SerializedName(SERIALIZED_NAME_RECV_MONITOR)
  private Monitor recvMonitor;

  public static final String SERIALIZED_NAME_CHANNELS = "Channels";
  @SerializedName(SERIALIZED_NAME_CHANNELS)
  private List<Channel> channels = null;


  public ConnectionStatus duration(String duration) {
    
    this.duration = duration;
    return this;
  }

   /**
   * Get duration
   * @return duration
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "168901057956119", value = "")

  public String getDuration() {
    return duration;
  }


  public void setDuration(String duration) {
    this.duration = duration;
  }


  public ConnectionStatus sendMonitor(Monitor sendMonitor) {
    
    this.sendMonitor = sendMonitor;
    return this;
  }

   /**
   * Get sendMonitor
   * @return sendMonitor
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Monitor getSendMonitor() {
    return sendMonitor;
  }


  public void setSendMonitor(Monitor sendMonitor) {
    this.sendMonitor = sendMonitor;
  }


  public ConnectionStatus recvMonitor(Monitor recvMonitor) {
    
    this.recvMonitor = recvMonitor;
    return this;
  }

   /**
   * Get recvMonitor
   * @return recvMonitor
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Monitor getRecvMonitor() {
    return recvMonitor;
  }


  public void setRecvMonitor(Monitor recvMonitor) {
    this.recvMonitor = recvMonitor;
  }


  public ConnectionStatus channels(List<Channel> channels) {
    
    this.channels = channels;
    return this;
  }

  public ConnectionStatus addChannelsItem(Channel channelsItem) {
    if (this.channels == null) {
      this.channels = new ArrayList<Channel>();
    }
    this.channels.add(channelsItem);
    return this;
  }

   /**
   * Get channels
   * @return channels
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<Channel> getChannels() {
    return channels;
  }


  public void setChannels(List<Channel> channels) {
    this.channels = channels;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConnectionStatus connectionStatus = (ConnectionStatus) o;
    return Objects.equals(this.duration, connectionStatus.duration) &&
        Objects.equals(this.sendMonitor, connectionStatus.sendMonitor) &&
        Objects.equals(this.recvMonitor, connectionStatus.recvMonitor) &&
        Objects.equals(this.channels, connectionStatus.channels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(duration, sendMonitor, recvMonitor, channels);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConnectionStatus {\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    sendMonitor: ").append(toIndentedString(sendMonitor)).append("\n");
    sb.append("    recvMonitor: ").append(toIndentedString(recvMonitor)).append("\n");
    sb.append("    channels: ").append(toIndentedString(channels)).append("\n");
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

