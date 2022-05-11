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
package eu.frenchxcore.model.ibc.messages.core.channel.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.frenchxcore.model.BaseMessage;
import eu.frenchxcore.model.ibc.types.core.client.v1.Height;

import java.nio.channels.Channel;

/**
 * MsgChannelOpenInit defines a msg sent by a Relayer to try to open a channel
 * on Chain B. The version field within the Channel field has been deprecated.
 * Its value will be ignored by core IBC.
 */
@JsonTypeName("/ibc.core.channel.v1.MsgChannelOpenTry")
public class MsgChannelOpenTry extends BaseMessage {

    @JsonProperty("port_id")
    public String portId;

    /**
     * in the case of crossing hello's, when both chains call OpenInit, we need
     * the channel identifier of the previous channel in state INIT
     */
    @JsonProperty("previous_channel_id")
    public String previousChannelId;

    /**
     * NOTE: the version field within the channel has been deprecated. Its value will be ignored by core IBC.
     */
    @JsonProperty("channel")
    public Channel channel;

    @JsonProperty("counterparty_version")
    public String counterpartyVersion;

    @JsonProperty("proof_init")
    public String proof_init;

    @JsonProperty("proof_height")
    public Height proofHeight;

    @JsonProperty("signer")
    public String signer;

}
