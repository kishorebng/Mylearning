package arrays;


/*
   Given an array of integers nums, write a method that returns the "pivot" index of this array.

  We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.

  If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
 */
public class PivotIndex {

	
	/**
	 * 
	 *  first find the sum of all the elements in array
	 *  calculate left sum by adding 1
	 *  then check leftsum  is equal with (sum -leftsum - current index value) if equal then current index is pivot
	 */
    public static int pivotIndex(int[] nums) {
    	int sum =0, leftsum =0;
    	for (int i=0;i<nums.length;i++) {
    		sum += nums[i];
    	}
    	for (int i=0;i<nums.length;i++) {
    		if (i!=0) {
    			leftsum+= nums[i-1];
    		}
    		if (sum - leftsum - nums[i] == leftsum) {
    			return i;
    		}
    		
    	}
        
    	return -1;
    }
    
    public static void main(String[] args) {
    	int nums[] = {1, 7, 3, 6, 5, 6};	
    	System.out.println("pivotIndex is "+pivotIndex(nums));
	}
}
