package eu.frenchxcore.fxcore.types.gravity.crosschain.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.frenchxcore.cosmossdk.types.base.v1beta1.Coin;

import java.util.List;

public class InitCrossChainParamsProposalParams {

    @JsonProperty("gravity_id")
    public String gravityId;

    @JsonProperty("average_block_time")
    public String averageBlockTime;

    @JsonProperty("external_batch_timeout")
    public String externalBatchTimeout;

    @JsonProperty("average_external_block_time")
    public String averageExternalBlockTime;

    @JsonProperty("signed_window")
    public String signedWindow;

    @JsonProperty("slash_fraction")
    public String slashFraction;

    @JsonProperty("oracle_set_update_power_change_percent")
    public String oracleSetUpdatePowerChangePercent;

    @JsonProperty("ibc_transfer_timeout_height")
    public String ibcTransferTimeoutHeight;

    @JsonProperty("oracles")
    public List<String> oracles;

    @JsonProperty("deposit_threshold")
    public Coin depositThreshold;

}
