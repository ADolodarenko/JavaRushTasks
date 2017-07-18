package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s)
	{
		int maxSize = 0;

        if (s != null && !s.isEmpty())
		{
			Set<Character> symbols = new HashSet<>();
			char[] chars = s.toCharArray();

			for (int i = 0; i < chars.length; i++)
			{
				Character character = chars[i];

				if (!symbols.isEmpty())
					if (symbols.contains(character))
					{
						if (maxSize < symbols.size())
							maxSize = symbols.size();

						symbols.clear();
					}

				symbols.add(character);
			}

			if (maxSize < symbols.size())
				maxSize = symbols.size();
		}

		return maxSize;
    }
}
