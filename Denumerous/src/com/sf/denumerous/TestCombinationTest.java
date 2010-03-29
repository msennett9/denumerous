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

import java.util.HashSet;

import org.junit.Test;

import com.sf.denumerous.TestCombination;
import com.sf.denumerous.TestCombinationValue;
import com.sf.denumerous.ValuePair;

public class TestCombinationTest
{
	@Test
	public void testGetPairs()
	{
		TestCombinationValue val1 = new TestCombinationValue("A", "a1");
		TestCombinationValue val2 = new TestCombinationValue("B", "b1");
		TestCombinationValue val3 = new TestCombinationValue("C", "c1");
		TestCombinationValue val4 = new TestCombinationValue("D", "d1");
		
		TestCombinationValue[] arr1 = {val1, val2, val3, val4};
		
		TestCombination combo1 = new TestCombination(arr1);
		
		ValuePair[] valPairArr = combo1.getPairs();
		
		assertEquals(6, valPairArr.length);
		assertEquals("<A:a1-B:b1>", valPairArr[0].toString());
		
		HashSet<ValuePair> set = new HashSet<ValuePair>();
		
		for(int i=0; i<valPairArr.length; i++)
		{
			set.add(valPairArr[i]);
		}
		
		assertEquals(6, set.size());
		
		for(int i=0; i<valPairArr.length; i++)
		{
			assertTrue(set.contains(valPairArr[i]));
		}
	}
}
