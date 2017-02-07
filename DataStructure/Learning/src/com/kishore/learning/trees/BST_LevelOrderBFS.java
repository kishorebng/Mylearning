package com.kishore.learning.trees;

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

public class BST_LevelOrderBFS {

	static Queue<BinaryNode> mQueue = new LinkedList<BinaryNode>();

	// Level Order or BFS
	/*
	 * Using Queue we implement it i.e we enqueue node and its child at every
	 * iteration and dequeue one node at every iteration this process repeats
	 * until queue is empty.
	 */
	static void levelOrder(BinaryNode root) {
		// Write your code here
		if (root == null) {
			return;
		} else {
			mQueue.add(root);
			while (!mQueue.isEmpty()) {
				if (mQueue.peek().left != null) {
					mQueue.add(mQueue.peek().left);
				}
				if (mQueue.peek().right != null) {
					mQueue.add(mQueue.peek().right);
				}
				System.out.println(mQueue.remove().data);
			}
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		BinaryNode root = null;
		BinarySearchTree binarSearchTree = new BinarySearchTree();
		while (T-- > 0) {
			int data = sc.nextInt();
			root = binarSearchTree.insert(root, data);
		}
		levelOrder(root);

	}

}
