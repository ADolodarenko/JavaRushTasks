package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n_alex on 13.05.2017.
 */
public class AdvertisementStorage
{
	private static AdvertisementStorage ourInstance = new AdvertisementStorage();
	
	private final List<Advertisement> videos = new ArrayList<>();
	
	public static AdvertisementStorage getInstance()
	{
		return ourInstance;
	}
	
	private AdvertisementStorage()
	{
		Object someContent = new Object();
		videos.add(new Advertisement(someContent, "First Video", 5000, 100, 15 * 60)); // 15 min
		videos.add(new Advertisement(someContent, "Second Video", 100, 10, 3 * 60)); //3 min
		videos.add(new Advertisement(someContent, "Лапушка Video", 400, 2, 10 * 60)); //10 min
		videos.add(new Advertisement(someContent, "Forth Video", 12, 3, 1 * 60)); //1 min
		videos.add(new Advertisement(someContent, "Fifth Video", 8, 2, 1 * 60)); //1 min
		videos.add(new Advertisement(someContent, "Sixth Video", 16, 8, 1 * 60)); //1 min
	}
	
	public List<Advertisement> list()
	{
		return videos;
	}
	
	public void add(Advertisement advertisement)
	{
		videos.add(advertisement);
	}
}
