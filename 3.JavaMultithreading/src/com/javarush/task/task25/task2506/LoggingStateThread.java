package com.javarush.task.task25.task2506;

/**
 * Created by n_alex on 09.04.2017.
 */
public class LoggingStateThread extends Thread
{
	private Thread target;
	private State state;
	
	public LoggingStateThread(Thread target)
	{
		this.target = target;
	}
	
	@Override
	public void run()
	{
		boolean isFinish = false;
		
		while (!isFinish)
		{
			State currentState = target.getState();
			
			if (currentState != state)
			{
				state = currentState;
				System.out.println(state);
			}
			
			isFinish = state == State.TERMINATED;
		}
	}
}
