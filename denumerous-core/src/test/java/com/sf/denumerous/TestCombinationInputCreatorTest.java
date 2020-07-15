/*
 * Copyright 2008 State Farm Insurance Co.
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

package com.sf.denumerous;

import static org.junit.Assert.*;

import java.io.StringWriter;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class TestCombinationInputCreatorTest
{
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testFromFile() throws XPathExpressionException
	{
		String fileName = "Input/3x3.txt";
		
		TestCombinationInputCreator inputCreator = new TestCombinationInputCreator();
		
		Parameter[] params = inputCreator.fromFile(fileName);
		
		AllPairsCombinationGenerator2 apcg2 = new AllPairsCombinationGenerator2();
		
		TestCombinationList combinations = apcg2.generateCombinations(params);
		
		TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(combinations);
		
		Document doc = outputCreator.toXMLDocument(false);
		XPath evaluator = XPathFactory.newInstance().newXPath();
	    
	    assertEquals("true", evaluator.evaluate("/TestCombinations/TestCombination/@id='1'", doc));
	    assertEquals("true", evaluator.evaluate("/TestCombinations/TestCombination[@id='1']/Parameter[3]/@name='C'", doc));
	}

	@Test
	public void testFromString()
	{
		String file = "A\n\ta1\n\ta2\n\ta3\nB\n\tb1\n\tb2\nC\n\tc1\n\tc2\n\tc3";
		
		TestCombinationInputCreator inputCreator = new TestCombinationInputCreator();
		
		Parameter[] params = inputCreator.fromString(file);
		
		AllPairsCombinationGenerator2 apcg2 = new AllPairsCombinationGenerator2();
		
		TestCombinationList combinations = apcg2.generateCombinations(params);
		
		TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(combinations);
	    
		StringWriter writer = new StringWriter();
		
		outputCreator.toXMLDocument(writer, false);
		
		String xmlString = writer.toString();
		
		assertTrue(xmlString.contains("?xml"));
		assertTrue(xmlString.contains("<TestCombinations>"));
		assertTrue(xmlString.contains("</TestCombinations>"));
	}
	
	public void displayParameters(Parameter[] params)
	{
		for(int i=0; i<params.length; i++)
		{
			System.out.println(params[i].getName());
			ParameterValue[] vals = params[i].getValues();
			for(int j=0; j<vals.length; j++)
			{
				System.out.println("  " + vals[j].getValue());
			}
		}
	}
}
