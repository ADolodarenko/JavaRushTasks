package com.javarush.task.task23.task2312;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n_alex on 12.03.2017.
 */
public class Snake
{
	private List<SnakeSection> sections;
	private boolean isAlive;
	private SnakeDirection direction;
	
	public Snake(int x, int y)
	{
		sections = new ArrayList<SnakeSection>();
		sections.add(new SnakeSection(x, y));
		
		isAlive = true;
	}
	
	public int getX()
	{
		return sections.get(0).getX();
	}
	
	public int getY()
	{
		return sections.get(0).getY();
	}
	
	public void checkBorders(SnakeSection head)
	{
		int x = head.getX();
		int y = head.getY();
		int width = Room.game.getWidth();
		int height = Room.game.getHeight();
		
		if (x < 0 || y < 0 || x >= width || y >= height) isAlive = false;
	}
	
	public void checkBody(SnakeSection head)
	{
		if (sections.contains(head)) isAlive = false;
	}
	
	public void move()
	{
		if (isAlive)
			switch (direction)
			{
				case UP:
					move(0, -1);
					break;
				case RIGHT:
					move(1, 0);
					break;
				case DOWN:
					move(0, 1);
					break;
				case LEFT:
					move(-1, 0);
			}
	}
	
	public void move(int xOffset, int yOffset)
	{}
	
	public List<SnakeSection> getSections()
	{
		return sections;
	}
	
	public boolean isAlive()
	{
		return isAlive;
	}
	
	public SnakeDirection getDirection()
	{
		return direction;
	}
	
	public void setDirection(SnakeDirection direction)
	{
		this.direction = direction;
	}
}
