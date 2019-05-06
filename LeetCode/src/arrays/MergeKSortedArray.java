package arrays;

import java.util.PriorityQueue;

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

// Sorting will be    kn * log kn
// Merging will be  Kn * n

// adding element will take log n time 
// sol will  kn * log k as will adding one element at time 

class MergeArray implements Comparable<MergeArray> {
    
    int rowIndex;
    int colIndex;
	int value;
	
	public MergeArray(int rowIndex, int colIndex,int value) {
	    this.rowIndex = rowIndex;
	    this.colIndex = colIndex;
		this.value = value;
	}

	@Override
	public int compareTo(MergeArray arr) {
		if (value > arr.value) return 1;
		if (value < arr.value) return -1;
		return 0;
	}
	 
 }

public class MergeKSortedArray {
	
	 static public void merge(int A[][]) {
		 
		 //PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		 
		 PriorityQueue<MergeArray> mergeQueue = new PriorityQueue<MergeArray>();
	        
		 int totalSize =0;
		 for (int i=0;i< A.length;i++) {
		/*
		 * 
		     if this added order will n * m (n^2)
		 	 for(int j=0;j<A[i].length;j++) {
				 pq.add(A[i][j]);
			 }
		   */
			 totalSize+= A[i].length;
			 if (A[i].length>0) {
				 mergeQueue.add(new MergeArray(i, 0, A[i][0]));
			 }
			
		 }
		 // generate result array
		 int result [] = new int [totalSize];
		 for(int i =0; !mergeQueue.isEmpty();i++) {
			 MergeArray arr = mergeQueue.poll();
			 result[i] = arr.value;
			 int newcolValue = arr.colIndex +1;
			 if (newcolValue < A[arr.rowIndex].length) {
				 mergeQueue.add(new MergeArray(arr.rowIndex, newcolValue, A[arr.rowIndex][newcolValue]));
			 }
		 }
		 for(int a : result){
	            System.out.print(" " +a);
	      }
		 
	 }

	 
	 public static void main(String[] args) {
		 
		 int[][] A = new int[5][];
		 A[0] = new int[] { 1, 5, 8, 9 };
		 A[1] = new int[] { 2, 3, 7, 10 };
		 A[2] = new int[] { 4, 6, 11, 15 };
		 A[3] = new int[] { 9, 14, 16, 19 };
		 A[4] = new int[] { 2, 4, 6, 9 };
		 merge(A);
	 }
}
