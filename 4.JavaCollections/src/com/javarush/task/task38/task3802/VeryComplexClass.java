package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.text.SimpleDateFormat;
import java.util.Date;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("XXX");
    }

    public static void main(String[] args)
    {

    }
}
