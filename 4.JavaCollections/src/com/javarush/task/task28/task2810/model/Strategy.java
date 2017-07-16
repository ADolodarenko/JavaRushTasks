package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

/**
 * Created by n_alex on 16.07.17.
 */
public interface Strategy
{
	List<Vacancy> getVacancies(String searchString);
}
