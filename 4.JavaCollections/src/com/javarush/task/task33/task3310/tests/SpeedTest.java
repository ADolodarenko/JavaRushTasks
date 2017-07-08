package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by n_alex on 08.07.17.
 */
public class SpeedTest
{
	@Test
	public void testHashMapStorage()
	{
		Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
		Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

		Set<String> origStrings = new HashSet<>();
		for (int i = 0; i < 10000; i++)
			origStrings.add(Helper.generateRandomString());

		Set<Long> ids1 = new HashSet<>();
		Set<Long> ids2 = new HashSet<>();

		long duration1 = getTimeForGettingIds(shortener1, origStrings, ids1);
		long duration2 = getTimeForGettingIds(shortener2, origStrings, ids2);

		Assert.assertTrue(duration1 > duration2);

		Set<String> resStrings1 = new HashSet<>();
		Set<String> resStrings2 = new HashSet<>();

		duration1 = getTimeForGettingStrings(shortener1, ids1, resStrings1);
		duration2 = getTimeForGettingStrings(shortener2, ids2, resStrings2);

		Assert.assertEquals(duration1, duration2, 30);
	}

	public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids)
	{
		Date startDate = new Date();

		for (String string : strings)
			ids.add(shortener.getId(string));

		Date endDate = new Date();

		return endDate.getTime() - startDate.getTime();
	}

	public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings)
	{
		Date startDate = new Date();

		for (Long id : ids)
			strings.add(shortener.getString(id));

		Date endDate = new Date();

		return endDate.getTime() - startDate.getTime();
	}
}
