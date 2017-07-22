package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper
{
	private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

	public static void writeMessage(String message)
	{
		System.out.println(message);
	}

	public static String readString() throws InterruptOperationException
	{
		String result = null;

		try
		{
			result = bis.readLine();
		}
		catch (IOException e)
		{}

		if ("EXIT".equals(result.toUpperCase()))
			throw new InterruptOperationException();

		return result;
	}

	public static String askCurrencyCode() throws InterruptOperationException
	{
		while (true)
		{
			writeMessage("Enter a currency code:");
			String code = readString();

			if (code != null && code.length() == 3)
				return code.toUpperCase();
			else
				writeMessage("Your code isn't correct.");
		}
	}

	public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException
	{
		String[] digits;

		String message = String.format("Enter the denomination and quantity for %s", currencyCode);
		while (true)
		{
			writeMessage(message);
			digits = readString().split(" ");

			boolean isCorrect = false;

			if (digits.length == 2)
				try
				{
					int denomination = Integer.parseInt(digits[0]);
					int quantity = Integer.parseInt(digits[1]);

					if (denomination > 0 && quantity > 0)
						isCorrect = true;
				}
				catch (NumberFormatException e)
				{}

			if (isCorrect)
				break;
			else
				writeMessage("Your values aren't correct.");
		}

		return digits;
	}

	public static Operation askOperation() throws InterruptOperationException
	{
		String message = "Enter an operation number:";

		while (true)
		{
			writeMessage(message);
			int number = 0;
			try
			{
				number = Integer.parseInt(readString());
			}
			catch (NumberFormatException e)
			{}

			try
			{
				return Operation.getAllowableOperationByOrdinal(number);
			}
			catch (IllegalArgumentException e)
			{
				writeMessage("Your operation number is wrong.");
			}
		}
	}
}
