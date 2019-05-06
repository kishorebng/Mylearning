package arrays;

public class Median {

	// O(m+n)
	static float findmedian1(int nums1[], int nums2[]) {
		int aLength = nums1.length;
		int bLength = nums2.length;
		int merge [] = new int [aLength+ bLength];
		int i = 0,j =0,k=0;
		while (i < aLength && j < bLength) {
			if (nums1[i] < nums2[j]) {
				merge[k] = nums1[i];
				i++;

			} else {
				merge[k] = nums2[j];
				j++;
			}
			k++;
		}
		while (i< aLength) {
			merge[k] = nums1[i];
			i++;
			k++;
		}
		while (j< bLength) {
			merge[k] = nums2[j];
			j++;
			k++;
		}
		int medianIndex = merge.length/2;
		return (merge.length%2 ==0)?(float) (merge[medianIndex]+merge[medianIndex-1])/2 : merge[medianIndex];
	}
	
	

	// O(log (m+n))
	static double findMedianSortedArrays(int nums1[], int nums2[]) {
        // if 1st array is length swap order for median
		if (nums1.length> nums2.length) {
			return findMedianSortedArrays(nums2,nums1);
		}
		
		int xLength = nums1.length;
	    int yLength = nums2.length;
		
	    int start = 0;
		int end = xLength;
		while (start <= end) {

			int midX = (start+end)/2;
			int midY = (xLength+yLength+1)/2 - midX;
			
			 //if MidX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if MidX is length of input then there is nothing on right side. Use +INF for minRightX
			int maxLeftX = (midX==0)?Integer.MIN_VALUE: nums1[midX-1];
			int minRightX = (midX==xLength)?Integer.MAX_VALUE: nums1[midX];
			
			 //if MidY is 0 it means nothing is there on left side. Use -INF for maxLeftY
            //if MidY is length of input then there is nothing on right side. Use +INF for minRightY
			int maxLeftY = (midY==0)?Integer.MIN_VALUE: nums2[midY-1];
			int minRightY = (midY==yLength)?Integer.MAX_VALUE: nums2[midY];
			
						
			if (maxLeftX <= minRightY && maxLeftY <= minRightY) {
				 //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
				if ((xLength + yLength)%2 ==0){
					return ((double) (max(maxLeftX,maxLeftY) +min(minRightX, minRightY))) /2;
				}
			     return  max(maxLeftX,maxLeftY);
			} else if (maxLeftX > minRightY) {
				//we are too far on right side for partitionX. Go on left side.
				end = midX - 1;
			} else {
				 //we are too far on left side for partitionX. Go on right side.
				start = midX + 1;
			}
		}
		//Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
	}
	
	static int max(int x, int y) {
		return x>y?x:y;
	}
	
	static int min(int x, int y) {
		return x<y?x:y;
	}


	public static void main(String[] args) {
		
		int a [] = {1,2}; 
		int b [] = {3,4,5,6}; 
		
		System.out.println("Merge method "+findmedian1(a,b));
		
		
		System.out.println(findMedianSortedArrays(a,b));

	}

}
