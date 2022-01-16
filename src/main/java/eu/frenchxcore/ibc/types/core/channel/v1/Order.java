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
package eu.frenchxcore.ibc.types.core.channel.v1;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Order defines if a channel is ORDERED or UNORDERED
 */
public enum Order {
    /**
     * zero-value for channel ordering
     */
    ORDER_NONE_UNSPECIFIED(0, "NONE"),

    /**
     * packets can be delivered in any order, which may differ from the order in which they were sent.
     */
    ORDER_UNORDERED(1, "UNORDERED"),

    /**
     * packets are delivered exactly in the order which they were sent
     */
    ORDER_ORDERED(2, "ORDERED");

    public int iVal;
    public String sVal;
    
    @JsonValue
    public String sName;
    
    Order(int iVal, String sVal) {
        this.iVal = iVal;
        this.sVal = sVal;
        this.sName = this.name();
    }
}
