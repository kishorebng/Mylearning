package com.learn.hackerrank;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * Task A level-order traversal, also known as a breadth-first search, visits
 * each level of a tree's nodes from left to right, top to bottom. You are given
 * a pointer, , pointing to the root of a binary search tree. Complete the
 * levelOrder function provided in your editor so that it prints the level-order
 * traversal of the binary search tree.
 * 
 * Hint: You'll find a queue helpful in completing this challenge.
 * 
 * Input Format
 * 
 * The locked stub code in your editor reads the following inputs and assembles
 * them into a BST: The first line contains an integer, (the number of test
 * cases). The subsequent lines each contain an integer, , denoting the value of
 * an element that must be added to the BST.
 * 
 * Output Format
 * 
 * Print the value of each node in the tree's level-order traversal as a single
 * line of space-separated integers.
 */

class LNode {
	LNode left, right;
	int data;

	LNode(int data) {
		this.data = data;
		left = right = null;
	}

}

	

public class Dayb23_LevelOrderBST {
	
	static Queue<LNode> mQueue = new LinkedList<LNode>();
		
	//Level Order or BFS
	static void levelOrder(LNode root) {
		// Write your code here
		if (root == null) {
			return;
		} else {
			mQueue.add(root); 
			while (!mQueue.isEmpty()) {
				if(mQueue.peek().left != null) {
					mQueue.add(mQueue.peek().left);
				}
				if(mQueue.peek().right !=null) {
					mQueue.add(mQueue.peek().right);
				}
				System.out.println(mQueue.remove().data);
			}
		}
	}

	public static LNode insert(LNode root, int data) {
		if (root == null) {
			return new LNode(data);
		} else {
			LNode cur;
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

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		LNode root = null;
		while (T-- > 0) {
			int data = sc.nextInt();
			root = insert(root, data);
		}
		levelOrder(root);
	}

}
