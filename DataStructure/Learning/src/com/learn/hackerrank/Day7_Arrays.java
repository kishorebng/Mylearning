package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 *
 */

/**
 * Task Given an array, , of integers, print 's elements in reverse order as a
 * single line of space-separated numbers.
 * 
 * Input Format
 * 
 * The first line contains an integer, (the size of our array). The second line
 * contains space-separated integers describing array 's elements.
 * 
 * Constraints
 * 
 * , where is the integer in the array. Output Format
 * 
 * Print the elements of array in reverse order as a single line of
 * space-separated numbers.
 * 
 * Sample Input
 * 
 * 4 1 4 3 2
 * Sample Output
 * 
 * 2 3 4 1
 */

public class Day7_Arrays {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		in.close();
		for (int i = 0; i < n / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[n - i - 1];
			arr[n - i - 1] = temp;
		}

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
