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
package eu.frenchxcore.messages.ibc.messages.core.connection.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.frenchxcore.messages.BaseMessage;
import eu.frenchxcore.messages.ibc.types.core.client.v1.Height;
import eu.frenchxcore.messages.ibc.types.core.connection.v1.Counterparty;
import eu.frenchxcore.messages.ibc.types.core.connection.v1.Version;
import eu.frenchxcore.messages.ibc.types.lightclients.tendermint.v1.ClientState;
import java.math.BigInteger;
import java.util.List;

/**
 * In the case of crossing hello's, when both chains call OpenInit, we need the
 * connection identifier of the previous connection in state INIT
 */
@JsonTypeName("/ibc.core.connection.v1.MsgConnectionOpenTry")
public class MsgConnectionOpenTry extends BaseMessage {

    @JsonProperty("client_id")
    public String clientId;

    /**
     * in the case of crossing hello's, when both chains call OpenInit, we need
     * the connection identifier of the previous connection in state INIT
     */
    @JsonProperty("previous_connection_id")
    public String previousConnectionId;

    @JsonProperty("client_state")
    public ClientState clientState;

    @JsonProperty("counterparty")
    public Counterparty counterparty;

    @JsonProperty("delay_period")
    public BigInteger delayPeriod;

    @JsonProperty("counterparty_versions")
    public List<Version> counterpartyVersions;

    @JsonProperty("proof_height")
    public Height proofHeight;

    /**
     * proof of the initialization the connection on Chain A: `UNITIALIZED ->
     * INIT`
     */
    @JsonProperty("proof_init")
    public String proofInit;

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