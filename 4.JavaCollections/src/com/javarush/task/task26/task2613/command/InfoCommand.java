package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.Collection;

class InfoCommand implements Command
{
	@Override
	public void execute()
	{
		boolean hasMoney = false;

		Collection<CurrencyManipulator> manipulators = CurrencyManipulatorFactory.getAllCurrencyManipulators();

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
			ConsoleHelper.writeMessage("No money available.");
	}
}
