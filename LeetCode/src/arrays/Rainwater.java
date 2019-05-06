package arrays;

public class Rainwater {

	 static public int trap(int[] height) {
	        int totalAmount = 0;
	        if (height == null || height.length == 0) {
	            return totalAmount;
	        }
	        //construct left most array  with length +1  and intialize 0th to 0 then iterate and assign max of height[i] or leftarray[i]
	        int[] leftMost = new int[height.length + 1];
	        leftMost[0] = 0;        
	        for (int i = 0; i < height.length; i++) {
	            leftMost[i + 1] = Math.max(leftMost[i], height[i]);            
	        }
	        
	        
	        
	        int rightMost = 0; // intialize right most to 0 to start with
	        for (int i = height.length - 1; i >= 0; i--) {
	            rightMost = Math.max(height[i], rightMost); //max of orignal height and rightmost 
	            // if minimum of (left most, right most) is greater than current height then substract  minimum of (left most, right most) with  current height else 0
	            totalAmount += Math.min(leftMost[i], rightMost) > height[i] ? Math.min(leftMost[i], rightMost) - height[i] : 0;            
	        }
	        
	        return totalAmount;
	    }
	 
	public static void main(String[] args) {
		int a[] = {0,1,2,1,0,3,1,0,2};
		System.out.println(trap(a));
	}
}
