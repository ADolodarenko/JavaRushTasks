package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by n_alex on 05.07.17.
 */
public class Solution
{
	public static void main(String[] args)
	{
		testStrategy(new HashMapStorageStrategy(), 10000);
	}

	public static void testStrategy(StorageStrategy strategy, long elementNumber)
	{
		Helper.printMessage(strategy.getClass().getSimpleName());

		Set<String> strings = new HashSet<>();
		for (long i = 0; i < elementNumber; i++)
			strings.add(Helper.generateRandomString());

		Shortener shortener = new Shortener(strategy);

		Date startDate = new Date();
		Set<Long> ids = getIds(shortener, strings);
		Date endDate = new Date();

		long millis = endDate.getTime() - startDate.getTime();
		Helper.printMessage(Long.toString(millis));

		startDate = new Date();
		Set<String> values = getStrings(shortener, ids);
		endDate = new Date();

		millis = endDate.getTime() - startDate.getTime();
		Helper.printMessage(Long.toString(millis));

		if (values.equals(strings))
			Helper.printMessage("Тест пройден.");
		else
			Helper.printMessage("Тест не пройден.");
	}

	public static Set<Long> getIds(Shortener shortener, Set<String> strings)
	{
		Set<Long> ids = new HashSet<>();

		for (String string : strings)
			ids.add(shortener.getId(string));

		return ids;
	}

	public static Set<String> getStrings(Shortener shortener, Set<Long> keys)
	{
		Set<String> strings = new HashSet<>();

		for (Long key : keys)
			strings.add(shortener.getString(key));

		return strings;
	}
}
