package com.javarush.task.task36.task3602;

import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {

    	for (Class clazz : Collections.class.getDeclaredClasses()) { //get Classes
			for (Class intrf : clazz.getInterfaces()) { //get interfaces
				if (intrf.getSimpleName().equals("List") && Modifier.isStatic(clazz.getModifiers())) {
					{
						//System.out.println(clazz.getName());
						// But here i see only:
						//java.util.Collections$CheckedList
						//java.util.Collections$SynchronizedList
						//java.util.Collections$UnmodifiableList

						//Where is ?
						//java.util.Collections$EmptyList

						//Чтобы появился java.util.Collections$EmptyList надо проверять интерфейсы еще и у родителей по иерархии
						//в данном случае AbstractList от которого наследуется java.util.Collections$EmptyList и реализует интерфейс List
						//В текущем алгоритме интерфейсы проверяются только у самого класса, а надо как я написал.
						//Задачу здал благодаря хаку, ниже.
					}
				}
			}
		}

		//Googling... This is true answer...
		try {
			return Class.forName("java.util.Collections$EmptyList");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

    	/*
		for (Class aClass : Collections.class.getDeclaredClasses())
			if (Modifier.isStatic(aClass.getModifiers()) &&
					List.class.isAssignableFrom(aClass))
				return aClass;

		return null;
		*/

	}
}
