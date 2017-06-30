package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<Integer>();
		int max = 1, temp = 1;

		for (int i = 0; i < 10; i++)
			list.add(Integer.valueOf(reader.readLine()));

		for (int j = 0; j < list.size() - 1; j++)
		{
			if (list.get(j) != (list.get(j + 1)))
				temp = 1;
			else
				temp++;

			if (temp > max)
				max = temp;
		}

		System.out.println(max);
	}
}