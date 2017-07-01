package com.javarush.task.task09.task0906;

/* 
Логирование стек трейса
*/

public class Solution {
    public static void main(String[] args) {
        log("In main method");
    }

    public static void log(String s) {
        StackTraceElement[] frames = Thread.currentThread().getStackTrace();

        if (frames.length >= 3)
		{
			StackTraceElement frame = frames[2];

			System.out.println(frame.getClassName() + ": " + frame.getMethodName() + ": " + s);
		}
    }
}
