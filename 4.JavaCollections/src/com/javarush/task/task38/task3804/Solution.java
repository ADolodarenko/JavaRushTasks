package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args)
	{
		ExceptionFactory.getInstance(ExceptionApplicationMessage.SOCKET_IS_CLOSED);
    }
}