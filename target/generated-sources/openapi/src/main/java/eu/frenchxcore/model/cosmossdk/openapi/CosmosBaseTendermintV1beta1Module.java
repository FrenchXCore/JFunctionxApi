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
 * CosmosBaseTendermintV1beta1Module
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosBaseTendermintV1beta1Module {
  public static final String SERIALIZED_NAME_PATH = "path";
  @SerializedName(SERIALIZED_NAME_PATH)
  private String path;

  public static final String SERIALIZED_NAME_VERSION = "version";
  @SerializedName(SERIALIZED_NAME_VERSION)
  private String version;

  public static final String SERIALIZED_NAME_SUM = "sum";
  @SerializedName(SERIALIZED_NAME_SUM)
  private String sum;


  public CosmosBaseTendermintV1beta1Module path(String path) {
    
    this.path = path;
    return this;
  }

   /**
   * Get path
   * @return path
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getPath() {
    return path;
  }


  public void setPath(String path) {
    this.path = path;
  }


  public CosmosBaseTendermintV1beta1Module version(String version) {
    
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getVersion() {
    return version;
  }


  public void setVersion(String version) {
    this.version = version;
  }


  public CosmosBaseTendermintV1beta1Module sum(String sum) {
    
    this.sum = sum;
    return this;
  }

   /**
   * Get sum
   * @return sum
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getSum() {
    return sum;
  }


  public void setSum(String sum) {
    this.sum = sum;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosBaseTendermintV1beta1Module cosmosBaseTendermintV1beta1Module = (CosmosBaseTendermintV1beta1Module) o;
    return Objects.equals(this.path, cosmosBaseTendermintV1beta1Module.path) &&
        Objects.equals(this.version, cosmosBaseTendermintV1beta1Module.version) &&
        Objects.equals(this.sum, cosmosBaseTendermintV1beta1Module.sum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(path, version, sum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosBaseTendermintV1beta1Module {\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    sum: ").append(toIndentedString(sum)).append("\n");
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

