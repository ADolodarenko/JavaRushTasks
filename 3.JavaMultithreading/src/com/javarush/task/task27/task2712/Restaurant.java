package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by n_alex on 11.05.2017.
 */
public class Restaurant
{
	private static final int ORDER_CREATING_INTERVAL = 100;
	private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
	
	public static void main(String[] args)
	{
		Cook cook = new Cook("Amigo");
		cook.setQueue(orderQueue);
		cook.addObserver(new Waiter());
		new Thread(cook).start();
		
		Cook cook2 = new Cook("Muchacho");
		cook2.setQueue(orderQueue);
		cook.addObserver(new Waiter());
		new Thread(cook2).start();
		
		List<Tablet> tablets = new ArrayList<>();
		for (int i = 0; i < 5; i++)
		{
			Tablet tablet = new Tablet(i + 1);
			tablet.setQueue(orderQueue);
			
			tablets.add(tablet);
		}
		
		Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
		thread.start();
		
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{}
		
		thread.interrupt();
		
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		
		DirectorTablet directorTablet = new DirectorTablet();
		directorTablet.printAdvertisementProfit();
		directorTablet.printCookWorkloading();
		directorTablet.printActiveVideoSet();
		directorTablet.printArchivedVideoSet();
	}
}
