package com.javarush.task.task27.task2712.kitchen;

/**
 * Created by n_alex on 11.05.2017.
 */
public enum Dish
{
	Fish(25),
	Steak(30),
	Soup(15),
	Juice(5),
	Water(3);
	
	private int duration;
	
	public static String allDishesToString()
	{
		String result = null;
		
		int length = values().length;
		
		if (length > 0)
		{
			StringBuilder builder = new StringBuilder(values()[0].toString());
			
			for (int i = 1; i < length; i++)
				builder.append(", ").append(values()[i].toString());
			
			result = builder.toString();
		}
		
		return result;
	}
	
	Dish(int duration)
	{
		this.duration = duration;
	}
	
	public int getDuration()
	{
		return duration;
	}
}
