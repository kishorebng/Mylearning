package arrays;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
 
		
	public static List<List<Integer>> subset_givensum(int[] nums, int sum) {
		List<List<Integer>> list = new ArrayList<>();
		subset_givensum_r( nums, list, new ArrayList<>(),0,sum);
		return list;
	}
	
		
	static void subset_givensum_r(int a[],List<List<Integer>> list , List<Integer> resultList,int start,int sum) {
		if (sum ==0) {
			list.add(new ArrayList<>(resultList));
			return;
		}
		if (start == a.length || sum <0) {
			 return;
		}
		resultList.add(a[start]);
		subset_givensum_r(a, list ,resultList,start+1,sum- a[start]) ;
		resultList.remove(resultList.size()-1);
		subset_givensum_r(a, list ,resultList,start+1,sum) ;
		 
	}
	

	/**
     * Solves the subset sum problem using a dynamic programming approach. The time complexity is O(sum*n).
     * 
     * @param s
     * @param n
     * @param sum
     * @return
     */
    public static boolean isSubsetSumDP(int[] s, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= sum; i++) {
            dp[0][i] = false;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= s[i - 1]) {
                    dp[i][j] = dp[i][j] || dp[i - 1][j - s[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }
	
	public static void main(String[] args) {
		int nums[] = {3,2,1};	
		
		List<List<Integer>> sumsub =  subset_givensum(nums,3);
		for (List<Integer> per : sumsub) {
			System.out.print(" [");
			for (Integer val : per) {
				System.out.print(" " + val);
			}
			System.out.println(" ]");
		}
		
	}
	
	
	
	
	
	
	
}
