package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

/**
 * Created by n_alex on 20.05.2017.
 */
public class Consumer implements Runnable
{
	private TransferQueue<ShareItem> queue;
	
	public Consumer(TransferQueue<ShareItem> queue)
	{
		this.queue = queue;
	}
	
	
	@Override
	public void run()
	{
		try
		{
			Thread.sleep(450);
			
			while (!Thread.interrupted())
			{
				ShareItem item = queue.take();
				System.out.format("Processing %s", item.toString());
			}
		}
		catch (InterruptedException e)
		{}
	}
}
