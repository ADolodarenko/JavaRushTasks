package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery
{
	private Path logDir;

	public LogParser(Path logDir)
	{
		this.logDir = logDir;
	}

	@Override
	public int getNumberOfUniqueIPs(Date after, Date before)
	{
		return getUniqueIPs(after, before).size();
	}

	@Override
	public Set<String> getUniqueIPs(Date after, Date before)
	{
		Set<String> ips = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			ips.add(line.getIp());

		return ips;
	}

	@Override
	public Set<String> getIPsForUser(String user, Date after, Date before)
	{
		Set<String> ips = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getUser().equals(user))
				ips.add(line.getIp());

		return ips;
	}

	@Override
	public Set<String> getIPsForEvent(Event event, Date after, Date before)
	{
		Set<String> ips = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getEvent() == event)
				ips.add(line.getIp());

		return ips;
	}

	@Override
	public Set<String> getIPsForStatus(Status status, Date after, Date before)
	{
		Set<String> ips = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getStatus() == status)
				ips.add(line.getIp());

		return ips;
	}

	private List<LogLine> getLinesInPeriod(Date after, Date before)
	{
		List<LogLine> lines = getAllLines();

		if (lines.size() > 0)
		{
			Iterator<LogLine> iterator = lines.iterator();

			while (iterator.hasNext())
			{
				LogLine line = iterator.next();

				Date date = line.getDate();

				if (after != null && (date.before(after)))
					iterator.remove();
				else if (before != null && (date.after(before)))
					iterator.remove();
			}
		}

		return lines;
	}

	private List<LogLine> getAllLines()
	{
		List<LogLine> lines = new ArrayList<>();

		File[] files = logDir.toFile().listFiles();
		if (files != null)
			for (File file : files)
				if (file.getName().endsWith(".log"))
					loadLinesFromFile(file, lines);

		return lines;
	}

	private void loadLinesFromFile(File file, List<LogLine> lines)
	{
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String str;
			while ((str = reader.readLine()) != null)
			{
				String[] elements = str.split("\t");

				if (elements.length == 5)
					try
					{
						String ip = elements[0];
						String user = elements[1];
						Date date = format.parse(elements[2]);

						String[] eventPars = elements[3].split(" ");
						Event event = Event.valueOf(eventPars[0]);
						int taskNumber = eventPars.length > 1 ? Integer.parseInt(eventPars[1]) : 0;

						Status status = Status.valueOf(elements[4]);

						lines.add(new LogLine(ip, user, date, event, status, taskNumber));
					}
					catch (Exception e)
					{}
			}
		}
		catch (IOException e)
		{}
	}
}