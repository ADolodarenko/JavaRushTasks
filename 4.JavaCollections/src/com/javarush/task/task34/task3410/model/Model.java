package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;

public class Model
{
	public static final int FIELD_CELL_SIZE = 20;

	private EventListener eventListener;
	private GameObjects gameObjects;
	private int currentLevel = 1;
	private LevelLoader levelLoader = new LevelLoader(Paths.get(Paths.get(this.getClass().getResource("").getPath()).getParent().toString() + "/res/levels.txt"));

	public void setEventListener(EventListener eventListener)
	{
		this.eventListener = eventListener;
	}

	public GameObjects getGameObjects()
	{
		return gameObjects;
	}

	public void restartLevel(int level)
	{
		gameObjects = levelLoader.getLevel(level);
	}

	public void restart()
	{
		restartLevel(currentLevel);
	}

	public void startNextLevel()
	{
		currentLevel++;
		restart();
	}

	public void move(Direction direction)
	{
		Player player = gameObjects.getPlayer();

		if (checkWallCollision(player, direction))
			return;

		if (checkBoxCollisionAndMoveIfAvaliable(direction))
			return;

		int dx = 0, dy = 0;
		switch (direction)
		{
			case LEFT:
				dx = -Model.FIELD_CELL_SIZE;
				break;
			case UP:
				dy = -Model.FIELD_CELL_SIZE;
				break;
			case RIGHT:
				dx = Model.FIELD_CELL_SIZE;
				break;
			case DOWN:
				dy = Model.FIELD_CELL_SIZE;
		}

		player.move(dx, dy);

		checkCompletion();
	}

	public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
	{
		for (Wall wall : gameObjects.getWalls())
			if (gameObject.isCollision(wall, direction))
				return true;

		return false;
	}

	public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction)
	{
		Player player = gameObjects.getPlayer();
		GameObject stoped = null;
		for (GameObject gameObject : gameObjects.getAll()) {
			if (!(gameObject instanceof Player) && !(gameObject instanceof Home) && player.isCollision(gameObject, direction)) {
				stoped = gameObject;
			}
		}

		if ((stoped == null)) {
			return false;
		}

		if (stoped instanceof Box) {
			Box stopedBox = (Box) stoped;
			if (checkWallCollision(stopedBox, direction)) {
				return true;
			}
			for (Box box : gameObjects.getBoxes()) {
				if (stopedBox.isCollision(box, direction)) {
					return true;
				}
			}

			switch (direction) {
				case LEFT:
					stopedBox.move(-FIELD_CELL_SIZE, 0);
					break;
				case RIGHT:
					stopedBox.move(FIELD_CELL_SIZE, 0);
					break;
				case UP:
					stopedBox.move(0, -FIELD_CELL_SIZE);
					break;
				case DOWN:
					stopedBox.move(0, FIELD_CELL_SIZE);
			}
		}
		return false;
	}

	public void checkCompletion()
	{
		for (Home home : gameObjects.getHomes())
		{
			boolean homeIsDone = false;

			for (Box box : gameObjects.getBoxes())
				if (box.getX() == home.getX() && box.getY() == home.getY())
				{
					homeIsDone = true;
					break;
				}

			if (!homeIsDone)
				return;
		}

		eventListener.levelCompleted(currentLevel);
	}
}
