package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Home;
import com.javarush.task.task34.task3410.model.Player;
import com.javarush.task.task34.task3410.model.Box;
import com.javarush.task.task34.task3410.model.Wall;

import javax.swing.*;
import java.awt.*;

public class Field extends JPanel
{
	private View view;

	private EventListener eventListener;

	public Field(View view)
	{
		this.view = view;
	}

	public void setEventListener(EventListener eventListener)
	{
		this.eventListener = eventListener;
	}

	public void paint(Graphics g)
	{
		Player player = new Player(100, 100);
		player.draw(g);

		Box box = new Box(130, 130);
		box.draw(g);

		Home home = new Home(140, 140);
		home.draw(g);

		Wall wall = new Wall(60, 60);
		wall.draw(g);
	}
}
