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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sf.denumerous.AllPairsCombinationGenerator2;
import com.sf.denumerous.Parameter;
import com.sf.denumerous.ParameterValue;
import com.sf.denumerous.TestCombination;
import com.sf.denumerous.TestCombinationList;
import com.sf.denumerous.TestCombinationOutputCreator;
import com.sf.denumerous.TestCombinationValue;
import com.sf.denumerous.ValuePair;

public class TestCombinationOutputCreatorTest
{	
	private AllPairsCombinationGenerator2 APCG2;
	private Parameter[] parameters;
	private TestCombinationList tcl;
	
	private int numParameters = 4;
	private int numValues = 6;
	
	@Before
	public void setUp() throws Exception
	{
		APCG2 = new AllPairsCombinationGenerator2();
		parameters = makeParameterArr();
	}
	
	private Parameter[] makeParameterArr()
	{
		Parameter[] paramArr = new Parameter[numParameters];
		
		for(int i=0; i<paramArr.length; i++)
		{
			paramArr[i] = makeParameter(i);
		}
		
		return paramArr;
	}
	
	private Parameter makeParameter(int index)
	{
		int letter = index + 65;
		char c = (char) letter;
		String s = ""+c;
		
		ParameterValue[] paramValArr = makeParamValArr(index);
		
		Parameter p = new Parameter(s ,paramValArr);
		
		return p;
	}
	
	private ParameterValue[] makeParamValArr(int index)
	{
		ParameterValue[] paramValArr = new ParameterValue[numValues];
		
		for(int i=0; i<paramValArr.length; i++)
		{
			paramValArr[i] = makeParameterValue(index, i+1);
		}
		
		return paramValArr;
	}
	
	private ParameterValue makeParameterValue(int index, int n)
	{
		int letter = index + 97;
		char c = (char) letter;
		String s = ""+c+n;
		ParameterValue pval = new ParameterValue(s);
		
		return pval;
	}

	//***********************************TESTS********************************************
	 
	 @Test
	 public void testToXMLDocument() throws XPathExpressionException
	 {
		tcl = APCG2.generateCombinations(parameters);
	    
		Document doc = new TestCombinationOutputCreator(tcl).toXMLDocument(false);
	    
	    XPath evaluator = XPathFactory.newInstance().newXPath();
	    
	    evaluator.evaluate("/TestCombinations/TestCombination/@id='1'", doc);
	    evaluator.evaluate("/TestCombinations/TestCombination[@id='1']/Parameter[3]/@name='C'", doc);
	}
	
	@Test
	public void testToXMLDocumentToWriter()
	{
		tcl = APCG2.generateCombinations(parameters);
		
		TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(tcl);
		
		StringWriter writer = new StringWriter();
		
		outputCreator.toXMLDocument(writer, false);
		
		String xmlString = writer.toString();
		
		assertTrue(xmlString.contains("?xml"));
		assertTrue(xmlString.contains("<TestCombinations>"));
		assertTrue(xmlString.contains("</TestCombinations>"));
	}
	 
	 @Test
	 public void testOutputWithNoCombinations()
	 {
		 TestCombinationList empty = new TestCombinationList();
		 
		 TestCombinationOutputCreator oc = new TestCombinationOutputCreator(empty);
		 
		 Document doc = oc.toXMLDocument(false);
		 
		 NodeList root = doc.getChildNodes();
		 
		 assertNotNull(root);
		 
		 Node n = root.item(0);
	     NodeList children = n.getChildNodes();
	     
		 assertEquals(0, children.getLength());
	 }
	 
	 @Test
	 public void testXMLOutputToFile() throws IOException
	 {
		tcl = APCG2.generateCombinations(parameters);
			
		TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(tcl);
			
		FileWriter writer = new FileWriter("Output\\4x6.xml", false);
			
		outputCreator.toXMLDocument(writer, false);
			
		FileReader reader = new FileReader("Output\\4x6.xml");
		
		BufferedReader buffReader = new BufferedReader(reader);
		
		String xmlString = buffReader.readLine();
		
		assertTrue(xmlString.contains("?xml"));
		assertTrue(xmlString.contains("<TestCombinations>"));
		assertTrue(xmlString.contains("</TestCombinations>"));
	 }
	 
	 @Test
	 public void testToCSVFile()
	 {
		 tcl = APCG2.generateCombinations(parameters);
		 
		 TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(tcl);
		 
		 StringWriter w = new StringWriter();
		 
		 outputCreator.toCSVFile(w);
		 
		 String result = w.toString();
		 
		 assertTrue(result.contains("A,B,C,D"));
	 }
	 
	 //*********************************END TESTS***************************************
	
	protected boolean containsPair(String name1, String val1, String name2, String val2)
	{
		TestCombinationValue tcv1 = new TestCombinationValue(name1, val1);
		TestCombinationValue tcv2 = new TestCombinationValue(name2, val2);
		
		ValuePair vp = new ValuePair(tcv1, tcv2);
		
		boolean result = false;
		
		Iterator<String> comboListIterator = tcl.keySet().iterator();
		while(comboListIterator.hasNext())
		{
			String key = (String)comboListIterator.next();
			TestCombination tc = tcl.get(key);
			ValuePair[] tcPairs = tc.getPairs();
			
			for(int i=0; i<tcPairs.length; i++)
			{
				if(vp.toString().equals(tcPairs[i].toString()))
				{
					result = true;
				}
			}
		}
		
		return result;
	}

	protected void displayParameters()
	{
		for(int i=0; i<parameters.length; i++)
		{
			ParameterValue[] paramVals = parameters[i].getValues();
			for(int j=0; j<paramVals.length; j++)
			{
				System.out.print("Parameter: " + parameters[i].getName());
				System.out.println(" Value: " + paramVals[j].getValue());
			}
		}
	}
	
	protected void displayCombinations()
	{
		Iterator<String> comboListIterator = tcl.keySet().iterator();
		
		int count = 1;
		while(comboListIterator.hasNext()) {
			System.out.println(count + ": " + comboListIterator.next());
			count++;
		}
	}
	
	protected void displayTime(long elapsedTimeMillis)
	{
		//---Get elapsed time in seconds---
	    float elapsedTimeSec = elapsedTimeMillis/(1000F);
	    System.out.println("Elapsed Time: " + elapsedTimeSec + " seconds.");
	    
	    //---Get elapsed time in minutes---
	    //float elapsedTimeMin = elapsedTimeMillis/(60*1000F);
		//System.out.println("Elapsed Time: " + elapsedTimeMin + " minutes.");
	}
}
