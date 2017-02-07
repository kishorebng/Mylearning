package com.kishore.learning.arrays;

import java.util.Scanner;

public class ArrayRotate {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		
		Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    int posRotate = sc.nextInt();
	        
	    int lRot[] = new int [n];
	    int rRot[] = new int [n];
	    
	    for (int i=0; i< n; i++) {
	    	int value = sc.nextInt();
	    	lRot[(i+n-posRotate)%n] = value;
	    	rRot[(i+n+posRotate)%n] = value;
	    }
	    
	    System.out.println("Left rotate array");
	    for (int i=0; i< n; i++) {
	    	System.out.print(lRot[i]+" ");
	    }
	    
	    System.out.println();
	    System.out.println("Right rotate array");
	    for (int i=0; i< n; i++) {
	    	System.out.print(rRot[i]+" ");
	    }
	    
	}

}
