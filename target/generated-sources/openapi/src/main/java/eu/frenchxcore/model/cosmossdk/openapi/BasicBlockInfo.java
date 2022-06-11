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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * Consensus captures the consensus rules for processing a block in the blockchain, including all blockchain data structures and the rules of the application&#39;s state transition machine.
 */
@ApiModel(description = "Consensus captures the consensus rules for processing a block in the blockchain, including all blockchain data structures and the rules of the application's state transition machine.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class BasicBlockInfo {
  public static final String SERIALIZED_NAME_BLOCK = "block";
  @SerializedName(SERIALIZED_NAME_BLOCK)
  private String block;

  public static final String SERIALIZED_NAME_APP = "app";
  @SerializedName(SERIALIZED_NAME_APP)
  private String app;


  public BasicBlockInfo block(String block) {
    
    this.block = block;
    return this;
  }

   /**
   * Get block
   * @return block
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getBlock() {
    return block;
  }


  public void setBlock(String block) {
    this.block = block;
  }


  public BasicBlockInfo app(String app) {
    
    this.app = app;
    return this;
  }

   /**
   * Get app
   * @return app
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

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
    BasicBlockInfo basicBlockInfo = (BasicBlockInfo) o;
    return Objects.equals(this.block, basicBlockInfo.block) &&
        Objects.equals(this.app, basicBlockInfo.app);
  }

  @Override
  public int hashCode() {
    return Objects.hash(block, app);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BasicBlockInfo {\n");
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

