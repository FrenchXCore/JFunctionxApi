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
package eu.frenchxcore.model.ibc.messages.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.frenchxcore.model.BaseMessage;
import eu.frenchxcore.model.ibc.types.core.client.v1.Height;
import eu.frenchxcore.model.ibc.types.core.connection.v1.Version;
import eu.frenchxcore.model.ibc.types.lightclients.tendermint.v1.ClientState;

/**
 * MsgConnectionOpenAck defines a msg sent by a Relayer to Chain A to
 * acknowledge the change of connection state to TRYOPEN on Chain B.
 */
@JsonTypeName("/ibc.core.connection.v1.MsgConnectionOpenAck")
public class MsgConnectionOpenAck extends BaseMessage {

    @JsonProperty("connection_id")
    public String connectionId;

    @JsonProperty("counterparty_connection_id")
    public String counterpartyConnectionId;

    @JsonProperty("version")
    public Version version;

    @JsonProperty("client_state")
    public ClientState clientState;

    @JsonProperty("proof_height")
    public Height proofHeight;

    /**
     * proof of the initialization the connection on Chain B: `UNITIALIZED -> TRYOPEN`
     */
    @JsonProperty("proof_try")
    public String proofTry;

    /**
     * proof of client state included in message
     */
    @JsonProperty("proof_client")
    public String proofClient;

    /**
     * proof of client consensus state
     */
    @JsonProperty("proof_consensus")
    public String proofConsensus;
    
    @JsonProperty("consensus_height")
    public Height consensusHeight;

    @JsonProperty("signer")
    public String signer;
    
}
