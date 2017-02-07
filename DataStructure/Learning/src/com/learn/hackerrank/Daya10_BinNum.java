package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * @author Kishore Task Given a base- integer, , convert it to binary (base-).
 *         Then find and print the base- integer denoting the maximum number of
 *         consecutive 's in 's binary representation.
 * 
 *         Input Format
 * 
 *         A single integer, .
 * 
 *         Constraints
 * 
 *         Output Format
 * 
 *         Print a single base- integer denoting the maximum number of
 *         consecutive 's in the binary representation of .
 * 
 *         Sample Input 5 Sample Output 1
 * 
 *         Sample Input 13 Sample Output 2
 * 
 *         2
 */
public class Daya10_BinNum {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		// System.out.println(countOneBit(n));
		//basics();
		System.out.println(findMaxConsecutiveOnes(n));

	}

	static void basics() {

		int a = 60; /* 60 = 0011 1100 */
		int b = 13; /* 13 = 0000 1101 */
		int c = 0;

		c = a & b; /* 12 = 0000 1100 */
		System.out.println("a & b = " + c);

		c = a | b; /* 61 = 0011 1101 */
		System.out.println("a | b = " + c);

		c = a ^ b; /* 49 = 0011 0001 */
		System.out.println("a ^ b = " + c);

		c = ~a; /*-61 = 1100 0011 */
		System.out.println("~a = " + c);

		c = a << 2; /* 240 = 1111 0000 */
		System.out.println("a << 2 = " + c);

		c = a >> 2; /* 15 = 1111 */
		System.out.println("a >> 2 = " + c);

		c = a >>> 2; /* 15 = 0000 1111 */
		System.out.println("a >>> 2 = " + c);

	}

	static int findMaxConsecutiveOnes(int n) {
		String num = Integer.toBinaryString(n);
		System.out.println(" Binary : "+num);
		int maxCount = 1;
		for (int i = 0; i < num.length(); i++) {
			int count = 1;

			for (int j = i+1; j < num.length(); j++) {
				if (num.charAt(i) == num.charAt(j) && num.charAt(j) == '1') {
					count++;
				} else {
					break;
				}
			}
			if (count > maxCount) {
				maxCount = count;
			}
		}

		return maxCount;
	}

	static int countOneBit(int n) {
		int count = 0;
		while (n != 0) {
			n = n & (n - 1);
			count++;
		}
		return count;
	}

}
