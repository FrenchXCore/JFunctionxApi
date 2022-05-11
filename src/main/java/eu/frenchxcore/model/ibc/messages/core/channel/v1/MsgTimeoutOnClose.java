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
import eu.frenchxcore.model.ibc.types.core.channel.v1.Packet;
import eu.frenchxcore.model.ibc.types.core.client.v1.Height;

import java.math.BigInteger;

/**
 * MsgTimeoutOnClose timed-out packet upon counterparty channel closure.
 */
@JsonTypeName("/ibc.core.channel.v1.MsgTimeoutOnClose")
public class MsgTimeoutOnClose extends BaseMessage {

    @JsonProperty("packet")
    public Packet packet;

    @JsonProperty("proof_unreceived")
    public String proofUnreceived;

    @JsonProperty("proof_close")
    public String proofClose;

    @JsonProperty("proof_height")
    public Height proofHeight;

    @JsonProperty("next_sequence_recv")
    public BigInteger nextSequenceRecv;

    @JsonProperty("signer")
    public String signer;

}
