package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.view.View;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n_alex on 17.07.17.
 */
public class Model
{
	private View view;
	private Provider[] providers;

	public Model(View view, Provider... providers)
	{
		if (view == null || providers == null || providers.length < 1)
			throw new IllegalArgumentException();

		this.view = view;
		this.providers = providers;
	}

	public void selectCity(String city)
	{
		List<Vacancy> allVacancies = new ArrayList<>();
		for (Provider provider : providers)
			allVacancies.addAll(provider.getJavaVacancies(city));

		view.update(allVacancies);
	}
}
