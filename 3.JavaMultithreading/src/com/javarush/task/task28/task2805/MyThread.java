package com.javarush.task.task28.task2805;

/**
 * Created by n_alex on 01.05.2017.
 */
public class MyThread extends Thread
{
	private static int priority = 0;
	
	private static void computePriority()
	{
		priority = (priority % 10) + 1;
	}
	
	public MyThread()
	{
		computePriority();
		setPriority(priority);
	}
	
	public MyThread(Runnable target)
	{
		super(target);
		computePriority();
		setPriority(priority);
	}
	
	public MyThread(ThreadGroup group, Runnable target)
	{
		super(group, target);
		computePriority();
		setPriority(priority);
	}
	
	public MyThread(String name)
	{
		super(name);
		computePriority();
		setPriority(priority);
	}
	
	public MyThread(ThreadGroup group, String name)
	{
		super(group, name);
		computePriority();
		setPriority(priority);
	}
	
	public MyThread(Runnable target, String name)
	{
		super(target, name);
		computePriority();
		setPriority(priority);
	}
	
	public MyThread(ThreadGroup group, Runnable target, String name)
	{
		super(group, target, name);
		computePriority();
		setPriority(priority);
	}
	
	public MyThread(ThreadGroup group, Runnable target, String name, long stackSize)
	{
		super(group, target, name, stackSize);
		computePriority();
		setPriority(priority);
	}
}
