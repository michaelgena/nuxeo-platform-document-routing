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

package org.nuxeo.ecm.restapi.server.jaxrs.routing.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @since 7.1
 */
public class RoutingRequest {

    protected List<String> documentIds = new ArrayList<String>();

    protected Map<String, Serializable> worflowVariables;

    public String routeModelId;

    public RoutingRequest() {
        super();
    }

    public List<String> getDocumentIds() {
        return documentIds;
    }

    public String getRouteModelId() {
        return routeModelId;
    }

    public Map<String, Serializable> getWorflowVariables() {
        return worflowVariables;
    }

    public void setDocumentIds(List<String> documentIds) {
        this.documentIds = documentIds;
    }

    public void setRouteModelId(String routeModelId) {
        this.routeModelId = routeModelId;
    }

    public void setWorflowVariables(Map<String, Serializable> worflowVariables) {
        this.worflowVariables = worflowVariables;
    }

}
