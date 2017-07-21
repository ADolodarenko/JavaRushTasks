package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

public final class CurrencyManipulatorFactory
{
	private static Map<String, CurrencyManipulator> map = new HashMap<>();

	public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
	{
		String key = currencyCode.toUpperCase();
		CurrencyManipulator manipulator;

		if (map.containsKey(key))
			manipulator = map.get(key);
		else
		{
			manipulator = new CurrencyManipulator(key);
			map.put(key, manipulator);
		}

		return manipulator;
	}

	private CurrencyManipulatorFactory(){}
}
