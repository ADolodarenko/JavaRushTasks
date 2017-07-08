package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by n_alex on 08.07.17.
 */
public class FunctionalTest
{
	public void testStorage(Shortener shortener)
	{
		String str1 = new String("Igogogo!");
		String str2 = new String("XXX");
		String str3 = new String("Igogogo!");

		Long id1 = shortener.getId(str1);
		Long id2 = shortener.getId(str2);
		Long id3 = shortener.getId(str3);

		Assert.assertNotEquals(id2, id1);
		Assert.assertNotEquals(id2, id3);

		Assert.assertEquals(id1, id3);

		String res1 = shortener.getString(id1);
		String res2 = shortener.getString(id2);
		String res3 = shortener.getString(id3);

		Assert.assertEquals(res1, str1);
		Assert.assertEquals(res2, str2);
		Assert.assertEquals(res3, str3);
	}

	@Test
	public void testHashMapStorageStrategy()
	{
		StorageStrategy strategy = new HashMapStorageStrategy();
		Shortener shortener = new Shortener(strategy);

		testStorage(shortener);
	}

	@Test
	public void testOurHashMapStorageStrategy()
	{
		StorageStrategy strategy = new OurHashMapStorageStrategy();
		Shortener shortener = new Shortener(strategy);

		testStorage(shortener);
	}

	@Test
	public void testFileStorageStrategy()
	{
		StorageStrategy strategy = new FileStorageStrategy();
		Shortener shortener = new Shortener(strategy);

		testStorage(shortener);
	}

	@Test
	public void testHashBiMapStorageStrategy()
	{
		StorageStrategy strategy = new HashBiMapStorageStrategy();
		Shortener shortener = new Shortener(strategy);

		testStorage(shortener);
	}

	@Test
	public void testDualHashBidiMapStorageStrategy()
	{
		StorageStrategy strategy = new DualHashBidiMapStorageStrategy();
		Shortener shortener = new Shortener(strategy);

		testStorage(shortener);
	}

	@Test
	public void testOurHashBiMapStorageStrategy()
	{
		StorageStrategy strategy = new OurHashBiMapStorageStrategy();
		Shortener shortener = new Shortener(strategy);

		testStorage(shortener);
	}
}
