package com.kishore.learning.dynamic;

import java.util.HashMap;
import java.util.Map;

public class MinCoin {

	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		int coins[] = { 1, 5, 6, 8 };
		int total = 11;

		System.out.println(" ways To Coin Change " + waysToCoinChange(coins, total));
		System.out.println(" Min coin bottom up " + minCoinsApproach1(coins, total));
		System.out.println(" Min coin bottom up matrix " + minCoinsApproach2(coins, total));
		System.out.println(" Min coin Top down " + minCoinsTopDown(coins, total));
		System.out.println(" Min coin Memoization " + minCoinsTopDownMemoization(coins, total));

	}

	static int waysToCoinChange(int c[], int total) {

		int row = c.length + 1;
		int col = total + 1;
		int solution[][] = new int[row][col];

		// if amount=0 then just return empty set to make the change
		for (int i = 0; i < row; i++) {
			solution[i][0] = 1;
		}

		// if no coins given, 0 ways to change the amount
		for (int i = 1; i < col; i++) {
			solution[0][i] = 0;
		}

		// now fill rest of the matrix.

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				// check if the coin value is less than the amount needed
				if (c[i - 1] <= j) {
					// reduce the amount by coin value and
					// use the subproblem solution (amount-v[i]) and
					// add the solution from the top to it
					solution[i][j] = solution[i - 1][j] + solution[i][j - c[i - 1]];
				} else {
					// just copy the value from the top
					solution[i][j] = solution[i - 1][j];
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(solution[i][j] + " ");
			}
			System.out.println();
		}

		return solution[c.length][total];
	}

	/**
	 * 
	 * Bottom-Up Approach1:
	 */

	static int minCoinsApproach1(int c[], int total) {
		int minCoins[] = new int[total + 1];
		minCoins[0] = 0;
		for (int t = 1; t <= total; t++) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < c.length; i++) {
				if (c[i] <= t && minCoins[(t - c[i])] + 1 < min) {
					min = minCoins[(t - c[i])] + 1;
					minCoins[t] = min;
				}

			}
		}
		return minCoins[total];
	}

	/**
	 * 
	 * Bottom-Up Approach2:
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
			;
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

	/***
	 * Top down
	 */

	static int minCoinsTopDown(int c[], int total) {

		if (total == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < c.length; i++) {

			if (c[i] > total) {
				continue;
			}
			int res = minCoinsTopDown(c, total - c[i]);

			if (res < min) {
				min = res;
			}
		}
		return (min == Integer.MAX_VALUE ? min : min + 1);
	}

	/***
	 * Top down Memoization
	 */

	static int minCoinsTopDownMemoization(int c[], int total) {

		if (total == 0) {
			return 0;
		}
		if (map.containsKey(total)) {
			return map.get(total);
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < c.length; i++) {

			if (c[i] > total) {
				continue;
			}
			int res = minCoinsTopDownMemoization(c, total - c[i]);

			if (res < min) {
				min = res;
			}
		}
		min = (min == Integer.MAX_VALUE ? min : min + 1);

		map.put(total, min);
		return min;
	}
}
