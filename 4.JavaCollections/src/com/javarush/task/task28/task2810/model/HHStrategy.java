package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by n_alex on 16.07.17.
 */
public class HHStrategy implements Strategy
{
	private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

	@Override
	public List<Vacancy> getVacancies(String searchString)
	{
		try
		{
			Document document = getDocument(searchString, 0);

			Elements elements = document.select("vacancy-serp__vacancy");
			if (elements != null && elements.size() > 0)
				for (Element element : elements)
				{

				}
		}
		catch (IOException e)
		{}

		return new ArrayList<>();
	}

	protected Document getDocument(String searchString, int page) throws IOException
	{
		return Jsoup.connect("http://javarush.ru/testdata/big28data.html").userAgent("Mozilla/5.0").referrer("no-referrer-when-downgrade").get();
	}
}