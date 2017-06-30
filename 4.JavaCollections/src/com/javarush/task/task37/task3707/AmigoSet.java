package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 * Created by dolodarenko on 22.06.2017.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable
{
	private static final Object PRESENT = new Object();
	
	private transient HashMap<E, Object> map;
	
	public AmigoSet()
	{
		map = new HashMap<>();
	}
	
	public AmigoSet(Collection<? extends E> collection)
	{
		map = new HashMap<>(Math.max(16, (int)(collection.size()/0.75f +1)));
		
		addAll(collection);
	}
	
	@Override
	public boolean add(E e)
	{
		Object prev = map.put(e, PRESENT);
		
		if (e == null)
			return true;
		else
			return prev == null;
	}
	
	@Override
	public Iterator<E> iterator()
	{
		return map.keySet().iterator();
	}
	
	@Override
	public int size()
	{
		return map.size();
	}
	
	@Override
	public boolean isEmpty()
	{
		return map.isEmpty();
	}
	
	@Override
	public boolean contains(Object o)
	{
		return map.containsKey(o);
	}
	
	@Override
	public void clear()
	{
		map.clear();
	}
	
	@Override
	public boolean remove(Object o)
	{
		return map.remove(o) != null;
	}

	@Override
	public Object clone()
	{
		AmigoSet<E> clone;

		try
		{
			clone = (AmigoSet<E>) super.clone();
			clone.map = (HashMap<E, Object>) map.clone();
		}
		catch(Exception e)
		{
			throw new InternalError();
		}

		return clone;
	}

	private void writeObject(ObjectOutputStream stream) throws IOException
	{
		stream.defaultWriteObject();

		/* С Х..я ли это нужно опускать?
		Integer capacityWrapper = HashMapReflectionHelper.<Integer>callHiddenMethod(map, "capacity");
		int capacity = (capacityWrapper == null) ? 0 : capacityWrapper.intValue();
		stream.writeInt(capacity);

		Float factorWrapper = HashMapReflectionHelper.<Float>callHiddenMethod(map, "loadFactor");
		float factor = (factorWrapper == null) ? .0f : factorWrapper.floatValue();
		stream.writeFloat(factor);
		*/
	}

	private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException
	{
		stream.defaultReadObject();

		/* С Х..я ли это нужно опускать?
		int capacity = stream.readInt();
		float factor = stream.readFloat();

		map = new HashMap<E, Object>(capacity, factor);
		*/
	}
}
