package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

class DepositCommand implements Command
{
	private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.concat("deposit"), Locale.ENGLISH);

	@Override
	public void execute() throws InterruptOperationException
	{
		ConsoleHelper.writeMessage(res.getString("before"));

		String currencyCode = ConsoleHelper.askCurrencyCode();
		String[] denominations = ConsoleHelper.getValidTwoDigits(currencyCode);

		try
		{
			int denomination = Integer.parseInt(denominations[0]);
			int quantity = Integer.parseInt(denominations[1]);

			CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
			manipulator.addAmount(denomination, quantity);

			ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denomination * quantity, currencyCode));
		}
		catch (NumberFormatException e)
		{
			ConsoleHelper.writeMessage(res.getString("invalid.data"));
		}
	}
}
