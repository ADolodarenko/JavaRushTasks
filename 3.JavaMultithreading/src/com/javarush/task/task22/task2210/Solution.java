package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
		System.out.println(Arrays.toString(getTokens("level22.lesson13.task01", ".")));
    }
    public static String [] getTokens(String query, String delimiter) {
    	if (query == null) return null;
    	if (delimiter == null) return new String[]{query};
	
		List<String> stringList = new ArrayList<>();
		
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        while (tokenizer.hasMoreElements())
			stringList.add(tokenizer.nextToken());
		
        String[] stringArray = new String[stringList.size()];
        
        return stringList.toArray(stringArray);
    }
}
