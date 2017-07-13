package com.javarush.task.task39.task3913;

import java.util.Date;

/**
 * Created by n_alex on 11.07.17.
 */
public class LogLine
{
	private String ip;
	private String user;
	private Date date;
	private Event event;
	private Status status;
	private int taskNumber;

	public LogLine(String ip, String user, Date date, Event event, Status status, int taskNumber)
	{
		this.ip = ip;
		this.user = user;
		this.date = date;
		this.event = event;
		this.status = status;
		this.taskNumber = taskNumber;
	}

	public String getIp()
	{
		return ip;
	}

	public String getUser()
	{
		return user;
	}

	public Date getDate()
	{
		return date;
	}

	public Event getEvent()
	{
		return event;
	}

	public Status getStatus()
	{
		return status;
	}

	public int getTaskNumber()
	{
		return taskNumber;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		LogLine logLine = (LogLine) o;

		if (taskNumber != logLine.taskNumber) return false;
		if (ip != null ? !ip.equals(logLine.ip) : logLine.ip != null) return false;
		if (user != null ? !user.equals(logLine.user) : logLine.user != null) return false;
		if (date != null ? !date.equals(logLine.date) : logLine.date != null) return false;
		if (event != logLine.event) return false;
		return status == logLine.status;
	}

	@Override
	public int hashCode()
	{
		int result = ip != null ? ip.hashCode() : 0;
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (event != null ? event.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + taskNumber;
		return result;
	}
}
