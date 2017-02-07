package com.kishore.learning.dynamic;

import java.util.HashMap;
import java.util.Map;

public class MinCoinBottomUp {

	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		int coins[] = { 1, 5, 6, 8 };
		int total = 11;

		System.out.println(" Min coin bottom up " + minCoinsApproach1(coins, total));
		System.out.println(" Min coin bottom up matrix " + minCoinsApproach2(coins, total));

	}

	
	/**
	 * 
	 * Bottom-Up Approach1:
	 *  Maintain a array for min coin starting from 0 to total
	 */

	static int minCoinsApproach1(int c[], int total) {
		int minCoins[] = new int[total + 1];
		minCoins[0] = 0;
		for (int t = 1; t <= total; t++) { //  1 to total iteration
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < c.length; i++) {   // iterate coins
				if (c[i] <= t && minCoins[(t - c[i])] + 1 < min) {  // if coin is less than t and mincoin [t - coin[i]] is less than min replace min   
					min = minCoins[(t - c[i])] + 1;
					minCoins[t] = min;
				}

			}
		}
		return minCoins[total];
	}

	/**
	 * 
	 * Bottom-Up Approach2:   Matrix ways of solving the problem  
	 * Row is 0 to total
	 * column is no of coins as index for coins staring 1  O is empty
	 */

	static int minCoinsApproach2(int c[], int total) {
		int row = c.length + 1;
		int col = total + 1;
		int mat[][] = new int[row][col];

		// if amount=0 then just return empty set to make the change
		for (int i = 0; i < row; i++) {
			mat[i][0] = 0;
		}

		// if no coins given, 0 ways to change the amount
		for (int i = 1; i < col; i++) {
			mat[0][i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (j >= c[i - 1]) {
					mat[i][j] = Math.min(mat[i - 1][j], 1 + mat[i][j - c[i - 1]]);
				} else {
					mat[i][j] = mat[i - 1][j];
				}
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}

		return mat[row - 1][col - 1];

	}

}
