package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by n_alex on 12.06.2017.
 */

@XmlType(name = "shop")
@XmlRootElement
public class Shop
{
	public Goods goods;
	
	public int count;
	
	public double profit;
	
	public String[] secretData;
	
	public Shop(){}
	
	static class Goods
	{
		public List<String> names;
		
		public Goods(){}
	}
}