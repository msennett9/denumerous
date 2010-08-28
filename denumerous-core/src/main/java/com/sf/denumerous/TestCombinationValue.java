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

public class TestCombinationValue
{
	private String name;
	private String value;
//	private final String notCareVal = "Don't Care";
	
	public TestCombinationValue(String valName, String val)
	{
		name = valName;
		value = val;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String newValue)
	{
		value = newValue;
	}
	
	public String toString()
	{
		String result = "";
		result += name + "-" + value;
		
		return result;
	}
}
