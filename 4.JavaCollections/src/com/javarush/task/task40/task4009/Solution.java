package com.javarush.task.task40.task4009;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

/* 
Buon Compleanno!
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(weekDayOfBirthday("30.05.1984", "2015"));
    }

    public static String weekDayOfBirthday(String birthday, String year)
    {
        LocalDate birthDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("d.M.yyyy"));
        Year destYear = Year.parse(year);

        LocalDate destDate = birthDate.withYear(destYear.getValue());
        return destDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALIAN);
    }
}
