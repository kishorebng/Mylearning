package com.kishore.learning.trees;

public class BinaryTree {

	/* Function to insert data recursively */
	public static BinaryNode insert(BinaryNode root, int data) {

		if (root == null) {
			return new BinaryNode(data);
		} else {
			if (root.left == null) {
				root.left = insert(root.left, data);
			} else {
				root.right = insert(root.right, data);
			}
		}
		return root;
	}

	public static int getHeight(BinaryNode root) {
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

}
