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
 * Params represents the parameters used for by the slashing module.
 */
@ApiModel(description = "Params represents the parameters used for by the slashing module.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosSlashingV1beta1Params {
  public static final String SERIALIZED_NAME_SIGNED_BLOCKS_WINDOW = "signed_blocks_window";
  @SerializedName(SERIALIZED_NAME_SIGNED_BLOCKS_WINDOW)
  private String signedBlocksWindow;

  public static final String SERIALIZED_NAME_MIN_SIGNED_PER_WINDOW = "min_signed_per_window";
  @SerializedName(SERIALIZED_NAME_MIN_SIGNED_PER_WINDOW)
  private byte[] minSignedPerWindow;

  public static final String SERIALIZED_NAME_DOWNTIME_JAIL_DURATION = "downtime_jail_duration";
  @SerializedName(SERIALIZED_NAME_DOWNTIME_JAIL_DURATION)
  private String downtimeJailDuration;

  public static final String SERIALIZED_NAME_SLASH_FRACTION_DOUBLE_SIGN = "slash_fraction_double_sign";
  @SerializedName(SERIALIZED_NAME_SLASH_FRACTION_DOUBLE_SIGN)
  private byte[] slashFractionDoubleSign;

  public static final String SERIALIZED_NAME_SLASH_FRACTION_DOWNTIME = "slash_fraction_downtime";
  @SerializedName(SERIALIZED_NAME_SLASH_FRACTION_DOWNTIME)
  private byte[] slashFractionDowntime;


  public CosmosSlashingV1beta1Params signedBlocksWindow(String signedBlocksWindow) {
    
    this.signedBlocksWindow = signedBlocksWindow;
    return this;
  }

   /**
   * Get signedBlocksWindow
   * @return signedBlocksWindow
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getSignedBlocksWindow() {
    return signedBlocksWindow;
  }


  public void setSignedBlocksWindow(String signedBlocksWindow) {
    this.signedBlocksWindow = signedBlocksWindow;
  }


  public CosmosSlashingV1beta1Params minSignedPerWindow(byte[] minSignedPerWindow) {
    
    this.minSignedPerWindow = minSignedPerWindow;
    return this;
  }

   /**
   * Get minSignedPerWindow
   * @return minSignedPerWindow
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public byte[] getMinSignedPerWindow() {
    return minSignedPerWindow;
  }


  public void setMinSignedPerWindow(byte[] minSignedPerWindow) {
    this.minSignedPerWindow = minSignedPerWindow;
  }


  public CosmosSlashingV1beta1Params downtimeJailDuration(String downtimeJailDuration) {
    
    this.downtimeJailDuration = downtimeJailDuration;
    return this;
  }

   /**
   * Get downtimeJailDuration
   * @return downtimeJailDuration
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDowntimeJailDuration() {
    return downtimeJailDuration;
  }


  public void setDowntimeJailDuration(String downtimeJailDuration) {
    this.downtimeJailDuration = downtimeJailDuration;
  }


  public CosmosSlashingV1beta1Params slashFractionDoubleSign(byte[] slashFractionDoubleSign) {
    
    this.slashFractionDoubleSign = slashFractionDoubleSign;
    return this;
  }

   /**
   * Get slashFractionDoubleSign
   * @return slashFractionDoubleSign
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public byte[] getSlashFractionDoubleSign() {
    return slashFractionDoubleSign;
  }


  public void setSlashFractionDoubleSign(byte[] slashFractionDoubleSign) {
    this.slashFractionDoubleSign = slashFractionDoubleSign;
  }


  public CosmosSlashingV1beta1Params slashFractionDowntime(byte[] slashFractionDowntime) {
    
    this.slashFractionDowntime = slashFractionDowntime;
    return this;
  }

   /**
   * Get slashFractionDowntime
   * @return slashFractionDowntime
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public byte[] getSlashFractionDowntime() {
    return slashFractionDowntime;
  }


  public void setSlashFractionDowntime(byte[] slashFractionDowntime) {
    this.slashFractionDowntime = slashFractionDowntime;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosSlashingV1beta1Params cosmosSlashingV1beta1Params = (CosmosSlashingV1beta1Params) o;
    return Objects.equals(this.signedBlocksWindow, cosmosSlashingV1beta1Params.signedBlocksWindow) &&
        Arrays.equals(this.minSignedPerWindow, cosmosSlashingV1beta1Params.minSignedPerWindow) &&
        Objects.equals(this.downtimeJailDuration, cosmosSlashingV1beta1Params.downtimeJailDuration) &&
        Arrays.equals(this.slashFractionDoubleSign, cosmosSlashingV1beta1Params.slashFractionDoubleSign) &&
        Arrays.equals(this.slashFractionDowntime, cosmosSlashingV1beta1Params.slashFractionDowntime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signedBlocksWindow, Arrays.hashCode(minSignedPerWindow), downtimeJailDuration, Arrays.hashCode(slashFractionDoubleSign), Arrays.hashCode(slashFractionDowntime));
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosSlashingV1beta1Params {\n");
    sb.append("    signedBlocksWindow: ").append(toIndentedString(signedBlocksWindow)).append("\n");
    sb.append("    minSignedPerWindow: ").append(toIndentedString(minSignedPerWindow)).append("\n");
    sb.append("    downtimeJailDuration: ").append(toIndentedString(downtimeJailDuration)).append("\n");
    sb.append("    slashFractionDoubleSign: ").append(toIndentedString(slashFractionDoubleSign)).append("\n");
    sb.append("    slashFractionDowntime: ").append(toIndentedString(slashFractionDowntime)).append("\n");
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

