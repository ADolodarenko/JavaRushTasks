package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginCommand implements Command
{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.concat(".verifiedCards"));
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH.concat("login"), Locale.ENGLISH);

    @Override
    public void execute() throws InterruptOperationException
    {
        ConsoleHelper.writeMessage(res.getString("before"));

        while (true)
        {
            ConsoleHelper.writeMessage(res.getString("specify.data"));

            String number = ConsoleHelper.readString();
            boolean isCorrect = (number != null && number.matches("^[0-9]{12}$"));

            if (!isCorrect)
            {
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));

                continue;
            }

            String pin = ConsoleHelper.readString();
            isCorrect = (pin != null && pin.matches("^[0-9]{4}$"));

            if (!isCorrect)
            {
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));

                continue;
            }

            if (validCreditCards.containsKey(number) && pin.equals(validCreditCards.getString(number)))
            {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), number));

                break;
            }
        }
    }
}
