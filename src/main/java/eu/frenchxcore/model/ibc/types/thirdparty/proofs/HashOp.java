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
package eu.frenchxcore.model.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * State defines if a channel is in one of the following states: CLOSED, INIT,
 * TRYOPEN, OPEN or UNINITIALIZED.
 */
public enum HashOp {
    /**
     * NO_HASH is the default if no data passed. Note this is an illegal
     * argument some places.
     */
    NO_HASH(0),
    SHA256(1),
    SHA512(2),
    KECCAK(3),
    RIPEMD160(4),
    /**
     * ripemd160(sha256(x))
     */
    BITCOIN(5);

    public final int iVal;

    @JsonValue
    public final String sName;

    HashOp(int iVal) {
        this.iVal = iVal;
        this.sName = this.name();
    }
}
