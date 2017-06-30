package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

/**
 * Created by n_alex on 21.05.2017.
 */
public class BinaryRepresentationTask extends RecursiveTask<String>
{
	private int x;
	
	public BinaryRepresentationTask(int x)
	{
		this.x = x;
	}
	
	@Override
	protected String compute()
	{
		int a = x % 2;
		int b = x / 2;
		
		String result = String.valueOf(a);
		
		if (b > 0)
		{
			BinaryRepresentationTask task = new BinaryRepresentationTask(b);
			task.fork();
			
			result = task.join() + result;
		}
		
		return result;
	}
}
