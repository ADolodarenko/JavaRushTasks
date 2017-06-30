package com.javarush.task.task21.task2109;

/* 
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    
        @Override
        public int hashCode()
        {
            return 31 * i + j;
        }
    
        @Override
        public boolean equals(Object obj)
        {
            if (obj == null) return false;
            if (this == obj) return true;
            if ( !(obj instanceof A) ) return false;
            
            A that = (A) obj;
            return (this.i == that.i) && (this.j == that.j);
        }
	
		@Override
		public Object clone() throws CloneNotSupportedException
		{
			return super.clone();
		}
	}

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }

        public String getName() {
            return name;
        }
	
		@Override
		public Object clone() throws CloneNotSupportedException
		{
			throw new CloneNotSupportedException();
		}
	}

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }
	
		@Override
		public int hashCode()
		{
			String name = getName();
			return 31 * getI() + getJ() + ( name == null ? 0 : name.hashCode() );
		}
	
		@Override
		public boolean equals(Object obj)
		{
			if (obj == null) return false;
			if (this == obj) return true;
			if ( !(obj instanceof C) ) return false;
			
			C that = (C) obj;
			
			if (this.getI() != that.getI()) return false;
			if (this.getJ() != that.getJ()) return false;
			
			String name = this.getName();
			if ( name == null ? that.getName() != null : !(name.equals(that.getName())) ) return false;
			
			return true;
		}
	
		@Override
		public Object clone() throws CloneNotSupportedException
		{
			return new C(getI(), getJ(), getName());
		}
	}

    public static void main(String[] args)
	{
		A a = new A(10, 12);
		A a1 = null;
		try
		{
			a1 = (A)a.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		System.out.println("a == a1: " + (a == a1));
		System.out.println("a.getClass() == a1.getClass(): " + (a.getClass() == a1.getClass()));
		System.out.println("a.equals(a1): " + (a.equals(a1)));
		
		
		B b = new B(100, 102, "XXX");
		B b1 = null;
		try
		{
			b1 = (B)b.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		C c = new C(100, 102, "YYY");
		C c1 = null;
		try
		{
			c1 = (C)c.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		System.out.println("c == c1: " + (c == c1));
		System.out.println("c.getClass() == c1.getClass(): " + (c.getClass() == c1.getClass()));
		System.out.println("c.equals(c1): " + (c.equals(c1)));
		
	}
}
