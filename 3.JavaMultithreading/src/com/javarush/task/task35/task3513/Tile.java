package com.javarush.task.task35.task3513;

import java.awt.*;

/**
 * Created by n_alex on 23.05.2017.
 */
public class Tile
{
	int value;
	
	public Tile()
	{
		this(0);
	}
	
	public Tile(int value)
	{
		this.value = value;
	}
	
	public boolean isEmpty()
	{
		return value == 0;
	}
	
	public Color getFontColor()
	{
		if (value < 16)
			return new Color(0x776e65);
		else
			return new Color(0xf9f6f2);
	}
	
	public Color getTileColor()
	{
		int rgb;
		
		switch (value)
		{
			case 0:
				rgb = 0xcdc1b4;
				break;
			case 2:
				rgb = 0xeee4da;
				break;
			case 4:
				rgb = 0xede0c8;
				break;
			case 8:
				rgb = 0xf2b179;
				break;
			case 16:
				rgb = 0xf59563;
				break;
			case 32:
				rgb = 0xf67c5f;
				break;
			case 64:
				rgb = 0xf65e3b;
				break;
			case 128:
				rgb = 0xedcf72;
				break;
			case 256:
				rgb = 0xedcc61;
				break;
			case 512:
				rgb = 0xedc850;
				break;
			case 1024:
				rgb = 0xedc53f;
				break;
			case 2048:
				rgb = 0xedc22e;
				break;
			default:
				rgb = 0xff0000;
		}
		
		return new Color(rgb);
	}
}
