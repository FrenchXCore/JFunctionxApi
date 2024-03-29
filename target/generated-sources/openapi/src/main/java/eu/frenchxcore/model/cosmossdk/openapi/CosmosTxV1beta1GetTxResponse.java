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
import eu.frenchxcore.model.cosmossdk.openapi.CosmosTxV1beta1Tx;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20050TxResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * GetTxResponse is the response type for the Service.GetTx method.
 */
@ApiModel(description = "GetTxResponse is the response type for the Service.GetTx method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosTxV1beta1GetTxResponse {
  public static final String SERIALIZED_NAME_TX = "tx";
  @SerializedName(SERIALIZED_NAME_TX)
  private CosmosTxV1beta1Tx tx;

  public static final String SERIALIZED_NAME_TX_RESPONSE = "tx_response";
  @SerializedName(SERIALIZED_NAME_TX_RESPONSE)
  private InlineResponse20050TxResponse txResponse;


  public CosmosTxV1beta1GetTxResponse tx(CosmosTxV1beta1Tx tx) {
    
    this.tx = tx;
    return this;
  }

   /**
   * Get tx
   * @return tx
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public CosmosTxV1beta1Tx getTx() {
    return tx;
  }


  public void setTx(CosmosTxV1beta1Tx tx) {
    this.tx = tx;
  }


  public CosmosTxV1beta1GetTxResponse txResponse(InlineResponse20050TxResponse txResponse) {
    
    this.txResponse = txResponse;
    return this;
  }

   /**
   * Get txResponse
   * @return txResponse
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20050TxResponse getTxResponse() {
    return txResponse;
  }


  public void setTxResponse(InlineResponse20050TxResponse txResponse) {
    this.txResponse = txResponse;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosTxV1beta1GetTxResponse cosmosTxV1beta1GetTxResponse = (CosmosTxV1beta1GetTxResponse) o;
    return Objects.equals(this.tx, cosmosTxV1beta1GetTxResponse.tx) &&
        Objects.equals(this.txResponse, cosmosTxV1beta1GetTxResponse.txResponse);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tx, txResponse);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosTxV1beta1GetTxResponse {\n");
    sb.append("    tx: ").append(toIndentedString(tx)).append("\n");
    sb.append("    txResponse: ").append(toIndentedString(txResponse)).append("\n");
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

