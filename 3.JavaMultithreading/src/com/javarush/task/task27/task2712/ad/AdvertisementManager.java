package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;

/**
 * Created by n_alex on 13.05.2017.
 */
public class AdvertisementManager
{
	private int timeSeconds;
	private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
	
	public AdvertisementManager(int timeSeconds)
	{
		this.timeSeconds = timeSeconds;
	}
	
	public void processVideos()
	{
		List<Advertisement> allAdvs = storage.list();
		int size = allAdvs.size();
		
		ArrayList<Advertisement> showedAdvs = new ArrayList<>();
		
		int totalTime = timeSeconds;
		long totalAmount = selection(totalTime, allAdvs, size, showedAdvs);
		
		if (showedAdvs.isEmpty())
			throw new NoVideoAvailableException();
		
		Collections.sort(showedAdvs, new Comparator<Advertisement>()
		{
			@Override
			public int compare(Advertisement o1, Advertisement o2)
			{
				long result = o2.getAmountPerOneDisplaying() - o1.getAmountPerOneDisplaying();
				
				if (result == 0)
					result = (long)(o1.getAmountPerOneDisplaying()*1000.0/o1.getDuration() - o2.getAmountPerOneDisplaying()*1000.0/o2.getDuration());
					
				return (int)result;
			}
		});
		
		int duration = 0;
		for (Advertisement advertisement : showedAdvs)
			duration += advertisement.getDuration();
		
		StatisticManager.getInstance().register(new VideoSelectedEventDataRow(showedAdvs, totalAmount, duration));
		
		for(Advertisement x: showedAdvs)
		{
			ConsoleHelper.writeMessage(x.getName() + " is displaying... " +
					x.getAmountPerOneDisplaying() + ", " +
					x.getAmountPerOneDisplaying() * 1000 / x.getDuration());
			
			x.revalidate();
		}
	}
	
	public static long selection(int totalTime, List<Advertisement> items, int numItems, ArrayList<Advertisement> taken)
	{
		if (numItems == 0 || totalTime == 0)
			return 0;
		
		if (items.get(numItems - 1).getHits() < 1 || items.get(numItems - 1).getDuration() > totalTime)
			return selection(totalTime, items, numItems - 1, taken);
		else
		{
			final int preTookSize = taken.size();
			final long took = items.get(numItems - 1).getAmountPerOneDisplaying() +
					selection(totalTime - items.get(numItems - 1).getDuration(), items, numItems - 1, taken);
			
			final int preLeftSize = taken.size();
			final long left = selection(totalTime, items, numItems - 1, taken);
			
			if (took > left)
			{
				if (taken.size() > preLeftSize)
					taken.subList(preLeftSize, taken.size()).clear();
				taken.add(items.get(numItems - 1));
				return took;
			}
			else
			{
				if (preLeftSize > preTookSize)
					taken.subList(preTookSize, preLeftSize).clear();
				
				return left;
			}
		}
	}
}
