package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Symmertic {

	  TreeNode root;
	  
	  /** Initialize your data structure here. */
		public Symmertic() {
			root = null;
		}
	
	 // Symmertic solution
	 
	 public static boolean isSymmertic(TreeNode root) {
		 if (root == null) {
			 return true;
		 }
		 return isSymmerticRecursive (root.left,root.right); 
	 }
	 
	 static boolean isSymmerticRecursive(TreeNode left,TreeNode right) {
		 if (left == null && right ==null) {
			 return true;
		 } else if (left != null && right !=null){
			 return (left.val == right.val) && isSymmerticRecursive(left.left,right.right) && isSymmerticRecursive(left.right,right.left);
		 }  else {
			 return false;
		 }
	 }
	 
	 public static boolean isSymmerticIterative(TreeNode root) {
		 Queue<TreeNode> mQueue = new LinkedList<TreeNode>();
		 if (root == null) {
			 return true;
		 }
		 mQueue.add(root.left);
		 mQueue.add(root.right);
		 while (!mQueue.isEmpty()) {
			 TreeNode left = mQueue.remove();
			 TreeNode right = mQueue.remove();
			 if (left == null && right ==null) {
				 continue;
			 }
			 if ((left != null && right ==null) || (left == null && right !=null)){
				 return false;
			 }
			 if (left.val != right.val) {
				 return false;
			 }
			 mQueue.add(left.left);
			 mQueue.add(right.right);
			 mQueue.add(left.right);
			 mQueue.add(right.left);
		 }
	     return true;
    }
	 
	 public static void main(String[] args) {
		 Symmertic obj = new Symmertic();
		 obj.root = new TreeNode(1);
		 obj.root.left = new TreeNode(2);
		 obj.root.right = new TreeNode(2);
		 obj.root.left.left = new TreeNode(3);
		 obj.root.left.right = new TreeNode(4);
		 obj.root.right.left = new TreeNode(4);
		 obj.root.right.right = new TreeNode(3);
		 System.out.println("Symmertic "+isSymmertic(obj.root));
	}
}
