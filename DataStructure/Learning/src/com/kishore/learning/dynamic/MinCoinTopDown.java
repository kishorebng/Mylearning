package com.kishore.learning.dynamic;

import java.util.HashMap;
import java.util.Map;

public class MinCoinTopDown {

	static Map<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		int coins[] = { 1, 5, 6, 8 };
		int total = 11;
		System.out.println(" Min coin Top down " + minCoinsTopDown(coins, total));
		System.out.println(" Min coin Memoization " + minCoinsTopDownMemoization(coins, total));

	}

	
	/**
	 *     Eg : Coins 1 2 3
	 *     Change 5 
	 * 
	 *                                                     5
	 *                                                   1,2,3
	 *                                          4                 3                2                   
	 *                                         1,2,3             1,2,3            1,2,3
	 *                                 3          2     1
	 *                                1,2,3    1,2,3   1,2,3
	 *                         2        1   0
	 *                       1,2,3    1,2,3
	 *                  1           0
	 *                 1,2,3
	 *          0  
	 * 
	 *
	 */

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
