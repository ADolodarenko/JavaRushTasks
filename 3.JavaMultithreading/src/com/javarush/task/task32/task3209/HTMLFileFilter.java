package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by n_alex on 08.05.2017.
 */
public class HTMLFileFilter extends FileFilter
{
	@Override
	public boolean accept(File f)
	{
		if (f.isDirectory())
			return true;
		
		String fileName = f.getName().toLowerCase();
		return fileName.endsWith(".html") || fileName.endsWith(".htm");
	}
	
	@Override
	public String getDescription()
	{
		return "HTML и HTM файлы";
	}
}
