package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Wall extends CollisionObject
{
	public Wall(int x, int y)
	{
		super(x, y);
	}

	@Override
	public void draw(Graphics graphics)
	{
		int x = getX() - getWidth()/2;
		int y = getY() - getHeight()/2;

		graphics.setColor(Color.BLUE);
		graphics.fillRect(x, y, getWidth(), getHeight());
	}
}
