package eu.frenchxcore.cosmossdk.types.slashing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.math.BigDecimal;
import java.math.BigInteger;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName(value = "params")
public class SlashingParams {

    @JsonProperty("signed_blocks_window")
    public BigInteger signedBlocksWindow;
    
    @JsonProperty("min_signed_per_window")
    public BigDecimal minSignedPerWindow;
    
    @JsonProperty("downtime_jail_duration")
    public String downtimeJailDuration;
    
    @JsonProperty("slash_fraction_double_sign")
    public BigDecimal slashFractionDoubleSign;
    
    @JsonProperty("slash_fraction_downtime")
    public BigDecimal slashFractionDowntime;
    
}
