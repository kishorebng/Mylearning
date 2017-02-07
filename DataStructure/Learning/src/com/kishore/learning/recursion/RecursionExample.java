package com.kishore.learning.recursion;

import java.util.Scanner;

public class RecursionExample {

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
