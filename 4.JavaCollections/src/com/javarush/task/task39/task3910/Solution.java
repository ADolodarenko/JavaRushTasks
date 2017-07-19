package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args)
	{
		System.out.println(isPowerOfThree(3));
		System.out.println(isPowerOfThree(6));
		System.out.println(isPowerOfThree(9));
		System.out.println(isPowerOfThree(4));
	}

    public static boolean isPowerOfThree(int n)
	{
		if (n == 0)
			return false;

		while ((n % 3) == 0)
			n = n / 3;

		if (n == 1)
			return true;

		return false;
    }
}
