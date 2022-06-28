/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package eu.frenchxcore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Message is the abstract JSON message type to deserialize all message types.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "@type",
        visible = true,
        defaultImpl = DefaultMessage.class
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.bank.v1beta1.MsgSend.class, name = "/cosmos.bank.v1beta1.MsgSend"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.bank.v1beta1.MsgMultiSend.class, name = "/cosmos.bank.v1beta1.MsgMultiSend"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.distribution.v1beta1.MsgFundCommunityPool.class, name = "/cosmos.distribution.v1beta1.MsgFundCommunityPool"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.distribution.v1beta1.MsgSetWithdrawAddress.class, name = "/cosmos.distribution.v1beta1.MsgSetWithdrawAddress"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.distribution.v1beta1.MsgWithdrawDelegatorReward.class, name = "/cosmos.distribution.v1beta1.MsgWithdrawDelegatorReward"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.distribution.v1beta1.MsgWithdrawValidatorCommission.class, name = "/cosmos.distribution.v1beta1.MsgWithdrawValidatorCommission"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.gov.v1beta1.MsgDeposit.class, name = "/cosmos.gov.v1beta1.MsgDeposit"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.gov.v1beta1.MsgSubmitProposal.class, name = "/cosmos.gov.v1beta1.MsgSubmitProposal"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.gov.v1beta1.MsgVote.class, name = "/cosmos.gov.v1beta1.MsgVote"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.gov.v1beta1.MsgVoteWeighted.class, name = "/cosmos.gov.v1beta1.MsgVoteWeighted"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.nft.v1beta1.MsgSend.class, name = "/cosmos.nft.v1beta1.MsgSend"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.slashing.v1beta1.MsgUnjail.class, name = "/cosmos.slashing.v1beta1.MsgUnjail"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.staking.v1beta1.MsgBeginRedelegate.class, name = "/cosmos.staking.v1beta1.MsgBeginRedelegate"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.staking.v1beta1.MsgCreateValidator.class, name = "/cosmos.staking.v1beta1.MsgCreateValidator"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.staking.v1beta1.MsgDelegate.class, name = "/cosmos.staking.v1beta1.MsgDelegate"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.staking.v1beta1.MsgEditValidator.class, name = "/cosmos.staking.v1beta1.MsgEditValidator"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.cosmossdk.messages.staking.v1beta1.MsgUndelegate.class, name = "/cosmos.staking.v1beta1.MsgUndelegate"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgAddOracleDeposit.class, name = "/fx.gravity.crosschain.v1.MsgAddOracleDeposit"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgBridgeTokenClaim.class, name = "/fx.gravity.crosschain.v1.MsgBridgeTokenClaim"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgCancelSendToExternal.class, name = "/fx.gravity.crosschain.v1.MsgCancelSendToExternal"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgConfirmBatch.class, name = "/fx.gravity.crosschain.v1.MsgConfirmBatch"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgOracleSetConfirm.class, name = "/fx.gravity.crosschain.v1.MsgOracleSetConfirm"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgOracleSetUpdatedClaim.class, name = "/fx.gravity.crosschain.v1.MsgOracleSetUpdatedClaim"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgRequestBatch.class, name = "/fx.gravity.crosschain.v1.MsgRequestBatch"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgSendToExternal.class, name = "/fx.gravity.crosschain.v1.MsgSendToExternal"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgSendToExternalClaim.class, name = "/fx.gravity.crosschain.v1.MsgSendToExternalClaim"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgSendToFxClaim.class, name = "/fx.gravity.crosschain.v1.MsgSendToFxClaim"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.crosschain.v1.MsgSetOrchestratorAddress.class, name = "/fx.gravity.crosschain.v1.MsgSetOrchestratorAddress"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgCancelSendToEth.class, name = "/fx.gravity.v1.MsgCancelSendToEth"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgConfirmBatch.class, name = "/fx.gravity.v1.MsgConfirmBatch"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgDepositClaim.class, name = "/fx.gravity.v1.MsgDepositClaim"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgFxOriginatedTokenClaim.class, name = "/fx.gravity.v1.MsgFxOriginatedTokenClaim"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgRequestBatch.class, name = "/fx.gravity.v1.MsgRequestBatch"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgSendToEth.class, name = "/fx.gravity.v1.TxendToEth"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgSetOrchestratorAddress.class, name = "/fx.gravity.v1.TxetOrchestratorAddress"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgValsetConfim.class, name = "/fx.gravity.v1.MsgValsetConfirm"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgValsetUpdatedClaim.class, name = "/fx.gravity.v1.MsgValsetUpdatedClaim"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.gravity.v1.MsgWithdrawClaim.class, name = "/fx.gravity.v1.MsgWithdrawClaim"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.fxcore.messages.ibc.applications.transfer.v1.MsgTransfer.class, name = "/fx.ibc.applications.transfer.v1.MsgTransfer"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.applications.transfer.v1.MsgTransfer.class, name = "/ibc.applications.transfer.v1.MsgTransfer"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgAcknowledgement.class, name = "/ibc.core.channel.v1.MsgAcknowledgement"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgChannelCloseConfirm.class, name = "/ibc.core.channel.v1.MsgChannelCloseConfirm"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgChannelCloseInit.class, name = "/ibc.core.channel.v1.MsgChannelCloseInit"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgChannelOpenAck.class, name = "/ibc.core.channel.v1.MsgChannelOpenAck"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgChannelOpenConfirm.class, name = "/ibc.core.channel.v1.MsgChannelOpenConfirm"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgChannelOpenInit.class, name = "/ibc.core.channel.v1.MsgChannelOpenInit"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgChannelOpenTry.class, name = "/ibc.core.channel.v1.MsgChannelOpenTry"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgRecvPacket.class, name = "/ibc.core.channel.v1.MsgRecvPacket"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgTimeout.class, name = "/ibc.core.channel.v1.MsgTimeout"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.channel.v1.MsgTimeoutOnClose.class, name = "/ibc.core.channel.v1.MsgTimeoutOnClose"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.client.v1.MsgCreateClient.class, name = "/ibc.core.client.v1.MsgCreateClient"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.client.v1.MsgSubmitMisbehaviour.class, name = "/ibc.core.client.v1.MsgSubmitMisbehaviour"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.client.v1.MsgUpdateClient.class, name = "/ibc.core.client.v1.MsgUpdateClient"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.client.v1.MsgUpgradeClient.class, name = "/ibc.core.client.v1.MsgUpgradeClient"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.connection.v1.MsgConnectionOpenAck.class, name = "/ibc.core.connection.v1.MsgConnectionOpenAck"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.connection.v1.MsgConnectionOpenConfirm.class, name = "/ibc.core.connection.v1.MsgConnectionOpenConfirm"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.connection.v1.MsgConnectionOpenInit.class, name = "/ibc.core.connection.v1.MsgConnectionOpenInit"),
    @JsonSubTypes.Type(value = eu.frenchxcore.model.ibc.messages.core.connection.v1.MsgConnectionOpenTry.class, name = "/ibc.core.connection.v1.MsgConnectionOpenTry")
})

public class BaseMessage {

    @JsonProperty("@type")
    public String type;
    
}