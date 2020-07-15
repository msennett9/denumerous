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

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class AllPairsCombinationGenerator2Test
{
	private AllPairsCombinationGenerator2 APCG2;
	private Parameter[] parameters;
	private TestCombinationList tcl;
	
	private int numParameters = 3;
	private int numValues = 3;
	
	private int maxSize = 150; //Maximum number of test combinations
	
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

	//***********************************TESTS*****************************************
	@Test
	public void testGenerateCombinations()
	{	
		//Get current time
	    long start = System.currentTimeMillis();
		
		tcl = APCG2.generateCombinations(parameters);
		
		//Get elapsed time in milliseconds
	    long elapsedTimeMillis = System.currentTimeMillis()-start;
	    
	    //Use to display combinations and time
//	    displayCombinations();
//	    displayTime(elapsedTimeMillis);
	    
		//---Tests for 4x9---
//	    assertTrue(containsPair("A", "a1", "B", "b1")); // Test: First Pair (a1,b1)
//	    assertTrue(containsPair("C", "c9", "D", "d9")); // Test: Last Pair (c9,d9)
//	    assertTrue(containsPair("A", "a3", "C", "c5")); // Test: A,C 3,5
//	    assertTrue(containsPair("A", "a6", "D", "d4")); // Test: A,D 6,4
//	    assertTrue(containsPair("B", "b7", "C", "c2")); // Test: B,C 7,2
//	    assertTrue(containsPair("B", "b4", "D", "d8")); // Test: B,D 4,8
	    
	    assertTrue(tcl.size() < maxSize);
	    assertTrue(elapsedTimeMillis >= 0);
	}
	
	@Test
	public void testBuildAllPairs()
	{
		ParameterValue val11 = new ParameterValue("a1");
		ParameterValue val12 = new ParameterValue("a2");
		ParameterValue val13 = new ParameterValue("a3");
		ParameterValue[] valArr1 = {val11,val12, val13};
		
		ParameterValue val21 = new ParameterValue("yes");
		ParameterValue val22 = new ParameterValue("no");
		ParameterValue[] valArr2 = {val21,val22};
		
		ParameterValue val31 = new ParameterValue("yes");
		ParameterValue val32 = new ParameterValue("no");
		ParameterValue[] valArr3 = {val31,val32};
		
		Parameter param1 = new Parameter("A", valArr1);
		Parameter param2 = new Parameter("B", valArr2);
		Parameter param3 = new Parameter("C", valArr3);
		
		Parameter[] parameters2 = {param1, param2, param3};
		
		ArrayList<ValuePair> pairs = APCG2.buildAllPairs(parameters2);
		
		assertTrue(pairs.size() == 16);
		
		tcl = APCG2.generateCombinations(parameters2);
		
		assertTrue("Missing pair", containsPair("A", "a2", "B", "no"));
		assertTrue("Missing pair", containsPair("A", "a3", "B", "yes"));
		assertTrue("Missing pair", containsPair("B", "yes", "C", "no"));
	}
	
	//***********************************END TESTS*************************************
	
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
	}
}
