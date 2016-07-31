package ru.stqa.javacourse.sandbox;

/**
 * Created by Sunlab on 28.07.16.
 */
public class Eqation {
	private double a;
	private double b;
	private double c;
	private int n;

	public Eqation (double a, double b, double c){

		this.a = a;
		this.b = b;
		this.c = c;

		double d = b*b - 4*a*c;
		if (d>0){
			n=2;
		}
		else{
			if (d ==0){
				n = 1;
			}
			else{
				n=0;
			}
		}
	}
}
