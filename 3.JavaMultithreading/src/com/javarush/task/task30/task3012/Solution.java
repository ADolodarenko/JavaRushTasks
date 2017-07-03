package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {
        StringBuilder stringBuilder = new StringBuilder (number + " =");
    
        List<Integer> integerList = new LinkedList<>( );
        while (number > 0)
        {
            integerList.add(number % 3);
            number /= 3;
        }
        
        ListIterator<Integer> listIterator = integerList.listIterator();
        while (listIterator.hasNext())
        {
            int t = listIterator.next();
            switch (t)
            {
                case 3:
                    listIterator.set(0);
                    if (listIterator.hasNext())
                        integerList.set(listIterator.nextIndex(), integerList.get(listIterator.nextIndex()) + 1);
                    else listIterator.add(1);
                    break;
                case 2:
                    listIterator.set(-1);
                    if (listIterator.hasNext())
                        integerList.set(listIterator.nextIndex(), integerList.get(listIterator.nextIndex()) + 1);
                    else listIterator.add(1);
                    break;
            }
        }
        
        for (int i = 0; i < integerList.size(); i++)
        {
            if (integerList.get (i) != 0)
                stringBuilder.append(integerList.get(i) >= 0 ? " + " : " - ").append(Math.abs((int) Math.pow (3, i) * (integerList.get (i)))).toString();
        }
    
        System.out.println(stringBuilder);
    }
}