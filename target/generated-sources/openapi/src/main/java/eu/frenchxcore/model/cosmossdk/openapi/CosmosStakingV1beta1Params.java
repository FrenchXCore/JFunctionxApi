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
 * Params defines the parameters for the staking module.
 */
@ApiModel(description = "Params defines the parameters for the staking module.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T23:37:17.045990200+02:00[Europe/Paris]")
public class CosmosStakingV1beta1Params {
  public static final String SERIALIZED_NAME_UNBONDING_TIME = "unbonding_time";
  @SerializedName(SERIALIZED_NAME_UNBONDING_TIME)
  private String unbondingTime;

  public static final String SERIALIZED_NAME_MAX_VALIDATORS = "max_validators";
  @SerializedName(SERIALIZED_NAME_MAX_VALIDATORS)
  private Long maxValidators;

  public static final String SERIALIZED_NAME_MAX_ENTRIES = "max_entries";
  @SerializedName(SERIALIZED_NAME_MAX_ENTRIES)
  private Long maxEntries;

  public static final String SERIALIZED_NAME_HISTORICAL_ENTRIES = "historical_entries";
  @SerializedName(SERIALIZED_NAME_HISTORICAL_ENTRIES)
  private Long historicalEntries;

  public static final String SERIALIZED_NAME_BOND_DENOM = "bond_denom";
  @SerializedName(SERIALIZED_NAME_BOND_DENOM)
  private String bondDenom;


  public CosmosStakingV1beta1Params unbondingTime(String unbondingTime) {
    
    this.unbondingTime = unbondingTime;
    return this;
  }

   /**
   * unbonding_time is the time duration of unbonding.
   * @return unbondingTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "unbonding_time is the time duration of unbonding.")

  public String getUnbondingTime() {
    return unbondingTime;
  }


  public void setUnbondingTime(String unbondingTime) {
    this.unbondingTime = unbondingTime;
  }


  public CosmosStakingV1beta1Params maxValidators(Long maxValidators) {
    
    this.maxValidators = maxValidators;
    return this;
  }

   /**
   * max_validators is the maximum number of validators.
   * @return maxValidators
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "max_validators is the maximum number of validators.")

  public Long getMaxValidators() {
    return maxValidators;
  }


  public void setMaxValidators(Long maxValidators) {
    this.maxValidators = maxValidators;
  }


  public CosmosStakingV1beta1Params maxEntries(Long maxEntries) {
    
    this.maxEntries = maxEntries;
    return this;
  }

   /**
   * max_entries is the max entries for either unbonding delegation or redelegation (per pair/trio).
   * @return maxEntries
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "max_entries is the max entries for either unbonding delegation or redelegation (per pair/trio).")

  public Long getMaxEntries() {
    return maxEntries;
  }


  public void setMaxEntries(Long maxEntries) {
    this.maxEntries = maxEntries;
  }


  public CosmosStakingV1beta1Params historicalEntries(Long historicalEntries) {
    
    this.historicalEntries = historicalEntries;
    return this;
  }

   /**
   * historical_entries is the number of historical entries to persist.
   * @return historicalEntries
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "historical_entries is the number of historical entries to persist.")

  public Long getHistoricalEntries() {
    return historicalEntries;
  }


  public void setHistoricalEntries(Long historicalEntries) {
    this.historicalEntries = historicalEntries;
  }


  public CosmosStakingV1beta1Params bondDenom(String bondDenom) {
    
    this.bondDenom = bondDenom;
    return this;
  }

   /**
   * bond_denom defines the bondable coin denomination.
   * @return bondDenom
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "bond_denom defines the bondable coin denomination.")

  public String getBondDenom() {
    return bondDenom;
  }


  public void setBondDenom(String bondDenom) {
    this.bondDenom = bondDenom;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosStakingV1beta1Params cosmosStakingV1beta1Params = (CosmosStakingV1beta1Params) o;
    return Objects.equals(this.unbondingTime, cosmosStakingV1beta1Params.unbondingTime) &&
        Objects.equals(this.maxValidators, cosmosStakingV1beta1Params.maxValidators) &&
        Objects.equals(this.maxEntries, cosmosStakingV1beta1Params.maxEntries) &&
        Objects.equals(this.historicalEntries, cosmosStakingV1beta1Params.historicalEntries) &&
        Objects.equals(this.bondDenom, cosmosStakingV1beta1Params.bondDenom);
  }

  @Override
  public int hashCode() {
    return Objects.hash(unbondingTime, maxValidators, maxEntries, historicalEntries, bondDenom);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosStakingV1beta1Params {\n");
    sb.append("    unbondingTime: ").append(toIndentedString(unbondingTime)).append("\n");
    sb.append("    maxValidators: ").append(toIndentedString(maxValidators)).append("\n");
    sb.append("    maxEntries: ").append(toIndentedString(maxEntries)).append("\n");
    sb.append("    historicalEntries: ").append(toIndentedString(historicalEntries)).append("\n");
    sb.append("    bondDenom: ").append(toIndentedString(bondDenom)).append("\n");
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

