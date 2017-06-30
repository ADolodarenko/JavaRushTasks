package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by n_alex on 11.05.2017.
 */
public class Tablet
{
	final int number;
	private static Logger logger = Logger.getLogger(Tablet.class.getName());
	private LinkedBlockingQueue<Order> queue = new LinkedBlockingQueue<>();
	
	public Tablet(int number)
	{
		this.number = number;
	}
	
	public void setQueue(LinkedBlockingQueue<Order> queue)
	{
		this.queue = queue;
	}
	
	public Order createOrder()
	{
		Order result = null;
		
		try
		{
			result = new Order(this);
			
			publishOrder(result);
		}
		catch (IOException e)
		{
			logger.log(Level.SEVERE, "Console is unavailable.");
		}
		catch (NoVideoAvailableException e)
		{
			logger.log(Level.INFO, "No video is available for the order " + result);
		}
		
		return result;
	}
	
	private void publishOrder(Order result)
	{
		if (!result.isEmpty())
		{
			ConsoleHelper.writeMessage(result.toString());
			
			try
			{
				queue.put(result);
			}
			catch (InterruptedException e)
			{}
			
			new AdvertisementManager(result.getTotalCookingTime() * 60).processVideos();
		}
	}
	
	public void createTestOrder()
	{
		Order order = null;
		
		try
		{
			order = new TestOrder(this);
			
			publishOrder(order);
		}
		catch (IOException e)
		{
			logger.log(Level.SEVERE, "Console is unavailable.");
		}
		catch (NoVideoAvailableException e)
		{
			logger.log(Level.INFO, "No video is available for the order " + order);
		}
	}
	
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "{number=" + number + "}";
	}
}
