package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 * 
 * 
 *         Task Write a factorial function that takes a positive integer, as a
 *         parameter and prints the result of ( factorial).
 * 
 *         Note: If you fail to use recursion or fail to name your recursive
 *         function factorial or Factorial, you will get a score of .
 * 
 *         Input Format
 * 
 *         A single integer, (the argument to pass to factorial).
 * 
 *         Constraints
 * 
 *         Your submission must contain a recursive function named factorial.
 *         Output Format
 * 
 *         Print a single integer denoting .
 * 
 *         Sample Input
 * 
 *         3 Sample Output
 * 
 *         6
 */
public class Day9_Recursion {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int p = in.nextInt();
		System.out.println(" Factorial "+factorial(n));		
		System.out.println(" exponential "+exponential(n,p));
		System.out.println(" exponential "+summation(n));
		System.out.println(" fibonacci "+fibonacci(n));
	}
	
	 static int factorial (int n) {
		if (n<=1) {
			return 1;
		} else {
			return n* factorial(n-1);
		}
	}
	 
	 static int summation (int n) {
			if (n<=0) {
				return 0;
			} else {
				return n+ summation(n-1);
			}
		}
	 
	 static int exponential (int n,int p) {
			if (p<=0) {
				return 1;
			} else {
				return n* exponential(n,p-1);
			}
	 }
	 
	 static public int fibonacci(int n)  {
		    if(n == 0)
		        return 0;
		    else if(n == 1)
		      return 1;
		   else
		      return fibonacci(n - 1) + fibonacci(n - 2);
		}
}
