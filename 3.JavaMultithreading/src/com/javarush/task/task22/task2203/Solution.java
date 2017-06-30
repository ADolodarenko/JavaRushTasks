package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null || string.isEmpty()) throw new TooShortStringException();
        
        int beginIndex = string.indexOf('\t');
        if (beginIndex == -1) throw new TooShortStringException();
        
        int endIndex = string.indexOf('\t', beginIndex + 1);
		if (endIndex == -1) throw new TooShortStringException();
		
		return string.substring(beginIndex + 1, endIndex);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
