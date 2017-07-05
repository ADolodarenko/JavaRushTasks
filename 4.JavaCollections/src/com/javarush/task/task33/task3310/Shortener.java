package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

/**
 * Created by n_alex on 05.07.17.
 */
public class Shortener
{
	private Long lastId = new Long(0L);
	private StorageStrategy storageStrategy;

	public Shortener(StorageStrategy storageStrategy)
	{
		this.storageStrategy = storageStrategy;
	}

	public synchronized Long getId(String string)
	{
		if (storageStrategy.containsValue(string))
			return storageStrategy.getKey(string);

		lastId++;
		storageStrategy.put(lastId, string);

		return lastId;
	}

	public synchronized String getString(Long id)
	{
		return storageStrategy.getValue(id);
	}

}
