package com.javarush.task.task36.task3601;

import java.util.ArrayList;
import java.util.List;

/* 
MVC - простая версия
*/
public class Solution {
    private View view;

    public static void main(String[] args) {
    	Solution solution = new Solution();
    	View view = new View();

        view.fireEventShowData();
    }
}
