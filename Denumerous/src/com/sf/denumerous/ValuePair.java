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

import com.sf.denumerous.TestCombinationValue;
import com.sf.denumerous.ValuePair;

public class ValuePair
{
	private TestCombinationValue firstValue;
	private TestCombinationValue secondValue;
	
	public ValuePair(TestCombinationValue value1, TestCombinationValue value2)
	{
		firstValue = value1;
		secondValue = value2;
	}

	public TestCombinationValue getFirstValue()
	{
		return firstValue;
	}

	public void setFirstValue(TestCombinationValue firstValue)
	{
		this.firstValue = firstValue;
	}

	public TestCombinationValue getSecondValue()
	{
		return secondValue;
	}

	public void setSecondValue(TestCombinationValue secondValue)
	{
		this.secondValue = secondValue;
	}

	@Override
	public String toString()
	{
		String result = "<" + firstValue.getName() + ":" + firstValue.getValue() + "-" + secondValue.getName() + ":" + secondValue.getValue() + ">";
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		ValuePair vp = (ValuePair)obj;
		
		return this.toString().equals(vp.toString());
	}
	
	@Override
	public int hashCode()
	{
		return this.toString().hashCode();
	}
}
