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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20041Validators;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20043HistHeader;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * hist defines the historical info at the given height.
 */
@ApiModel(description = "hist defines the historical info at the given height.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse20043Hist {
  public static final String SERIALIZED_NAME_HEADER = "header";
  @SerializedName(SERIALIZED_NAME_HEADER)
  private InlineResponse20043HistHeader header;

  public static final String SERIALIZED_NAME_VALSET = "valset";
  @SerializedName(SERIALIZED_NAME_VALSET)
  private List<InlineResponse20041Validators> valset = null;


  public InlineResponse20043Hist header(InlineResponse20043HistHeader header) {
    
    this.header = header;
    return this;
  }

   /**
   * Get header
   * @return header
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20043HistHeader getHeader() {
    return header;
  }


  public void setHeader(InlineResponse20043HistHeader header) {
    this.header = header;
  }


  public InlineResponse20043Hist valset(List<InlineResponse20041Validators> valset) {
    
    this.valset = valset;
    return this;
  }

  public InlineResponse20043Hist addValsetItem(InlineResponse20041Validators valsetItem) {
    if (this.valset == null) {
      this.valset = new ArrayList<InlineResponse20041Validators>();
    }
    this.valset.add(valsetItem);
    return this;
  }

   /**
   * Get valset
   * @return valset
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<InlineResponse20041Validators> getValset() {
    return valset;
  }


  public void setValset(List<InlineResponse20041Validators> valset) {
    this.valset = valset;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20043Hist inlineResponse20043Hist = (InlineResponse20043Hist) o;
    return Objects.equals(this.header, inlineResponse20043Hist.header) &&
        Objects.equals(this.valset, inlineResponse20043Hist.valset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, valset);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20043Hist {\n");
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    valset: ").append(toIndentedString(valset)).append("\n");
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

