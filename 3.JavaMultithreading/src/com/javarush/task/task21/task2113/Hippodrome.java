package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n_alex on 16.02.2017.
 */
public class Hippodrome
{
	public static Hippodrome game;
	
	private List<Horse> horses;
	
	public Hippodrome(List<Horse> horses)
	{
		this.horses = horses;
	}
	
	public List<Horse> getHorses()
	{
		return horses;
	}
	
	public void run()
	{
		for (int i = 1; i < 101; i++)
		{
			move();
			print();
			
			try
			{
				Thread.sleep(200);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void move()
	{
		for (Horse horse : horses)
			horse.move();
	}
	
	public void print()
	{
		for (Horse horse : horses)
			horse.print();
		
		for (int i = 0; i < 10; i++)
			System.out.println();
	}
	
	public Horse getWinner()
	{
		Horse result = null;
		double maxDistance = 0.0;
		
		for (Horse horse : horses)
		{
			double currentDistance = horse.getDistance();
			
			if (currentDistance > maxDistance)
			{
				maxDistance = currentDistance;
				result = horse;
			}
		}
		
		return result;
	}
	
	public void printWinner()
	{
		Horse winner = null;
		double maxDistance = 0.0;
		
		for (Horse horse : horses)
		{
			double currentDistance = horse.getDistance();
			
			if (currentDistance > maxDistance)
			{
				maxDistance = currentDistance;
				winner = horse;
			}
		}
		
		System.out.println("Winner is " + winner.getName() + "!");
	}
	
	public static void main(String[] args)
	{
		game = new Hippodrome(new ArrayList<Horse>());
		
		game.getHorses().add(new Horse("Black", 3, 0));
		game.getHorses().add(new Horse("White", 3, 0));
		game.getHorses().add(new Horse("Red", 3, 0));
		
		game.run();
		
		game.printWinner();
	}
}
