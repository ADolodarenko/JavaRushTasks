package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Работа с Java 8 DateTime API
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

        String[] values = date.split(" ");

        boolean datePresented = false;
        boolean timePresented = false;
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();

        if (values[0].contains("."))
		{
			localDate = LocalDate.parse(values[0], DateTimeFormatter.ofPattern("d.M.yyyy"));
			datePresented = true;
		}
		else if (values[0].contains(":"))
		{
			localTime = LocalTime.parse(values[0], DateTimeFormatter.ofPattern("H:m:ss"));
			timePresented = true;
		}

		if (!timePresented && values.length > 1)
		{
			localTime = LocalTime.parse(values[1], DateTimeFormatter.ofPattern("H:m:ss"));
			timePresented = true;
		}

		if (datePresented)
		{
			System.out.println(String.format("День: %d", localDate.getDayOfMonth()));
			System.out.println(String.format("День недели: %d", localDate.getDayOfWeek().getValue()));
			System.out.println(String.format("День месяца: %d", localDate.getDayOfMonth()));
			System.out.println(String.format("День года: %d", localDate.getDayOfYear()));
			System.out.println(String.format("Неделя месяца: %d", localDate.get(WeekFields.of(Locale.getDefault()).weekOfMonth())));
			System.out.println(String.format("Неделя года: %d", localDate.get(WeekFields.of(Locale.getDefault()).weekOfYear())));
			System.out.println(String.format("Месяц: %d", localDate.getMonthValue()));
			System.out.println(String.format("Год: %d", localDate.getYear()));
		}

		if (timePresented)
		{
			System.out.println(String.format("AM или PM: %s", localTime.get(ChronoField.AMPM_OF_DAY) == 0 ? "AM" : "PM"));
			System.out.println(String.format("Часы: %d", localTime.get(ChronoField.HOUR_OF_AMPM)));
			System.out.println(String.format("Часы дня: %d", localTime.get(ChronoField.HOUR_OF_DAY)));
			System.out.println(String.format("Минуты: %d", localTime.getMinute()));
			System.out.println(String.format("Секунды: %d", localTime.getSecond()));
		}
    }
}
