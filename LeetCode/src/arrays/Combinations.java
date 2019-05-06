package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//numbers-with-repeated-digits
// https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)

public class Combinations {

	/*
	 As each recursion call will represent subset here, we will add resultList(see recursion code below) to the list of subsets in each call.
     Iterate over elements of a set.
       In each iteration
            Add elements to the list
           explore(recursion) and make start = i+1 to go through remaining elements of the array.
           Remove element from the list.
	 */
	public List<List<Integer>> subsetsOrCombination(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		subsetsHelper(list, new ArrayList<>(), nums,0);
		return list;
	}
 
	private void subsetsHelper(List<List<Integer>> list , List<Integer> resultList, int [] nums, int start){
		list.add(new ArrayList<>(resultList));
		for(int i = start; i < nums.length; i++){
           // add element
			resultList.add(nums[i]);
           // Explore
			subsetsHelper(list, resultList, nums, i + 1);
           // remove
			resultList.remove(resultList.size() - 1);
		}
	}
	
	
	// With Duplicate	
	 public List<List<Integer>> subsetsWithDup(int[] nums) {
         List<List<Integer>> list = new ArrayList<>();
         Arrays.sort(nums);
		subsetsHelper(list, new ArrayList<>(), nums,0);
		return list;
    }
    
      private void subsetsHelperDup(List<List<Integer>> list , List<Integer> resultList, int [] nums, int start){
		list.add(new ArrayList<>(resultList));
		for(int i = start; i < nums.length; i++){
        if(i > start && nums[i] == nums[i-1]) continue; // element already exists, skip
           // add element
			resultList.add(nums[i]);
           // Explore
			subsetsHelperDup(list, resultList, nums, i + 1);
           // remove
			resultList.remove(resultList.size() - 1);
		}
	}
	
	

	
	 static void printSubsets(int set[])
		{
			int n = set.length;
	 
			// Run a loop from 0 to 2^n
			for (int i = 0; i < (1<<n); i++)
			{
				System.out.print("{ ");
				int m = 1; // m is used to check set bit in binary representation.
				// Print current subset
				for (int j = 0; j < n; j++)
				{
					if ((i & m) > 0)
					{		
					   System.out.println(" val i "+i);
						System.out.print(set[j] + " ");
					}
					m = m << 1;
				}
				
				System.out.println("}");
			}
		}
	
	public static void main(String[] args) {
		
		int a[] = {1,2,3,4};
		Combinations obj = new Combinations();
		List<List<Integer>> perresult = obj.subsetsOrCombination(a);
		for (List<Integer> per : perresult) {
			System.out.print(" [");
			for (Integer val : per) {
				System.out.print(" " + val);
			}
			System.out.println(" ]");
		}
		
		printSubsets(a);
		
		System.out.println("With duplicate");
		
		int a1[] = {1,2,2};
		 perresult = obj.subsetsWithDup(a1);
		for (List<Integer> per : perresult) {
			System.out.print(" [");
			for (Integer val : per) {
				System.out.print(" " + val);
			}
			System.out.println(" ]");
		}

	}
}
