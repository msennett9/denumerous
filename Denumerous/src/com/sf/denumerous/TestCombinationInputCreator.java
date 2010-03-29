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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.sf.denumerous.Parameter;
import com.sf.denumerous.ParameterValue;

public class TestCombinationInputCreator
{	
	public Parameter[] fromFile(String fileName)
	{
		try
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			ArrayList<String> input = new ArrayList<String>();
			
			while(br.ready())
			{
				input.add(br.readLine());
			}
			
			String[] inputArr = new String[input.size()];
			input.toArray(inputArr);
			
			Parameter[] result = makeTestParameters(inputArr);
			
			return result;
		}
		catch(FileNotFoundException exp)
		{
			throw new RuntimeException(exp);
		}
		catch(IOException exp)
		{
			throw new RuntimeException(exp);
		}
		catch(Exception exp)
		{
			throw new RuntimeException(exp);
		}
	}
	
	
	public Parameter[] fromString(String str)
	{
		String[] input = str.split("\n");
		
		Parameter[] result = makeTestParameters(input);
		
		return result;
	}
	
	private Parameter[] makeTestParameters(String[] input)
	{	
		ArrayList<Parameter> parameters = new ArrayList<Parameter>();
		ArrayList<ParameterValue> paramVals = new ArrayList<ParameterValue>();
		
		String currentParam = input[0].trim();
		
		for(int i=1; i<input.length; i++)
		{
			String trimmed = input[i].trim(); //Gets rid of spaces**
			if(trimmed.length() != 0) //Checks for a blank line**
			{
				if(input[i].charAt(0) == '\t' || input[i].charAt(0) == ' ')
				{
					paramVals.add(new ParameterValue(input[i].trim()));
				}
				else
				{
					ParameterValue[] paramValsArr = new ParameterValue[paramVals.size()];
					paramVals.toArray(paramValsArr);
					
					Parameter p = new Parameter(currentParam, paramValsArr);
					
					parameters.add(p);
					
					currentParam = input[i].trim();
					paramVals.clear();
				}
			}
		}
		
		//---Add the last one---
		ParameterValue[] paramValsArr = new ParameterValue[paramVals.size()];
		paramVals.toArray(paramValsArr);
		
		Parameter p = new Parameter(currentParam, paramValsArr);
		
		parameters.add(p);
		
		//---Create result array---
		Parameter[] result = new Parameter[parameters.size()];
		parameters.toArray(result);
		
		return result;
	}
}
