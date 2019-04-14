package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaxDepth {

	// Depth or height Solution
	
		 public static int maxDepth(TreeNode root) {
			 if (root == null) {
				 return -1;
			 }
				int leftCount = 1 + maxDepth(root.left);
				int rightCount = 1 + maxDepth(root.right);
			    return  Math.max(leftCount,rightCount);
		 }
		 
		 public static int maxDepthIterative(TreeNode root) {
			    int depth =0;
				Queue<TreeNode> mQueue = new LinkedList<TreeNode>();
				if (root != null) {
					mQueue.add(root);
					while (!mQueue.isEmpty()) {
						// iterate by current size of queue so that it will be in current level
						List<Integer> order = new ArrayList<Integer>();
						int size = mQueue.size();
						while (size > 0) {
							if (mQueue.peek().left != null) {
								mQueue.add(mQueue.peek().left);
							}
							if (mQueue.peek().right != null) {
								mQueue.add(mQueue.peek().right);
							}
							order.add(mQueue.remove().val);
							size--;
						}
						depth++;
					}
				}
				return depth;
		}
		 
	public static void main(String[] args) {

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);
		System.out.println(" Depth of root "+maxDepth(root));
	}
		 
}
