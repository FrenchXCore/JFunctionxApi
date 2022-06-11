/*
 * Cosmos SDK - gRPC Gateway docs
 * A REST interface for state queries
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package eu.frenchxcore.model.cosmossdk.openapi;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import eu.frenchxcore.model.cosmossdk.openapi.BlockID;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20010Block;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * GetLatestBlockResponse is the response type for the Query/GetLatestBlock RPC method.
 */
@ApiModel(description = "GetLatestBlockResponse is the response type for the Query/GetLatestBlock RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class CosmosBaseTendermintV1beta1GetLatestBlockResponse {
  public static final String SERIALIZED_NAME_BLOCK_ID = "block_id";
  @SerializedName(SERIALIZED_NAME_BLOCK_ID)
  private BlockID blockId;

  public static final String SERIALIZED_NAME_BLOCK = "block";
  @SerializedName(SERIALIZED_NAME_BLOCK)
  private InlineResponse20010Block block;


  public CosmosBaseTendermintV1beta1GetLatestBlockResponse blockId(BlockID blockId) {
    
    this.blockId = blockId;
    return this;
  }

   /**
   * Get blockId
   * @return blockId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public BlockID getBlockId() {
    return blockId;
  }


  public void setBlockId(BlockID blockId) {
    this.blockId = blockId;
  }


  public CosmosBaseTendermintV1beta1GetLatestBlockResponse block(InlineResponse20010Block block) {
    
    this.block = block;
    return this;
  }

   /**
   * Get block
   * @return block
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20010Block getBlock() {
    return block;
  }


  public void setBlock(InlineResponse20010Block block) {
    this.block = block;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosBaseTendermintV1beta1GetLatestBlockResponse cosmosBaseTendermintV1beta1GetLatestBlockResponse = (CosmosBaseTendermintV1beta1GetLatestBlockResponse) o;
    return Objects.equals(this.blockId, cosmosBaseTendermintV1beta1GetLatestBlockResponse.blockId) &&
        Objects.equals(this.block, cosmosBaseTendermintV1beta1GetLatestBlockResponse.block);
  }

  @Override
  public int hashCode() {
    return Objects.hash(blockId, block);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosBaseTendermintV1beta1GetLatestBlockResponse {\n");
    sb.append("    blockId: ").append(toIndentedString(blockId)).append("\n");
    sb.append("    block: ").append(toIndentedString(block)).append("\n");
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

