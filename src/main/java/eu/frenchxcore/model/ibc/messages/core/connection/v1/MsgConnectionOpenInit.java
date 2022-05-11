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
import eu.frenchxcore.model.ibc.types.core.connection.v1.Counterparty;
import eu.frenchxcore.model.ibc.types.core.connection.v1.Version;

import java.math.BigInteger;

/**
 * MsgConnectionOpenInit defines the msg sent by an account on Chain A to
 * initialize a connection with Chain B.
 */
@JsonTypeName("/ibc.core.connection.v1.MsgConnectionOpenInit")
public class MsgConnectionOpenInit extends BaseMessage {

    @JsonProperty("client_id")
    public String clientId;

    @JsonProperty("counterparty")
    public Counterparty counterparty;

    @JsonProperty("version")
    public Version version;

    @JsonProperty("delay_period")
    public BigInteger delayPeriod;

    @JsonProperty("signer")
    public String signer;

}
