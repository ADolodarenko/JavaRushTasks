package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by n_alex on 16.05.2017.
 */
public class DirectorTablet
{
	public void printAdvertisementProfit()
	{
		Map<Date, Double> groups = StatisticManager.getInstance().calculateAdvAmountByDay();
		List<Date> dates = new ArrayList<>(groups.keySet());
		Collections.sort(dates, Collections.reverseOrder());
		
		double totalAmount = 0d;
		for (Date date : dates)
		{
			double amount = groups.get(date);
			
			if (amount > 0)
			{
				totalAmount += amount;
				
				DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
				ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", format.format(date), amount));
			}
		}
		
		ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalAmount));
		ConsoleHelper.writeMessage("");
	}
	
	public void printCookWorkloading()
	{
		Map<Date, Map<String, Integer>> groups = StatisticManager.getInstance().calculateCooksWorkloadByDay();
		List<Date> dates = new ArrayList<>(groups.keySet());
		Collections.sort(dates, Collections.reverseOrder());
		
		for (Date date : dates)
		{
			DateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			System.out.println(format.format(date));
			
			Map<String, Integer> cooks = groups.get(date);
			List<String> names = new ArrayList<>(cooks.keySet());
			Collections.sort(names);
			
			for (String name : names)
			{
				int time = cooks.get(name);
				
				if (time > 0)
					ConsoleHelper.writeMessage(String.format("%s - %d min", name, (int)Math.ceil(time * 1.0 / 60)));
			}
			
			ConsoleHelper.writeMessage("");
		}
	}
	
	public void printActiveVideoSet()
	{
		List<Advertisement> advertisements = StatisticAdvertisementManager.getInstance().getActiveAdvertisements();
		Collections.sort(advertisements, new Comparator<Advertisement>()
		{
			@Override
			public int compare(Advertisement o1, Advertisement o2)
			{
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		
		for (Advertisement advertisement : advertisements)
			ConsoleHelper.writeMessage(String.format("%s - %d", advertisement.getName(), advertisement.getHits()));
	}
	
	public void printArchivedVideoSet()
	{
		List<Advertisement> advertisements = StatisticAdvertisementManager.getInstance().getArchivedAdvertisements();
		Collections.sort(advertisements, new Comparator<Advertisement>()
		{
			@Override
			public int compare(Advertisement o1, Advertisement o2)
			{
				return o1.getName().compareToIgnoreCase(o2.getName());
			}
		});
		
		for (Advertisement advertisement : advertisements)
			ConsoleHelper.writeMessage(advertisement.getName());
	}
}
