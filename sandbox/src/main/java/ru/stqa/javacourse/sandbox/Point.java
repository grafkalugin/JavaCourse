package ru.stqa.javacourse.sandbox;


public class Point {
	Double a;
	Double b;

	public Point(Double a, Double b){
		this.a = a;
		this.b = b;
	}
	public double distance(Point p2) {
		Double x = this.a - p2.a;
		Double y = this.b - p2.b;
		return Math.sqrt(x * x + y * y);
	}
}
