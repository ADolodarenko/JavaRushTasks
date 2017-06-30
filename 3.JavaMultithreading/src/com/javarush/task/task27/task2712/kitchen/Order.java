package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by n_alex on 11.05.2017.
 */
public class Order
{
	private final Tablet tablet;
	protected List<Dish> dishes;
	
	public Order(Tablet tablet) throws IOException
	{
		this.tablet = tablet;
		initDishes();
	}
	
	protected void initDishes() throws IOException
	{
		dishes = ConsoleHelper.getAllDishesForOrder();
	}
	
	@Override
	public String toString()
	{
		String result = null;
		
		if (dishes.size() > 0)
		{
			StringBuilder builder = new StringBuilder("Your order:[");
			
			builder.append(dishes.get(0));
			for (int i = 1; i < dishes.size(); i++)
				builder.append(", ").append(dishes.get(i));
			
			builder.append("] of ").append(tablet);
			builder.append(", cooking time ").append(getTotalCookingTime()).append("min");
			
			result = builder.toString();
		}
		
		return result;
	}
	
	public int getTotalCookingTime()
	{
		int result = 0;
		
		for (Dish dish : dishes)
			result += dish.getDuration();
		
		return result;
	}
	
	public boolean isEmpty()
	{
		return dishes.isEmpty();
	}
	
	public List<Dish> getDishes()
	{
		return dishes;
	}
	
	public Tablet getTablet()
	{
		return tablet;
	}
}
