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

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

import com.sf.denumerous.CombinationGeneratorFactory;
import com.sf.denumerous.ITestCombinationGenerator;
import com.sf.denumerous.Parameter;
import com.sf.denumerous.TestCombinationInputCreator;
import com.sf.denumerous.TestCombinationList;
import com.sf.denumerous.TestCombinationOutputCreator;

public class ResultsController
{
	private String inputParameters;
	private String combinationGenerator;
	
	public boolean error;
	public String errorMsg;
	
	public ResultsController(String parameters, String generator)
	{
		inputParameters = parameters;
		combinationGenerator = generator;
	}
	
	public TestCombinationList generateCombinations()
	{
		TestCombinationList combinations = null;
		try {
			//Get the Parameter[] array
			Parameter[] parameters = new TestCombinationInputCreator().fromString(inputParameters);
			
			//Get the TestCombinationList from the correct generator
			CombinationGeneratorFactory f = new CombinationGeneratorFactory();
			f.initialize("generator.properties");
			ITestCombinationGenerator generator = f.generatorForName(combinationGenerator);
			
			combinations = generator.generateCombinations(parameters);
			
			return combinations;
		}
		catch(RuntimeException re)
		{
			error = true;
			errorMsg = re.getMessage();
			return combinations;
		}
	}
	
	public void convertResultsToHtml(TestCombinationList combinations, Writer writer, String filePath)
	{
		try
		{
			TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(combinations);
			
			Document xmlDoc = outputCreator.toXMLDocument(true);
			Source xmlSource = new DOMSource(xmlDoc);
			
			Result result = new StreamResult(writer);
			
			File xslFile = new File(filePath); //FileNotFoundException
			Source xslSource = new StreamSource(xslFile);
			Transformer xslFormer = TransformerFactory.newInstance().newTransformer(xslSource); //TransformerConfigurationException
			
			xslFormer.transform(xmlSource, result); //TransformerException
		}
		catch(Exception ex)
		{
			error = true;
			errorMsg = ex.getMessage();
		}
	}
	
	public String convertResultsToXML(TestCombinationList combinations)
	{
		String result = null;
		try
		{
			TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(combinations);
			
			StringWriter sWriter = new StringWriter();
			
			outputCreator.toXMLDocument(sWriter, false);
			
			result = sWriter.toString();
			
			return result;
		}
		catch(Exception ex)
		{
			error = true;
			errorMsg = ex.getMessage();
			return result;
		}
	}
	
	public String convertResultsToCSV(TestCombinationList combinations)
	{
		String result = null;
		try
		{
		TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(combinations);
		
		StringWriter sWriter = new StringWriter();
		
		outputCreator.toCSVFile(sWriter);
		
		result = sWriter.toString();
		
		return result;
		}
		catch(Exception ex)
		{
			error = true;
			errorMsg = ex.getMessage();
			return result;
		}
	}
}
