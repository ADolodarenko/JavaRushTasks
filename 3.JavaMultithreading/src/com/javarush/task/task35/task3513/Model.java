package com.javarush.task.task35.task3513;

import java.util.*;

/**
 * Created by n_alex on 23.05.2017.
 */
public class Model
{
	private static final int FIELD_WIDTH = 4;
	private Tile[][] gameTiles;
	
	int score = 0;
	int maxTile = 2;
	
	private Stack<Tile[][]> previousStates;
	private Stack<Integer> previousScores;
	
	boolean isSaveNeeded = true;
	
	public Model()
	{
		gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
		
		resetGameTiles();
		
		previousStates = new Stack<>();
		previousScores = new Stack<>();
	}
	
	public Tile[][] getGameTiles()
	{
		return gameTiles;
	}
	
	private List<Tile> getEmptyTiles()
	{
		List<Tile> result = new ArrayList<>();
		
		for (int i = 0; i < gameTiles.length; i++)
			for (int j = 0; j < gameTiles[i].length; j++)
				if (gameTiles[i][j].isEmpty())
					result.add(gameTiles[i][j]);
		
		return result;
	}
	
	public boolean canMove()
	{
		boolean result = false;
		
		List<Tile> emptyTiles = getEmptyTiles();
		
		if ((emptyTiles != null) && (!emptyTiles.isEmpty()))
			result = true;
		else
		{
			for (int i = 0; i < gameTiles.length; i++)
			{
				for (int j = 1; j < gameTiles[i].length; j++)
					if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
					{
						result = true;
						break;
					}
				
				if (result)
					break;
			}
			
			if (!result)
				for (int i = 1; i < gameTiles.length; i++)
				{
					for (int j = 0; j < gameTiles[i].length; j++)
						if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
						{
							result = true;
							break;
						}
						
					if (result)
						break;
				}
		}
		
		return result;
	}
	
	private void addTile()
	{
		List<Tile> emptyTiles = getEmptyTiles();
		
		if (emptyTiles != null && (!emptyTiles.isEmpty()))
		{
			int index = (int)(Math.random() * emptyTiles.size());
			int value = (Math.random() < 0.9) ? 2 : 4;
			
			Tile tile = emptyTiles.get(index);
			tile.value = value;
		}
	}
	
	public void resetGameTiles()
	{
		for (int i = 0; i < gameTiles.length; i++)
			for (int j = 0; j < gameTiles[i].length; j++)
				gameTiles[i][j] = new Tile();
		
		addTile();
		addTile();
	}
	
	private void rotateToRight()
	{
		for (int i = 0; i < FIELD_WIDTH / 2; i++)
			for (int j = i; j < FIELD_WIDTH - 1 - i; j++)
			{
				Tile tmp = gameTiles[i][j];
				gameTiles[i][j] = gameTiles[FIELD_WIDTH - 1 - j][i];
				gameTiles[FIELD_WIDTH - 1 - j][i] = gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j];
				gameTiles[FIELD_WIDTH - 1 - i][FIELD_WIDTH - 1 - j] = gameTiles[j][FIELD_WIDTH - 1 - i];
				gameTiles[j][FIELD_WIDTH - 1 - i] = tmp;
			}
	}
	
	public void left()
	{
		if (isSaveNeeded)
			saveState(gameTiles);
		
		int k = 0;
		
		for (int i = 0; i < gameTiles.length; i++)
			if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]))
				k++;
		
		if (k > 0)
			addTile();
		
		isSaveNeeded = true;
	}
	
	public void up()
	{
		saveState(gameTiles);
		
		rotateToRight();
		rotateToRight();
		rotateToRight();
		left();
		rotateToRight();
	}
	
	public void right()
	{
		saveState(gameTiles);
		
		rotateToRight();
		rotateToRight();
		left();
		rotateToRight();
		rotateToRight();
	}
	
	public void down()
	{
		saveState(gameTiles);
		
		rotateToRight();
		left();
		rotateToRight();
		rotateToRight();
		rotateToRight();
	}
	
	public void randomMove()
	{
		int number = (int)(Math.random() * 100) % 4;
		
		switch (number)
		{
			case 0:
				left();
				break;
			case 1:
				up();
				break;
			case 2:
				right();
				break;
			case 3:
				down();
		}
	}
	
	private boolean compressTiles(Tile[] tiles)
	{
		boolean result = false;
		
		int[] values = new int[tiles.length];
		for (int i = 0; i < values.length; i++)
			values[i] = 0;
		
		int j = 0;
		for (int i = 0; i < tiles.length; i++)
			if (!tiles[i].isEmpty())
			{
				values[j] = tiles[i].value;
				j++;
			}
			
		for (int i = 0; i < tiles.length; i++)
			if (tiles[i].value != values[i])
			{
				tiles[i].value = values[i];
				
				if (!result)
					result = true;
			}
			
		return result;
	}
	
	private boolean mergeTiles(Tile[] tiles)
	{
		boolean result = false;
		
		for (int i = 1; i < tiles.length; i++)
		{
			int currentValue = tiles[i].value;
			
			if (currentValue > 0 && (currentValue == tiles[i - 1].value))
			{
				int newValue = currentValue * 2;
				
				tiles[i - 1].value = newValue;
				if (newValue > maxTile)
					maxTile = newValue;
				score += newValue;
				
				tiles[i].value = 0;
				
				if (!result)
					result = true;
			}
		}
		
		if (result)
			compressTiles(tiles);
		
		return result;
	}
	
	private void saveState(Tile[][] tiles)
	{
		Tile[][] tilesForSave = new Tile[tiles.length][tiles.length];
		
		for (int i = 0; i < tiles.length; i++)
			for (int j = 0; j < tiles[i].length; j++)
				tilesForSave[i][j] = new Tile(tiles[i][j].value);
		
		previousStates.push(tilesForSave);
		previousScores.push(score);
		
		isSaveNeeded = false;
	}
	
	public void rollback()
	{
		if (!previousStates.empty() && !previousScores.empty())
		{
			gameTiles = previousStates.pop();
			score = previousScores.pop();
		}
	}
	
	private boolean hasBoardChanged()
	{
		boolean result = false;
		
		Tile[][] previousTiles = previousStates.peek();
		
		for (int i = 0; i < gameTiles.length; i++)
		{
			for (int j = 0; j < gameTiles[i].length; j++)
				if (gameTiles[i][j].value != previousTiles[i][j].value)
				{
					result = true;
					break;
				}
				
			if (result)
				break;
		}
		
		return result;
	}
	
	public MoveEfficiency getMoveEfficiency(Move move)
	{
		MoveEfficiency result;
		
		move.move();
		
		if (hasBoardChanged())
			result = new MoveEfficiency(getEmptyTiles().size(), score, move);
		else
			result = new MoveEfficiency(-1, 0, move);
		
		rollback();
		
		return result;
	}
	
	public void autoMove()
	{
		PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
		
		queue.offer(getMoveEfficiency(this::left));
		queue.offer(getMoveEfficiency(this::up));
		queue.offer(getMoveEfficiency(this::right));
		queue.offer(getMoveEfficiency(this::down));
		
		queue.peek().getMove().move();
	}
}
