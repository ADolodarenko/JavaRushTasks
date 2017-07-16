package com.javarush.task.task38.task3812;

/* 
Обработка аннотаций
*/

public class Solution {
    public static void main(String[] args) {
        printFullyQualifiedNames(Solution.class);
        printFullyQualifiedNames(SomeTest.class);

        printValues(Solution.class);
        printValues(SomeTest.class);
    }

    public static boolean printFullyQualifiedNames(Class c) {
    	if (c.isAnnotationPresent(PrepareMyTest.class))
		{
			PrepareMyTest a = (PrepareMyTest)c.getAnnotation(PrepareMyTest.class);
			for (String name : a.fullyQualifiedNames())
				System.out.println(name);

			return true;
		}
		else
        	return false;
    }

    public static boolean printValues(Class c) {
		if (c.isAnnotationPresent(PrepareMyTest.class))
		{
			PrepareMyTest a = (PrepareMyTest)c.getAnnotation(PrepareMyTest.class);
			for (Class<?> value : a.value())
				System.out.println(value.getSimpleName());

			return true;
		}
		else
			return false;
    }
}
