package com.kishore.learning.sorting;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 * 
 *  
 *  Divide & Conquer
 *  Recursive
 *  Stable
 *  Not In-place  space complexity  O (n)
 *   time complexity O (n log n)
 */


 

public class MergeSort {

	static void mergeSort(int arr[]) {
		int n = arr.length;
		if (n < 2) {
			return; // no need to sort has it has less than 2 elements
		}
		int mid = n / 2;   // mid position
		int left[] = new int[mid]; // left array
		int right[] = new int[n - mid]; // right array
		// copy values to left and right
		for (int i = 0; i < mid; i++) { // left array from start to mid
			left[i] = arr[i];
		}

		for (int i = mid; i < n; i++) { // right array from mid to n
			right[i - mid] = arr[i];
		}

//		System.out.println("Array to sort");
//		for (int a_i = 0; a_i < arr.length; a_i++) {
//			System.out.print(arr[a_i] + " ");
//		}
//		System.out.println();
//
//		System.out.println("left Array");
//		for (int a_i = 0; a_i < left.length; a_i++) {
//			System.out.print(left[a_i] + " ");
//		}
//		System.out.println();
//
//		System.out.println(" Right Array");
//		for (int a_i = 0; a_i < right.length; a_i++) {
//			System.out.print(right[a_i] + " ");
//		}
//		System.out.println();

		mergeSort(left); // again divide left
		mergeSort(right); // again divide right
		merge(left, right, arr); // perform merge of left & right
	}

	static void merge(int left[], int right[], int a[]) {
		int nL = left.length;
		int nR = right.length;
		int i;
		int j;
		int k;

		i = j = k = 0;

//		System.out.println("Left Array to merge");
//		for (int a_i = 0; a_i < left.length; a_i++) {
//			System.out.print(left[a_i] + " ");
//		}
//		System.out.println();
//
//		System.out.println(" Right Array to merge ");
//		for (int a_i = 0; a_i < right.length; a_i++) {
//			System.out.print(right[a_i] + " ");
//		}
//		System.out.println();

		// Perform Merge
		while (i < nL && j < nR) {
			if (left[i] < right[j]) {
				a[k] = left[i];
				i++;

			} else {
				a[k] = right[j];
				j++;
			}
			k++;
		}
		while (i < nL) {
			a[k] = left[i];
			i++;
			k++;
		}
		while (j < nR) {
			a[k] = right[j];
			j++;
			k++;
		}

//		System.out.println("Merged Array ");
//		for (int a_i = 0; a_i < a.length; a_i++) {
//			System.out.print(a[a_i] + " ");
//		}
//		System.out.println();

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}
		mergeSort(a);
		// sorted array
		System.out.println("Sorted Array ");
		for (int a_i = 0; a_i < n; a_i++) {
			System.out.print(a[a_i] + " ");
		}
	}

}
