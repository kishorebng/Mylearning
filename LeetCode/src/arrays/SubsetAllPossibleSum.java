package arrays;

public class SubsetAllPossibleSum {
 
	/*
	  Complete sum  subset and given subset
	 */
	static void subsetsum_r(int a[], int l, int r,int sum) {
		if (l>r) {
			System.out.print(sum+" ");
			return;
		}
		 subsetsum_r(a, l+1, r,sum+a[l]);
		 subsetsum_r(a, l+1, r,sum);
	
	}
	
	static void subsetSums(int arr[], int n) 
	{ 
	    // There are totoal 2^n subsets 
	     int total = 1<<n; 
	  
	    // Consider all numbers from 0 to 2^n - 1 
	    for ( int i=0; i<total; i++) 
	    { 
	         int sum = 0; 
	  
	        // Consider binary reprsentation of 
	        // current i to decide which elements 
	        // to pick. 
	        for (int j=0; j<n; j++) 
	            if ((i & (1<<j)) > 0) 
	                sum += arr[j]; 
	  
	        // Print sum of picked elements. 
	        System.out.print(sum+" ");
	    } 
	} 
	

	public static void main(String[] args) {
		int nums[] = {3,2,1};	
		subsetsum_r(nums,0,nums.length-1,0);
		System.out.println();
		subsetSums(nums,nums.length);
		System.out.println();
	}
	

	
}
