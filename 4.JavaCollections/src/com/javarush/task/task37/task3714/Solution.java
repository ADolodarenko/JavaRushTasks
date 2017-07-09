package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s)
    {
		int sum = 0;

		if (s.length() > 0)
		{
			char[] digits = s.toCharArray();

			Map<Character, Integer> romanMap = new HashMap<>();
			romanMap.put('I', 1);
			romanMap.put('V', 5);
			romanMap.put('X', 10);
			romanMap.put('L', 50);
			romanMap.put('C', 100);
			romanMap.put('D', 500);
			romanMap.put('M', 1000);

			int prev = romanMap.get(digits[0]);

			for (int i = 1; i < digits.length; i++)
			{
				int current = romanMap.get(digits[i]);

				if (current > prev)
					sum -= prev;
				else
					sum += prev;

				prev = current;
			}

			sum += prev;
		}

		return sum;
    }
}
