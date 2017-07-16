package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

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
		return new ArrayList<>();
	}
}