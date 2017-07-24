package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator
{
	private String currencyCode;
	private Map<Integer, Integer> denominations;

	public CurrencyManipulator(String currencyCode)
	{
		this.currencyCode = currencyCode;
		this.denominations = new HashMap<>();
	}

	public String getCurrencyCode()
	{
		return currencyCode;
	}

	public void addAmount(int denomination, int count)
	{
		int resultCount = count;

		if (denominations.containsKey(denomination))
			resultCount += denominations.get(denomination);

		denominations.put(denomination, resultCount);
	}

	public int getTotalAmount()
	{
		int totalAmount = 0;

		for (Map.Entry<Integer, Integer> entry : denominations.entrySet())
			totalAmount += (entry.getKey() * entry.getValue());

		return totalAmount;
	}

	public boolean hasMoney()
	{
		return !denominations.isEmpty();
	}

	public boolean isAmountAvailable(int expectedAmount)
	{
		return expectedAmount <= getTotalAmount();
	}

	public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException
	{
		Map<Integer, Integer> amounts = new HashMap<>();

		Map<Integer, Integer> denoms = new TreeMap<>(new Comparator<Integer>()
		{
			@Override
			public int compare(Integer o1, Integer o2)
			{
				return o2.compareTo(o1);
			}
		});
		denoms.putAll(denominations);

		Iterator<Map.Entry<Integer, Integer>> iterator = denoms.entrySet().iterator();

		while (expectedAmount > 0 && iterator.hasNext())
		{
			Map.Entry<Integer, Integer> entry = iterator.next();
			Integer key = entry.getKey();
			Integer value = entry.getValue();

			int quantity = expectedAmount/key;

			if (quantity > 0 && value >= quantity)
			{
				amounts.put(key, quantity);

				expectedAmount %= key;
			}
		}

		if (expectedAmount == 0 && !amounts.isEmpty())
		{
			for (Map.Entry<Integer, Integer> entry : amounts.entrySet())
			{
				Integer key = entry.getKey();
				Integer value = denominations.get(key) - entry.getValue();
				denominations.put(key, value);
			}
		}
		else
			throw new NotEnoughMoneyException();

		return amounts;
	}
}
