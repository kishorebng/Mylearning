package com.kishore.learning.sorting;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 * 
 * worst case & Avg : O (n ^ 2)
 * best case  : O (n)
 *
 */

public class BubbleSort {

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
			System.out.print("iteration result ");
			for (int k = 0; k < n; k++) {
				System.out.print(a[k]+" ");
			}	
			System.out.println();
		}
		System.out.println("Array is sorted in " + numberOfSwaps + " swaps.");
		// sorted array
		System.out.println("Sorted Array ");
		for (int a_i = 0; a_i < n; a_i++) {
			System.out.println(a[a_i] + " ");
		}
	}

}
