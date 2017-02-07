package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * Task Read a string, , and print its integer value; if cannot be converted to
 * an integer, print Bad String.
 * 
 * Note: You must use the String-to-Integer and exception handling constructs
 * built into your submission language. If you attempt to use loops/conditional
 * statements, you will get a score.
 * 
 * Input Format
 * 
 * A single string, .
 * 
 * Constraints
 * 
 * , where is the length of string . is composed of either lowercase letters ()
 * or decimal digits (). Output Format
 * 
 * Print the parsed integer value of , or Bad String if cannot be converted to
 * an integer.
 * 
 * Sample Input 0
 * 
 * 3 Sample Output 0
 * 
 * 3 Sample Input 1
 * 
 * za Sample Output 1
 * 
 * Bad String
 */

public class Daya16_Exceptions {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String S = in.next();

		try {
			System.out.println(Integer.parseInt(S));
		} catch (NumberFormatException e) {
			System.out.println("Bad String");
		}

	}
}
