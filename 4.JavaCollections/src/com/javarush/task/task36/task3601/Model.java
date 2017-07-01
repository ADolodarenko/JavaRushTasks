package com.javarush.task.task36.task3601;

import java.util.List;

/**
 * Created by n_alex on 01.07.17.
 */
public class Model
{
	private Service service = new Service();

	public List<String> getStringDataList() {
		return service.getData();
	}
}
