package com.javarush.task.task38.task3804;

/**
 * Created by n_alex on 13.07.17.
 */
public class ExceptionFactory
{
	public static Throwable getInstance(Enum e)
	{
		Throwable result;

		if (e != null)
		{
			Class enumClass = e.getClass();
			String enumName = e.name();

			if (enumName.length() > 1)
			{
				enumName = enumName.toLowerCase().replace('_', ' ');
				enumName = enumName.substring(0, 1).toUpperCase() + enumName.substring(1);
			}


			if (enumClass.equals(ExceptionApplicationMessage.class))
				result = new Exception(enumName);
			else if (enumClass.equals(ExceptionDBMessage.class))
				result = new RuntimeException(enumName);
			else if (enumClass.equals(ExceptionUserMessage.class))
				result = new Error(enumName);
			else
				result = new IllegalArgumentException();
		}
		else
			result = new IllegalArgumentException();

		return result;
	}
}
