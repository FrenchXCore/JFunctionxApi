package eu.frenchxcore.cosmossdk.types.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import eu.frenchxcore.cosmossdk.types._customdeserializer.PublicKeyDeserializer;

@JsonDeserialize(using = PublicKeyDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicKey {

    @JsonProperty("type")
    public String type;

    @JsonProperty("key")
    public byte[] key;

    @JsonProperty("b64")
    public String b64;

    @JsonProperty("b64_decoded")
    public byte[] b64decoded;

}
