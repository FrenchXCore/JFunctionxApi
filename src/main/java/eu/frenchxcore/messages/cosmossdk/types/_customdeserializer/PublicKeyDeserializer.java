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
package eu.frenchxcore.messages.cosmossdk.types._customdeserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import eu.frenchxcore.messages.cosmossdk.types.base.PublicKey;
import eu.frenchxcore.tools.Bech32;

import java.io.IOException;

/**
 *
 */
public class PublicKeyDeserializer extends StdDeserializer<PublicKey> {
    
    public PublicKeyDeserializer() { 
        this(null); 
    } 

    public PublicKeyDeserializer(Class<?> vc) { 
        super(vc); 
    }

    @Override
    public PublicKey deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        PublicKey pk = new PublicKey();
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("@type").asText();
        String key = node.get("key").asText();
        pk.b64 = key;
        pk.b64decoded = Bech32.base64Decode(key);
        if (type.equalsIgnoreCase("/cosmos.crypto.ed25519.PubKey")) {
            pk.type = type;
            pk.key = Bech32.convertPublicKey(key);
        }
        return pk;
    }

}
