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

import com.sf.denumerous.TestCombination;
import com.sf.denumerous.TestCombinationValue;
import com.sf.denumerous.ValuePair;

public class TestCombination
{
	private TestCombinationValue[] values;
	
	public TestCombination(TestCombinationValue[] vals)
	{
		values = new TestCombinationValue[vals.length];
		for(int i=0; i<values.length; i++)
		{
			values[i] = vals[i];
		}
	}
	
	public TestCombinationValue[] getValues()
	{
		return values;
	}
	
	public String toString()
	{
		String result = "";
		
		for(int i=0; i<values.length; i++)
		{
			result = result + "<" + values[i].toString() + ">";
		}
		
		return result;
	}

	public boolean equals(TestCombination tc)
	{
		return this.toString().equals(tc.toString());
	}
	
	public ValuePair[] getPairs()
	{
		int n = values.length;
		int totalPairs = n*(n-1)/2;
		ValuePair[] arr = new ValuePair[totalPairs];
		
		int index=0;
		
		for(int i=0; i<values.length-1; i++)
		{
			for(int j=i+1; j<values.length; j++)
			{
				arr[index] = new ValuePair(values[i], values[j]);
				index++;
			}
		}
		return arr;
	}
}
