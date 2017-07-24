package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;
import java.util.Locale;
import java.util.ResourceBundle;

class InfoCommand implements Command
{
	private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.concat("info"), Locale.ENGLISH);

	@Override
	public void execute()
	{
		boolean hasMoney = false;

		Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();

		ConsoleHelper.writeMessage(res.getString("before"));

		if (!manipulators.isEmpty())
		{
			for (CurrencyManipulator manipulator : manipulators)
				if (manipulator.hasMoney())
				{
					ConsoleHelper.writeMessage(manipulator.getCurrencyCode() + " - " + manipulator.getTotalAmount());

					hasMoney = true;
				}
		}

		if (!hasMoney)
			ConsoleHelper.writeMessage(res.getString("no.money"));
	}
}
