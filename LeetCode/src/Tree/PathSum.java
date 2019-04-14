package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class PathSum {


	public static boolean hasPathSumIterative(TreeNode root, int sum) {
		Queue<TreeNode> mQueue = new LinkedList<TreeNode>();
		Queue<Integer> mRem = new LinkedList<Integer>();
		if (root != null) {
			mQueue.add(root);
			mRem.add(sum-root.val);
			while (!mQueue.isEmpty()) {
				if (mQueue.peek().left == null && mQueue.peek().right == null && mRem.peek() == 0) {
					return true;
				}
				if (mQueue.peek().left != null) {
					mQueue.add(mQueue.peek().left);
					mRem.add(mRem.peek()-mQueue.peek().left.val);
				}
				if (mQueue.peek().right != null) {
					mQueue.add(mQueue.peek().right);
					mRem.add(mRem.peek()-mQueue.peek().right.val);
				}
				mQueue.remove();
				mRem.remove();
			}
		}
		return false;
	}
	 
	public static boolean hasPathSumRecursive(TreeNode root, int sum) {
		 if (root == null) {
			 return false;
		 }
		 if (root.left == null && root.right == null && root.val == sum) {
			 return true;
		 }
		 return hasPathSumRecursive(root.left, sum- root.val) || hasPathSumRecursive(root.right, sum- root.val);
			 
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		System.out.println("sum path "+hasPathSumIterative(root,19));
	}
}
