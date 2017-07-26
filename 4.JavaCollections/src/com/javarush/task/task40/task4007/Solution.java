package com.javarush.task.task40.task4007;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датами
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date)
	{
		if (date == null || date.isEmpty())
			return;

		boolean datePresented = date.contains(".");
		boolean timePresented = date.contains(":");

		if (!datePresented && !timePresented)
			return;

		DateFormat formatter;

		if (datePresented && !timePresented)
			formatter = new SimpleDateFormat("d.M.yyyy");
		else if (!datePresented && timePresented)
			formatter = new SimpleDateFormat("HH:mm:ss");
		else
			formatter = new SimpleDateFormat("d.M.yyyy HH:mm:ss");

		Calendar calendar = Calendar.getInstance();

		try
		{
			calendar.setTime(formatter.parse(date));
		}
		catch (ParseException e)
		{
			return;
		}

		if (datePresented)
		{
			System.out.println(String.format("День: %d", calendar.get(Calendar.DATE)));

			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			if (dayOfWeek == 0)
				dayOfWeek = 7;

			System.out.println(String.format("День недели: %d", dayOfWeek));
			System.out.println(String.format("День месяца: %d", calendar.get(Calendar.DAY_OF_MONTH)));
			System.out.println(String.format("День года: %d", calendar.get(Calendar.DAY_OF_YEAR)));
			System.out.println(String.format("Неделя месяца: %d", calendar.get(Calendar.WEEK_OF_MONTH)));
			System.out.println(String.format("Неделя года: %d", calendar.get(Calendar.WEEK_OF_YEAR)));
			System.out.println(String.format("Месяц: %d", calendar.get(Calendar.MONTH) + 1));
			System.out.println(String.format("Год: %d", calendar.get(Calendar.YEAR)));
		}

		if (timePresented)
		{
			System.out.println(String.format("AM или PM: %s", calendar.get(Calendar.AM_PM) == 0 ? "AM" : "PM"));
			System.out.println(String.format("Часы: %d", calendar.get(Calendar.HOUR)));
			System.out.println(String.format("Часы дня: %d", calendar.get(Calendar.HOUR_OF_DAY)));
			System.out.println(String.format("Минуты: %d", calendar.get(Calendar.MINUTE)));
			System.out.println(String.format("Секунды: %d", calendar.get(Calendar.SECOND)));
		}
	}
}
