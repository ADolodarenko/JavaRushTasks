package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n_alex on 17.05.2017.
 */
public class StatisticAdvertisementManager
{
	private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();
	
	private AdvertisementStorage storage = AdvertisementStorage.getInstance();
	
	public static StatisticAdvertisementManager getInstance()
	{
		return ourInstance;
	}
	
	private StatisticAdvertisementManager()
	{
	}
	
	public List<Advertisement> getActiveAdvertisements()
	{
		List<Advertisement> allAdvs = storage.list();
		List<Advertisement> result = new ArrayList<>();
		
		for (Advertisement advertisement : allAdvs)
			if (advertisement.getHits() > 0)
				result.add(advertisement);
		
		return result;
	}
	
	public List<Advertisement> getArchivedAdvertisements()
	{
		List<Advertisement> allAdvs = storage.list();
		List<Advertisement> result = new ArrayList<>();
		
		for (Advertisement advertisement : allAdvs)
			if (advertisement.getHits() < 1)
				result.add(advertisement);
		
		return result;
	}
}
