package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
        	wheels = new ArrayList<>();
        	
            for (String wheelName : loadWheelNamesFromDB())
				wheels.add(Wheel.valueOf(wheelName));
			
            if (wheels.size() != 4)
            	throw new RuntimeException();
            
            boolean isCorrect = wheels.contains(Wheel.BACK_LEFT)
					&& wheels.contains(Wheel.BACK_RIGHT)
					&& wheels.contains(Wheel.FRONT_LEFT)
					&& wheels.contains(Wheel.FRONT_RIGHT);
            
            if (!isCorrect)
            	throw new RuntimeException();
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
