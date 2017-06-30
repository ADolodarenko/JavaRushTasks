package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        double sum = 0.0;
        int quantity = 0;
        int x = -1;
        
        do
        {
            try
            {
                x = Integer.parseInt(reader.readLine());
            }
            catch (NumberFormatException ex)
            {}
            
            if (x != -1)
            {
                sum += x;
                quantity++;
            }
        }
        while (x != -1);
        
        reader.close();
    
        System.out.println(sum/quantity);
    }
}

