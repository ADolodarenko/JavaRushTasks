package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by n_alex on 12.05.2017.
 */
public class ConsoleHelper
{
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void writeMessage(String message)
	{
		System.out.println(message);
	}
	
	public static String readString() throws IOException
	{
		return reader.readLine();
	}
	
	public static List<Dish> getAllDishesForOrder() throws IOException
	{
		writeMessage(Dish.allDishesToString());
		
		List<Dish> result = new ArrayList<>();
		String line;
		
		writeMessage("Выберите блюдо:");
		
		while (!"exit".equals(line = readString()))
		{
			Dish dish = null;
			
			try
			{
				dish = Dish.valueOf(line);
			}
			catch (IllegalArgumentException e)
			{}
			
			if (dish != null)
				result.add(dish);
			else
				writeMessage("Такого блюда нет.");
			
			writeMessage("Выберите блюдо:");
		}
		
		return result;
	}
}
