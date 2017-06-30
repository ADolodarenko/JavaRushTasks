package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        int number = 0;
        
        try
        {
            number = Integer.parseInt(reader.readLine());
        }
        catch (Exception ex)
        {}
        
        reader.close();
        
        do
        {
            int digit = number % 10;
            
            if (digit % 2 == 0)
                even++;
            else
                odd++;
            
            number /= 10;
        }
        while (number > 0);
    
        System.out.println(String.format("Even: %d Odd: %d", even, odd));
    }
}
