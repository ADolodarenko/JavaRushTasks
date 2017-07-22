package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command
{
	@Override
	public void execute() throws InterruptOperationException
	{
		String currencyCode = ConsoleHelper.askCurrencyCode();
		String[] denominations = ConsoleHelper.getValidTwoDigits(currencyCode);

		int denomination = Integer.parseInt(denominations[0]);
		int quantity = Integer.parseInt(denominations[1]);

		CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
		manipulator.addAmount(denomination, quantity);
	}
}
