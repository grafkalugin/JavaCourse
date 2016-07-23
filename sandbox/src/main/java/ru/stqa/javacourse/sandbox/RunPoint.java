package ru.stqa.javacourse.sandbox;

public class RunPoint {
	public static void main (String[] args){
		Point p1 = new Point(2.0, 2.0);
		Point p2 = new Point(2.0, 6.0);
		System.out.println("Длинна расстояния между точками равна " + distance(p1, p2));
		System.out.println("Длинна расстояния между точками равна " + p1.distance(p2));

	}
	public static double distance(Point p1, Point p2){
		Double x = p1.a - p2.a;
		Double y = p1.b - p2.b;
		return Math.sqrt(x * x + y * y);
	}
}
