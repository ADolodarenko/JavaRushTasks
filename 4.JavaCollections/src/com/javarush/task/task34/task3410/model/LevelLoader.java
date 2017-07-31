package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader
{
	private Path levels;

	public LevelLoader(Path levels)
	{
		this.levels = levels;
	}

	public GameObjects getLevel(int level)
	{
		Set<Wall> walls = new HashSet<>();
		Set<Box> boxes = new HashSet<>();
		Set<Home> homes = new HashSet<>();
		Player player = null;

		int loopLevel;
		if (level > 60)
			loopLevel = level % 60;
		else
			loopLevel = level;

		try (BufferedReader reader = new BufferedReader(new FileReader(levels.toFile())))
		{
			int readLevel = 0;
			int x;
			int y = Model.FIELD_CELL_SIZE / 2;
			boolean isLevelMap = false;

			String line;
			while ((line = reader.readLine()) != null)
			{
				if (line.contains("Maze:"))
				{
					readLevel = Integer.valueOf(line.split(" ")[1]);
					continue;
				}

				if (readLevel == loopLevel)
				{
					if (line.length() == 0)
					{
						boolean isEnd = isLevelMap;

						isLevelMap = !isLevelMap;

						if (isEnd && !isLevelMap)
							break;
						else
							continue;
					}

					if (isLevelMap)
					{
						x = Model.FIELD_CELL_SIZE / 2;

						char[] chars = line.toCharArray();
						for (char c : chars)
						{
							switch (c)
							{
								case 'X':
									walls.add(new Wall(x, y));
									break;
								case '*':
									boxes.add(new Box(x, y));
									break;
								case '.':
									homes.add(new Home(x, y));
									break;
								case '&':
									boxes.add(new Box(x, y));
									homes.add(new Home(x, y));
									break;
								case '@':
									player = new Player(x, y);
							}
							x += Model.FIELD_CELL_SIZE;
						}
						y += Model.FIELD_CELL_SIZE;
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return new GameObjects(walls, boxes, homes, player);

		/*List<String> lines = new ArrayList<>();

		int mazeNumber = level % 60;
		boolean partFound = false;

		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()));

			while (true)
			{
				String line = reader.readLine();
				if (line == null)
					break;

				if (line.contains("Maze: " + String.valueOf(mazeNumber)))
				{
					partFound = true;
					break;
				}
			}

			if (partFound)
			{
				partFound = false;

				while (true)
				{
					String line = reader.readLine();
					if (line == null)
						break;

					if (line.isEmpty())
					{
						partFound = true;
						break;
					}
				}
			}

			if (partFound)
				while (true)
				{
					String line = reader.readLine();
					if (line == null || line.isEmpty())
						break;

					lines.add(line);
				}

			reader.close();
		}
		catch (IOException e)
		{}

		Set<Wall> walls = new HashSet<>();
		Set<Home> homes = new HashSet<>();
		Set<Box> boxes = new HashSet<>();
		Player player = null;

		if (!lines.isEmpty())
			for (int i = 0; i < lines.size(); i++)
			{
				char[] chars = lines.get(i).toCharArray();

				for (int j = 0; j < chars.length; j++)
				{
					int x = Model.FIELD_CELL_SIZE / 2 + Model.FIELD_CELL_SIZE * j;
					int y = Model.FIELD_CELL_SIZE / 2 + Model.FIELD_CELL_SIZE * i;

					switch (chars[j])
					{
						case 'X':
							walls.add(new Wall(x, y));
							break;
						case '*':
							boxes.add(new Box(x, y));
							break;
						case '.':
							homes.add(new Home(x, y));
							break;
						case '&':
							homes.add(new Home(x, y));
							boxes.add(new Box(x, y));
							break;
						case '@':
							player = new Player(x, y);
							break;
						default:
					}
				}
			}

		return new GameObjects(walls, boxes, homes, player);*/
	}
}
