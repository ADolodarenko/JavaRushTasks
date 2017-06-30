package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {

    }

    public static Integer[] sort(Integer[] array) {
        int lenght = array.length;
        int index = lenght / 2;
        
        boolean even = (lenght % 2 == 0);
        
        if (even)
            index--;
        
        List<Integer> values = Arrays.asList(array);
    
        Collections.sort(values);
        
        double median = values.get(index).doubleValue();
        if (even)
            median = (median + values.get(++index).intValue())/2;
        
        final double medianValue = median;
        
        Comparator<Integer> medianComparator = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int value1 = o1.intValue();
                int value2 = o2.intValue();
                
                double diff1 = Math.abs(value1 - medianValue);
                double diff2 = Math.abs(value2 - medianValue);
                
                double result = diff1 - diff2;
                
                if (result == 0)
                    result = value1 - value2;
                
                return (int) result;
            }
        };
        
        Collections.sort(values, medianComparator);
        
        Integer[] result = new Integer[lenght];
        
        return values.toArray(result);
    }
}
