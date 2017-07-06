package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by n_alex on 07.07.17.
 */
public class Entry implements Serializable
{
	Long key;
	String value;
	Entry next;
	int hash;

	public Entry(int hash, Long key, String value, Entry next)
	{
		this.hash = hash;
		this.key = key;
		this.value = value;
		this.next = next;
	}

	public Long getKey()
	{
		return key;
	}

	public String getValue()
	{
		return value;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (o instanceof Entry)
		{
			Entry e = (Entry)o;

			if (Objects.equals(key, e.getKey()) &&
					Objects.equals(value, e.getValue()))
				return true;
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(key) ^ Objects.hashCode(value);
	}

	@Override
	public String toString()
	{
		return key + "=" + value;
	}
}
