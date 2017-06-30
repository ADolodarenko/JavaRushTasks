package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }
	
	@Override
	public int hashCode()
	{
		int result = 31 * ( (first == null) ? 0 : first.hashCode() );
		result += (last == null) ? 0 : last.hashCode();
		
		return result;
	}
	
	public boolean equals(Object o)
    {
        if (o == null)
        	return false;
        
        if (this == o)
        	return true;
        
        if (!(o instanceof Solution))
            return false;
        
        Solution n = (Solution) o;
        
        boolean sameFirst = (first == null)?(n.first == null):(first.equals(n.first));
		boolean sameLast = (last == null)?(n.last == null):(last.equals(n.last));
		
        return sameFirst && sameLast;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
