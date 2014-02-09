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
package edu.toronto.cs.xcurator.rdf;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.vocabulary.RDF;
import edu.toronto.cs.xcurator.mapping.Mapping;
import edu.toronto.cs.xcurator.mapping.XmlBasedMapping;
import edu.toronto.cs.xcurator.xml.ElementIdGenerator;
import edu.toronto.cs.xcurator.xml.XPathFinder;
import edu.toronto.cs.xcurator.xml.XmlParser;
import java.io.File;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author zhuerkan
 */
public class RdfGenerationTest {

  private RdfGeneration step;
  private XmlBasedMappingDeserialization mappingDeserialization;
  private Mapping mapping;
  private String testTdbDir;

  @Rule
  public TemporaryFolder testTdbFolder = new TemporaryFolder();

  @Before
  public void setup() {
    // Setup deserializer
    mappingDeserialization = new XmlBasedMappingDeserialization(
            RdfGenerationTest.class.getResourceAsStream(
                    "/secxbrls/mapping/fb-20121231-mapping.xml"),
            new XmlParser());
    mapping = new XmlBasedMapping();
    
    // Use temporary directory for setting up testing TDB
    File testTdb = testTdbFolder.newFolder("testTdb");
    testTdbDir = testTdb.getAbsolutePath();
    step = new RdfGeneration(testTdbDir,
            RdfGenerationTest.class.getResourceAsStream(
                    "/secxbrls/data/fb-20121231.xml"), new XmlParser(), 
            new XPathFinder(), new ElementIdGenerator());
  }
  
  @Test
  public void test_process() {
    // Deserialize the mapping first
    mappingDeserialization.process(mapping);
    Assert.assertTrue(mapping.isInitialized());
    
    // Generate RDFs
    step.process(mapping);
    
    // Verify
    Model model = TDBFactory.createModel(testTdbDir);
    Assert.assertFalse("No RDF was generated. TDB directory: " + testTdbDir, model.isEmpty());
    
    ResIterator iter = model.listResourcesWithProperty(RDF.type);
    while (iter.hasNext()) {
      Resource resource = iter.nextResource();
      System.out.println(resource.getLocalName());
      StmtIterator iterStm = resource.listProperties();
      while (iterStm.hasNext()) {
        System.out.println(iterStm.nextStatement().toString());
      }
    }
  }
}
