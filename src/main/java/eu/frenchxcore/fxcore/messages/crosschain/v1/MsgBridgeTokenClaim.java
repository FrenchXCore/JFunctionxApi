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
package eu.frenchxcore.fxcore.messages.crosschain.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

/**
 *
 */
public class MsgBridgeTokenClaim {
    
    @JsonProperty("event_nonce")
    public BigInteger eventNonce;
    
    @JsonProperty("block_height")
    public BigInteger blockHeight;
    
    @JsonProperty("token_contract")
    public String tokenContract;
    
    @JsonProperty("name")
    public String name;
    
    @JsonProperty("symbol")
    public String symbol;
    
    @JsonProperty("decimals")
    public BigInteger decimals;
    
    @JsonProperty("orchestrator")
    public String orchestrator;
    
    /**
     * Bridge Token channel IBC
     */
    @JsonProperty("channel_ibc")
    public String channelIBC;
    
    @JsonProperty("chain_name")
    public String chainName;
    
}