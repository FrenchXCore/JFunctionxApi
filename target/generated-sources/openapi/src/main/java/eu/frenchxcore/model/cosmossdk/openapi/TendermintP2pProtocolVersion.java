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
 * TendermintP2pProtocolVersion
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class TendermintP2pProtocolVersion {
  public static final String SERIALIZED_NAME_P2P = "p2p";
  @SerializedName(SERIALIZED_NAME_P2P)
  private String p2p;

  public static final String SERIALIZED_NAME_BLOCK = "block";
  @SerializedName(SERIALIZED_NAME_BLOCK)
  private String block;

  public static final String SERIALIZED_NAME_APP = "app";
  @SerializedName(SERIALIZED_NAME_APP)
  private String app;


  public TendermintP2pProtocolVersion p2p(String p2p) {
    
    this.p2p = p2p;
    return this;
  }

   /**
   * Get p2p
   * @return p2p
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getP2p() {
    return p2p;
  }


  public void setP2p(String p2p) {
    this.p2p = p2p;
  }


  public TendermintP2pProtocolVersion block(String block) {
    
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


  public TendermintP2pProtocolVersion app(String app) {
    
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
    TendermintP2pProtocolVersion tendermintP2pProtocolVersion = (TendermintP2pProtocolVersion) o;
    return Objects.equals(this.p2p, tendermintP2pProtocolVersion.p2p) &&
        Objects.equals(this.block, tendermintP2pProtocolVersion.block) &&
        Objects.equals(this.app, tendermintP2pProtocolVersion.app);
  }

  @Override
  public int hashCode() {
    return Objects.hash(p2p, block, app);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TendermintP2pProtocolVersion {\n");
    sb.append("    p2p: ").append(toIndentedString(p2p)).append("\n");
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

