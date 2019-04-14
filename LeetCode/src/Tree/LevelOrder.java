package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrder {

	//Level order or BFS solution
	
		public static List<Integer> levelOrder(TreeNode root) {
			 // public List<List<Integer>> 
			List<Integer> order = new ArrayList<Integer>();
			Queue<TreeNode> mQueue = new LinkedList<TreeNode>();
			if (root != null) {
				mQueue.add(root);
				while (!mQueue.isEmpty()) {
					if (mQueue.peek().left != null) {
						mQueue.add(mQueue.peek().left);
					}
					if (mQueue.peek().right != null) {
						mQueue.add(mQueue.peek().right);
					}
					order.add(mQueue.remove().val);
				}
			} 
			return order;
		}
		
		public static List<List<Integer>> levelOrderIterative(TreeNode root) {
			List<List<Integer>> levelOrder = new ArrayList<List<Integer>>();
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
					levelOrder.add(order);
				}
			}
			
			return levelOrder;
		}
		
		public static List<List<Integer>> levelOrder_r(TreeNode root) {
			 List<List<Integer>> result = new ArrayList<>();
			 if(root != null)	{          
		        levelRecursive(root,0,result);
			 }
		     return result;
		}	
		 public static void levelRecursive(TreeNode root, int level, List<List<Integer>> result) {
		        if(root==null)
		            return;
		        if(level >= result.size()) {
		            result.add(new ArrayList<Integer>());
		        }
		        result.get(level).add(root.val);
		        levelRecursive(root.left,level+1,result);
		        levelRecursive(root.right,level+1,result);
		    }
		 
		 public static void main(String[] args) {
			    TreeNode root = new TreeNode(1);
				root.left = new TreeNode(2);
				root.right = new TreeNode(3);
				root.left.left = new TreeNode(4);
				root.left.right = new TreeNode(5);
				root.right.left = new TreeNode(6);
				root.right.right = new TreeNode(7);
				
				List<List<Integer>> levelOrder = levelOrder_r(root);
				System.out.println("Level Order iterative");
				for (List<Integer> order1 : levelOrder) {
					System.out.print(" [");
					for (Integer val : order1) {
						System.out.print(" " + val);
					}
					System.out.println(" ]");
				}
		}
}
