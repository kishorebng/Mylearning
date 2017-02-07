package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * @author Kishore 
/*
		Given a  2D Array, :
		
		1 1 1 0 0 0
		0 1 0 0 0 0
		1 1 1 0 0 0
		0 0 0 0 0 0
		0 0 0 0 0 0
		0 0 0 0 0 0
		We define an hourglass in  to be a subset of values with indices falling in this pattern in 's graphical representation:
		
		a b c
		  d
		e f g
		There are  hourglasses in , and an hourglass sum is the sum of an hourglass' values.
		
		Task 
		Calculate the hourglass sum for every hourglass in , then print the maximum hourglass sum.
		
		Input Format
		
		There are  lines of input, where each line contains  space-separated integers describing 2D Array ; every value in  will be in the inclusive range of  to .
		
		Constraints
		
		Output Format
		
		Print the largest (maximum) hourglass sum found in .
		
		Sample Input
		
		1 1 1 0 0 0
		0 1 0 0 0 0
		1 1 1 0 0 0
		0 0 2 4 4 0
		0 0 0 2 0 0
		0 0 1 2 4 0
		Sample Output
		
		19
		Explanation
		
		 contains the following hourglasses:
		
		1 1 1   1 1 0   1 0 0   0 0 0
		  1       0       0       0
		1 1 1   1 1 0   1 0 0   0 0 0
		
		0 1 0   1 0 0   0 0 0   0 0 0
		  1       1       0       0
		0 0 2   0 2 4   2 4 4   4 4 0
		
		1 1 1   1 1 0   1 0 0   0 0 0
		  0       2       4       4
		0 0 0   0 0 2   0 2 0   2 0 0
		
		0 0 2   0 2 4   2 4 4   4 4 0
		  0       0       2       0
		0 0 1   0 1 2   1 2 4   2 4 0
		The hourglass with the maximum sum () is:
		
		2 4 4
		  2
		1 2 4
		
		-1 -1 0 -9 -2 -2
-2 -1 -6 -8 -2 -5
-1 -1 -1 -2 -3 -4
-1 -9 -2 -4 -4 -5
-7 -3 -3 -2 -9 -9
-1 -3 -1 -2 -4 -5

-6

0 -4 -6 0 -7 -6
-1 -2 -6 -8 -3 -1
-8 -4 -2 -8 -8 -6
-3 -1 -2 -5 -7 -4
-3 -5 -3 -6 -6 -6
-3 -6 0 -8 -6 -7

-19
*/
public class Daya11_2DArray {

	
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int arr[][] = new int[6][6];
//        for(int i=0; i < 6; i++){
//            for(int j=0; j < 6; j++){
//                arr[i][j] = in.nextInt();
//            }
//        }
        int arr[][] = { {-1 ,-1, 0, -9, -2, -2},
    	{-2, -1, -6, -8, -2, -5},
    	{-1, -1, -1, -2, -3, -4},
    	{-1,-9, -2, -4, -4, -5},
    	{-7, -3, -3, -2, -9, -9},
    	{-1, -3, -1, -2, -4, -5}};
        
      for(int i=0; i < 6; i++){
      for(int j=0; j < 6; j++){
    	  System.out.print(arr[i][j] +" ");
       
      }
      System.out.println();
  }

        int maxSum = -9*7;
        for(int i=0; i < 6-2; i++){
            for(int j=0; j < 6-2; j++){
                int sum = arr[i][j] + arr[i][j+1]+ arr[i][j+2]+
                		   arr[i+1][j+1]+
                		   arr[i+2][j] + arr[i+2][j+1]+ arr[i+2][j+2];
                if (sum > maxSum) {
                	maxSum = sum;
                }
                		   
            }
        }
        System.out.println(maxSum);
    }

}
