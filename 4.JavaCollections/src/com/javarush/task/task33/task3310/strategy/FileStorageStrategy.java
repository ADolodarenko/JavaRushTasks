package com.javarush.task.task33.task3310.strategy;

/**
 * Created by n_alex on 07.07.17.
 */
public class FileStorageStrategy implements StorageStrategy
{
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;

	private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
	private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
	private int size;
	private long maxBucketSize;

	public int hash(Long k)
	{
		int h;
		return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
	}

	public int indexFor(int hash, int length)
	{
		return hash & (length - 1);
	}

	public Entry getEntry(Long key)
	{
		int hash = hash(key);
		int index = indexFor(hash, table.length);

		if (table[index] != null)
			for (Entry e = table[index].getEntry();
				 e != null;
			 	e = e.next)
			{
				Long k;

				if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
					return e;
			}

		return null;
	}

	public void resize(int newCapacity)
	{
		FileBucket[] oldTable = table;
		int oldCapacity = oldTable.length;

		if (oldCapacity == 1 << 30)
			return;

		FileBucket[] newTable = new FileBucket[newCapacity];
		transfer(newTable);
		table = newTable;
	}

	public void transfer(FileBucket[] newTable)
	{
		FileBucket[] src = table;
		int newCapacity = newTable.length;

		for (int j = 0; j < src.length; j++)
		{
			Entry e = null;

			if (src[j] != null)
				e = src[j].getEntry();

			if (e != null)
			{
				src[j].remove();
				src[j] = null;

				do
				{
					Entry next = e.next;
					int i = indexFor(e.hash, newCapacity);

					if (newTable[i] == null)
						newTable[i] = new FileBucket();

					e.next = newTable[i].getEntry();
					newTable[i].putEntry(e);
					e = next;
				}
				while (e != null);
			}
		}
	}

	public void addEntry(int hash, Long key, String value, int bucketIndex)
	{
		if (table[bucketIndex] == null)
			table[bucketIndex] = new FileBucket();

		Entry e = table[bucketIndex].getEntry();
		table[bucketIndex].putEntry(new Entry(hash, key, value, e));

		size++;
		if (table[bucketIndex].getFileSize() > bucketSizeLimit)
			resize(2 * table.length);
	}

	public void createEntry(int hash, Long key, String value, int bucketIndex)
	{
		if (table[bucketIndex] == null)
			table[bucketIndex] = new FileBucket();

		Entry e = table[bucketIndex].getEntry();
		table[bucketIndex].putEntry(new Entry(hash, key, value, e));
		size++;
	}

	public long getBucketSizeLimit()
	{
		return bucketSizeLimit;
	}

	public void setBucketSizeLimit(long bucketSizeLimit)
	{
		this.bucketSizeLimit = bucketSizeLimit;
	}

	@Override
	public boolean containsKey(Long key)
	{
		return getEntry(key) != null;
	}

	@Override
	public boolean containsValue(String value)
	{
		FileBucket[] tab = table;

		if (value == null)
		{
			for (int i = 0; i < tab.length; i++)
				if (tab[i] != null)
					for (Entry e = tab[i].getEntry(); e != null; e = e.next)
						if (e.value == null)
							return true;
		}
		else
		{
			for (int i = 0; i < tab.length; i++)
				if (tab[i] != null)
					for (Entry e = tab[i].getEntry(); e != null; e = e.next)
						if (value.equals(e.value))
							return true;
		}

		return false;
	}

	@Override
	public void put(Long key, String value)
	{
		int hash = hash(key);
		int i = indexFor(hash, table.length);

		if (table[i] != null)
			for (Entry e = table[i].getEntry(); e != null; e = e.next)
			{
				Long k;

				if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
					e.value = value;
			}

		addEntry(hash, key, value, i);
	}

	@Override
	public Long getKey(String value)
	{
		FileBucket[] tab = table;

		if (value == null)
		{
			for (int i = 0; i < tab.length; i++)
				if (tab[i] != null)
					for (Entry e = tab[i].getEntry(); e != null; e = e.next)
						if (e.value == null)
							return e.key;
		}
		else
		{
			for (int i = 0; i < tab.length; i++)
				if (tab[i] != null)
					for (Entry e = tab[i].getEntry(); e != null; e = e.next)
						if (value.equals(e.value))
							return e.key;
		}

		return null;
	}

	@Override
	public String getValue(Long key)
	{
		Entry e = getEntry(key);

		if (e != null)
			return e.value;
		else
			return null;
	}
}
