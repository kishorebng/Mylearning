package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * Given an array, , of size containing distinct elements , sort array in
 * ascending order using the Bubble Sort algorithm above. Once sorted, print the
 * following lines:
 * 
 * 
 * where is the number of swaps that took place.
 * 
 * where is the first element in the sorted array.
 * 
 * where is the last element in the sorted array. Hint: To complete this
 * challenge, you will need to add a variable that keeps a running tally of all
 * swaps that occur during execution.
 * 
 * Input Format
 * 
 * The first line contains an integer, , denoting the number of elements in
 * array . The second line contains space-separated integers describing , where
 * the integer is , .
 * 
 * Constraints
 * 
 * , Output Format
 * 
 * There should be lines of output:
 * 
 * 
 * where is the number of swaps that took place.
 * 
 * where is the first element in the sorted array.
 * 
 * where is the last element in the sorted array. Sample Input 0
 * 
 * 3 1 2 3 
 * 
 * Sample Output 0
 * 
 * Array is sorted in 0 swaps.
 * First Element: 1
 * Last Element: 3 
 * 
 * Sample Input 1
 * 
 * 3 3 2 1 
 * 
 * Sample Output 1
 * 
 * Array is sorted in 3 swaps. 
 * First Element: 1 
 * Last Element: 3
 */


 

public class Dayb20_Sorting {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}

		int numberOfSwaps = 0;
		for (int i = 0; i < n; i++) {
			boolean swap = false;
			// Track number of elements swapped during a single array traversal
			for (int j = 0; j < n - 1; j++) {
				// Swap adjacent elements if they are in decreasing order
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					numberOfSwaps++;
					swap = true;
				}
			}
			// If no elements were swapped during a traversal, array is sorted
			if (!swap) {
				break;
			}
		}
	    System.out.println("Array is sorted in "+numberOfSwaps+" swaps.");
		System.out.println("First Element: "+a[0]);
		System.out.println("Last Element: "+a[n-1]);
	}

}
