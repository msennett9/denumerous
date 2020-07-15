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

public class AllCombinationsGeneratorTest {

	private Parameter param1;
	private Parameter param2;
	private Parameter param3;
	
	private TestCombination testCombo1;
	private TestCombination testCombo2;
	private TestCombination testCombo3;
	private TestCombination testCombo4;
	private TestCombination testCombo5;
	private TestCombination testCombo6;
	private TestCombination testCombo7;
	private TestCombination testCombo8;
	private TestCombination testCombo9;
	private TestCombination testCombo10;
	private TestCombination testCombo11;
	private TestCombination testCombo12;
	
	@Before
	public void setUp() throws Exception {
		//--------Paramater Declarations----------
		ParameterValue paramVal11 = new ParameterValue("a1");
		ParameterValue paramVal12 = new ParameterValue("a2");
		ParameterValue paramVal13 = new ParameterValue("a3");
		ParameterValue[] paramValArr1 = {paramVal11, paramVal12, paramVal13};
		
		ParameterValue paramVal21 = new ParameterValue("b1");
		ParameterValue paramVal22 = new ParameterValue("b2");
		ParameterValue[] paramValArr2 = {paramVal21, paramVal22};
		
		ParameterValue paramVal31 = new ParameterValue("c1");
		ParameterValue paramVal32 = new ParameterValue("c2");
		ParameterValue[] paramValArr3 = {paramVal31, paramVal32};
		
		param1 = new Parameter("A", paramValArr1);
		param2 = new Parameter("B", paramValArr2);
		param3 = new Parameter("C", paramValArr3);
		
		//---------TestCombination Declarations---------
		TestCombinationValue val1 = new TestCombinationValue("A", "a1");
		TestCombinationValue val2 = new TestCombinationValue("A", "a2");
		TestCombinationValue val3 = new TestCombinationValue("A", "a3");
		TestCombinationValue val4 = new TestCombinationValue("B", "b1");
		TestCombinationValue val5 = new TestCombinationValue("B", "b2");
		TestCombinationValue val6 = new TestCombinationValue("C", "c1");
		TestCombinationValue val7 = new TestCombinationValue("C", "c2");
		
		TestCombinationValue[] testComboVal1 = {val1, val4, val6};
		TestCombinationValue[] testComboVal2 = {val1, val4, val7};
		TestCombinationValue[] testComboVal3 = {val1, val5, val6};
		TestCombinationValue[] testComboVal4 = {val1, val5, val7};
		TestCombinationValue[] testComboVal5 = {val2, val4, val6};
		TestCombinationValue[] testComboVal6 = {val2, val4, val7};
		TestCombinationValue[] testComboVal7 = {val2, val5, val6};
		TestCombinationValue[] testComboVal8 = {val2, val5, val7};
		TestCombinationValue[] testComboVal9 = {val3, val4, val6};
		TestCombinationValue[] testComboVal10 = {val3, val4, val7};
		TestCombinationValue[] testComboVal11 = {val3, val5, val6};
		TestCombinationValue[] testComboVal12 = {val3, val5, val7};
		
		testCombo1 = new TestCombination(testComboVal1);
		testCombo2 = new TestCombination(testComboVal2);
		testCombo3 = new TestCombination(testComboVal3);
		testCombo4 = new TestCombination(testComboVal4);
		testCombo5 = new TestCombination(testComboVal5);
		testCombo6 = new TestCombination(testComboVal6);
		testCombo7 = new TestCombination(testComboVal7);
		testCombo8 = new TestCombination(testComboVal8);
		testCombo9 = new TestCombination(testComboVal9);
		testCombo10 = new TestCombination(testComboVal10);
		testCombo11 = new TestCombination(testComboVal11);
		testCombo12 = new TestCombination(testComboVal12);
	}
	
	@Test
	public void testCombinationsCalculation()
	{
		Parameter[] paramArr = {param1, param2, param3};
		
		AllCombinationsGenerator ACG = new AllCombinationsGenerator();
		assertEquals(12, ACG.calculateTotalCombinations(paramArr));

		Parameter[] paramArr2 = {param1};
		assertEquals(3, ACG.calculateTotalCombinations(paramArr2));
	}

