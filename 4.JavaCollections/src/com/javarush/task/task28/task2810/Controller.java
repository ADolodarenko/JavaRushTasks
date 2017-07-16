package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by n_alex on 16.07.17.
 */
public class Controller
{
	private Provider[] providers;

	public Controller(Provider... providers)
	{
		if (providers == null || providers.length < 1)
			throw new IllegalArgumentException();

		this.providers = providers;
	}

	@Override
	public String toString()
	{
		return "Controller{" +
				"providers=" + Arrays.toString(providers) +
				'}';
	}

	public void scan()
	{
		List<Vacancy> allVacancies = new ArrayList<>();
		for (Provider provider : providers)
			allVacancies.addAll(provider.getJavaVacancies(null));

		System.out.println(allVacancies.size());
	}
}
