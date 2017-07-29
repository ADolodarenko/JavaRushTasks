package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject
{
	public Home(int x, int y)
	{
		super(x, y, 2, 2);
	}

	@Override
	public void draw(Graphics graphics)
	{
		int x = getX() - getWidth()/2;
		int y = getY() - getHeight()/2;

		graphics.setColor(Color.RED);
		graphics.drawOval(x, y, getWidth(), getHeight());
	}
}
