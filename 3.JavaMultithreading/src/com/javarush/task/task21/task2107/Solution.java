package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
    
        @Override
        public int hashCode()
        {
            return 31 * age + ( name == null ? 0 : name.hashCode() );
        }
    
        @Override
        public boolean equals(Object obj)
        {
            if (obj == null) return false;
            if (this == obj) return true;
            if ( !(obj instanceof User) ) return false;
            
            User that = (User)obj;
            if (this.age != that.age) return false;
			if ( this.name == null ? that.name != null : !this.name.equals(that.name) ) return false;
			
			return true;
        }
    
        @Override
        protected User clone() throws CloneNotSupportedException
        {
            return (User) super.clone();
        }
    }
	
	@Override
	protected Solution clone() throws CloneNotSupportedException
	{
		Solution copy = (Solution) super.clone();
		
		Map<String, User> map = new LinkedHashMap();
		for (Map.Entry<String, User> entry : this.users.entrySet())
			map.put(entry.getKey(), entry.getValue().clone());
		
		copy.users = map;
		
		return copy;
	}
}
