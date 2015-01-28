/*
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     <a href="mailto:grenard@nuxeo.com">Guillaume Renard</a>
 *
 */

package org.nuxeo.ecm.restapi.server.jaxrs.routing.io.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.CoreSession;

/**
 * @since 7.2
 */
public class JsonEncodeDecodeUtils {

    public static void encodeBlob(Blob blob, JsonGenerator jg, ServletRequest request) throws JsonGenerationException,
            IOException {
        if (blob == null) {
            jg.writeNull();
            return;
        }
        jg.writeStartObject();
        String v = blob.getFilename();
        if (v == null) {
            jg.writeNullField("name");
        } else {
            jg.writeStringField("name", v);
        }
        v = blob.getMimeType();
        if (v == null) {
            jg.writeNullField("mime-type");
        } else {
            jg.writeStringField("mime-type", v);
        }
        v = blob.getEncoding();
        if (v == null) {
            jg.writeNullField("encoding");
        } else {
            jg.writeStringField("encoding", v);
        }
        v = blob.getDigest();
        if (v == null) {
            jg.writeNullField("digest");
        } else {
            jg.writeStringField("digest", v);
        }
        jg.writeStringField("length", Long.toString(blob.getLength()));

        // TODO encode data url

        jg.writeEndObject();
    }

    public static Map<String, Serializable> decodeVariables(JsonNode jsnode,
            Map<String, Serializable> originalVariables, CoreSession session) throws ClassNotFoundException,
            IOException {
        Map<String, Serializable> variables = new HashMap<String, Serializable>();
        Iterator<Entry<String, JsonNode>> it = jsnode.getFields();
        while (it.hasNext()) {
            Entry<String, JsonNode> variable = it.next();
            String key = variable.getKey();
            JsonNode value = variable.getValue();
            /*
             * Serializable originalValue = node.getVariables().get(key); Serializable readValue = null; if
             * (originalValue instanceof List) { readValue = jsnode.traverse().readValueAs(ArrayList.class); } else if
             * (value.isTextual()) { readValue = jsnode.getTextValue(); } else if (jsnode.isNumber()) { readValue =
             * jsnode.getNumberValue(); } else if (originalValue instanceof Boolean) { readValue =
             * jsnode.getBooleanValue(); } else { readValue = (Serializable) codecService.read(jsnode.traverse(),
             * Thread.currentThread().getContextClassLoader(), SessionFactory.getSession(request)); } variables.put(key,
             * readValue);
             */

            variables.put(key, value.getTextValue());
        }
        return variables;
    }

    public static void encodeVariableEntry(Entry<String, Serializable> e, JsonGenerator jg, HttpServletRequest request)
            throws JsonGenerationException, IOException {
        if (e.getValue() instanceof Blob) {
            jg.writeFieldName(e.getKey());
            JsonEncodeDecodeUtils.encodeBlob((Blob) e.getValue(), jg, request);
        } else {
            jg.writeObjectField(e.getKey(), e.getValue());
        }
    }

}
