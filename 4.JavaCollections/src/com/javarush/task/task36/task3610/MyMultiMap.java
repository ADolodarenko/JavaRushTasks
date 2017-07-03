package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size()
	{
    	int size = 0;

        for (List<V> values : map.values())
        	size += values.size();

        return size;
    }

    @Override
    public V put(K key, V value)
	{
        if (map.containsKey(key))
        {
        	List<V> values = map.get(key);

        	V previous = values.size() > 0 ? values.get(values.size() - 1) : null;

        	if (values.size() == repeatCount)
				values.remove(0);

			values.add(value);

			return previous;
        }
        else
        {
            List<V> values = new LinkedList<>();
            values.add(value);

            map.put(key, values);

            return null;
        }
    }

    @Override
    public V remove(Object key)
	{
        if (map.containsKey(key))
		{
			List<V> values = map.get(key);

			V value = null;

			if (values.size() > 0)
			{
				value = values.get(0);
				values.remove(0);
			}

			if (values.size() == 0)
				map.remove(key);


			return value;
		}
		else
			return null;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> allValues = new ArrayList<>();

        for (List<V> values : map.values())
        	allValues.addAll(values);

        return allValues;
    }

    @Override
    public boolean containsKey(Object key)
	{
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value)
	{
        for (List<V> values : map.values())
        	for (V currentValue : values)
        		if (currentValue.equals(value))
        			return true;

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}