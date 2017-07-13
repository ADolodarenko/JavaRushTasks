package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.Date;

public class VeryComplexClass {
    public void methodThrowsClassCastException()
	{
		Object i = Integer.valueOf(42);
		String s = (String)i;
    }

    public void methodThrowsNullPointerException()
    {
		Date date = new Date();
		date = null;
		long millis = date.getTime();
    }

    public static void main(String[] args)
	{
		VeryComplexClass complexClass = new VeryComplexClass();
		complexClass.methodThrowsClassCastException();

    }
}
