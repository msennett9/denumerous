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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import com.sf.denumerous.ITestCombinationGenerator;
import com.sf.denumerous.Parameter;
import com.sf.denumerous.ParameterValue;
import com.sf.denumerous.PartialTestCombination;
import com.sf.denumerous.TestCombination;
import com.sf.denumerous.TestCombinationList;
import com.sf.denumerous.TestCombinationValue;
import com.sf.denumerous.ValuePair;

public class AllPairsCombinationGenerator2 implements ITestCombinationGenerator
{
	private boolean[] pairsUsed;
	private ValuePair[] allPairsArr;
	
	public TestCombinationList generateCombinations(Parameter[] parameters)
	{
		//---Create allPairs---
		ArrayList<ValuePair> allPairsList = buildAllPairs(parameters);
		
		//---Initialize pairsUsed, goes with allPairs---
		pairsUsed = new boolean[allPairsList.size()];
		for(int i=0; i<pairsUsed.length; i++)
		{
			pairsUsed[i] = false;
		}
		
		//---Shuffle allPairs---
		Collections.shuffle(allPairsList);
		
		//---Put into an array---
		allPairsArr = new ValuePair[allPairsList.size()];
		allPairsList.toArray(allPairsArr);
		
		//---Create ArrayList of type partialTestCombination---
		ArrayList<PartialTestCombination> partialCombinations = new ArrayList<PartialTestCombination>();
		
		//---Create TestCombinationList for complete combinations---
		TestCombinationList completeCombinations = new TestCombinationList();
		
		//---For each pair---
		for(int i=0; i<allPairsArr.length; i++)
		{
			boolean added = false;
			
			//---if the pair has not already been used---
			if(!pairsUsed[i])
			{
				//---For each partial combination---		
				for(int j=0; j<partialCombinations.size(); j++)
				{
					PartialTestCombination ptc = partialCombinations.get(j);
					
					//---Can this pair fit into this partial combination?---
					if(ptc.addPair(allPairsArr[i]))
					{
						//---Yes, it was added---
						
						//---Mark off its pairs---
						markPairs(ptc);
						
						//---Check if partial combination is now complete---
						if(ptc.complete())
						{
							//---Mark off its pairs---
//							markPairs(ptc);
							
							//---Yes, move to complete combinations list---
							TestCombination tc = new TestCombination(ptc.getValues());
							completeCombinations.put(tc);
							
							//---and take out of partial combinations---
							partialCombinations.remove(j);
						}
						
						added = true;
						break;
					}
				}
				//---If not added, start a new partial combination and add it to the partialCombination list---
				if(!added)
				{
					PartialTestCombination ptc = new PartialTestCombination(parameters);
					ptc.addPair(allPairsArr[i]);
					partialCombinations.add(ptc);
					
					//---Mark off its pairs---
					markPairs(ptc);
				}
			}
		}
		
		//---Finish putting partialCombinations into complete combinations---
		while(!partialCombinations.isEmpty())
		{
			PartialTestCombination ptc = partialCombinations.remove(0);
			
			ptc.dontCare();
			
			TestCombination tc = new TestCombination(ptc.getValues());

			completeCombinations.put(tc);
		}
		
		return completeCombinations;
	}
	
	public void properties(Properties props) 
	{
		// do nothing - no properties used
	}

	protected void markPairs(PartialTestCombination ptc)
	{
		ValuePair[] newPairs = ptc.getPairs();
		
		for(int i=0; i<newPairs.length; i++)
		{
			//for each new pair
			for(int j=0; j<allPairsArr.length; j++)
			{
				//check against every pair
				if(newPairs[i].toString().equals(allPairsArr[j].toString()) && pairsUsed[j] == false)
				{
					pairsUsed[j] = true;
				}
			}
		}
	}
	
	protected ArrayList<ValuePair> buildAllPairs(Parameter[] parameters)
	{
		ArrayList<ValuePair> pairs = new ArrayList<ValuePair>();
			
		for(int i=0; i<parameters.length-1; i++)
		{ //Parameter array
			ParameterValue[] paramValues1 = parameters[i].getValues();
			for(int j=0; j<paramValues1.length; j++)
			{ //ParameterValue array
				//---Make TestCombinationValue---
				TestCombinationValue testVal1 = new TestCombinationValue(parameters[i].getName(), paramValues1[j].getValue());
				
				for(int k=i+1; k<parameters.length; k++)
				{ //Parameter array 2
					ParameterValue[] paramValues2 = parameters[k].getValues();
					for(int l=0; l<paramValues2.length; l++)
					{ //ParameterValue array 2
						//---Make TestCombinationValue---
						TestCombinationValue testVal2 = new TestCombinationValue(parameters[k].getName(), paramValues2[l].getValue());
						
						//---Make ValuePair---
						ValuePair pair = new ValuePair(testVal1, testVal2);
						
						//---Add ValuePair to arrayList---
						pairs.add(pair);
					}
				}
					
			}
		}
		
		return pairs;
	}
}
