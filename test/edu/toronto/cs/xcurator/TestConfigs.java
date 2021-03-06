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
package edu.toronto.cs.xcurator;

import edu.toronto.cs.xcurator.common.RdfUriConfig;

/**
 *
 * @author ekzhu
 */
public class TestConfigs {

    public static RdfUriConfig testRdfUriConfig() {
        return new RdfUriConfig() {

            @Override
            public String getResourceUriBase() {
                return "http://example.org/resource";
            }

            @Override
            public String getTypeResourceUriBase() {
                return "http://example.org/resource/class";
            }

            @Override
            public String getPropertyResourceUriBase() {
                return "http://example.org/resource/property";
            }

            @Override
            public String getTypeResourcePrefix() {
                return "class";
            }

            @Override
            public String getPropertyResourcePrefix() {
                return "property";
            }
        };
    }
}
