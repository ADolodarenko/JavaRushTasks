package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by dolodarenko on 29.06.2017.
 */
public class FactoryProducer
{
	public static enum HumanFactoryType
	{
		MALE, FEMALE
	}
	
	public static AbstractFactory getFactory(HumanFactoryType type)
	{
		AbstractFactory result = null;
		
		switch (type)
		{
			case MALE: result = new MaleFactory();
			break;
			case FEMALE: result = new FemaleFactory();
		}
		
		return result;
	}
}
