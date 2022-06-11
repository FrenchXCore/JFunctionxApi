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
import eu.frenchxcore.model.cosmossdk.openapi.InlineResponse2003Balances;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deposit defines an amount deposited by an account address to an active proposal.
 */
@ApiModel(description = "Deposit defines an amount deposited by an account address to an active proposal.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-06-11T08:16:25.027082+02:00[Europe/Paris]")
public class CosmosGovV1beta1Deposit {
  public static final String SERIALIZED_NAME_PROPOSAL_ID = "proposal_id";
  @SerializedName(SERIALIZED_NAME_PROPOSAL_ID)
  private String proposalId;

  public static final String SERIALIZED_NAME_DEPOSITOR = "depositor";
  @SerializedName(SERIALIZED_NAME_DEPOSITOR)
  private String depositor;

  public static final String SERIALIZED_NAME_AMOUNT = "amount";
  @SerializedName(SERIALIZED_NAME_AMOUNT)
  private List<InlineResponse2003Balances> amount = null;


  public CosmosGovV1beta1Deposit proposalId(String proposalId) {
    
    this.proposalId = proposalId;
    return this;
  }

   /**
   * Get proposalId
   * @return proposalId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getProposalId() {
    return proposalId;
  }


  public void setProposalId(String proposalId) {
    this.proposalId = proposalId;
  }


  public CosmosGovV1beta1Deposit depositor(String depositor) {
    
    this.depositor = depositor;
    return this;
  }

   /**
   * Get depositor
   * @return depositor
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public String getDepositor() {
    return depositor;
  }


  public void setDepositor(String depositor) {
    this.depositor = depositor;
  }


  public CosmosGovV1beta1Deposit amount(List<InlineResponse2003Balances> amount) {
    
    this.amount = amount;
    return this;
  }

  public CosmosGovV1beta1Deposit addAmountItem(InlineResponse2003Balances amountItem) {
    if (this.amount == null) {
      this.amount = new ArrayList<InlineResponse2003Balances>();
    }
    this.amount.add(amountItem);
    return this;
  }

   /**
   * Get amount
   * @return amount
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<InlineResponse2003Balances> getAmount() {
    return amount;
  }


  public void setAmount(List<InlineResponse2003Balances> amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CosmosGovV1beta1Deposit cosmosGovV1beta1Deposit = (CosmosGovV1beta1Deposit) o;
    return Objects.equals(this.proposalId, cosmosGovV1beta1Deposit.proposalId) &&
        Objects.equals(this.depositor, cosmosGovV1beta1Deposit.depositor) &&
        Objects.equals(this.amount, cosmosGovV1beta1Deposit.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(proposalId, depositor, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CosmosGovV1beta1Deposit {\n");
    sb.append("    proposalId: ").append(toIndentedString(proposalId)).append("\n");
    sb.append("    depositor: ").append(toIndentedString(depositor)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
