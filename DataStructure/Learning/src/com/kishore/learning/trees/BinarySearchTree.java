package com.kishore.learning.trees;

public class BinarySearchTree {

	BinarySearchTree() {

	}
	public BinaryNode insert(BinaryNode root, int data) {
		if (root == null) {
			return new BinaryNode(data);
		} else {
			BinaryNode cur;
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

	public int getHeight(BinaryNode root) {
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
