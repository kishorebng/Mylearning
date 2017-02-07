package com.kishore.learning.sorting;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 *
 *    In Insertion sort we assume one side as sorted and other unsorted and sorting someting card
 *    pick one number and do shift until we find appropriate position
 *    
 *    
 *    Example :     7 2 4 1 5 3     
 *           
 *                  
 *    1st iteration          7  |  2 4 1 5 3     pick 2 and compare with previous position value shift if greater -> 2 7 4 1 5 3
 *    2nd iteration          2 7  |  4 1 5 3     pick 4 and compare with previous position value shift if greater -> 2 4 7 1 5 3 
 *    3rd                    2 4 7  |  1 5 3     pick 1 and compare with previous position value shift if greater -> 1 2 4 7 5 3
 *    3rd                    1 2 4 7  |  5 3     pick 5 and compare with previous position value shift if greater -> 1 2 4 5 7 3
 *    4rd                    1 2 4 5 7 | 3       pick 3 and compare with previous position value shift if greater -> 1 2 3 4 5 7
 *    5th                    1 2 3 4 5 7        //sorted array
 *    
 *    worst case  if array is reversed sorted :  O (n ^ 2)
 *    Avg :  O (n ^ 2)
 *    best case  : O (n)
 */
public class InsertionSort {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}

		for (int i = 1; i < n; i++) {
			int value = a[i];
			int shiftPos = i;
			while (shiftPos >0  && a[shiftPos-1] > value) {
				a[shiftPos] = a[shiftPos-1];
				shiftPos = shiftPos -1;
			}
             a[shiftPos] = value;
             
			System.out.print("iteration result ");
			for (int k = 0; k < n; k++) {
				System.out.print(a[k]+" ");
			}	
			System.out.println();
		}
		// sorted array
		System.out.println("Sorted Array ");
		for (int a_i = 0; a_i < n; a_i++) {
			System.out.print(a[a_i]+" ");
		}	    
	}

}
