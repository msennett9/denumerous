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

package com.sf.denumerouslambda;

import java.io.StringWriter;

import com.amazonaws.services.lambda.runtime.Context; 
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import com.sf.denumerous.CombinationGeneratorFactory;
import com.sf.denumerous.ITestCombinationGenerator;
import com.sf.denumerous.Parameter;
import com.sf.denumerous.TestCombinationInputCreator;
import com.sf.denumerous.TestCombinationList;
import com.sf.denumerous.TestCombinationOutputCreator;

public class CombinationsGenerator
{
	private LambdaLogger logger;
	
	public boolean error;
	public String errorMsg;
	
	public String generateRequestHandler(String parameters, Context context) {
		logger = context.getLogger();
		logger.log("received : " + parameters + "\n");
		
		TestCombinationList results = generateCombinations(parameters);
		
		return convertResultsToXML(results);
	}

	private TestCombinationList generateCombinations(String inputParameters)
	{
		TestCombinationList combinations = null;
		try {
			//Get the Parameter[] array
			Parameter[] parameters = new TestCombinationInputCreator().fromString(inputParameters);

			logger.log("parameters: " + parameters.toString() + "\n");
			
			//Get the TestCombinationList from the default (for now) generator
			CombinationGeneratorFactory f = new CombinationGeneratorFactory();
			f.initialize("generator.properties");
			ITestCombinationGenerator generator = f.generatorForName("default");
			
			combinations = generator.generateCombinations(parameters);
			
			logger.log("generated combinations: " + combinations.toString() + "\n");
			
			return combinations;
		}
		catch(RuntimeException re)
		{
			logger.log("Error generating combinations: " + re.getMessage() + "\n");
			throw re;
		}
	}
	
	private String convertResultsToXML(TestCombinationList combinations)
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
			logger.log("Error generating output: " + ex.getMessage() + "\n");
			throw ex;
		}
	}
	
}
