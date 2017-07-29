package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Player extends CollisionObject implements Movable
{
	public Player(int x, int y)
	{
		super(x, y);
	}

	@Override
	public void draw(Graphics graphics)
	{
		int x = getX() - getWidth()/2;
		int y = getY() - getHeight()/2;

		graphics.setColor(Color.YELLOW);
		graphics.fillOval(x, y, getWidth(), getHeight());
	}

	@Override
	public void move(int x, int y)
	{
		setX(getX() + x);
		setY(getY() + y);
	}
}
