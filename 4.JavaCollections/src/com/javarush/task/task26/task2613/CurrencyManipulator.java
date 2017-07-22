package com.javarush.task.task26.task2613;

import java.util.HashMap;
import java.util.Map;

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
}
