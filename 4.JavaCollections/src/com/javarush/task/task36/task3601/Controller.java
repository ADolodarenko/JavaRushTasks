package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by n_alex on 01.07.17.
 */
public class Controller
{
	private Model model = new Model();

	public List<String> onDataListShow() {
		return model.getStringDataList();
	}
}
