package ru.stqa.javacourse.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sunlab on 07.08.16.
 */
public class PrimeTest {
	@Test
	public void primeTest(){
		Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
	}
	@Test (enabled = false)
	public void noPrimeTest(){
		Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
	}
	@Test
	public void primeWhileTest(){
		Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
	}
	@Test (enabled = false)
	public void noPrimeWhileTest(){
		Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
	}
	@Test (enabled = false)
	public void primeTestLong(){
		long l = Integer.MAX_VALUE;
		Assert.assertTrue(Primes.isPrime(l));
	}
	@Test
	public void primeFastTest(){
		Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
	}
	@Test
	public void primeFastSqrtTest(){
		Assert.assertTrue(Primes.isPrimeFastSqrt(Integer.MAX_VALUE));
	}
}
