package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject
{
	public CollisionObject(int x, int y)
	{
		super(x, y);
	}

	public boolean isCollision(GameObject gameObject, Direction direction)
	{
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

		return  (this.getX() + dx == gameObject.getX() && this.getY() + dy == gameObject.getY());
	}
}
