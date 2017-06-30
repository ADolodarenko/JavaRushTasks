package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by n_alex on 13.05.2017.
 */
public class Cook extends Observable implements Runnable
{
	private String name;
	private volatile boolean busy;
	private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();
	
	public Cook(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	public boolean isBusy()
	{
		return busy;
	}
	
	public void setQueue(LinkedBlockingQueue<Order> queue)
	{
		this.queue = queue;
	}
	
	public void startCookingOrder(Order order)
	{
		busy = true;
		
		ConsoleHelper.writeMessage("Start cooking - " + order);
		
		try
		{
			Thread.sleep(order.getTotalCookingTime() * 10);
		}
		catch (InterruptedException e)
		{}
		
		String tabletName = order.getTablet().toString();
		int cookingTime = order.getTotalCookingTime() * 60;
		List<Dish> dishes = order.getDishes();
		
		StatisticManager.getInstance().register(new CookedOrderEventDataRow(tabletName, name, cookingTime, dishes));
		
		setChanged();
		notifyObservers(order);
		
		busy = false;
	}
	
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				if (!queue.isEmpty() && !isBusy())
						startCookingOrder(queue.take());
				
				Thread.sleep(10);
			}
		}
		catch (InterruptedException e)
		{}
	}
}
