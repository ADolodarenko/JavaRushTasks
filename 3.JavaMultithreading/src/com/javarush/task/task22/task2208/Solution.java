package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
    	Map<String, String> pairs = new HashMap<>();
    	pairs.put("name", "Ivanov");
    	pairs.put("country", "Ukraine");
    	pairs.put("city", "Kiev");
    	pairs.put("age", null);
	
		System.out.println(getQuery(pairs));
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder builder = new StringBuilder();
        
        for (Map.Entry<String, String> entry : params.entrySet())
		{
			String key = entry.getKey();
			String value = entry.getValue();
			
			if (value != null)
				builder.append(" and ").append(key).append(" = '").append(value).append("'");
		}
		
		if (builder.length() > 0)
			builder.delete(0, 5);
        
        return builder.toString();
    }
}
