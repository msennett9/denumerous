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

import org.junit.Before;
import org.junit.Test;

import com.sf.denumerous.TestCombination;
import com.sf.denumerous.TestCombinationList;
import com.sf.denumerous.TestCombinationValue;

public class TestCombinationListTest
{
	private TestCombination testCombo1;
	private TestCombination testCombo2;
	private TestCombination testCombo3;
	private TestCombination testCombo4;
	
	@Before
	public void setUp() throws Exception
	{
		TestCombinationValue val1 = new TestCombinationValue("A", "a1");
		TestCombinationValue val2 = new TestCombinationValue("A", "a2");
		TestCombinationValue val3 = new TestCombinationValue("B", "b1");
		TestCombinationValue val4 = new TestCombinationValue("B", "b2");
		
		TestCombinationValue[] testComboVal1 = {val1, val3};
		TestCombinationValue[] testComboVal2 = {val1, val4};
		TestCombinationValue[] testComboVal3 = {val2, val3};
		TestCombinationValue[] testComboVal4 = {val2, val4};
		
		testCombo1 = new TestCombination(testComboVal1);
		testCombo2 = new TestCombination(testComboVal2);
		testCombo3 = new TestCombination(testComboVal3);
		testCombo4 = new TestCombination(testComboVal4);
	}

	@Test
	public void testPutTestCombination()
	{	
		TestCombinationList testList = new TestCombinationList();
		
		testList.put(testCombo1);
		testList.put(testCombo2);
		
		assertEquals(2, testList.size());
		assertNotNull(testList.get("<A-a1><B-b1>"));
		assertNotNull(testList.get("<A-a1><B-b2>"));
		
		testList.put(testCombo3);
		
		assertEquals(3, testList.size());
		assertNotNull(testList.get("<A-a2><B-b1>"));
		
		testList.remove("<A-a1><B-b2>");
		assertNull(testList.get("<A-a1><B-b2>"));
	}

	@Test
	public void testContainsValueTestCombination()
	{
		
		TestCombinationList testList = new TestCombinationList();
		
		testList.put(testCombo1);
		testList.put(testCombo2);
		
		assertTrue(testList.containsValue(testCombo1));
		assertTrue(testList.containsValue(testCombo2));
		assertFalse(testList.containsValue(testCombo3));
		assertFalse(testList.containsValue(testCombo4));
		
		testList.remove("<A-a1><B-b1>");
		assertFalse(testList.containsValue(testCombo1));
	}

}
