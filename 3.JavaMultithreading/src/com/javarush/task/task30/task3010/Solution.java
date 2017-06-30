package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args)
    {
        try
        {
            String line = args[0].toUpperCase();
            int minRadix = Character.MIN_RADIX;
        
            for (int i = 0; i < line.length(); i++)
			{
				int c = (int) (line.charAt(i));
		
				if (c < 48 || (c > 57 && c < 65) || c > 90)
				{
					System.out.println("incorrect");
		
					return;
				}
				else
				{
                    int currentRadix = minRadix;
                    
                    if (c < 58)
					{
						currentRadix = Integer.parseInt(String.valueOf((char) c)) + 1;
						
						if (currentRadix < Character.MIN_RADIX)
						    currentRadix = Character.MIN_RADIX;
					}
					else
                    {
                        currentRadix = c - 54;
                    }
                    
                    if (currentRadix > minRadix)
                        minRadix = currentRadix;
				}
			}
    
            System.out.println(minRadix);
        }
        catch (Exception e)
        {}
    }
}