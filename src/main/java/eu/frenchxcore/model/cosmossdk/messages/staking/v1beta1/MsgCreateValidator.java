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
package eu.frenchxcore.model.cosmossdk.messages.staking.v1beta1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.frenchxcore.model.BaseMessage;
import eu.frenchxcore.model.cosmossdk.types.base.PublicKey;
import eu.frenchxcore.model.cosmossdk.types.base.v1beta1.Coin;
import eu.frenchxcore.model.cosmossdk.types.staking.CommissionRates;
import eu.frenchxcore.model.cosmossdk.types.staking.Description;

import java.math.BigInteger;

/**
 *
 */
@JsonTypeName("/cosmos.staking.v1beta1.MsgCreateValidator")
public class MsgCreateValidator extends BaseMessage {
    
    @JsonProperty("description")
    public Description description;
    
    @JsonProperty("commission")
    public CommissionRates commission;
    
    @JsonProperty("min_self_delegation")
    public BigInteger minSelfDelegation;
    
    @JsonProperty("delegator_address")
    public String delegatorAddress;
    
    @JsonProperty("validator_address")
    public String validatorAddress;
    
    @JsonProperty("pubkey")
    public PublicKey pubkey;
    
    @JsonProperty("value")
    public Coin value;
    
}