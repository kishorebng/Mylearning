package Tree;

public class LowestCommonAncestor {

	// solution conditions is something similar OR operation
	
	static TreeNode findLca(TreeNode root, int one, int two) {
		// Exit condition
		if (root == null)
			return null;

		// if there is match with any of the value return
		if (root.val == one || root.val == two) {
			return root;
		}
		// iterate through left of tree
		TreeNode left = findLca(root.left, one, two);
		// iterate through right of tree
		TreeNode right = findLca(root.right, one, two);

		// if both nodes are not null then we have ancestor
		if (left != null && right != null) {
			return root;
		}
		return (left != null ? left : right);
	}
	
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	// Exit condition
    			if (root == null)
    				return null;

    			// if there is match with any of the value return
    			if (root.val == p.val || root.val == q.val) {
    				return root;
    			}
    			// iterate through left of tree
    			TreeNode left = lowestCommonAncestor(root.left, p, q);
    			// iterate through right of tree
    			TreeNode right = lowestCommonAncestor(root.right, p, q);

    			// if both nodes are not null then we have ancestor
    			if (left != null && right != null) {
    				return root;
    			}
    			return (left != null ? left : right);
    }

	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(6);
		root.left.right = new TreeNode(2);
		root.right.left = new TreeNode(0);
		root.right.right = new TreeNode(8);

		root.left.right.left = new TreeNode(7);
		root.left.right.right = new TreeNode(4);

		TreeNode node = findLca(root, 5, 1);
		System.out.println(" value " + (node == null ? " No Ancestor " : "LCA is " + node.val));
		node = findLca(root, 5, 4);
		System.out.println(" value " + (node == null ? " No Ancestor " : "LCA is " + node.val));
	}

}
