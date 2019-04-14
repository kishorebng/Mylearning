package Tree;

import java.util.List;

public class ConstructTreewithPreIn {
	


	public static TreeNode buildBinTreewithPreOrder( int[] inorder, int[] preOrder, int inStart,int inEnd, int preStart){
		 if (inStart> inEnd) {
			 return null;
		 }
		 TreeNode root = new TreeNode(preOrder[preStart]);
		 int rootIndex = -1;
			// find the index in inorder:
			for(int i = inStart; i <= inEnd; i++){
				if(inorder[i] == root.val){
					rootIndex = i;
					break;
				}
		}
		if (rootIndex == -1) {
				return null;	
		}
		 root.left = buildBinTreewithPreOrder(inorder, preOrder ,inStart, rootIndex -1, preStart +1); //left side array item of root will form left side tree 
		//right side array item of root will form right side tree
		//pre Start end is +1 & no of elements in left sub tree
		root.right = buildBinTreewithPreOrder( inorder, preOrder , rootIndex + 1, inEnd, preStart+1+(rootIndex - inStart) ); 
		return  root;
	 }
	

	 public static void main(String[] args) {
		 int preorder [] = {3,9,20,15,7};
		  int inorder [] = {9,3,15,20,7};
			List<Integer> order = Operation.inorderTraversal(buildBinTreewithPreOrder(inorder,preorder,0,inorder.length-1,0));		
			System.out.println(" In Order");
			for (Integer val : order) {
				System.out.print(" " + val);
			}
			System.out.println(" ");
	}
}
