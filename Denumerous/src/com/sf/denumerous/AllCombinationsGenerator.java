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

import java.util.Properties;

import com.sf.denumerous.ITestCombinationGenerator;
import com.sf.denumerous.Parameter;
import com.sf.denumerous.ParameterValue;
import com.sf.denumerous.TestCombination;
import com.sf.denumerous.TestCombinationList;
import com.sf.denumerous.TestCombinationValue;

public class AllCombinationsGenerator implements ITestCombinationGenerator 
{
	private TestCombinationList allCombinations;
	private TestCombinationValue[] testValArr;
	private Properties generatorProperties = new Properties();
	
	public TestCombinationList generateCombinations(Parameter[] parameters) 
	{
		allCombinations = new TestCombinationList();
		testValArr = new TestCombinationValue[parameters.length];
		
		// Calculate the total number of values
		int totalCombinations = calculateTotalCombinations(parameters);
		int max = getMaxCombinationsAllowed();
		if (totalCombinations > max)
		{
			throw new RuntimeException("Maximum combinations exceeded (" + totalCombinations + "). Max = " + max);
		}
		
		recursiveGenerateCombinations(parameters, 0);
		
		return allCombinations;
	}
	
	private int getMaxCombinationsAllowed() 
	{
		String maxAllowed = generatorProperties.getProperty("allCombinations.max", "1000");
		
		return Integer.parseInt(maxAllowed);
	}

	public void properties(Properties props) 
	{
		generatorProperties = props;
	}

	protected int calculateTotalCombinations(Parameter[] parameters) 
	{
		int combinations = parameters[0].getValues().length;
		
		for (int i=1; i<parameters.length; i++)
		{
			combinations = combinations * parameters[i].getValues().length; 
		}
		return combinations;
	}

	private void recursiveGenerateCombinations(Parameter[] parameters, int index) 
	{
		if(parameters.length == 1) {
			ParameterValue[] paramValues = parameters[0].getValues();
			for(int k=0; k<paramValues.length; k++) {
				//Make TestCombination Value
				TestCombinationValue testVal = new TestCombinationValue(parameters[0].getName(), paramValues[k].getValue());
				
				//Add to Array of TestCombinationValues
				testValArr[index] = testVal;
				
				//Make TestCombination
				TestCombination testCombination = new TestCombination(testValArr);
				
				//Add TestCombination to allCombinations
				allCombinations.put(testCombination);
			}
			return;
		}
		
		ParameterValue[] paramValues = parameters[0].getValues();
		for(int i=0; i<paramValues.length; i++) {
			//Make TestCombination Value
			TestCombinationValue testVal = new TestCombinationValue(parameters[0].getName(), paramValues[i].getValue());
			
			//Add to Array of TestCombinationValues
			testValArr[index] = testVal;
			int newIndex = index+1;
			
			//Make new parameters array
			Parameter[] newParameters = new Parameter[parameters.length-1];
			for(int j=0; j<newParameters.length; j++) {
				newParameters[j] = parameters[j+1];
			}
			
			recursiveGenerateCombinations(newParameters, newIndex);
		}
		return;
	}
}
