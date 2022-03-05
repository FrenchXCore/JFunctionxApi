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
package eu.frenchxcore.messages.ibc.types.thirdparty.proofs;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * State defines if a channel is in one of the following states: CLOSED, INIT,
 * TRYOPEN, OPEN or UNINITIALIZED.
 */
public enum LengthOp {
    /**
     * NO_PREFIX don't include any length info
     */
    NO_PREFIX(0),

    /**
     * VAR_PROTO uses protobuf (and go-amino) varint encoding of the length
     */
    VAR_PROTO(1),

    /**
     * VAR_RLP uses rlp int encoding of the length
     */
    VAR_RLP(2),

    /**
     * FIXED32_BIG uses big-endian encoding of the length as a 32 bit integer
     */
    FIXED32_BIG(3),

    /**
     * FIXED32_LITTLE uses little-endian encoding of the length as a 32 bit integer
     */
    FIXED32_LITTLE(4),

    /**
     * FIXED64_BIG uses big-endian encoding of the length as a 64 bit integer
     */
    FIXED64_BIG(5),

    /**
     * FIXED64_LITTLE uses little-endian encoding of the length as a 64 bit integer
     */
    FIXED64_LITTLE(6),

    /**
     * REQUIRE_32_BYTES is like NONE, but will fail if the input is not exactly 32 bytes (sha256 output)
     */
    REQUIRE_32_BYTES(7),

    /**
     * REQUIRE_64_BYTES is like NONE, but will fail if the input is not exactly 64 bytes (sha512 output)
     */
    REQUIRE_64_BYTES(8);

    public final int iVal;

    @JsonValue
    public final String sName;

    LengthOp(int iVal) {
        this.iVal = iVal;
        this.sName = this.name();
    }
}
