package com.kishore.learning.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class BST_DFS {

	static Queue<BinaryNode> mQueue = new LinkedList<BinaryNode>();

	// DFS or postorder
	/*
	 * Using Queue we implement it i.e we enqueue node and its child at every
	 * iteration and dequeue one node at every iteration this process repeats
	 * until queue is empty.
	 */
	static void DFSorPostorder(BinaryNode root) {
		if(root != null) {
			DFSorPostorder(root.left);
			DFSorPostorder(root.right);
			System.out.println(root.data);
		}		
	}
	
	static void inOrder(BinaryNode root) {
		if(root != null) {
			inOrder(root.left);
			System.out.println(root.data);
			inOrder(root.right);
		}		
	}
	
	static void preOrder(BinaryNode root) {
		if(root != null) {
			System.out.println(root.data);
			preOrder(root.left);
			preOrder(root.right);
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
		
		DFSorPostorder(root);
		System.out.println();
		inOrder(root);
		System.out.println();
		preOrder(root);
	}

}
