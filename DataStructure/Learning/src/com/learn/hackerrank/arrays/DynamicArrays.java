package com.learn.hackerrank.arrays;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Kishore
 * 
 *         Create a list,seqList , of N empty sequences, where each sequence is indexed
 *         from  0 to N-1. The elements within each of the N sequences also use 0-indexing. 
 *         
 *         Create an integer, lastAns, and initialize it to 0.
 *         
 *         The types of queries that can be performed on your list of sequences (seqList) are
 *         described below: 
 *         
 *         Query: 1 x y 
 *         
 *         Find the sequence, seq, at index ( (x ^ lastAns)%N)in seqList.
 *         Append integer y to sequence seq.
 *          
 *         
 *         Query: 2 x y 
 *         
 *         Find the sequence, seq, at  index ((x ^ lastAns)%N)in seqList .
 *        
 *         Find the value of element y% size in seq (where is the size of seq) and assign it to lastAns. 
 *         
 *         Print the new value of lastAns on a new line 
 *         
 *         Task 
 *         
 *         Given N, Q,and Q queries, execute each query.
 *         
 *         
 *         The calculated index is definied in the two bullet points:
           x y: Insert y at the end of the ((x XOR lastans) mod N)th sequence
           x y: Print the value of the (y mod size)th element of the ((x XOR lastans) mod N)th sequence. Here, 
           size denotes the size of the related sequence. Then, assign this integer to lastans.
           So 
           given 2 squences (N=2), lastans=0 and 
           1 0 5 | {type = 1, x = 0, y = 5} y
           you would apply operation #1 to 0 and 5:
           You would insert 5 at the end of the squence at the calculated index of ((0^0)%2)
           Think of it like a hashmap with buckets: the sequences are buckets, and 
           you are inserting a new collision at the end of the bucket's linked list.

 */
public class DynamicArrays {
	
	public static void main(String[] args) {
		
		/** Basic programming  
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int Q = in.nextInt();
		int lastAns = 0;
		int seqlist[][] = new int[N][Q];
		int arr[][] = new int[Q][3];
		int seqlistSize [] [] = new int [N] [1];

		for (int i=0;i < N;i ++) {
			seqlistSize [i][0] = 0;
		}
		for (int i = 0; i < Q; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		for (int k = 0; k < Q; k++) {
			int query = arr[k][0];
			int x = arr[k][1];
			int y = arr[k][2];
			int index = ((x ^lastAns)%N);
			switch (query) {
			case 1:
				    seqlist[index] [seqlistSize[index][0]] = y;
				    seqlistSize[index][0]++;
				    break;
			case 2:
				    lastAns = seqlist[index][y%(seqlistSize[index][0])];
				    System.out.println(lastAns);
				    break;
			}
		} */
		
		Scanner sc = new Scanner(System.in);
	    int lastans =0;
	    int N = sc.nextInt();
	    int Q = sc.nextInt();
	    int tag, x, y, index;

	    ArrayList<Integer>[] list = new ArrayList[N];

	    while(Q-->0){
	        tag = sc.nextInt();
	        x = sc.nextInt();
	        y = sc.nextInt();
	        index = (x^lastans)%N;
	        switch (tag) {
	            case 1:
	                if (list[index] == null) {
	                    ArrayList<Integer> myList = new ArrayList<>();
	                    myList.add(y);
	                    list[index] = myList;
	                } else
	                    list[index].add(y);
	                break;
	            case 2:
	                System.out.println(lastans = list[index].get(y % list[index].size()));
	                break;
	        }
	    }
		
	}

}
