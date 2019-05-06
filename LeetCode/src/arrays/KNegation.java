package arrays;

import java.util.PriorityQueue;

public class KNegation {

	 static public int largestSumAfterKNegations(int[] A, int K) {
		 
	        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	        
	        for(int x: A) pq.add(x);	    
	        
	        while( K--  > 0) pq.add(-pq.poll());	  
	        int sum  = 0;
	        
	        for(int i = 0; i < A.length; i++){
	            System.out.println(pq.peek());
	        }
	        
	        for(int i = 0; i < A.length; i++){
	            sum += pq.poll();
	        }
	        return sum;
	    }
	 
	 public static void main(String[] args) {
		 int A [] = {2,-3,-1,5,-4};
		 int K = 2;
		 largestSumAfterKNegations(A,K);
		//System.out.println(largestSumAfterKNegations(A,K));
	}
}
