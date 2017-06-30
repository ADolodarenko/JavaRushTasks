package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by n_alex on 05.06.2017.
 */
public class CustomInvocationHandler implements InvocationHandler
{
	private SomeInterfaceWithMethods iface;
	
	public CustomInvocationHandler(SomeInterfaceWithMethods iface)
	{
		this.iface = iface;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		String methodName = method.getName();
		
		System.out.println(methodName + " in");
		Object result = method.invoke(iface, args);
		System.out.println(methodName + " out");
		
		return result;
	}
}
