package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by n_alex on 07.07.17.
 */
public class FileBucket
{
	private Path path;

	public FileBucket()
	{
		try
		{
			path = Files.createTempFile(null, null);
			Files.deleteIfExists(path);
			Files.createFile(path);

			path.toFile().deleteOnExit();
		}
		catch (IOException e)
		{
			ExceptionHandler.log(e);
		}
	}

	public long getFileSize()
	{
		long size = 0;

		try
		{
			size = Files.size(path);
		}
		catch (IOException e)
		{
			ExceptionHandler.log(e);
		}

		return size;
	}

	public void putEntry(Entry entry)
	{
		try
		{
			OutputStream stream = Files.newOutputStream(path);
			ObjectOutputStream objStream = new ObjectOutputStream(stream);
			objStream.writeObject(entry);

			objStream.close();
		}
		catch (IOException e)
		{
			ExceptionHandler.log(e);
		}
	}

	public Entry getEntry()
	{
		Entry entry = null;

		if (getFileSize() > 0)
			try
			{
				InputStream stream = Files.newInputStream(path);
				ObjectInputStream objStream = new ObjectInputStream(stream);
				entry = (Entry)objStream.readObject();

				objStream.close();
			}
			catch (IOException e)
			{
				ExceptionHandler.log(e);
			}
			catch (ClassNotFoundException e)
			{
				ExceptionHandler.log(e);
			}

		return entry;
	}

	public void remove()
	{
		try
		{
			Files.delete(path);
		}
		catch (IOException e)
		{
			ExceptionHandler.log(e);
		}
	}
}
