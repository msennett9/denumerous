package com.sf.denumerous;

import java.io.InputStream;
import java.util.Properties;

public class CombinationGeneratorFactory 
{
	private static Properties generatorProps;

	public void initialize(String configFileName)
	{
		if (generatorProps != null)
		{
			return;
		}
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(configFileName);
		if (in == null) 
		{
			throw new RuntimeException("Properties file not found.");
		}
		
		try 
		{
			generatorProps = new Properties();
			generatorProps.load(in);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Unable to load properties.", e);
		}
	}

	public ITestCombinationGenerator generatorForName(String name)
	{
		if (name == null)
		{
			throw new RuntimeException("Generator name is null");
		}
		
		String generatorClassName = generatorProps.getProperty(name + ".generator");
		ITestCombinationGenerator generator;
		try 
		{
			Class<?> theClass  = Class.forName(generatorClassName);
			generator = (ITestCombinationGenerator) theClass.getDeclaredConstructor().newInstance();
			generator.properties(generatorProps);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new RuntimeException("Unable to create generator for " + name, e);
		}

		return generator;
	}
	
	public ITestCombinationGenerator defaultGenerator()
	{
		return generatorForName("default");
	}
	
}
