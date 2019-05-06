package arrays;

/*
    Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) 
to hold additional elements from nums2.

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

 */

public class MergeSortedArray {
	
	 static public void merge(int[] nums1, int m, int[] nums2, int n) {
	     if (m+n > nums1.length ) {
	    	 try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
		 int nums1Index = m -1;
		 int nums2Index = n -1;
		 int mergeIndex = m + n -1; // length
		 
		 while (nums1Index >= 0 && nums2Index >= 0) {
			 if (nums1[nums1Index] > nums2[nums2Index]) {
				 nums1[mergeIndex]  = nums1[nums1Index];
				 nums1Index --;
			 } else {
				 nums1[mergeIndex]  = nums2[nums2Index];
				 nums2Index --;
			 }
			 mergeIndex --;			 
		 }
		 
		 while (nums2Index >= 0) {
			 nums1[mergeIndex]  = nums2[nums2Index];
			 nums2Index --;
			 mergeIndex --;		
		 }
	 }

	 
	 public static void main(String[] args) {
		
		 int nums1 [] = {1,2,4,5,6,0};
		 int nums2 [] = {3};
		 merge(nums1,5,nums2,1);
		 for (int a :nums1) {
			 System.out.print(" "+a);
		 }
	 }
}
