package Tree;

/*
 *  Binary tree is said to be balanced if depth of left and right node won't difference more than 1
 */
public class BalancedBT {

	  TreeNode root;
	  
	  /** Initialize your data structure here. */
		public BalancedBT() {
			root = null;
		}
	
	public static boolean isBalanced(TreeNode root) {
			if (getBalanced(root) > -1) 
				return true;
			return false;
		}
	
		// Balanced BT 
	 public static int getBalanced(TreeNode root) {
		 if (root == null) {
			 return 0;
		 }
		 int lh = getBalanced(root.left);
		 int rh = getBalanced(root.left);
		 
		 if (lh == -1 || rh == -1) {
			 return -1;
		 }
		 
		 if (Math.abs(lh-rh)> 1) {
			 return -1;
		 }
		 
		 return (lh>rh)?lh+1:rh+1;
	 }
	 
	 
	 public static void main(String[] args) {
		 BalancedBT obj = new BalancedBT();
		 obj.root = new TreeNode(1);
		 obj.root.left = new TreeNode(2);
		 obj.root.right = new TreeNode(2);
		 obj.root.left.left = new TreeNode(3);
		 obj.root.left.right = new TreeNode(4);
		 obj.root.right.left = new TreeNode(4);
		 obj.root.right.right = new TreeNode(3);
		 System.out.println("Balanced  "+isBalanced(obj.root));
	}
}
