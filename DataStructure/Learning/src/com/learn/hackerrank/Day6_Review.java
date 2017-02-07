package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 *
 */

/**
 * Task Given a string, , of length that is indexed from to , print its
 * even-indexed and odd-indexed characters as space-separated strings on a
 * single line (see the Sample below for more detail).
 * 
 * Note: is considered to be an even index.
 * 
 * Input Format
 * 
 * The first line contains an integer, (the number of test cases). Each line of
 * the subsequent lines contain a String, .
 * 
 * Constraints
 * 
 * Output Format
 * 
 * For each String (where ), print 's even-indexed characters, followed by a
 * space, followed by 's odd-indexed characters.
 */

public class Day6_Review {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int noTestcase = Integer.parseInt(scan.nextLine());

		String stringArray[] = new String[noTestcase];
		String evenResult[] = new String[noTestcase];
		String oddResult[] = new String[noTestcase];
		for (int i = 0; i < noTestcase; i++) {
			stringArray[i] = scan.nextLine();
		}
		for (int i = 0; i < noTestcase; i++) {
			evenResult[i]  = "";
			oddResult[i] = "";
			for (int j = 0; j < stringArray[i].length(); j++) {
				if (j % 2 == 0) {
					evenResult[i] = evenResult[i] + stringArray[i].charAt(j);
				} else {
					oddResult[i] = oddResult[i] + stringArray[i].charAt(j);
				}
			}

		}

		for (int i = 0; i < noTestcase; i++) {
			System.out.println(evenResult[i] + " " + oddResult[i]);
		}

	}
}
