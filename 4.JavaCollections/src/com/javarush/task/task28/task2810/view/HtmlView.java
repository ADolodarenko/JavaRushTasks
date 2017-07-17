package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

/**
 * Created by n_alex on 17.07.17.
 */
public class HtmlView implements View
{
	private Controller controller;
	private final String filePath = "./src/" + this.getClass().getPackage().getName().replace(".", "/")
			+ "/vacancies.html";

	@Override
	public void update(List<Vacancy> vacancies)
	{
		try
		{
			updateFile(getUpdatedFileContent(vacancies));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void setController(Controller controller)
	{
		this.controller = controller;
	}

	public void userCitySelectEmulationMethod()
	{
		controller.onCitySelect("Odessa");
	}

	private String getUpdatedFileContent(List<Vacancy> vacancies)
	{
		return null;
	}

	private void updateFile(String fileName)
	{}
}
