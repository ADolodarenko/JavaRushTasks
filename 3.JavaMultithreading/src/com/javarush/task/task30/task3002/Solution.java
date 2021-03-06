package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/
public class Solution {

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16"));
        System.out.println(convertToDecimalSystem("012"));
        System.out.println(convertToDecimalSystem("0b10"));
        System.out.println(convertToDecimalSystem("62"));
    }

    public static String convertToDecimalSystem(String s) {
        if (s.startsWith("0x")) {
            return String.valueOf(Integer.parseInt(s.substring(2, s.length()), 16));
        } else if (s.startsWith("0b")) {
            return String.valueOf(Integer.parseInt(s.substring(2, s.length()), 2));
        } else if (s.startsWith("0")) {
            return String.valueOf(Integer.parseInt(s, 8));
        } else {
            return s;
        }
    }
}
