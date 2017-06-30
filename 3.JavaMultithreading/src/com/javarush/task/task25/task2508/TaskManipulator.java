package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread thread;
    
    @Override
    public void run() {
    	Thread currentThread = Thread.currentThread();
    	
    	while (!currentThread.isInterrupted())
		{
			System.out.println(currentThread.getName());
			
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException e)
			{
				break;
			}
		}
    }
    
    @Override
    public void start(String threadName)
    {
    	thread = new Thread(this);
    	thread.setName(threadName);
    	thread.start();
    }
    
    @Override
    public void stop()
    {
    	thread.interrupt();
    }
}
