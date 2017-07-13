package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery
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

	@Override
	public Set<String> getAllUsers()
	{
		Set<String> users = new HashSet<>();

		List<LogLine> lines = getAllLines();
		for (LogLine line : lines)
			users.add(line.getUser());

		return users;
	}

	@Override
	public int getNumberOfUsers(Date after, Date before)
	{
		Set<String> users = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			users.add(line.getUser());

		return users.size();
	}

	@Override
	public int getNumberOfUserEvents(String user, Date after, Date before)
	{
		Set<Event> events = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getUser().equals(user))
				events.add(line.getEvent());

		return events.size();
	}

	@Override
	public Set<String> getUsersForIP(String ip, Date after, Date before)
	{
		Set<String> users = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getIp().equals(ip))
				users.add(line.getUser());

		return users;
	}

	@Override
	public Set<String> getLoggedUsers(Date after, Date before)
	{
		return getUsersByEvent(Event.LOGIN, after, before);
	}

	@Override
	public Set<String> getDownloadedPluginUsers(Date after, Date before)
	{
		return getUsersByEvent(Event.DOWNLOAD_PLUGIN, after, before);
	}

	@Override
	public Set<String> getWroteMessageUsers(Date after, Date before)
	{
		return getUsersByEvent(Event.WRITE_MESSAGE, after, before);
	}

	@Override
	public Set<String> getSolvedTaskUsers(Date after, Date before)
	{
		return getUsersByEvent(Event.SOLVE_TASK, after, before);
	}

	@Override
	public Set<String> getSolvedTaskUsers(Date after, Date before, int task)
	{
		return getUsersByEvent(Event.SOLVE_TASK, task, after, before);
	}

	@Override
	public Set<String> getDoneTaskUsers(Date after, Date before)
	{
		return getUsersByEvent(Event.DONE_TASK, after, before);
	}

	@Override
	public Set<String> getDoneTaskUsers(Date after, Date before, int task)
	{
		return getUsersByEvent(Event.DONE_TASK, task, after, before);
	}

	private Set<String> getUsersByEvent(Event event, Date after, Date before)
	{
		Set<String> users = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getEvent() == event)
				users.add(line.getUser());

		return users;
	}

	private Set<String> getUsersByEvent(Event event, int taskNumber, Date after, Date before)
	{
		Set<String> users = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getEvent() == event && line.getTaskNumber() == taskNumber)
				users.add(line.getUser());

		return users;
	}

	@Override
	public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before)
	{
		Set<Date> dates = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getUser().equals(user) && line.getEvent() == event)
				dates.add(line.getDate());

		return dates;
	}

	@Override
	public Set<Date> getDatesWhenSomethingFailed(Date after, Date before)
	{
		return getDatesByStatus(Status.FAILED, after, before);
	}

	@Override
	public Set<Date> getDatesWhenErrorHappened(Date after, Date before)
	{
		return getDatesByStatus(Status.ERROR, after, before);
	}

	@Override
	public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before)
	{
		TreeSet<Date> dates = new TreeSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getUser().equals(user) && line.getEvent() == Event.LOGIN)
				dates.add(line.getDate());

		return dates.isEmpty() ? null : dates.first();
	}

	@Override
	public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before)
	{
		return getFirstDateByUserEvent(user, Event.SOLVE_TASK, task, after, before);
	}

	@Override
	public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before)
	{
		return getFirstDateByUserEvent(user, Event.DONE_TASK, task, after, before);
	}

	@Override
	public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before)
	{
		return getDatesForUserAndEvent(user, Event.WRITE_MESSAGE, after, before);
	}

	@Override
	public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before)
	{
		return getDatesForUserAndEvent(user, Event.DOWNLOAD_PLUGIN, after, before);
	}

	private Set<Date> getDatesByStatus(Status status, Date after, Date before)
	{
		Set<Date> dates = new HashSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getStatus() == status)
				dates.add(line.getDate());

		return dates;
	}

	private Date getFirstDateByUserEvent(String user, Event event, int task, Date after, Date before)
	{
		TreeSet<Date> dates = new TreeSet<>();

		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getUser().equals(user) &&
					line.getEvent() == event &&
					line.getTaskNumber() == task)
				dates.add(line.getDate());

		return dates.isEmpty() ? null : dates.first();
	}
	
	@Override
	public int getNumberOfAllEvents(Date after, Date before)
	{
		return getAllEvents(after, before).size();
	}
	
	@Override
	public Set<Event> getAllEvents(Date after, Date before)
	{
		Set<Event> events = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			events.add(line.getEvent());
		
		return events;
	}
	
	@Override
	public Set<Event> getEventsForIP(String ip, Date after, Date before)
	{
		Set<Event> events = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getIp().equals(ip))
				events.add(line.getEvent());
		
		return events;
	}
	
	@Override
	public Set<Event> getEventsForUser(String user, Date after, Date before)
	{
		Set<Event> events = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getUser().equals(user))
				events.add(line.getEvent());
		
		return events;
	}
	
	@Override
	public Set<Event> getFailedEvents(Date after, Date before)
	{
		return getEventsByStatus(Status.FAILED, after, before);
	}
	
	@Override
	public Set<Event> getErrorEvents(Date after, Date before)
	{
		return getEventsByStatus(Status.ERROR, after, before);
	}
	
	@Override
	public int getNumberOfAttemptToSolveTask(int task, Date after, Date before)
	{
		return getNumberOfEventsByTask(Event.SOLVE_TASK, task, after, before);
	}
	
	@Override
	public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before)
	{
		return getNumberOfEventsByTask(Event.DONE_TASK, task, after, before);
	}
	
	@Override
	public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before)
	{
		return getTasksAndTheirNumberByEvent(Event.SOLVE_TASK, after, before);
	}
	
	@Override
	public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before)
	{
		return getTasksAndTheirNumberByEvent(Event.DONE_TASK, after, before);
	}
	
	private Set<Event> getEventsByStatus(Status status, Date after, Date before)
	{
		Set<Event> events = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getStatus() == status)
				events.add(line.getEvent());
		
		return events;
	}
	
	private int getNumberOfEventsByTask(Event event, int task, Date after, Date before)
	{
		int quantity = 0;
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getEvent() == event && line.getTaskNumber() == task)
				quantity++;
		
		return quantity;
	}
	
	private Map<Integer, Integer> getTasksAndTheirNumberByEvent(Event event, Date after, Date before)
	{
		Map<Integer, Integer> events = new HashMap<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			if (line.getEvent() == event)
			{
				int taskNumber = line.getTaskNumber();
				
				if (taskNumber > 0)
				{
					int quantity = 1;
					
					if (events.containsKey(taskNumber))
						quantity += events.get(taskNumber);
					
					events.put(taskNumber, quantity);
				}
			}
		
		return events;
	}
	
	@Override
	public Set<Object> execute(String query)
	{
		Set<Object> result = new HashSet<>();
		
		if (query != null && !query.isEmpty())
		{
			Pattern pattern = Pattern.compile("get (ip|user|date|event|status)" +
					"( for (ip|user|date|event|status) = \"(.*?)\")?" +
					"( and date between \"(.*?)\" and \"(.*?)\")?");
			Matcher matcher = pattern.matcher(query);
			
			if (matcher.find())
			{
				String field1 = matcher.group(1);
				String field2 = matcher.group(3);
				String value1 = matcher.group(4);
				String strAfter = matcher.group(6);
				String strBefore = matcher.group(7);

				Date after = null;
				if (strAfter != null)
				{
					try
					{
						after = new SimpleDateFormat("dd.MM.yyyy H:mm:ss").parse(strAfter);
					}
					catch (ParseException e)
					{
						after = null;
					}

					if (after == null)
						try
						{
							after = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(strAfter);
						}
						catch (ParseException e)
						{
							after = null;
						}
				}

				Date before = null;
				if (strBefore != null)
				{
					try
					{
						before = new SimpleDateFormat("dd.MM.yyyy H:mm:ss").parse(strBefore);
					}
					catch (ParseException e)
					{
						before = null;
					}

					if (before == null)
						try
						{
							before = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(strBefore);
						}
						catch (ParseException e)
						{
							before = null;
						}
				}
				
				switch (field1)
				{
					case "ip":
						result.addAll(getAllIPs(field2, value1, after, before));
						break;
					case "user":
						result.addAll(getAllUsers(field2, value1, after, before));
						break;
					case "date":
						result.addAll(getAllDates(field2, value1, after, before));
						break;
					case "event":
						result.addAll(getAllEvents(field2, value1, after, before));
						break;
					case "status":
						result.addAll(getAllStates(field2, value1, after, before));
						break;
				}
			}
		}
		
		return result;
	}
	
	private Set<String> getAllIPs(String field, String value, Date after, Date before)
	{
		Set<String> result = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);

		for (LogLine line : lines)
			try
			{
				if (matches(line, field, value))
					result.add(line.getIp());
			}
			catch (ParseException e)
			{}
		
		return result;
	}
	
	private Set<String> getAllUsers(String field, String value, Date after, Date before)
	{
		Set<String> result = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			try
			{
				if (matches(line, field, value))
					result.add(line.getUser());
			}
			catch (ParseException e)
			{}
		
		return result;
	}
	
	private Set<Date> getAllDates(String field, String value, Date after, Date before)
	{
		Set<Date> result = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			try
			{
				if (matches(line, field, value))
					result.add(line.getDate());
			}
			catch (ParseException e)
			{}
		
		return result;
	}
	
	private Set<Event> getAllEvents(String field, String value, Date after, Date before)
	{
		Set<Event> result = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			try
			{
				if (matches(line, field, value))
					result.add(line.getEvent());
			}
			catch (ParseException e)
			{}
		
		return result;
	}
	
	private Set<Status> getAllStates(String field, String value, Date after, Date before)
	{
		Set<Status> result = new HashSet<>();
		
		List<LogLine> lines = getLinesInPeriod(after, before);
		for (LogLine line : lines)
			try
			{
				if (matches(line, field, value))
					result.add(line.getStatus());
			}
			catch (ParseException e)
			{}
		
		return result;
	}
	
	private boolean matches(LogLine line, String field, String value) throws ParseException
	{
		boolean result = false;
		
		if (line != null)
		{
			if (field == null)
				result = true;
			else if (value != null)
			{
				switch (field)
				{
					case "ip":
						result = line.getIp().equals(value);
						break;
					case "user":
						result = line.getUser().equals(value);
						break;
					case "date":
						result = line.getDate().equals(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(value));
						break;
					case "event":
						result = line.getEvent() == Event.valueOf(value);
						break;
					case "status":
						result = line.getStatus() == Status.valueOf(value);
						break;
				}
			}
		}
		
		return result;
	}
}