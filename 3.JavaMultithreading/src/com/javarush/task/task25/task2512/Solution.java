package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
	
		List<Throwable> throwables = new ArrayList<>();
  
		Throwable throwable = e;
        while (throwable != null)
		{
			throwables.add(throwable);
			throwable = throwable.getCause();
		}
	
		for (int i = throwables.size() - 1; i >= 0 ; i--)
		{
			Throwable currentThr = throwables.get(i);
			System.out.println(currentThr.getClass().getName() + ": " + currentThr.getMessage());
		}
	}

    public static void main(String[] args) {
    }
}
