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
import eu.frenchxcore.model.tendermint.openapi.BlockResultsResponseResultEvents;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CheckTxResponseResult
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class CheckTxResponseResult {
  public static final String SERIALIZED_NAME_CODE = "code";
  @SerializedName(SERIALIZED_NAME_CODE)
  private String code;

  public static final String SERIALIZED_NAME_DATA = "data";
  @SerializedName(SERIALIZED_NAME_DATA)
  private String data;

  public static final String SERIALIZED_NAME_LOG = "log";
  @SerializedName(SERIALIZED_NAME_LOG)
  private String log;

  public static final String SERIALIZED_NAME_INFO = "info";
  @SerializedName(SERIALIZED_NAME_INFO)
  private String info;

  public static final String SERIALIZED_NAME_GAS_WANTED = "gas_wanted";
  @SerializedName(SERIALIZED_NAME_GAS_WANTED)
  private String gasWanted;

  public static final String SERIALIZED_NAME_GAS_USED = "gas_used";
  @SerializedName(SERIALIZED_NAME_GAS_USED)
  private String gasUsed;

  public static final String SERIALIZED_NAME_EVENTS = "events";
  @SerializedName(SERIALIZED_NAME_EVENTS)
  private List<BlockResultsResponseResultEvents> events = null;

  public static final String SERIALIZED_NAME_CODESPACE = "codespace";
  @SerializedName(SERIALIZED_NAME_CODESPACE)
  private String codespace;


  public CheckTxResponseResult code(String code) {
    
    this.code = code;
    return this;
  }

   /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(example = "0", required = true, value = "")

  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public CheckTxResponseResult data(String data) {
    
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(example = "", required = true, value = "")

  public String getData() {
    return data;
  }


  public void setData(String data) {
    this.data = data;
  }


  public CheckTxResponseResult log(String log) {
    
    this.log = log;
    return this;
  }

   /**
   * Get log
   * @return log
  **/
  @ApiModelProperty(example = "", required = true, value = "")

  public String getLog() {
    return log;
  }


  public void setLog(String log) {
    this.log = log;
  }


  public CheckTxResponseResult info(String info) {
    
    this.info = info;
    return this;
  }

   /**
   * Get info
   * @return info
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "", value = "")

  public String getInfo() {
    return info;
  }


  public void setInfo(String info) {
    this.info = info;
  }


  public CheckTxResponseResult gasWanted(String gasWanted) {
    
    this.gasWanted = gasWanted;
    return this;
  }

   /**
   * Get gasWanted
   * @return gasWanted
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "")

  public String getGasWanted() {
    return gasWanted;
  }


  public void setGasWanted(String gasWanted) {
    this.gasWanted = gasWanted;
  }


  public CheckTxResponseResult gasUsed(String gasUsed) {
    
    this.gasUsed = gasUsed;
    return this;
  }

   /**
   * Get gasUsed
   * @return gasUsed
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "0", value = "")

  public String getGasUsed() {
    return gasUsed;
  }


  public void setGasUsed(String gasUsed) {
    this.gasUsed = gasUsed;
  }


  public CheckTxResponseResult events(List<BlockResultsResponseResultEvents> events) {
    
    this.events = events;
    return this;
  }

  public CheckTxResponseResult addEventsItem(BlockResultsResponseResultEvents eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<BlockResultsResponseResultEvents>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Get events
   * @return events
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<BlockResultsResponseResultEvents> getEvents() {
    return events;
  }


  public void setEvents(List<BlockResultsResponseResultEvents> events) {
    this.events = events;
  }


  public CheckTxResponseResult codespace(String codespace) {
    
    this.codespace = codespace;
    return this;
  }

   /**
   * Get codespace
   * @return codespace
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "bank", value = "")

  public String getCodespace() {
    return codespace;
  }


  public void setCodespace(String codespace) {
    this.codespace = codespace;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CheckTxResponseResult checkTxResponseResult = (CheckTxResponseResult) o;
    return Objects.equals(this.code, checkTxResponseResult.code) &&
        Objects.equals(this.data, checkTxResponseResult.data) &&
        Objects.equals(this.log, checkTxResponseResult.log) &&
        Objects.equals(this.info, checkTxResponseResult.info) &&
        Objects.equals(this.gasWanted, checkTxResponseResult.gasWanted) &&
        Objects.equals(this.gasUsed, checkTxResponseResult.gasUsed) &&
        Objects.equals(this.events, checkTxResponseResult.events) &&
        Objects.equals(this.codespace, checkTxResponseResult.codespace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, data, log, info, gasWanted, gasUsed, events, codespace);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CheckTxResponseResult {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    log: ").append(toIndentedString(log)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    gasWanted: ").append(toIndentedString(gasWanted)).append("\n");
    sb.append("    gasUsed: ").append(toIndentedString(gasUsed)).append("\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
    sb.append("    codespace: ").append(toIndentedString(codespace)).append("\n");
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