	@Test
	public void testGenerateCombinations() {
		
		Parameter[] paramArr = {param1, param2, param3};
		
		AllCombinationsGenerator ACG = new AllCombinationsGenerator();
		TestCombinationList comboList = ACG.generateCombinations(paramArr);
		
		assertEquals(12, comboList.size());
		assertTrue(comboList.containsKey("<A-a1><B-b2><C-c1>"));
		
		assertTrue(comboList.containsValue(testCombo1));
		assertTrue(comboList.containsValue(testCombo2));
		assertTrue(comboList.containsValue(testCombo3));
		assertTrue(comboList.containsValue(testCombo4));
		assertTrue(comboList.containsValue(testCombo5));
		assertTrue(comboList.containsValue(testCombo6));
		assertTrue(comboList.containsValue(testCombo7));
		assertTrue(comboList.containsValue(testCombo8));
		assertTrue(comboList.containsValue(testCombo9));
		assertTrue(comboList.containsValue(testCombo10));
		assertTrue(comboList.containsValue(testCombo11));
		assertTrue(comboList.containsValue(testCombo12));
		
		
		comboList.remove("<A-a1><B-b1><C-c1>");
		assertFalse(comboList.containsKey("<A-a1><B-b1><C-c1>"));
		assertFalse(comboList.containsValue(testCombo1));
	}
	
	
	@Test
	public void testTwoByTwo() {
		
		//------Actual------
		Parameter[] paramArr = {param1, param2};
		
		AllCombinationsGenerator ACG = new AllCombinationsGenerator();
		TestCombinationList comboList = ACG.generateCombinations(paramArr);
		
		//-------Expected-------
		TestCombinationValue val1 = new TestCombinationValue("B", "b1");
		TestCombinationValue val2 = new TestCombinationValue("B", "b2");
		TestCombinationValue val3 = new TestCombinationValue("C", "c1");
		TestCombinationValue val4 = new TestCombinationValue("C", "c2");
		
		TestCombinationValue[] testComboVal1 = {val1, val3};
		TestCombinationValue[] testComboVal2 = {val1, val4};
		TestCombinationValue[] testComboVal3 = {val2, val3};
		TestCombinationValue[] testComboVal4 = {val2, val4};
		
		TestCombination testCombo1 = new TestCombination(testComboVal1);
		TestCombination testCombo2 = new TestCombination(testComboVal2);
		TestCombination testCombo3 = new TestCombination(testComboVal3);
		TestCombination testCombo4 = new TestCombination(testComboVal4);
		
		TestCombinationList comboListExpected = new TestCombinationList();
		comboListExpected.put(testCombo1);
		comboListExpected.put(testCombo2);
		comboListExpected.put(testCombo3);
		comboListExpected.put(testCombo4);
		
		assertFalse(comboList.toString().equals(comboListExpected.toString()));
		assertEquals(6, comboList.size());
	}
	
	@Test
	public void testThreeByOne() {
		//--------Actual--------
		ParameterValue paramVal11 = new ParameterValue("a1");
		ParameterValue[] paramValArr1 = {paramVal11};
		
		ParameterValue paramVal21 = new ParameterValue("b1");
		ParameterValue[] paramValArr2 = {paramVal21};
		
		ParameterValue paramVal31 = new ParameterValue("c1");
		ParameterValue[] paramValArr3 = {paramVal31};
		
		Parameter param1 = new Parameter("A", paramValArr1);
		Parameter param2 = new Parameter("B", paramValArr2);
		Parameter param3 = new Parameter("C", paramValArr3);
		Parameter[] paramArr = {param1, param2, param3};
		
		AllCombinationsGenerator ACG = new AllCombinationsGenerator();
		TestCombinationList comboListActual = ACG.generateCombinations(paramArr);
		
		//-------Expected-------
		TestCombinationValue val1 = new TestCombinationValue("A", "a1");
		TestCombinationValue val2 = new TestCombinationValue("B", "b1");
		TestCombinationValue val3 = new TestCombinationValue("C", "c1");
		
		TestCombinationValue[] testComboVal1 = {val1, val2, val3};
		
		TestCombination testCombo1 = new TestCombination(testComboVal1);
		
		TestCombinationList comboListExpected = new TestCombinationList();
		comboListExpected.put(testCombo1);
		
		//-------Test-------
		assertTrue(comboListActual.toString().equals(comboListExpected.toString()));
	}
	
	@Test
	public void testMenu() {
		ParameterValue paramVal11 = new ParameterValue("Eggs");
		ParameterValue paramVal12 = new ParameterValue("Bacon");
		ParameterValue paramVal13 = new ParameterValue("Pancakes");
		ParameterValue[] paramValArr1 = {paramVal11, paramVal12, paramVal13};
		
		ParameterValue paramVal21 = new ParameterValue("Soup");
		ParameterValue paramVal22 = new ParameterValue("Sandwich");
		ParameterValue paramVal23 = new ParameterValue("Salad");
		ParameterValue[] paramValArr2 = {paramVal21, paramVal22, paramVal23};
		
		ParameterValue paramVal31 = new ParameterValue("Pizza");
		ParameterValue paramVal32 = new ParameterValue("Steak");
		ParameterValue paramVal33 = new ParameterValue("Pasta");
		ParameterValue[] paramValArr3 = {paramVal31, paramVal32, paramVal33};
		
		Parameter param1 = new Parameter("Breakfast", paramValArr1);
		Parameter param2 = new Parameter("Lunch", paramValArr2);
		Parameter param3 = new Parameter("Dinner", paramValArr3);
		Parameter[] paramArr = {param1, param2, param3};
		
		AllCombinationsGenerator ACG = new AllCombinationsGenerator();
		TestCombinationList testComboActual = ACG.generateCombinations(paramArr);
		
		assertEquals(27, testComboActual.size());
	}
	
	public void display(TestCombination[] arr) {
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i].toString());
		}
	}

}
