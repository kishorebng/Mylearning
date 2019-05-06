package arrays;

import java.util.ArrayList;
import java.util.List;

public class SubsetZero {
 
		
	/*** subset of zero sum */
	public static List<List<Integer>> subset_Zerosum(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		subset_Zerosum( nums, list, new ArrayList<>(),0,0);
		return list;
	}
	
		
	static void subset_Zerosum(int a[],List<List<Integer>> list , List<Integer> resultList,int start,int sum) {
		if (sum == 0 && resultList.size() > 0) {
			list.add(new ArrayList<>(resultList));
			return;
		}
		if (start == a.length ) {
			 return;
		}
		resultList.add(a[start]);
		subset_Zerosum(a, list ,resultList,start+1,sum + a[start]) ;
		resultList.remove(resultList.size()-1);
  	subset_Zerosum(a, list ,resultList,start+1,sum) ;
		 
	}
	
	public static void main(String[] args) {
		
		int nums1[] = {3,2,1,-1,-2,-3};	
		List<List<Integer>> sumsub =  subset_Zerosum(nums1);
			for (List<Integer> per : sumsub) {
				System.out.print(" [");
				for (Integer val : per) {
					System.out.print(" " + val);
				}
				System.out.println(" ]");
			}
	}

	
}
