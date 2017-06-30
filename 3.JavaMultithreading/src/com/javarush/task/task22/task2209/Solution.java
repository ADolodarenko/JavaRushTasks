package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException
	{
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader file = new BufferedReader(new FileReader(console.readLine()));
        console.close();
		
		List<String> words = new ArrayList<>();
        String line;
        while ((line = file.readLine()) != null)
		{
			String[] values = line.split(" ");
			words.addAll(Arrays.asList(values));
		}
		file.close();
        
        String[] wordsArray = new String[words.size()];
		
        StringBuilder result = getLine(words.toArray(wordsArray));
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        StringBuilder builder = new StringBuilder();
	
        List<String> strings = new ArrayList<>();
		Collections.addAll(strings, words);
		
		if (strings.size() > 0)
		{
			if (strings.size() == 1)
				builder.append(strings.get(0));
			else
			{
				while ( !listIsAccurate(strings) )
					Collections.shuffle(strings);
				
				builder.append(strings.get(0));
				
				for (int i = 1; i < strings.size(); i++)
					builder.append(" ").append(strings.get(i));
			}
		}
		
    	return builder;
    }
    
    public static boolean listIsAccurate(List<String> list)
	{
		for (int i = 0; i < list.size() - 1; i++)
		{
			String thisWord = new StringBuilder(list.get(i).toLowerCase()).reverse().toString();
			String nextWord = list.get(i + 1).toLowerCase();
			
			if (thisWord.charAt(0) != nextWord.charAt(0))
				return false;
		}
		
		return true;
	}
}
