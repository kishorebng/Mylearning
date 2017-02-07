package com.kishore.learning.dynamic;

import java.util.Scanner;

/*
 * 
 *  Recursive time complexity   T(n) = T(n-1) + T(n-2) + 1 = 2n = O(2n)
 *  
 *  Dynamic  Time Complexity: O(n) , Space Complexity : O(n)
 */
public class Fibonacci {

	/**
	 * 
	 *  Storing previous result and using it is called Memoization
	 */
	
	static int fib[];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		fib = new int [n+1];
		System.out.println("Bottom-Up Approach  " + fibonacciDynamic(n));
		System.out.println("Top down" + fibonacciDynamicRecursion(n));
	}

	/**
	 * 
	 * Bottom-Up Approach:
     *
     *  Suppose we need to solve the problem for N, We start solving the problem with the smallest possible inputs and store it for future. 
     *  Now as you calculate for the bigger values use the stored solutions (solution for smaller problems).
	 * 
	 */
	
	static public int fibonacciDynamic(int n) { //Bottom up approach
		int F[] = new int[n+1];
		F[0] = 0;
		F[1] = 1;
		for (int i= 2; i<n+1;i++) {
			F[i] = F[i-1] + F [i-2];
		}
	  return F[n];
	}
	
	/**
	 * Top-Down Approach
	 * 
	 * Break the prob­lem into sub-problems and solve them as needed and store the solu­tion for future.
	 */
	
	static public int fibonacciDynamicRecursion(int n) {
		if (n==1 || n== 2) {
			return 1;
		} 
		if (fib[n]!=0) {
			return fib[n];
		} else {
			fib[n] =  fibonacciDynamicRecursion(n-1) + fibonacciDynamicRecursion(n-2);
			return fib[n];
		}
		
	}
}
