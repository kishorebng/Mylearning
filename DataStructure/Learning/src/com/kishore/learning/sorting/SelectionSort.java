package com.kishore.learning.sorting;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 *
 *    In selection sort at the end of every iteration smallest element will be at correct position
 *    
 *    
 *    Example :    2 7 4 1 5 3     
 *
 *    1st iteration result   1 7 4 2 5 3
 *    2nd iteration          1 2 4 7 5 3
 *    3rd                    1 2 3 7 5 4
 *    4rd                    1 2 3 4 5 7
 *    5th                    1 2 3 4 5 7      
 *    
 *   time complexity :  O (n ^ 2)
 */
public class SelectionSort {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int a[] = new int[n];
		for (int a_i = 0; a_i < n; a_i++) {
			a[a_i] = in.nextInt();
		}

		for (int i = 0; i < n-1; i++) {
			int minPos = i;
			for (int j=i+1; j< n; j++) {
				if(a[j]< a[minPos]) {
					minPos = j;
				}
			}
			
			int temp = a[i];
			a[i] = a[minPos];
			a[minPos] = temp; 		
			
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
