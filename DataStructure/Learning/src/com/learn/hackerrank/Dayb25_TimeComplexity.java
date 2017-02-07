package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 *
 *         A prime is a natural number greater than that has no positive
 *         divisors other than and itself. Given a number, , determine and print
 *         whether it's or .
 * 
 *         Note: If possible, try to come up with a primality algorithm, or see
 *         what sort of optimizations you come up with for an algorithm. Be sure
 *         to check out the Editorial after submitting your code!
 * 
 *         Input Format
 * 
 *         The first line contains an integer, , the number of test cases. Each
 *         of the subsequent lines contains an integer, , to be tested for
 *         primality.
 * 
 *         Constraints
 * 
 *         Output Format
 * 
 *         For each test case, print whether is or on a new line.
 * 
 *         Sample Input
 * 
 *         3 12 5 7 Sample Output
 * 
 *         Not prime Prime Prime
 * 
 *         Explanation
 * 
 *         Test Case 0: . is divisible by numbers other than and itself (i.e.: ,
 *         , ), so we print on a new line.
 * 
 *         Test Case 1: . is only divisible and itself, so we print on a new
 *         line.
 * 
 *         Test Case 2: . is only divisible and itself, so we print on a new
 *         line.
 */

public class Dayb25_TimeComplexity {

	static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	static boolean isPrime1(int num) {
		if (num > 2 && num % 2 == 0) {
			return false;
		}
		int top = (int) Math.sqrt(num) + 1;
		for (int i = 3; i < top; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String args[]) {
		/* Enter your code here. */
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		in.close();

		for (int i = 0; i < n; i++) {
			System.out.println(isPrime1(arr[i]) ? "Prime" : "Not prime");
		}
	}
}
