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
 * DumpConsensusResponseResultPeerStateStats
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:20.933073900+02:00[Europe/Paris]")
public class DumpConsensusResponseResultPeerStateStats {
  public static final String SERIALIZED_NAME_VOTES = "votes";
  @SerializedName(SERIALIZED_NAME_VOTES)
  private String votes;

  public static final String SERIALIZED_NAME_BLOCK_PARTS = "block_parts";
  @SerializedName(SERIALIZED_NAME_BLOCK_PARTS)
  private String blockParts;


  public DumpConsensusResponseResultPeerStateStats votes(String votes) {
    
    this.votes = votes;
    return this;
  }

   /**
   * Get votes
   * @return votes
  **/
  @ApiModelProperty(example = "1159558", required = true, value = "")

  public String getVotes() {
    return votes;
  }


  public void setVotes(String votes) {
    this.votes = votes;
  }


  public DumpConsensusResponseResultPeerStateStats blockParts(String blockParts) {
    
    this.blockParts = blockParts;
    return this;
  }

   /**
   * Get blockParts
   * @return blockParts
  **/
  @ApiModelProperty(example = "4786", required = true, value = "")

  public String getBlockParts() {
    return blockParts;
  }


  public void setBlockParts(String blockParts) {
    this.blockParts = blockParts;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DumpConsensusResponseResultPeerStateStats dumpConsensusResponseResultPeerStateStats = (DumpConsensusResponseResultPeerStateStats) o;
    return Objects.equals(this.votes, dumpConsensusResponseResultPeerStateStats.votes) &&
        Objects.equals(this.blockParts, dumpConsensusResponseResultPeerStateStats.blockParts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(votes, blockParts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DumpConsensusResponseResultPeerStateStats {\n");
    sb.append("    votes: ").append(toIndentedString(votes)).append("\n");
    sb.append("    blockParts: ").append(toIndentedString(blockParts)).append("\n");
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

