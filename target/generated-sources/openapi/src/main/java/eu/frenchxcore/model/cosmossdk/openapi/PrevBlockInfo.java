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
import eu.frenchxcore.model.cosmossdk.openapi.PartsetHeader;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * PrevBlockInfo
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class PrevBlockInfo {
  public static final String SERIALIZED_NAME_HASH = "hash";
  @SerializedName(SERIALIZED_NAME_HASH)
  private byte[] hash;

  public static final String SERIALIZED_NAME_PART_SET_HEADER = "part_set_header";
  @SerializedName(SERIALIZED_NAME_PART_SET_HEADER)
  private PartsetHeader partSetHeader;


  public PrevBlockInfo hash(byte[] hash) {
    
    this.hash = hash;
    return this;
  }

   /**
   * Get hash
   * @return hash
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public byte[] getHash() {
    return hash;
  }


  public void setHash(byte[] hash) {
    this.hash = hash;
  }


  public PrevBlockInfo partSetHeader(PartsetHeader partSetHeader) {
    
    this.partSetHeader = partSetHeader;
    return this;
  }

   /**
   * Get partSetHeader
   * @return partSetHeader
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public PartsetHeader getPartSetHeader() {
    return partSetHeader;
  }


  public void setPartSetHeader(PartsetHeader partSetHeader) {
    this.partSetHeader = partSetHeader;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrevBlockInfo prevBlockInfo = (PrevBlockInfo) o;
    return Arrays.equals(this.hash, prevBlockInfo.hash) &&
        Objects.equals(this.partSetHeader, prevBlockInfo.partSetHeader);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(hash), partSetHeader);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrevBlockInfo {\n");
    sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
    sb.append("    partSetHeader: ").append(toIndentedString(partSetHeader)).append("\n");
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

