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
import eu.frenchxcore.model.tendermint.openapi.ValidatorPriority;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ValidatorsResponseResult
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:08.597986700+02:00[Europe/Paris]")
public class ValidatorsResponseResult {
  public static final String SERIALIZED_NAME_BLOCK_HEIGHT = "block_height";
  @SerializedName(SERIALIZED_NAME_BLOCK_HEIGHT)
  private String blockHeight;

  public static final String SERIALIZED_NAME_VALIDATORS = "validators";
  @SerializedName(SERIALIZED_NAME_VALIDATORS)
  private List<ValidatorPriority> validators = new ArrayList<ValidatorPriority>();

  public static final String SERIALIZED_NAME_COUNT = "count";
  @SerializedName(SERIALIZED_NAME_COUNT)
  private String count;

  public static final String SERIALIZED_NAME_TOTAL = "total";
  @SerializedName(SERIALIZED_NAME_TOTAL)
  private String total;


  public ValidatorsResponseResult blockHeight(String blockHeight) {
    
    this.blockHeight = blockHeight;
    return this;
  }

   /**
   * Get blockHeight
   * @return blockHeight
  **/
  @ApiModelProperty(example = "55", required = true, value = "")

  public String getBlockHeight() {
    return blockHeight;
  }


  public void setBlockHeight(String blockHeight) {
    this.blockHeight = blockHeight;
  }


  public ValidatorsResponseResult validators(List<ValidatorPriority> validators) {
    
    this.validators = validators;
    return this;
  }

  public ValidatorsResponseResult addValidatorsItem(ValidatorPriority validatorsItem) {
    this.validators.add(validatorsItem);
    return this;
  }

   /**
   * Get validators
   * @return validators
  **/
  @ApiModelProperty(required = true, value = "")

  public List<ValidatorPriority> getValidators() {
    return validators;
  }


  public void setValidators(List<ValidatorPriority> validators) {
    this.validators = validators;
  }


  public ValidatorsResponseResult count(String count) {
    
    this.count = count;
    return this;
  }

   /**
   * Get count
   * @return count
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "")

  public String getCount() {
    return count;
  }


  public void setCount(String count) {
    this.count = count;
  }


  public ValidatorsResponseResult total(String total) {
    
    this.total = total;
    return this;
  }

   /**
   * Get total
   * @return total
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "25", value = "")

  public String getTotal() {
    return total;
  }


  public void setTotal(String total) {
    this.total = total;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidatorsResponseResult validatorsResponseResult = (ValidatorsResponseResult) o;
    return Objects.equals(this.blockHeight, validatorsResponseResult.blockHeight) &&
        Objects.equals(this.validators, validatorsResponseResult.validators) &&
        Objects.equals(this.count, validatorsResponseResult.count) &&
        Objects.equals(this.total, validatorsResponseResult.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockHeight, validators, count, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidatorsResponseResult {\n");
    sb.append("    blockHeight: ").append(toIndentedString(blockHeight)).append("\n");
    sb.append("    validators: ").append(toIndentedString(validators)).append("\n");
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

