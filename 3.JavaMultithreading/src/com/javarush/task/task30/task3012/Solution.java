package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        String result = "";
        int n = number;
        int r = 0;
        
        while (n > 2) {
            n = n / 3;
            r = n % 3;
            
            
            
        }
    
        System.out.println(result);
    }
}