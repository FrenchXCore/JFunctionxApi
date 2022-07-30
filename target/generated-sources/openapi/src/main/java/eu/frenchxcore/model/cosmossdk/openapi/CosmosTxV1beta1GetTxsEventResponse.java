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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20014Pagination;
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse20050TxResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * GetTxsEventResponse is the response type for the Service.TxsByEvents RPC method.
 */
@ApiModel(description = "GetTxsEventResponse is the response type for the Service.TxsByEvents RPC method.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class CosmosTxV1beta1GetTxsEventResponse {
  public static final String SERIALIZED_NAME_TXS = "txs";
  @SerializedName(SERIALIZED_NAME_TXS)
  private List<CosmosTxV1beta1Tx> txs = null;

  public static final String SERIALIZED_NAME_TX_RESPONSES = "tx_responses";
  @SerializedName(SERIALIZED_NAME_TX_RESPONSES)
  private List<InlineResponse20050TxResponse> txResponses = null;

  public static final String SERIALIZED_NAME_PAGINATION = "pagination";
  @SerializedName(SERIALIZED_NAME_PAGINATION)
  private InlineResponse20014Pagination pagination;


  public CosmosTxV1beta1GetTxsEventResponse txs(List<CosmosTxV1beta1Tx> txs) {
    
    this.txs = txs;
    return this;
  }

  public CosmosTxV1beta1GetTxsEventResponse addTxsItem(CosmosTxV1beta1Tx txsItem) {
    if (this.txs == null) {
      this.txs = new ArrayList<CosmosTxV1beta1Tx>();
    }
    this.txs.add(txsItem);
    return this;
  }

   /**
   * txs is the list of queried transactions.
   * @return txs
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "txs is the list of queried transactions.")

  public List<CosmosTxV1beta1Tx> getTxs() {
    return txs;
  }


  public void setTxs(List<CosmosTxV1beta1Tx> txs) {
    this.txs = txs;
  }


  public CosmosTxV1beta1GetTxsEventResponse txResponses(List<InlineResponse20050TxResponse> txResponses) {
    
    this.txResponses = txResponses;
    return this;
  }

  public CosmosTxV1beta1GetTxsEventResponse addTxResponsesItem(InlineResponse20050TxResponse txResponsesItem) {
    if (this.txResponses == null) {
      this.txResponses = new ArrayList<InlineResponse20050TxResponse>();
    }
    this.txResponses.add(txResponsesItem);
    return this;
  }

   /**
   * tx_responses is the list of queried TxResponses.
   * @return txResponses
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "tx_responses is the list of queried TxResponses.")

  public List<InlineResponse20050TxResponse> getTxResponses() {
    return txResponses;
  }


  public void setTxResponses(List<InlineResponse20050TxResponse> txResponses) {
    this.txResponses = txResponses;
  }


  public CosmosTxV1beta1GetTxsEventResponse pagination(InlineResponse20014Pagination pagination) {
    
    this.pagination = pagination;
    return this;
  }

   /**
   * Get pagination
   * @return pagination
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public InlineResponse20014Pagination getPagination() {
    return pagination;
  }


  public void setPagination(InlineResponse20014Pagination pagination) {
    this.pagination = pagination;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosTxV1beta1GetTxsEventResponse cosmosTxV1beta1GetTxsEventResponse = (CosmosTxV1beta1GetTxsEventResponse) o;
    return Objects.equals(this.txs, cosmosTxV1beta1GetTxsEventResponse.txs) &&
        Objects.equals(this.txResponses, cosmosTxV1beta1GetTxsEventResponse.txResponses) &&
        Objects.equals(this.pagination, cosmosTxV1beta1GetTxsEventResponse.pagination);
  }

  @Override
  public int hashCode() {
    return Objects.hash(txs, txResponses, pagination);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosTxV1beta1GetTxsEventResponse {\n");
    sb.append("    txs: ").append(toIndentedString(txs)).append("\n");
    sb.append("    txResponses: ").append(toIndentedString(txResponses)).append("\n");
    sb.append("    pagination: ").append(toIndentedString(pagination)).append("\n");
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

