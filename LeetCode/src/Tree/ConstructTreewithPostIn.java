package Tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructTreewithPostIn {
	

	 public static TreeNode buildTreePost(int[] inorder, int[] postorder) {
		 if (inorder.length == 0) {
			 return null;
		 }
		 //map is used to avoid everytime iteration in recursive function  to find root index
		 Map<Integer,Integer> inOrderIndexmap = new HashMap<Integer, Integer>();
		 for (int i =0; i< inorder.length; i++) {
			 inOrderIndexmap.put(inorder[i], i);
		 }
		  return buildBinTree1withPost(inOrderIndexmap, inorder, postorder ,postorder.length-1, 0, inorder.length -1);  //postorder.length-1 because in post order root will be always at end.
	 }
	 
	 public static TreeNode buildBinTree1withPost(Map<Integer, Integer> inOrderIndexmap, int[] inorder, int[] postorder, int postRootIndex, int low, int high){
		 if (low> high) {
			 return null;
		 }
		 TreeNode root = new TreeNode(postorder[postRootIndex]);
//		 int rootIndex = inOrderIndexmap.get(postorder[postRootIndex]);
		 int rootIndex = 0;
			// find the index in inorder:
			for(int i = high; i >= low; i--){
				if(inorder[i] == root.val){
					rootIndex = i;
					break;
				}
			}
		 root.right = buildBinTree1withPost(inOrderIndexmap, inorder, postorder ,postRootIndex-1, rootIndex + 1, high); //right side array item of root will form right side tree
		//left side array item of root will form left side tree 
		//post end is -1 & no of elements in right sub tree
		 root.left = buildBinTree1withPost(inOrderIndexmap, inorder, postorder ,postRootIndex-(high-rootIndex)-1, low, rootIndex -1); 
		 return  root;
	 }
	 
	 
	 
	 // 2nd way
	 
	public static TreeNode buildBinTreewithPost( int[] inorder, int[] postorder, int inStart,int inEnd, int postEnd){
		 if (inStart> inEnd) {
			 return null;
		 }
		 TreeNode root = new TreeNode(postorder[postEnd]);
		 
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
		
		 root.right = buildBinTreewithPost( inorder, postorder , rootIndex + 1, inEnd, postEnd -1); //right side array item of root will form right side tree
		//left side array item of root will form left side tree 
		 //post end is -1 & no of elements in right sub tree
		 root.left = buildBinTreewithPost(inorder, postorder ,inStart, rootIndex -1, postEnd-1-(inEnd -rootIndex)); 
		 return  root;
	 }
	

	 public static void main(String[] args) {
			
			int inorder [] = {9,3,15,20,7};
			int	postorder [] = { 9,15,7,20,3};
			List<Integer> order = Operation.inorderTraversal(buildBinTreewithPost(inorder,postorder,0,inorder.length-1,postorder.length-1));		
			System.out.println(" In Order");
			for (Integer val : order) {
				System.out.print(" " + val);
			}
			System.out.println(" ");
	}
}
