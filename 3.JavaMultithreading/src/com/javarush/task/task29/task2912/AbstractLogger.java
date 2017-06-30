package com.javarush.task.task29.task2912;

/**
 * Created by n_alex on 09.05.2017.
 */
public abstract class AbstractLogger implements Logger
{
	int level;
	Logger next;
	
	public AbstractLogger(int level)
	{
		this.level = level;
	}
	
	@Override
	public void inform(String message, int level)
	{
		if (this.level <= level) {
			info(message);
		}
		if (next != null) {
			next.inform(message, level);
		}
	}
	
	@Override
	public void setNext(Logger next)
	{
		this.next = next;
	}
}
