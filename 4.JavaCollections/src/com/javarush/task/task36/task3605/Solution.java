package com.javarush.task.task36.task3605;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader(args[0]);
        TreeSet<Character> set = new TreeSet<>();

        while (reader.ready())
		{
			int c = reader.read();
			if (Character.isAlphabetic(c))
				set.add(Character.toLowerCase((char) c));
		}

        reader.close();

        StringBuilder builder = new StringBuilder();
		int i = 0;

		for (Character character : set)
		{
			if (i == 5)
				break;

			builder.append(character);

			i++;
		}

		System.out.println(builder.toString());
    }
}
