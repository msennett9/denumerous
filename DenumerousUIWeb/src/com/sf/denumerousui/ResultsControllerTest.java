/*
 * Copyright 2008 State Farm Insurance
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sf.denumerousui;

import static org.junit.Assert.*;

import java.io.StringWriter;

import org.junit.Before;
import org.junit.Test;

import com.sf.denumerous.TestCombinationList;

public class ResultsControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGenerate()
	{
		String file = "A\n\ta1\n\ta2\n\ta3\nB\n\tb1\n\tb2\nC\n\tc1\n\tc2\n\tc3";
		
		//TODO - remove dependency on generator.properties
		String generator = "allpairs1";
		
		ResultsController r = new ResultsController(file, generator);
		
		TestCombinationList combinations = r.generateCombinations();
		
		assertTrue(combinations.size() > 1);
		assertTrue(combinations.size() < 20);
	}
	
	@Test
	public void testToWeb()
	{
		String file = "A\n\ta1\n\ta2\n\ta3\nB\n\tb1\n\tb2\nC\n\tc1\n\tc2\n\tc3";
		String generator = "allpairs1";
		
		ResultsController r = new ResultsController(file, generator);
	    
		StringWriter writer = new StringWriter();
		
		TestCombinationList combinations = r.generateCombinations();
		
		r.convertResultsToHtml(combinations, writer, "WebContent/combinationsToHtml.xsl");
		
		String xmlString = writer.toString();
		
		assertTrue(xmlString.contains("<table border=\"1\" align=\"center\"><tr bgcolor=\"#9acd32\">"));
		assertTrue(xmlString.contains("</table>"));
	}
	
	@Test
	public void testToXMLFile()
	{
		String file = "A\n\ta1\n\ta2\n\ta3\nB\n\tb1\n\tb2\nC\n\tc1\n\tc2\n\tc3";
		String generator = "allpairs1";
		
		ResultsController r = new ResultsController(file, generator);
		
		TestCombinationList combinations = r.generateCombinations();
		
		String xmlString = r.convertResultsToXML(combinations);
				
		assertTrue(xmlString.contains("<TestCombinations>"));
		assertTrue(xmlString.contains("</TestCombination>"));
		assertTrue(xmlString.contains("</TestCombinations>"));
	}
	
	@Test
	public void testToCSVFile()
	{
		String file = "A\n\ta1\n\ta2\n\ta3\nB\n\tb1\n\tb2\nC\n\tc1\n\tc2\n\tc3";
		String generator = "allpairs1";
		
		ResultsController r = new ResultsController(file, generator);
		
		TestCombinationList combinations = r.generateCombinations();
		
		String csvString = r.convertResultsToCSV(combinations);
		
		assertTrue(csvString.contains("A,B,C"));
	}

}
