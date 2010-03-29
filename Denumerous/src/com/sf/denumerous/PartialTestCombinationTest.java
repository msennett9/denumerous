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

import com.sf.denumerous.Parameter;
import com.sf.denumerous.ParameterValue;
import com.sf.denumerous.PartialTestCombination;
import com.sf.denumerous.TestCombinationValue;
import com.sf.denumerous.ValuePair;

public class PartialTestCombinationTest
{
	private PartialTestCombination x;
	private PartialTestCombination y;
	private PartialTestCombination z;
	
	private ValuePair vp1;
	private ValuePair vp2;
	private ValuePair vp3;
	private ValuePair vp4;
	
	@Before
	public void setUp() throws Exception
	{
		ParameterValue a1 = new ParameterValue("a1");
		ParameterValue a2 = new ParameterValue("a2");
		ParameterValue a3 = new ParameterValue("a3");
		ParameterValue[] aArr = {a1,a2,a3};
		Parameter a = new Parameter("A", aArr);
		
		ParameterValue b1 = new ParameterValue("c1");
		ParameterValue b2 = new ParameterValue("c2");
		ParameterValue b3 = new ParameterValue("c3");
		ParameterValue[] bArr = {b1,b2,b3};
		Parameter b = new Parameter("B", bArr);
		
		ParameterValue c1 = new ParameterValue("c1");
		ParameterValue c2 = new ParameterValue("c2");
		ParameterValue c3 = new ParameterValue("c3");
		ParameterValue[] cArr = {c1,c2,c3};
		Parameter c = new Parameter("C", cArr);
		
		Parameter[] params = {a,b,c};
		
		x = new PartialTestCombination(params);
		y = new PartialTestCombination(params);
		z = new PartialTestCombination(params);
		
		
		TestCombinationValue testComboVal1 = new TestCombinationValue(a.getName(), a1.getValue());
		TestCombinationValue testComboVal2 = new TestCombinationValue(b.getName(), b1.getValue());
		TestCombinationValue testComboVal3 = new TestCombinationValue(c.getName(), c1.getValue());
		vp1 = new ValuePair(testComboVal1, testComboVal2);
		vp2 = new ValuePair(testComboVal1, testComboVal3);
		vp3 = new ValuePair(testComboVal2, testComboVal3);
		
		TestCombinationValue testComboVal4 = new TestCombinationValue(a.getName(), a2.getValue());
		vp4 = new ValuePair(testComboVal4, testComboVal2);
	}

	@Test
	public void testAddPair()
	{
		assertFalse(x.complete());
		assertTrue(x.addPair(vp1));
		assertTrue(x.addPair(vp2));
		assertTrue(x.complete());
		
		assertTrue(x.addPair(vp1));
		
		assertTrue(y.addPair(vp1));
		assertFalse(y.addPair(vp4));
		assertTrue(y.addPair(vp3));
	}
	
	@Test
	public void testEqualsPartialTestCombination()
	{
		x.addPair(vp1);
		x.addPair(vp3);
		
		y.addPair(vp1);
		y.addPair(vp3);
		
		z.addPair(vp2);
		
		assertFalse(y.equals(z));
		assertTrue(x.equals(y));
	}

	@Test
	public void testGetPairs()
	{
		x.addPair(vp1);
		x.addPair(vp3);
		
		y.addPair(vp1);
		y.addPair(vp3);
		
		z.addPair(vp2);
		
		ValuePair[] vpArr1 = x.getPairs();
		ValuePair[] vpArr2 = z.getPairs();
			
		assertTrue(vpArr1.length == 3);
		assertTrue(vpArr2.length == 1);
	}
}
