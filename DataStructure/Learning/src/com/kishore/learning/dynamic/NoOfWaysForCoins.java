package com.kishore.learning.dynamic;

import java.util.HashMap;
import java.util.Map;

public class NoOfWaysForCoins {

	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		int coins[] = { 1, 5, 6, 8 };
		int total = 11;
		System.out.println(" ways To Coin Change " + waysToCoinChange(coins, total));
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

}
