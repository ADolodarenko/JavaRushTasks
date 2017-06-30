package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush — лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
    	if (string == null || string.isEmpty()) throw new TooShortStringException();
    	    	
        int startIndex = string.indexOf(' ');
        if (startIndex < 0) throw new TooShortStringException();
        
        int endIndex = string.length();
        
        int spaceCount = 0;
        for (int i = 0; i < string.length(); i++)
		{
			if (string.charAt(i) == ' ')
			{
				spaceCount++;
				
				if (spaceCount == 5)
				{
					endIndex = i;
					break;
				}
			}
		}
		
		if (spaceCount < 4) throw new TooShortStringException();
        
        return string.substring(startIndex + 1, endIndex);
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
