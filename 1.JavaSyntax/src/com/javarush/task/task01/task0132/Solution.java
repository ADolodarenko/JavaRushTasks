package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {
        int result = 0;
        
        while (number > 0)
		{
			int remain = number % 10;
			
			result += remain;
			
			number = number/10;
		}
		
		return result;
    }
}