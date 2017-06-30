package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by n_alex on 15.05.2017.
 */
public class StatisticManager
{
	private static StatisticManager ourInstance = new StatisticManager();
	
	private StatisticStorage statisticStorage;
	
	public static StatisticManager getInstance()
	{
		return ourInstance;
	}
	
	private StatisticManager()
	{
		statisticStorage = new StatisticStorage();
	}
	
	public void register(EventDataRow data)
	{
		statisticStorage.put(data);
	}
	
	public Map<Date, Double> calculateAdvAmountByDay()
	{
		Map<Date, Double> result = new HashMap<>();
		
		List<EventDataRow> rows = statisticStorage.get(EventType.SELECTED_VIDEOS);
		
		if (rows != null)
		{
			Calendar calendar = Calendar.getInstance();
			
			for (EventDataRow row : rows)
				if (row instanceof VideoSelectedEventDataRow)
				{
					VideoSelectedEventDataRow videoSelectedRow = (VideoSelectedEventDataRow) row;
					
					calendar.setTime(videoSelectedRow.getDate());
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MILLISECOND, 0);
					
					Date key = calendar.getTime();
					double value = videoSelectedRow.getAmount() * 1d / 100;
					
					if (result.containsKey(key))
						value += result.get(key);
					
					result.put(key, value);
				}
		}
		
		return result;
	}
	
	public Map<Date, Map<String, Integer>> calculateCooksWorkloadByDay()
	{
		Map<Date, Map<String, Integer>> result = new HashMap<>();
		
		List<EventDataRow> rows = statisticStorage.get(EventType.COOKED_ORDER);
		
		if (rows != null)
		{
			Calendar calendar = Calendar.getInstance();
			
			for (EventDataRow row : rows)
				if (row instanceof CookedOrderEventDataRow)
				{
					CookedOrderEventDataRow cookedOrderRow = (CookedOrderEventDataRow) row;
					
					calendar.setTime(cookedOrderRow.getDate());
					calendar.set(Calendar.HOUR_OF_DAY, 0);
					calendar.set(Calendar.MINUTE, 0);
					calendar.set(Calendar.SECOND, 0);
					calendar.set(Calendar.MILLISECOND, 0);
					
					Date key = calendar.getTime();
					String cookName = cookedOrderRow.getCookName();
					int cookingTime = cookedOrderRow.getTime();
					
					Map<String, Integer> cooksResults;
					
					if (result.containsKey(key))
						cooksResults = result.get(key);
					else
					{
						cooksResults = new HashMap<>();
						result.put(key, cooksResults);
					}
					
					if (cooksResults.containsKey(cookName))
						cookingTime += cooksResults.get(cookName);
					
					cooksResults.put(cookName, cookingTime);
				}
		}
		
		return result;
	}
	
	private class StatisticStorage
	{
		private Map<EventType, List<EventDataRow>> storage;
		
		StatisticStorage()
		{
			storage = new HashMap<>();
			
			for (EventType type : EventType.values())
				storage.put(type, new ArrayList<EventDataRow>());
		}
		
		private void put(EventDataRow data)
		{
			if (data != null)
				storage.get(data.getType()).add(data);
		}
		
		private List<EventDataRow> get(EventType type)
		{
			if (storage.containsKey(type))
				return storage.get(type);
			else
				return null;
		}
	}
}
