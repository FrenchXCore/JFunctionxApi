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
 * description defines the description terms for the validator.
 */
@ApiModel(description = "description defines the description terms for the validator.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-07-30T14:34:33.051705600+02:00[Europe/Paris]")
public class InlineResponse20041Description {
  public static final String SERIALIZED_NAME_MONIKER = "moniker";
  @SerializedName(SERIALIZED_NAME_MONIKER)
  private String moniker;

  public static final String SERIALIZED_NAME_IDENTITY = "identity";
  @SerializedName(SERIALIZED_NAME_IDENTITY)
  private String identity;

  public static final String SERIALIZED_NAME_WEBSITE = "website";
  @SerializedName(SERIALIZED_NAME_WEBSITE)
  private String website;

  public static final String SERIALIZED_NAME_SECURITY_CONTACT = "security_contact";
  @SerializedName(SERIALIZED_NAME_SECURITY_CONTACT)
  private String securityContact;

  public static final String SERIALIZED_NAME_DETAILS = "details";
  @SerializedName(SERIALIZED_NAME_DETAILS)
  private String details;


  public InlineResponse20041Description moniker(String moniker) {
    
    this.moniker = moniker;
    return this;
  }

   /**
   * moniker defines a human-readable name for the validator.
   * @return moniker
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "moniker defines a human-readable name for the validator.")

  public String getMoniker() {
    return moniker;
  }


  public void setMoniker(String moniker) {
    this.moniker = moniker;
  }


  public InlineResponse20041Description identity(String identity) {
    
    this.identity = identity;
    return this;
  }

   /**
   * identity defines an optional identity signature (ex. UPort or Keybase).
   * @return identity
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "identity defines an optional identity signature (ex. UPort or Keybase).")

  public String getIdentity() {
    return identity;
  }


  public void setIdentity(String identity) {
    this.identity = identity;
  }


  public InlineResponse20041Description website(String website) {
    
    this.website = website;
    return this;
  }

   /**
   * website defines an optional website link.
   * @return website
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "website defines an optional website link.")

  public String getWebsite() {
    return website;
  }


  public void setWebsite(String website) {
    this.website = website;
  }


  public InlineResponse20041Description securityContact(String securityContact) {
    
    this.securityContact = securityContact;
    return this;
  }

   /**
   * security_contact defines an optional email for security contact.
   * @return securityContact
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "security_contact defines an optional email for security contact.")

  public String getSecurityContact() {
    return securityContact;
  }


  public void setSecurityContact(String securityContact) {
    this.securityContact = securityContact;
  }


  public InlineResponse20041Description details(String details) {
    
    this.details = details;
    return this;
  }

   /**
   * details define other optional details.
   * @return details
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "details define other optional details.")

  public String getDetails() {
    return details;
  }


  public void setDetails(String details) {
    this.details = details;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse20041Description inlineResponse20041Description = (InlineResponse20041Description) o;
    return Objects.equals(this.moniker, inlineResponse20041Description.moniker) &&
        Objects.equals(this.identity, inlineResponse20041Description.identity) &&
        Objects.equals(this.website, inlineResponse20041Description.website) &&
        Objects.equals(this.securityContact, inlineResponse20041Description.securityContact) &&
        Objects.equals(this.details, inlineResponse20041Description.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(moniker, identity, website, securityContact, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse20041Description {\n");
    sb.append("    moniker: ").append(toIndentedString(moniker)).append("\n");
    sb.append("    identity: ").append(toIndentedString(identity)).append("\n");
    sb.append("    website: ").append(toIndentedString(website)).append("\n");
    sb.append("    securityContact: ").append(toIndentedString(securityContact)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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

