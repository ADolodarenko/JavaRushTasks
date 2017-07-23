package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class WithdrawCommand implements Command
{
	@Override
	public void execute() throws InterruptOperationException
	{
		String currencyCode = ConsoleHelper.askCurrencyCode();
		CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

		while (true)
		{
			ConsoleHelper.writeMessage("Enter the sum:");
			boolean isCorrect = false;
			int sum = 0;
			try
			{
				sum = Integer.parseInt(ConsoleHelper.readString());

				if (sum > 0)
					isCorrect = true;
			}
			catch (NumberFormatException e)
			{}

			if (!isCorrect)
			{
				ConsoleHelper.writeMessage("Your sum isn't correct.");

				continue;
			}

			if (!manipulator.isAmountAvailable(sum))
				continue;

			try
			{
				Map<Integer, Integer> denominations = manipulator.withdrawAmount(sum);
				Map<Integer, Integer> sortedDenominations = new TreeMap<>(new Comparator<Integer>()
				{
					@Override
					public int compare(Integer o1, Integer o2)
					{
						return o2.intValue() - o1.intValue();
					}
				});

				sortedDenominations.putAll(denominations);

				for (Map.Entry<Integer, Integer> entry : sortedDenominations.entrySet())
					ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());

				ConsoleHelper.writeMessage("Transaction succeeded.");

				break;
			}
			catch (NotEnoughMoneyException e)
			{
				ConsoleHelper.writeMessage("There aren't enough denominations.");
			}
		}

	}
}
