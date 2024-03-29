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
 * message SomeRequest {          Foo some_parameter &#x3D; 1;          PageRequest pagination &#x3D; 2;  }
 */
@ApiModel(description = "message SomeRequest {          Foo some_parameter = 1;          PageRequest pagination = 2;  }")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosBaseQueryV1beta1PageRequest {
  public static final String SERIALIZED_NAME_KEY = "key";
  @SerializedName(SERIALIZED_NAME_KEY)
  private byte[] key;

  public static final String SERIALIZED_NAME_OFFSET = "offset";
  @SerializedName(SERIALIZED_NAME_OFFSET)
  private String offset;

  public static final String SERIALIZED_NAME_LIMIT = "limit";
  @SerializedName(SERIALIZED_NAME_LIMIT)
  private String limit;

  public static final String SERIALIZED_NAME_COUNT_TOTAL = "count_total";
  @SerializedName(SERIALIZED_NAME_COUNT_TOTAL)
  private Boolean countTotal;

  public static final String SERIALIZED_NAME_REVERSE = "reverse";
  @SerializedName(SERIALIZED_NAME_REVERSE)
  private Boolean reverse;


  public CosmosBaseQueryV1beta1PageRequest key(byte[] key) {
    
    this.key = key;
    return this;
  }

   /**
   * key is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of offset or key should be set.
   * @return key
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "key is a value returned in PageResponse.next_key to begin querying the next page most efficiently. Only one of offset or key should be set.")

  public byte[] getKey() {
    return key;
  }


  public void setKey(byte[] key) {
    this.key = key;
  }


  public CosmosBaseQueryV1beta1PageRequest offset(String offset) {
    
    this.offset = offset;
    return this;
  }

   /**
   * offset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.
   * @return offset
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "offset is a numeric offset that can be used when key is unavailable. It is less efficient than using key. Only one of offset or key should be set.")

  public String getOffset() {
    return offset;
  }


  public void setOffset(String offset) {
    this.offset = offset;
  }


  public CosmosBaseQueryV1beta1PageRequest limit(String limit) {
    
    this.limit = limit;
    return this;
  }

   /**
   * limit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.
   * @return limit
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "limit is the total number of results to be returned in the result page. If left empty it will default to a value to be set by each app.")

  public String getLimit() {
    return limit;
  }


  public void setLimit(String limit) {
    this.limit = limit;
  }


  public CosmosBaseQueryV1beta1PageRequest countTotal(Boolean countTotal) {
    
    this.countTotal = countTotal;
    return this;
  }

   /**
   * count_total is set to true  to indicate that the result set should include a count of the total number of items available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.
   * @return countTotal
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "count_total is set to true  to indicate that the result set should include a count of the total number of items available for pagination in UIs. count_total is only respected when offset is used. It is ignored when key is set.")

  public Boolean getCountTotal() {
    return countTotal;
  }


  public void setCountTotal(Boolean countTotal) {
    this.countTotal = countTotal;
  }


  public CosmosBaseQueryV1beta1PageRequest reverse(Boolean reverse) {
    
    this.reverse = reverse;
    return this;
  }

   /**
   * reverse is set to true if results are to be returned in the descending order.
   * @return reverse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "reverse is set to true if results are to be returned in the descending order.")

  public Boolean getReverse() {
    return reverse;
  }


  public void setReverse(Boolean reverse) {
    this.reverse = reverse;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosBaseQueryV1beta1PageRequest cosmosBaseQueryV1beta1PageRequest = (CosmosBaseQueryV1beta1PageRequest) o;
    return Arrays.equals(this.key, cosmosBaseQueryV1beta1PageRequest.key) &&
        Objects.equals(this.offset, cosmosBaseQueryV1beta1PageRequest.offset) &&
        Objects.equals(this.limit, cosmosBaseQueryV1beta1PageRequest.limit) &&
        Objects.equals(this.countTotal, cosmosBaseQueryV1beta1PageRequest.countTotal) &&
        Objects.equals(this.reverse, cosmosBaseQueryV1beta1PageRequest.reverse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Arrays.hashCode(key), offset, limit, countTotal, reverse);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosBaseQueryV1beta1PageRequest {\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    offset: ").append(toIndentedString(offset)).append("\n");
    sb.append("    limit: ").append(toIndentedString(limit)).append("\n");
    sb.append("    countTotal: ").append(toIndentedString(countTotal)).append("\n");
    sb.append("    reverse: ").append(toIndentedString(reverse)).append("\n");
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

