package com.javarush.task.task27.task2712;

import java.util.List;

/**
 * Created by n_alex on 17.05.2017.
 */
public class RandomOrderGeneratorTask implements Runnable
{
	private List<Tablet> tablets;
	private int interval;
	
	public RandomOrderGeneratorTask(List<Tablet> tablets, int interval)
	{
		this.tablets = tablets;
		this.interval = interval;
	}
	
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				Tablet tablet = tablets.get((int)(Math.random() * tablets.size()));
				tablet.createTestOrder();
				
				Thread.sleep(interval);
			}
		}
		catch (InterruptedException e)
		{}
	}
}
