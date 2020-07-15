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

import com.sf.denumerous.PartialTestCombination;

public class PartialTestCombination
{
	private TestCombinationValue[] values;
	
	public PartialTestCombination(Parameter[] params)
	{
		values = new TestCombinationValue[params.length];
		
		for(int i=0; i<values.length; i++)
		{
			TestCombinationValue tcVal = new TestCombinationValue(params[i].getName(), "empty");
			values[i] = tcVal;
		}
	}
	
	public TestCombinationValue[] getValues()
	{
		return values;
	}
	
	public String toString()
	{
		String result = "";
		
		for(int i=0; i<values.length; i++) {
			result = result + "<" + values[i].toString() + ">";
		}
		
		return result;
	}

	public boolean equals(PartialTestCombination tc)
	{
		return this.toString().equals(tc.toString());
	}
	
	public ValuePair[] getPairs()
	{	
		//---Find number of values---
		int n = values.length;
		for(int i=0; i<values.length; i++)
		{
			if(values[i].getValue().equals("empty"))
			{
				n--;
			}
		}
		
		//---Find number of pairs---
		int totalPairs = n*(n-1)/2;
		ValuePair[] arr = new ValuePair[totalPairs];
		int index = 0;
		
		//---Fill pair array---
		for(int i=0; i<values.length-1; i++)
		{
			if(!values[i].getValue().equals("empty"))
			{
				for(int j=i+1; j<values.length; j++)
				{
					if(!values[j].getValue().equals("empty"))
					{
						arr[index] = new ValuePair(values[i], values[j]);
						index++;
					}
				}
			}
		}
		return arr;
	}
	
	public boolean addPair(ValuePair vp)
	{
		boolean result = false;
		
		TestCombinationValue value1 = vp.getFirstValue();
		String newValue1 = value1.getValue();
		int location1 = -1;
		TestCombinationValue value2 = vp.getSecondValue();
		String newValue2 = value2.getValue();
		int location2 = -1;
		boolean result1 = false;
		boolean result2 = false;
		
		for(int i=0; i<values.length; i++)
		{
			if(values[i].getName().equals(value1.getName()))
			{
				if(values[i].getValue().equals(value1.getValue()))
				{
					result1 = true;
					location1 = i;
				}
				else if(values[i].getValue().equals("empty"))
				{
					result1 = true;
					location1 = i;
				}
			}
			
			if(values[i].getName().equals(value2.getName()))
			{
				if(values[i].getValue().equals(value2.getValue()))
				{
					result2 = true;
					location2 = i;
				}
				else if(values[i].getValue().equals("empty"))
				{
					result2 = true;
					location2 = i;
				}
			}
		}
		
		if(result1 == true && result2 == true)
		{
			values[location1].setValue(newValue1);
			values[location2].setValue(newValue2);
			result = true;
		}
		
		return result;
	}
	
	public boolean complete()
	{
		for(int i=0; i<values.length; i++)
		{
			if(values[i].getValue().equals("empty"))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean dontCare()
	{
		boolean result = false;

		for(int i=0; i<values.length; i++) {
			if(values[i].getValue().toString().equals("empty")) {
				result = true;
			}
		}
		
		return result;
	}
}
