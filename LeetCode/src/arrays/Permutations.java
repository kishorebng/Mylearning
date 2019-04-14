package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
   example
     abc  ->  abc, acb, bac, bca, cab, cba 
   
 */
public class Permutations {

	ArrayList<int []> permutation (int a[]) {
		ArrayList<int []> result =  new ArrayList<int[]>();
		 permutations(a,0, result);
		 return result;
	}
	
	/*
	 * we need to divide array like prefix and suffix
	 * so we use start 
	 */
	void permutations (int a[], int start,ArrayList<int []> result) {
		if (start >= a.length) {   // base case when start is greater or equal to array length
			result.add(a.clone());
		} else {
			    for (int i =start; i <a.length;i++) {
                      swap(a, start, i);
                      permutations(a,start+1,result);
                      swap(a, start, i);
			    }
		}
		
	}
	
	
	void swap (int a[] , int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	 public List<List<Integer>> permute(int[] nums) {
		 List<List<Integer>> result = new ArrayList<List<Integer>>();
		 permutations(nums, 0, result);
		 return result;
	 }
	 
	private void permutations(int[] nums, int start, List<List<Integer>> result) {
	        if (start >= nums.length) {   // base case when start is greater or equal to array length
			  int clone [] = nums.clone();
			  List<Integer> list = new ArrayList<Integer>();
				for (int val : clone) {
					list.add(val);
				}
				result.add(list);
		    } else {
			    for (int i =start; i <nums.length;i++) {
                   swap(nums, start, i);
                   permutations(nums,start+1,result);
                   swap(nums, start, i);
			    }
	     	}
		}
	
	public static void main(String[] args) {
		
		int a[] = {1,2,3};
		Permutations obj = new Permutations();
		ArrayList<int []> result = obj.permutation(a);
		for (int arr[] : result) {
			for (int ai : arr) {
				System.out.print(ai +" ");
			}
			System.out.println();
		}
		List<List<Integer>> perresult = obj.permute(a);
		for (List<Integer> per : perresult) {
			System.out.print(" [");
			for (Integer val : per) {
				System.out.print(" " + val);
			}
			System.out.println(" ]");
		}
	}
}
