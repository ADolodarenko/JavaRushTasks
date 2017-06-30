package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable
{
    public static void main(String[] args) {
        List<String> list = new CustomTree();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
    }
    
    Entry<String> root = new Entry<>("0");
    
    @Override
    public String get(int index)
    {
        throw new UnsupportedOperationException();
    }
    
    public boolean add(String s)
    {
        Entry<String> newEntry = new Entry(s);
        
        if (root == null)
        {
            root = newEntry;
            
            return true;
        }
        else
        {
            Queue<Entry<String>> entries = new LinkedList<>();
            entries.add(root);
    
            while (!entries.isEmpty())
            {
                Entry<String> currentEntry = entries.poll();
                currentEntry.checkChildren();
        
                if (currentEntry.availableToAddLeftChildren)
                {
                    currentEntry.leftChild = newEntry;
                    currentEntry.leftChild.parent = currentEntry;
                    
                    return true;
                }
                else if (currentEntry.availableToAddRightChildren)
                {
                    currentEntry.rightChild = newEntry;
                    currentEntry.rightChild.parent = currentEntry;
                    
                    return true;
                }
                else
                {
                    entries.add(currentEntry.leftChild);
                    entries.add(currentEntry.rightChild);
                }
            }
        }
        
        return false;
    }
    
    @Override
    public boolean remove(Object o)
    {
        if (o instanceof String)
            return remove((String)o);
        else
            throw new UnsupportedOperationException();
    }
    
    public boolean remove(String s)
    {
        boolean result = false;
    
        if (root != null)
            if (root.elementName.equals(s))
            {
                root = null;
                result = true;
            }
            else
            {
                Queue<Entry<String>> entries = new LinkedList<>();
                entries.add(root);
            
                while (!entries.isEmpty())
                {
                    Entry<String> entry = entries.poll();
                
                    if (entry.leftChild != null)
                        if (entry.leftChild.elementName.equals(s))
                        {
                            entry.leftChild.parent = null;
                            entry.leftChild = null;
                            entry.checkChildren();
                        
                            result = true;
                            break;
                        }
                        else
                            entries.add(entry.leftChild);
                
                    if (entry.rightChild != null)
                        if (entry.rightChild.elementName.equals(s))
                        {
                            entry.rightChild.parent = null;
                            entry.rightChild = null;
                            entry.checkChildren();
                        
                            result = true;
                            break;
                        }
                        else
                            entries.add(entry.rightChild);
                }
            }
    
        return result;
    }
    
    public String getParent(String s)
    {
        String result = null;
        
        if (root != null)
        {
            Queue<Entry<String>> entries = new LinkedList<>();
            entries.add(root);
            
            while (!entries.isEmpty())
            {
                Entry<String> entry = entries.poll();
                
                if (entry.elementName.equals(s))
                {
                    Entry<String> parent = entry.parent;
                    
                    if (parent != null)
                        result = parent.elementName;
                    
                    break;
                }
                else
                {
                    if (entry.leftChild != null)
                        entries.add(entry.leftChild);
                    if (entry.rightChild != null)
                        entries.add(entry.rightChild);
                }
            }
        }
        
        return result;
    }
    
    @Override
    public int size()
    {
        int result = 0;
    
        if (root != null)
        {
            Queue<Entry<String>> entries = new LinkedList<>();
            entries.add(root);
        
            while (!entries.isEmpty())
            {
                Entry<String> entry = entries.poll();
                if (entry != root)
                    result++;
            
                if (entry.leftChild != null)
                    entries.add(entry.leftChild);
                if (entry.rightChild != null)
                    entries.add(entry.rightChild);
            }
        }
    
        return result;
    }
    
    @Override
    public String set(int index, String element)
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void add(int index, String element)
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String remove(int index)
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends String> c)
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public List<String> subList(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected void removeRange(int fromIndex, int toIndex)
    {
        throw new UnsupportedOperationException();
    }
    
    static class Entry<T> implements Serializable
    {
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;
        
        public Entry(String elementName)
        {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }
        
        void checkChildren()
        {
            if (leftChild != null)
                availableToAddLeftChildren = false;
            
            if (rightChild != null)
                availableToAddRightChildren = false;
        }
        
        boolean isAvailableToAddChildren()
        {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
