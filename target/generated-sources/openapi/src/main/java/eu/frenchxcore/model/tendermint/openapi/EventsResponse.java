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
import eu.frenchxcore.model.tendermint.openapi.EventItem;
import eu.frenchxcore.model.tendermint.openapi.EventsResponseAllOf;
import eu.frenchxcore.model.tendermint.openapi.JSONRPC;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Events response
 */
@ApiModel(description = "Events response")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class EventsResponse {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Integer id;

  public static final String SERIALIZED_NAME_JSONRPC = "jsonrpc";
  @SerializedName(SERIALIZED_NAME_JSONRPC)
  private String jsonrpc;

  public static final String SERIALIZED_NAME_ITEMS = "items";
  @SerializedName(SERIALIZED_NAME_ITEMS)
  private List<EventItem> items = null;

  public static final String SERIALIZED_NAME_MORE = "more";
  @SerializedName(SERIALIZED_NAME_MORE)
  private Boolean more;

  public static final String SERIALIZED_NAME_OLDEST = "oldest";
  @SerializedName(SERIALIZED_NAME_OLDEST)
  private String oldest;

  public static final String SERIALIZED_NAME_NEWEST = "newest";
  @SerializedName(SERIALIZED_NAME_NEWEST)
  private String newest;


  public EventsResponse id(Integer id) {
    
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "0", value = "")

  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public EventsResponse jsonrpc(String jsonrpc) {
    
    this.jsonrpc = jsonrpc;
    return this;
  }

   /**
   * Get jsonrpc
   * @return jsonrpc
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2.0", value = "")

  public String getJsonrpc() {
    return jsonrpc;
  }


  public void setJsonrpc(String jsonrpc) {
    this.jsonrpc = jsonrpc;
  }


  public EventsResponse items(List<EventItem> items) {
    
    this.items = items;
    return this;
  }

  public EventsResponse addItemsItem(EventItem itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<EventItem>();
    }
    this.items.add(itemsItem);
    return this;
  }

   /**
   * Get items
   * @return items
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<EventItem> getItems() {
    return items;
  }


  public void setItems(List<EventItem> items) {
    this.items = items;
  }


  public EventsResponse more(Boolean more) {
    
    this.more = more;
    return this;
  }

   /**
   * Get more
   * @return more
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getMore() {
    return more;
  }


  public void setMore(Boolean more) {
    this.more = more;
  }


  public EventsResponse oldest(String oldest) {
    
    this.oldest = oldest;
    return this;
  }

   /**
   * Get oldest
   * @return oldest
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "0005d7c09065e9a7-01cf", value = "")

  public String getOldest() {
    return oldest;
  }


  public void setOldest(String oldest) {
    this.oldest = oldest;
  }


  public EventsResponse newest(String newest) {
    
    this.newest = newest;
    return this;
  }

   /**
   * Get newest
   * @return newest
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "0005d7c090660099-0200", value = "")

  public String getNewest() {
    return newest;
  }


  public void setNewest(String newest) {
    this.newest = newest;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventsResponse eventsResponse = (EventsResponse) o;
    return Objects.equals(this.id, eventsResponse.id) &&
        Objects.equals(this.jsonrpc, eventsResponse.jsonrpc) &&
        Objects.equals(this.items, eventsResponse.items) &&
        Objects.equals(this.more, eventsResponse.more) &&
        Objects.equals(this.oldest, eventsResponse.oldest) &&
        Objects.equals(this.newest, eventsResponse.newest);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, jsonrpc, items, more, oldest, newest);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventsResponse {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    jsonrpc: ").append(toIndentedString(jsonrpc)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    more: ").append(toIndentedString(more)).append("\n");
    sb.append("    oldest: ").append(toIndentedString(oldest)).append("\n");
    sb.append("    newest: ").append(toIndentedString(newest)).append("\n");
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

