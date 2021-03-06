/*
 *    Copyright (c) 2013, University of Toronto.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License"); you may
 *    not use this file except in compliance with the License. You may obtain
 *    a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 *    License for the specific language governing permissions and limitations
 *    under the License.
 */
package edu.toronto.cs.xcurator.mapping;

import edu.toronto.cs.xcurator.common.NsContext;
import java.util.Iterator;
import java.util.Map;

public interface Mapping {

    /**
     * Check if this mapping is initialized.
     *
     * @return
     */
    boolean isInitialized();

    /**
     * Set this mapping as initialized, return success flag.
     *
     * @return true if successfully initialized, false if not successful.
     */
    boolean setInitialized();

    /**
     * Set the base namespace context of the XML document to be transformed
     * using this mapping. This namespace context can be overrided by individual
     * entities.
     *
     * @param nsContext
     */
    void setBaseNamespaceContext(NsContext nsContext);

    /**
     * Get the base namespace context of the XML document to be transformed
     * using this mapping.
     *
     * @return
     */
    NsContext getBaseNamespaceContext();

    void addEntity(Schema schema);

    Schema getEntity(String id);

    void removeEntity(String id);

    public Map<String, Schema> getEntities();

    Iterator<Schema> getEntityIterator();

    public void removeInvalidRelations();

}
