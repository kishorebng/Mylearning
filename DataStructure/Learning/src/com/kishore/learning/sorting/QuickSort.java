package com.kishore.learning.sorting;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 * 
 * 
 *         Divide & Conquer Recursive Stable Not In-place space complexity O (n)
 *         time complexity O (n log n)
 */

public class QuickSort {

	static void quickSort(int arr[], int start, int end) {
		
		if (start < end) { // exit condition
			int partitionIndex = partition(arr, start, end);
			quickSort(arr, start, partitionIndex-1);
			quickSort(arr, partitionIndex + 1, end);
		}
	}
	
	/*
	 *   By end of partition method all the elements left to pivot will be lesser than pivot
	 *   Elements to right of  pivot will be greater than pivot.
	 */

	static int partition(int arr[], int start, int end) {

		int pivot = arr[end]; // select pivot
		int partitionIndex = start;  //set partition index as start

		for (int i = start; i < end; i++) {
			if (arr[i] <= pivot) { //swap if element is lesser than pivot
				int temp = arr[i];
				arr[i] = arr[partitionIndex]; 
				arr[partitionIndex] = temp;
				partitionIndex++;

			}
		}
		int temp = arr[end]; //swap if element at pivot Index
		arr[end] = arr[partitionIndex];
		arr[partitionIndex] = temp;
		return partitionIndex;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		quickSort(a, 0, n - 1);
		// sorted array
		System.out.println("Sorted Array ");
		for (int a_i = 0; a_i < n; a_i++) {
			System.out.print(a[a_i] + " ");
		}
	}

}
