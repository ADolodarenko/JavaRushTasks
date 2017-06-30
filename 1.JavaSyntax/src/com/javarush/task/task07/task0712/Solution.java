package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        int maxLength = 0;
        int maxLengthIndex = 0;
        int minLength = Integer.MAX_VALUE;
        int minLengthIndex = 0;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 10; i++)
        {
            String line = reader.readLine();
            
            if (line.length() > maxLength)
            {
                maxLength = line.length();
                maxLengthIndex = i;
            }
            
            if (line.length() < minLength)
            {
                minLength = line.length();
                minLengthIndex = i;
            }
            
            strings.add(line);
        }
        
        reader.close();
        
        if (minLengthIndex < maxLengthIndex)
            System.out.println(strings.get(minLengthIndex));
        else
            System.out.println(strings.get(maxLengthIndex));
    }
}
