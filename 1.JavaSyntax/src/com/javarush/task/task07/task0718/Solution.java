package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++)
            lines.add(reader.readLine());

        reader.close();

        for (int i = 1; i < 10; i++)
            if (lines.get(i).length() < lines.get(i-1).length())
            {
                System.out.println(i);
                break;
            }
    }
}

