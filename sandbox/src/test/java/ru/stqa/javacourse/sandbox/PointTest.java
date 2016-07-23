package ru.stqa.javacourse.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.stqa.javacourse.sandbox.RunPoint.distance;

public class PointTest {

	@Test
	public void methodTest(){
		Point p1 = new Point(2.0,2.0);
		Point p2 = new Point(2.0,4.0);
		Assert.assertEquals(p1.distance(p2), 2.0);
	}
	@Test
	public void functionTest(){
		Point p1 = new Point(2.0,2.0);
		Point p2 = new Point(2.0,4.0);
		assertThat("Длинна вычислена не верно.", distance(p1,p2), is(2.0)); //hamcrest
	}
}