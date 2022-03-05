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
package eu.frenchxcore.messages.cosmossdk.messages.bank.v1beta1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.frenchxcore.messages.BaseMessage;
import eu.frenchxcore.messages.cosmossdk.types.bank.Input;
import eu.frenchxcore.messages.cosmossdk.types.bank.Output;
import java.util.List;

/**
 *
 */
@JsonTypeName("/cosmos.bank.v1beta1.MsgMultiSend")
public class MsgMultiSend extends BaseMessage {
    
    @JsonProperty("inputs")
    public List<Input> inputs;
    
    @JsonProperty("outputs")
    public List<Output> outputs;
    
}
