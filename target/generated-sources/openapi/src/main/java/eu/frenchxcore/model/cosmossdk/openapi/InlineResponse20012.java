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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20012ApplicationVersion;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20012DefaultNodeInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * GetNodeInfoResponse is the request type for the Query/GetNodeInfo RPC method.
 */
@ApiModel(description = "GetNodeInfoResponse is the request type for the Query/GetNodeInfo RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class InlineResponse20012 {
  public static final String SERIALIZED_NAME_DEFAULT_NODE_INFO = "default_node_info";
  @SerializedName(SERIALIZED_NAME_DEFAULT_NODE_INFO)
  private InlineResponse20012DefaultNodeInfo defaultNodeInfo;

  public static final String SERIALIZED_NAME_APPLICATION_VERSION = "application_version";
  @SerializedName(SERIALIZED_NAME_APPLICATION_VERSION)
  private InlineResponse20012ApplicationVersion applicationVersion;


  public InlineResponse20012 defaultNodeInfo(InlineResponse20012DefaultNodeInfo defaultNodeInfo) {
    
    this.defaultNodeInfo = defaultNodeInfo;
    return this;
  }

   /**
   * Get defaultNodeInfo
   * @return defaultNodeInfo
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20012DefaultNodeInfo getDefaultNodeInfo() {
    return defaultNodeInfo;
  }


  public void setDefaultNodeInfo(InlineResponse20012DefaultNodeInfo defaultNodeInfo) {
    this.defaultNodeInfo = defaultNodeInfo;
  }


  public InlineResponse20012 applicationVersion(InlineResponse20012ApplicationVersion applicationVersion) {
    
    this.applicationVersion = applicationVersion;
    return this;
  }

   /**
   * Get applicationVersion
   * @return applicationVersion
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20012ApplicationVersion getApplicationVersion() {
    return applicationVersion;
  }


  public void setApplicationVersion(InlineResponse20012ApplicationVersion applicationVersion) {
    this.applicationVersion = applicationVersion;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20012 inlineResponse20012 = (InlineResponse20012) o;
    return Objects.equals(this.defaultNodeInfo, inlineResponse20012.defaultNodeInfo) &&
        Objects.equals(this.applicationVersion, inlineResponse20012.applicationVersion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultNodeInfo, applicationVersion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20012 {\n");
    sb.append("    defaultNodeInfo: ").append(toIndentedString(defaultNodeInfo)).append("\n");
    sb.append("    applicationVersion: ").append(toIndentedString(applicationVersion)).append("\n");
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

