package eu.frenchxcore.cosmossdk.types._base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidatorSigningInfo {

    @JsonProperty("address")
    public String address;
    
    @JsonProperty("start_height")
    public BigInteger startHeight;
    
    @JsonProperty("index_offset")
    public BigInteger indexOffset;
    
    @JsonProperty("jailed_until")
    public Instant jailedUntil;
    
    @JsonProperty("tombstoned")
    public Boolean tombstoned;
    
    @JsonProperty("missed_blocks_counter")
    public BigInteger missedBlocksCounter;
    
}