package eu.frenchxcore.model.ibc.types.core.channel.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.model.ibc.types.core.client.v1.Height;

import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Packet {

    /**
     * 
     */
    @JsonProperty("sequence")
    public BigInteger sequence;
    
    /**
     * 
     */
    @JsonProperty("source_port")
    public String sourcePort;

    /**
     * 
     */
    @JsonProperty("source_channel")
    public String sourceChannel;

    /**
     * 
     */
    @JsonProperty("destination_port")
    public String destinationPort;

    /**
     * 
     */
    @JsonProperty("destination_channel")
    public String destinationChannel;

    /**
     * 
     */
    @JsonProperty("data")
    public String data;

    /**
     * 
     */
    @JsonProperty("timeout_height")
    public Height timeoutHeight;

    /**
     * 
     */
    @JsonProperty("timeout_timestamp")
    public BigInteger timeoutTimestamp;

}