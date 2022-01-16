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
package eu.frenchxcore.ibc.messages.applications.transfer.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.frenchxcore.BaseMessage;
import eu.frenchxcore.cosmossdk.types.Coin;
import eu.frenchxcore.ibc.types.core.client.v1.Height;
import java.math.BigInteger;
import java.util.List;

/**
 * MsgTransfer defines a msg to transfer fungible tokens (i.e Coins) between
 * ICS20 enabled chains. See ICS Spec here:
 * https://github.com/cosmos/ibc/tree/master/spec/app/ics-020-fungible-token-transfer#data-structures
 */
@JsonTypeName("/ibc.applications.transfer.v1.MsgTransfer")
public class MsgTransfer extends BaseMessage {

    /**
     * the port on which the packet will be sent
     */
    @JsonProperty("source_port")
    public String sourcePort;

    /**
     * the channel by which the packet will be sent
     */
    @JsonProperty("source_channel")
    public String sourceChannel;

    /**
     * the tokens to be transferred
     */
    @JsonProperty("token")
    public List<Coin> token;

    /**
     * the sender address
     */
    @JsonProperty("sender")
    public String sender;

    /**
     * the recipient address on the destination chain
     */
    @JsonProperty("receiver")
    public String receiver;

    /**
     * Timeout height relative to the current block height. The timeout is disabled when set to 0.
     */
    @JsonProperty("timeout_height")
    public Height timeoutHeight;

    /**
     * Timeout timestamp (in nanoseconds) relative to the current block timestamp.
     * The timeout is disabled when set to 0.
     */
    @JsonProperty("timeout_timestamp")
    public BigInteger timeoutTimestamp;

}