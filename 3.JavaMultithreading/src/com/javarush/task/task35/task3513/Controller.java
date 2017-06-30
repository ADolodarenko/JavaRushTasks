package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by n_alex on 23.05.2017.
 */
public class Controller extends KeyAdapter
{
	private static final int WINNING_TILE = 2048;
	
	private Model model;
	private View view;
	
	public Controller(Model model)
	{
		this.model = model;
		this.view = new View(this);
	}
	
	public Tile[][] getGameTiles()
	{
		return model.getGameTiles();
	}
	
	public int getScore()
	{
		return model.score;
	}
	
	public View getView()
	{
		return view;
	}
	
	public void resetGame()
	{
		model.score = 0;
		model.maxTile = 2;
		
		view.isGameLost = false;
		view.isGameWon = false;
		
		model.resetGameTiles();
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_ESCAPE)
			resetGame();
		else if (keyCode == KeyEvent.VK_Z)
			model.rollback();
		else if (!model.canMove())
			view.isGameLost = true;
		else if (!view.isGameLost && !view.isGameWon)
		{
			switch (keyCode)
			{
				case KeyEvent.VK_LEFT:
					model.left();
					break;
				case KeyEvent.VK_RIGHT:
					model.right();
					break;
				case KeyEvent.VK_UP:
					model.up();
					break;
				case KeyEvent.VK_DOWN:
					model.down();
					break;
				case KeyEvent.VK_R:
					model.randomMove();
					break;
				case KeyEvent.VK_A:
					model.autoMove();
			}
			
			if (model.maxTile == WINNING_TILE)
				view.isGameWon = true;
		}
		
		view.repaint();
	}
}
