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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20041CommissionCommissionRates;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * Commission defines commission parameters for a given validator.
 */
@ApiModel(description = "Commission defines commission parameters for a given validator.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosStakingV1beta1Commission {
  public static final String SERIALIZED_NAME_COMMISSION_RATES = "commission_rates";
  @SerializedName(SERIALIZED_NAME_COMMISSION_RATES)
  private InlineResponse20041CommissionCommissionRates commissionRates;

  public static final String SERIALIZED_NAME_UPDATE_TIME = "update_time";
  @SerializedName(SERIALIZED_NAME_UPDATE_TIME)
  private OffsetDateTime updateTime;


  public CosmosStakingV1beta1Commission commissionRates(InlineResponse20041CommissionCommissionRates commissionRates) {
    
    this.commissionRates = commissionRates;
    return this;
  }

   /**
   * Get commissionRates
   * @return commissionRates
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20041CommissionCommissionRates getCommissionRates() {
    return commissionRates;
  }


  public void setCommissionRates(InlineResponse20041CommissionCommissionRates commissionRates) {
    this.commissionRates = commissionRates;
  }


  public CosmosStakingV1beta1Commission updateTime(OffsetDateTime updateTime) {
    
    this.updateTime = updateTime;
    return this;
  }

   /**
   * update_time is the last time the commission rate was changed.
   * @return updateTime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "update_time is the last time the commission rate was changed.")

  public OffsetDateTime getUpdateTime() {
    return updateTime;
  }


  public void setUpdateTime(OffsetDateTime updateTime) {
    this.updateTime = updateTime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosStakingV1beta1Commission cosmosStakingV1beta1Commission = (CosmosStakingV1beta1Commission) o;
    return Objects.equals(this.commissionRates, cosmosStakingV1beta1Commission.commissionRates) &&
        Objects.equals(this.updateTime, cosmosStakingV1beta1Commission.updateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commissionRates, updateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosStakingV1beta1Commission {\n");
    sb.append("    commissionRates: ").append(toIndentedString(commissionRates)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
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

