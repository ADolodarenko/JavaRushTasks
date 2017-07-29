package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable
{
	public Box(int x, int y)
	{
		super(x, y);
	}

	@Override
	public void draw(Graphics graphics)
	{
		int x1 = getX() - getWidth()/2;
		int y1 = getY() - getHeight()/2;

		graphics.setColor(Color.GREEN);
		graphics.fillRect(x1, y1, getWidth(), getHeight());
	}

	@Override
	public void move(int x, int y)
	{
		setX(getX() + x);
		setY(getY() + y);
	}
}
