package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Operation {


	/* Function to insert data recursively */
	public static TreeNode insert(TreeNode root, int data) {

		if (root == null) {
			return new TreeNode(data);
		} else {
			if (root.left == null) {
				root.left = insert(root.left, data);
			} else {
				root.right = insert(root.right, data);
			}
		}
		return root;
	}
	

	public static List<Integer> preorderTraversalIterative(TreeNode root) {
		List<Integer> order = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (root != null) {
			stack.push(root);
			while (!stack.empty()) {
				root = stack.peek();
				stack.pop();
				order.add(root.val);
				if (root.right != null) {
					stack.push(root.right);
				}
				if (root.left != null) {
					stack.push(root.left);
				}
			}
		}
		return order;
	}	
	
	public static List<Integer> inorderTraversalIterative(TreeNode root) {
		List<Integer> order = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while (!stack.empty() || root != null) {
			if (root !=null) {
				stack.push(root);
				root = root.left;
			} else {
				root = stack.peek();
				stack.pop();
				order.add(root.val);
				root = root.right;
			}
		}
		return order;
	}		
	
	public static List<Integer> postorderTraversalIterative(TreeNode root) {
		List<Integer> order = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode prev = null;
		while (!stack.empty() || root != null) {
			if (root !=null) {
				stack.push(root);
				root = root.left;
			} else {
				root = stack.peek();
				if (root.right != null && root.right != prev) {
					root = root.right;
				} else {
					order.add(root.val);
					stack.pop();
					prev = root;
					root = null;
				}
			}
		}
		return order;
	}	
	

	
	public static  List<Integer> preorderTraversal(TreeNode root) {
		return preorderTraversal(root, null);
	}

	public static  List<Integer> preorderTraversal(TreeNode root, List<Integer> order) {
		if (order == null) {
			order = new ArrayList<Integer>();
		}
		if (root != null) {
			order.add(root.val);
			order = preorderTraversal(root.left, order);
			order = preorderTraversal(root.right, order);
		}
		return order;
	}

	public static  List<Integer> inorderTraversal(TreeNode root) {
		return inorderTraversal(root, null);
	}

	public static  List<Integer> inorderTraversal(TreeNode root, List<Integer> order) {
		if (order == null) {
			order = new ArrayList<Integer>();
		}
		if (root != null) {
			order = inorderTraversal(root.left, order);
			order.add(root.val);
			order = inorderTraversal(root.right, order);
		}
		return order;
	}

	public static  List<Integer> postorderTraversal(TreeNode root) {
		return postorderTraversal(root, null);
	}

	public static  List<Integer> postorderTraversal(TreeNode root, List<Integer> order) {
		if (order == null) {
			order = new ArrayList<Integer>();
		}
		if (root != null) {
			order = postorderTraversal(root.left, order);
			order = postorderTraversal(root.right, order);
			order.add(root.val);
		}
		return order;
	}
	
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int T = sc.nextInt();
//		TreeNode root = null;
//		while (T-- > 0) {
//			int data = sc.nextInt();
//			root = insert(root, data);
//		}
		
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		List<Integer> order = preorderTraversal(root);
		System.out.println(" Pre Order");
		for (Integer val : order) {
			System.out.print(" " + val);
		}
		System.out.println(" ");

		order = inorderTraversal(root);
		System.out.println(" In Order");
		for (Integer val : order) {
			System.out.print(" " + val);
		}
		System.out.println(" ");

		order = postorderTraversal(root);
		System.out.println(" Post Order");
		for (Integer val : order) {
			System.out.print(" " + val);
		}
		System.out.println(" ");

		order = preorderTraversalIterative(root);
		System.out.println("  PreOrder iterative");
		for (Integer val : order) {
			System.out.print(" " + val);
		}
		System.out.println(" ");

		order = inorderTraversalIterative(root);
		System.out.println(" In Order iterative");
		for (Integer val : order) {
			System.out.print(" " + val);
		}
		System.out.println(" ");

		order = postorderTraversalIterative(root);
		System.out.println(" Post Order iterative");
		for (Integer val : order) {
			System.out.print(" " + val);
		}
		System.out.println(" ");
		
	}
}
