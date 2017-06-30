package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] houses = new int[15];
        int evenInhabitants = 0;
        int oddInhabitant = 0;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < houses.length; i++)
        {
            houses[i] = Integer.parseInt(reader.readLine());
            
            if (i % 2 == 0)
                evenInhabitants += houses[i];
            else
                oddInhabitant += houses[i];
        }
        
        reader.close();
        
        if (evenInhabitants > oddInhabitant)
            System.out.println("В домах с четными номерами проживает больше жителей.");
        else
            System.out.println("В домах с нечетными номерами проживает больше жителей.");
    }
}
