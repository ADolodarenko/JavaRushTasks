package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.*;

class WithdrawCommand implements Command
{
	private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.concat("withdraw"), Locale.ENGLISH);

	@Override
	public void execute() throws InterruptOperationException
	{
		ConsoleHelper.writeMessage(res.getString("before"));

		String currencyCode = ConsoleHelper.askCurrencyCode();
		CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

		while (true)
		{
			ConsoleHelper.writeMessage(res.getString("specify.amount"));

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
				ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));

				continue;
			}

			if (!manipulator.isAmountAvailable(sum))
			{
				ConsoleHelper.writeMessage(res.getString("not.enough.money"));

				continue;
			}

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

				ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currencyCode));

				break;
			}
			catch (NotEnoughMoneyException e)
			{
				ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
			}
		}

	}
}
