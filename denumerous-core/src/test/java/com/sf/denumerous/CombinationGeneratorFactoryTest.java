package com.sf.denumerous;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sf.denumerous.CombinationGeneratorFactory;
import com.sf.denumerous.ITestCombinationGenerator;

public class CombinationGeneratorFactoryTest 
{
	private CombinationGeneratorFactory factory;

	@Before
	public void testSetup()
	{
		factory = new CombinationGeneratorFactory();
		// Shouldn't use the real property file.
		factory.initialize("generator.properties");
	}
	
	@Test
	public void testExistingGenerator() 
	{
		ITestCombinationGenerator gen = factory.generatorForName("default");
		assertNotNull(gen);

	}

	@Test
	public void testNonExistentGenerator() 
	{
		try
		{
			factory.generatorForName("foo");
			fail("Should have thrown an exception.");
		}
		catch (RuntimeException re)
		{
			assertEquals("Unable to create generator for foo", re.getMessage());
		}

	}

	@Test
	public void testNullGeneratorName() 
	{
		try
		{
			factory.generatorForName(null);
			fail("Null name should throw exception.");
		}
		catch (RuntimeException e) { }
		
	}
}
