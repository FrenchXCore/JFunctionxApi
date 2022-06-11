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
import eu.frenchxcore.model.cosmossdk.openapi.GrantIsStoredInTheKVStoreToRecordAGrantWithFullContextAllowance;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * CosmosFeegrantV1beta1Grant
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class CosmosFeegrantV1beta1Grant {
  public static final String SERIALIZED_NAME_GRANTER = "granter";
  @SerializedName(SERIALIZED_NAME_GRANTER)
  private String granter;

  public static final String SERIALIZED_NAME_GRANTEE = "grantee";
  @SerializedName(SERIALIZED_NAME_GRANTEE)
  private String grantee;

  public static final String SERIALIZED_NAME_ALLOWANCE = "allowance";
  @SerializedName(SERIALIZED_NAME_ALLOWANCE)
  private GrantIsStoredInTheKVStoreToRecordAGrantWithFullContextAllowance allowance;


  public CosmosFeegrantV1beta1Grant granter(String granter) {
    
    this.granter = granter;
    return this;
  }

   /**
   * granter is the address of the user granting an allowance of their funds.
   * @return granter
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "granter is the address of the user granting an allowance of their funds.")

  public String getGranter() {
    return granter;
  }


  public void setGranter(String granter) {
    this.granter = granter;
  }


  public CosmosFeegrantV1beta1Grant grantee(String grantee) {
    
    this.grantee = grantee;
    return this;
  }

   /**
   * grantee is the address of the user being granted an allowance of another user&#39;s funds.
   * @return grantee
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "grantee is the address of the user being granted an allowance of another user's funds.")

  public String getGrantee() {
    return grantee;
  }


  public void setGrantee(String grantee) {
    this.grantee = grantee;
  }


  public CosmosFeegrantV1beta1Grant allowance(GrantIsStoredInTheKVStoreToRecordAGrantWithFullContextAllowance allowance) {
    
    this.allowance = allowance;
    return this;
  }

   /**
   * Get allowance
   * @return allowance
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public GrantIsStoredInTheKVStoreToRecordAGrantWithFullContextAllowance getAllowance() {
    return allowance;
  }


  public void setAllowance(GrantIsStoredInTheKVStoreToRecordAGrantWithFullContextAllowance allowance) {
    this.allowance = allowance;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosFeegrantV1beta1Grant cosmosFeegrantV1beta1Grant = (CosmosFeegrantV1beta1Grant) o;
    return Objects.equals(this.granter, cosmosFeegrantV1beta1Grant.granter) &&
        Objects.equals(this.grantee, cosmosFeegrantV1beta1Grant.grantee) &&
        Objects.equals(this.allowance, cosmosFeegrantV1beta1Grant.allowance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(granter, grantee, allowance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosFeegrantV1beta1Grant {\n");
    sb.append("    granter: ").append(toIndentedString(granter)).append("\n");
    sb.append("    grantee: ").append(toIndentedString(grantee)).append("\n");
    sb.append("    allowance: ").append(toIndentedString(allowance)).append("\n");
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

