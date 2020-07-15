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

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ProcessingInstruction;

public class TestCombinationOutputCreator
{
	private TestCombinationList combinations;
	
	protected DocumentBuilderFactory domFactory = null; 
    protected DocumentBuilder domBuilder = null;
	
	public TestCombinationOutputCreator(TestCombinationList combos)
	{
		combinations = combos;
		
		try
		{
			domFactory = DocumentBuilderFactory.newInstance(); 
            domBuilder = domFactory.newDocumentBuilder();
		}
        catch(Exception exp) 
        {
        	throw new RuntimeException(exp);
        }
	}
	
	public Document toXMLDocument(boolean web)
	{
		try
		{
			Document newDoc = domBuilder.newDocument();
			
			if(web)
			{
				//Insert: <?xml-stylesheet type="text/xsl" href="combinationsToHtml.xsl"?>
				ProcessingInstruction template = newDoc.createProcessingInstruction("xml-stylesheet", "type=\"text/xsl\" href=\"combinationsToHtml.xsl\"");
				newDoc.appendChild(template);
			}
			
			//Root element
			Element rootElement = newDoc.createElement("TestCombinations");
			newDoc.appendChild(rootElement);
			
			if(web)
			{
				//---InputParameters---
				//Create InputParameter element
				Element inputParameters = newDoc.createElement("InputParameters");
				rootElement.appendChild(inputParameters);
			
				//Get testCombination
				Iterator<String> it1 = combinations.keySet().iterator();
				String key1 = (String)it1.next();
				TestCombination tc1 = combinations.get(key1);
				TestCombinationValue[] tcValArr1 = tc1.getValues();
				
				//Make Parameter elements
				for(int i=0; i<tcValArr1.length; i++)
				{
					Element parameterName = newDoc.createElement("ParameterName");
					
					String name = tcValArr1[i].getName();
					
					parameterName.setAttribute("value", name);
					
					inputParameters.appendChild(parameterName);
				}
				//---Finished InputParameters
			}
			
			Iterator<String> it = combinations.keySet().iterator();
			
			int id = 1;
			
			while(it.hasNext())
			{	
				//Create TestCombination element
				Element testCombination = newDoc.createElement("TestCombination");
				rootElement.appendChild(testCombination);//right spot? or down after stuff has been added to it?
				
				//Add id attribute
				String idNum = Integer.toString(id);
				testCombination.setAttribute("id", idNum);
				
				//Get testCombination
				String key = (String)it.next();
				TestCombination tc = combinations.get(key);
				TestCombinationValue[] tcValArr = tc.getValues();
				
				//Make Parameter elements
				for(int i=0; i<tcValArr.length; i++)
				{
					Element parameter = newDoc.createElement("Parameter");
					
					String name = tcValArr[i].getName();
					String value = tcValArr[i].getValue();
					
					parameter.setAttribute("name", name);
					parameter.setAttribute("value", value);
					
					testCombination.appendChild(parameter);
				}
				
				id++;
			}
			
			return newDoc;
		}
        catch(Exception exp)
        {
        	throw new RuntimeException(exp);
        }
	}
	
	public void toXMLDocument(Writer w, boolean web)
	{
		Document doc = toXMLDocument(web);
		
		try {
            // Prepare the DOM document for writing
            Source source = new DOMSource(doc);
    
            Result result = new StreamResult(w);
    
            // Write the DOM document to the writer
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);
        }
		catch (Exception e)
		{
			throw new RuntimeException(e);
        }
	}
	
	public void toCSVFile(Writer sWriter)
	{
		try
		{
			PrintWriter pWriter = new PrintWriter(sWriter);
			
			TestCombination[] combinationsArr = combinations.toArray();
			
			//---Write out the Parameter names---
			TestCombination combination = combinationsArr[0];
			TestCombinationValue[] values = combination.getValues();
			for(int i=0; i<values.length; i++)
			{
				pWriter.print(values[i].getName());
				
				if(i != values.length-1)
				{
					pWriter.print(",");
				}
			}
			pWriter.println();
			
			//---Write out all the combinations---
			for(int i=0; i<combinationsArr.length; i++)
			{
				TestCombination currentCombination = combinationsArr[i];
				TestCombinationValue[] currentValues = currentCombination.getValues();
				
				for(int j=0; j<currentValues.length; j++)
				{
					pWriter.print(currentValues[j].getValue());
					
					if(j != currentValues.length-1)
					{
						pWriter.print(",");
					}
				}
				pWriter.println();
			}
			
			pWriter.close();
		}
		catch(Exception ex)
		{
			throw new RuntimeException();
		}
	}
}
