package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader
{
	public LevelLoader(Path levels)
	{

	}

	public GameObjects getLevel(int level)
	{
		Set<Wall> walls = new HashSet<>();
		walls.add(new Wall(Model.FIELD_CELL_SIZE/2, Model.FIELD_CELL_SIZE/2));
		walls.add(new Wall(Model.FIELD_CELL_SIZE*3/2, Model.FIELD_CELL_SIZE/2));
		walls.add(new Wall(Model.FIELD_CELL_SIZE*5/2, Model.FIELD_CELL_SIZE/2));
		walls.add(new Wall(Model.FIELD_CELL_SIZE*7/2, Model.FIELD_CELL_SIZE/2));

		Set<Home> homes = new HashSet<>();
		homes.add(new Home(Model.FIELD_CELL_SIZE*3, Model.FIELD_CELL_SIZE/2));

		Set<Box> boxes = new HashSet<>();
		boxes.add(new Box(Model.FIELD_CELL_SIZE*2, Model.FIELD_CELL_SIZE));

		Player player = new Player(Model.FIELD_CELL_SIZE, Model.FIELD_CELL_SIZE);

		return new GameObjects(walls, boxes, homes, player);
	}
}
