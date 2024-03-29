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
 * pool defines the pool info.
 */
@ApiModel(description = "pool defines the pool info.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse20045Pool {
  public static final String SERIALIZED_NAME_NOT_BONDED_TOKENS = "not_bonded_tokens";
  @SerializedName(SERIALIZED_NAME_NOT_BONDED_TOKENS)
  private String notBondedTokens;

  public static final String SERIALIZED_NAME_BONDED_TOKENS = "bonded_tokens";
  @SerializedName(SERIALIZED_NAME_BONDED_TOKENS)
  private String bondedTokens;


  public InlineResponse20045Pool notBondedTokens(String notBondedTokens) {
    
    this.notBondedTokens = notBondedTokens;
    return this;
  }

   /**
   * Get notBondedTokens
   * @return notBondedTokens
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getNotBondedTokens() {
    return notBondedTokens;
  }


  public void setNotBondedTokens(String notBondedTokens) {
    this.notBondedTokens = notBondedTokens;
  }


  public InlineResponse20045Pool bondedTokens(String bondedTokens) {
    
    this.bondedTokens = bondedTokens;
    return this;
  }

   /**
   * Get bondedTokens
   * @return bondedTokens
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getBondedTokens() {
    return bondedTokens;
  }


  public void setBondedTokens(String bondedTokens) {
    this.bondedTokens = bondedTokens;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20045Pool inlineResponse20045Pool = (InlineResponse20045Pool) o;
    return Objects.equals(this.notBondedTokens, inlineResponse20045Pool.notBondedTokens) &&
        Objects.equals(this.bondedTokens, inlineResponse20045Pool.bondedTokens);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notBondedTokens, bondedTokens);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20045Pool {\n");
    sb.append("    notBondedTokens: ").append(toIndentedString(notBondedTokens)).append("\n");
    sb.append("    bondedTokens: ").append(toIndentedString(bondedTokens)).append("\n");
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

