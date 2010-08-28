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

import java.io.FileWriter;
import java.io.IOException;

import com.sf.denumerous.CombinationGeneratorFactory;
import com.sf.denumerous.ITestCombinationGenerator;
import com.sf.denumerous.Parameter;
import com.sf.denumerous.TestCombinationInputCreator;
import com.sf.denumerous.TestCombinationList;
import com.sf.denumerous.TestCombinationOutputCreator;

public class Denumerous
{
	public static void main(String[] args) throws IOException
	{
		String inputFileName = args[0];
		String outputFileName = args[1];
		
		int position = -1;
		for(int i=0; i<outputFileName.length(); i++)
		{
			if(outputFileName.charAt(i) == '.')
			{
				position = i;
			}
		}
		String outputFileType = outputFileName.substring(position, position+4);
		
		String inputSource = inputFileName;
		String outputSource = outputFileName;

		CombinationGeneratorFactory f = new CombinationGeneratorFactory();
		f.initialize("generator.properties");

		TestCombinationInputCreator inputCreator = new TestCombinationInputCreator();
		Parameter[] params = inputCreator.fromFile(inputSource);
		
		ITestCombinationGenerator generator = f.defaultGenerator();
		TestCombinationList combinations = generator.generateCombinations(params);
		
		TestCombinationOutputCreator outputCreator = new TestCombinationOutputCreator(combinations);
		
		try
		{
			if(outputFileType.equals(".xml"))
			{
				FileWriter fwriter = new FileWriter(outputSource, false);
				outputCreator.toXMLDocument(fwriter, false);
				System.out.println("xml created - Done");
			}
			else
			{
				String outputFormat = args[2];
			
				if(outputFormat.equals("-x"))
				{
					FileWriter fwriter = new FileWriter(outputSource, false);
					outputCreator.toXMLDocument(fwriter, false);
					System.out.println("xml created - Done");
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException ex)
		{
			if(outputFileType.equals(".csv"))
			{
				FileWriter fwriter = new FileWriter(outputSource, false);
				outputCreator.toCSVFile(fwriter);
				System.out.println("csv created - Done");
			}
			else
			{
				System.out.println("No file created.");
			}
		}
	}
}
