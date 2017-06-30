package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by n_alex on 17.05.2017.
 */
public class TestOrder extends Order
{
	public TestOrder(Tablet tablet) throws IOException
	{
		super(tablet);
	}
	
	@Override
	public void initDishes() throws IOException
	{
		dishes = new ArrayList<>();
		
		int dishesQuantity = (int) (Math.random() * 5);
		if (dishesQuantity == 0)
			dishesQuantity++;
		
		for (int i = 0; i < dishesQuantity; i++)
		{
			int number = (int)(Math.random() * Dish.values().length);
			dishes.add(Dish.values()[number]);
		}
	}
}
