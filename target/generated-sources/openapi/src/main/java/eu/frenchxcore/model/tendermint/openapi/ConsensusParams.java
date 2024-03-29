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
import eu.frenchxcore.model.tendermint.openapi.ConsensusParamsBlock;
import eu.frenchxcore.model.tendermint.openapi.ConsensusParamsEvidence;
import eu.frenchxcore.model.tendermint.openapi.ConsensusParamsValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ConsensusParams
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class ConsensusParams {
  public static final String SERIALIZED_NAME_BLOCK = "block";
  @SerializedName(SERIALIZED_NAME_BLOCK)
  private ConsensusParamsBlock block;

  public static final String SERIALIZED_NAME_EVIDENCE = "evidence";
  @SerializedName(SERIALIZED_NAME_EVIDENCE)
  private ConsensusParamsEvidence evidence;

  public static final String SERIALIZED_NAME_VALIDATOR = "validator";
  @SerializedName(SERIALIZED_NAME_VALIDATOR)
  private ConsensusParamsValidator validator;


  public ConsensusParams block(ConsensusParamsBlock block) {
    
    this.block = block;
    return this;
  }

   /**
   * Get block
   * @return block
  **/
  @ApiModelProperty(required = true, value = "")

  public ConsensusParamsBlock getBlock() {
    return block;
  }


  public void setBlock(ConsensusParamsBlock block) {
    this.block = block;
  }


  public ConsensusParams evidence(ConsensusParamsEvidence evidence) {
    
    this.evidence = evidence;
    return this;
  }

   /**
   * Get evidence
   * @return evidence
  **/
  @ApiModelProperty(required = true, value = "")

  public ConsensusParamsEvidence getEvidence() {
    return evidence;
  }


  public void setEvidence(ConsensusParamsEvidence evidence) {
    this.evidence = evidence;
  }


  public ConsensusParams validator(ConsensusParamsValidator validator) {
    
    this.validator = validator;
    return this;
  }

   /**
   * Get validator
   * @return validator
  **/
  @ApiModelProperty(required = true, value = "")

  public ConsensusParamsValidator getValidator() {
    return validator;
  }


  public void setValidator(ConsensusParamsValidator validator) {
    this.validator = validator;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsensusParams consensusParams = (ConsensusParams) o;
    return Objects.equals(this.block, consensusParams.block) &&
        Objects.equals(this.evidence, consensusParams.evidence) &&
        Objects.equals(this.validator, consensusParams.validator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(block, evidence, validator);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsensusParams {\n");
    sb.append("    block: ").append(toIndentedString(block)).append("\n");
    sb.append("    evidence: ").append(toIndentedString(evidence)).append("\n");
    sb.append("    validator: ").append(toIndentedString(validator)).append("\n");
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

