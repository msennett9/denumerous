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

import com.sf.denumerous.TestCombinationValue;
import com.sf.denumerous.ValuePair;

public class ValuePairTest
{

	@Test
	public void testToString()
	{
		TestCombinationValue val1 = new TestCombinationValue("A", "a1");
		TestCombinationValue val2 = new TestCombinationValue("B", "b1");
		
		ValuePair vp = new ValuePair(val1, val2);
		
		assertEquals("<A:a1-B:b1>", vp.toString());
	}
	
	@Test
	public void testEquals()
	{
		TestCombinationValue val1 = new TestCombinationValue("A", "a1");
		TestCombinationValue val2 = new TestCombinationValue("B", "b1");
		
		ValuePair vp1 = new ValuePair(val1, val2);
		
		TestCombinationValue val3 = new TestCombinationValue("B", "b2");
		ValuePair vp2 = new ValuePair(val1, val3);
		
		assertFalse(vp1.equals(vp2));
		assertTrue(vp1.equals(vp1));
	}
	
	@Test
	public void testHashSetMembership()
	{
		TestCombinationValue val1 = new TestCombinationValue("A", "a1");
		TestCombinationValue val2 = new TestCombinationValue("B", "b1");
		
		ValuePair vp1 = new ValuePair(val1, val2);
		
		TestCombinationValue val3 = new TestCombinationValue("B", "b2");
		ValuePair vp2 = new ValuePair(val1, val3);
		
		TestCombinationValue val4 = new TestCombinationValue("A", "a1");
		TestCombinationValue val5 = new TestCombinationValue("B", "b1");
		
		ValuePair vp3 = new ValuePair(val4, val5);
		
		HashSet<ValuePair> set = new HashSet<ValuePair>();
		
		set.add(vp1);
		set.add(vp2);
		
		assertEquals(2, set.size());
		
		set.add(vp3);
		
		assertEquals(2, set.size());
	}
}
