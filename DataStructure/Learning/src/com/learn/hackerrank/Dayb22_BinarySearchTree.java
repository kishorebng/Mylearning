package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * Given an array, , of size containing distinct elements , sort array in
 * ascending order using the Bubble Sort algorithm above. Once sorted, print the
 * following lines:
 * 
 * 
 * where is the number of swaps that took place.
 * 
 * where is the first element in the sorted array.
 * 
 * where is the last element in the sorted array. Hint: To complete this
 * challenge, you will need to add a variable that keeps a running tally of all
 * swaps that occur during execution.
 * 
 * Input Format
 * 
 * The first line contains an integer, , denoting the number of elements in
 * array . The second line contains space-separated integers describing , where
 * the integer is , .
 * 
 * Constraints
 * 
 * , Output Format
 * 
 * There should be lines of output:
 * 
 * 
 * where is the number of swaps that took place.
 * 
 * where is the first element in the sorted array.
 * 
 * where is the last element in the sorted array. Sample Input 0
 * 
 * 3 1 2 3
 * 
 * Sample Output 0
 * 
 * Array is sorted in 0 swaps. First Element: 1 Last Element: 3
 * 
 * Sample Input 1
 * 
 * 3 3 2 1
 * 
 * Sample Output 1
 * 
 * Array is sorted in 3 swaps. First Element: 1 Last Element: 3
 */

class BNode {
	BNode left, right;
	int data;

	BNode(int data) {
		this.data = data;
		left = right = null;
	}
}

public class Dayb22_BinarySearchTree {

	/* Function to insert data recursively */
	public static BNode insertBin(BNode root, int data) {

		if (root == null) {
			return new BNode(data);
		} else {
			if (root.right == null) {
				root.right = insert(root.right, data);
			} else {
				root.left = insert(root.left, data);
			}
		}
		return root;
	}

	public static int getHeight(BNode root) {
		if (root == null) {
			return -1;
		}
		int leftCount = 1 + getHeight(root.left);
		int rightCount = 1 + getHeight(root.right);
		if (leftCount > rightCount) {
			return leftCount;
		} else {
			return rightCount;
		}

	}

	public static BNode insert(BNode root, int data) {
		if (root == null) {
			return new BNode(data);
		} else {
			BNode cur;
			if (data <= root.data) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		BNode root = null;
		BNode broot = null;
		while (T-- > 0) {
			int data = sc.nextInt();
			root = insert(root, data);
			broot = insertBin(broot, data);
		}
		int height = getHeight(root);
		System.out.println(height);
		
		int height1 = getHeight(broot);
		System.out.println(height1);
	}

}
