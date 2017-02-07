package com.kishore.learning.trees;

/**
 * 
 * @author Kishore
 *
 */

//Binary Node with left and right child 

public class BinaryNode {

	BinaryNode left, right;
	int data;
	BinaryNode(int data) {
		this.data = data;
		left = right = null;
	}

}
