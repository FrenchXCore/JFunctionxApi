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
 * Params defines the set of params for the distribution module.
 */
@ApiModel(description = "Params defines the set of params for the distribution module.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosDistributionV1beta1Params {
  public static final String SERIALIZED_NAME_COMMUNITY_TAX = "community_tax";
  @SerializedName(SERIALIZED_NAME_COMMUNITY_TAX)
  private String communityTax;

  public static final String SERIALIZED_NAME_BASE_PROPOSER_REWARD = "base_proposer_reward";
  @SerializedName(SERIALIZED_NAME_BASE_PROPOSER_REWARD)
  private String baseProposerReward;

  public static final String SERIALIZED_NAME_BONUS_PROPOSER_REWARD = "bonus_proposer_reward";
  @SerializedName(SERIALIZED_NAME_BONUS_PROPOSER_REWARD)
  private String bonusProposerReward;

  public static final String SERIALIZED_NAME_WITHDRAW_ADDR_ENABLED = "withdraw_addr_enabled";
  @SerializedName(SERIALIZED_NAME_WITHDRAW_ADDR_ENABLED)
  private Boolean withdrawAddrEnabled;


  public CosmosDistributionV1beta1Params communityTax(String communityTax) {
    
    this.communityTax = communityTax;
    return this;
  }

   /**
   * Get communityTax
   * @return communityTax
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getCommunityTax() {
    return communityTax;
  }


  public void setCommunityTax(String communityTax) {
    this.communityTax = communityTax;
  }


  public CosmosDistributionV1beta1Params baseProposerReward(String baseProposerReward) {
    
    this.baseProposerReward = baseProposerReward;
    return this;
  }

   /**
   * Get baseProposerReward
   * @return baseProposerReward
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getBaseProposerReward() {
    return baseProposerReward;
  }


  public void setBaseProposerReward(String baseProposerReward) {
    this.baseProposerReward = baseProposerReward;
  }


  public CosmosDistributionV1beta1Params bonusProposerReward(String bonusProposerReward) {
    
    this.bonusProposerReward = bonusProposerReward;
    return this;
  }

   /**
   * Get bonusProposerReward
   * @return bonusProposerReward
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getBonusProposerReward() {
    return bonusProposerReward;
  }


  public void setBonusProposerReward(String bonusProposerReward) {
    this.bonusProposerReward = bonusProposerReward;
  }


  public CosmosDistributionV1beta1Params withdrawAddrEnabled(Boolean withdrawAddrEnabled) {
    
    this.withdrawAddrEnabled = withdrawAddrEnabled;
    return this;
  }

   /**
   * Get withdrawAddrEnabled
   * @return withdrawAddrEnabled
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public Boolean getWithdrawAddrEnabled() {
    return withdrawAddrEnabled;
  }


  public void setWithdrawAddrEnabled(Boolean withdrawAddrEnabled) {
    this.withdrawAddrEnabled = withdrawAddrEnabled;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosDistributionV1beta1Params cosmosDistributionV1beta1Params = (CosmosDistributionV1beta1Params) o;
    return Objects.equals(this.communityTax, cosmosDistributionV1beta1Params.communityTax) &&
        Objects.equals(this.baseProposerReward, cosmosDistributionV1beta1Params.baseProposerReward) &&
        Objects.equals(this.bonusProposerReward, cosmosDistributionV1beta1Params.bonusProposerReward) &&
        Objects.equals(this.withdrawAddrEnabled, cosmosDistributionV1beta1Params.withdrawAddrEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(communityTax, baseProposerReward, bonusProposerReward, withdrawAddrEnabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosDistributionV1beta1Params {\n");
    sb.append("    communityTax: ").append(toIndentedString(communityTax)).append("\n");
    sb.append("    baseProposerReward: ").append(toIndentedString(baseProposerReward)).append("\n");
    sb.append("    bonusProposerReward: ").append(toIndentedString(bonusProposerReward)).append("\n");
    sb.append("    withdrawAddrEnabled: ").append(toIndentedString(withdrawAddrEnabled)).append("\n");
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

