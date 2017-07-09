package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

/**
 * Created by n_alex on 09.07.17.
 */
public class RedShapeDecorator extends ShapeDecorator
{
	public RedShapeDecorator(Shape decoratedShape)
	{
		super(decoratedShape);
	}

	@Override
	public void draw()
	{
		setBorderColor(this.decoratedShape);
		super.draw();
	}

	private void setBorderColor(Shape shape)
	{
		System.out.println("Setting border color for " + shape.getClass().getSimpleName() + " to red.");
	}
}
