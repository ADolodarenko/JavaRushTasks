package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

/**
 * Created by n_alex on 09.07.17.
 */
public class CachingProxyRetriever implements Retriever
{
	private OriginalRetriever retriever;
	private LRUCache<Long, Object> cache = new LRUCache<>(3);

	public CachingProxyRetriever(Storage storage)
	{
		retriever = new OriginalRetriever(storage);
	}

	@Override
	public Object retrieve(long id)
	{
		Object object = cache.find(id);

		if (object == null)
		{
			object = retriever.retrieve(id);

			cache.set(id, object);
		}

		return object;
	}
}
