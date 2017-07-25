package com.javarush.task.task40.task4004;

import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        if (polygon == null && polygon.isEmpty())
        	return false;

        if (polygon.size() == 1)
        	return (point.x == polygon.get(0).x && point.y == polygon.get(0).y);

        int result = 1;
        for (int i = 1; i < polygon.size(); i++)
        	result *= doesPointIntersect(point, polygon.get(i-1), polygon.get(i));

        result *= doesPointIntersect(point, polygon.get(polygon.size() - 1), polygon.get(0));

        return result == -1;


//		boolean res = false;
//		int j = polygon.size()-1;
//
//		for (int i = 0; i < polygon.size(); i++)
//		{
//			if (
//				(
//				 ((polygon.get(i).y < point.y) && (point.y < polygon.get(j).y)) ||
//				 ((polygon.get(j).y <= point.y) && (point.y < polygon.get(i).y))
//				) &&
//				(point.x > (polygon.get(j).x - polygon.get(i).x) * (point.y - polygon.get(i).y) / (polygon.get(j).y - polygon.get(i).y) + polygon.get(i).x)
//			   )
//				res = !res;
//			j=i;
//		}
//
//		return res;
    }

	private static int doesPointIntersect(Point point, Point point1, Point point2)
	{
		long ax = point1.x - point.x;
		long ay = point1.y - point.y;
		long bx = point2.x - point.x;
		long by = point2.y - point.y;
		int s = Long.signum(ax * by - ay * bx);
		if (s == 0 && (ay == 0 || by == 0) && ax * bx <= 0)
			return 0;
		if (ay < 0 ^ by < 0)
		{
			if (by < 0)
				return s;
			return -s;
		}
		return 1;
	}

}

