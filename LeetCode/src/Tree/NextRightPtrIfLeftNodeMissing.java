package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class NextRightPtrIfLeftNodeMissing {

	/*
	 *         1
	 *     2       3            2 -> is parent
	 * 4      5       7         4 -> childhead  first node in child &   child (nextChild) keeps moving to next 
	 * 
	 * we need 2 iteration one for vertical and other for horizontal  
	 */
    public static Node connectNode(Node root) {
    	
    	
    	 Node parent = root;    	
     	Node childHead = null;
     	Node nextChild = null;
     	        
     	     while (parent !=null) {
     	    	 while (parent != null) {
     	    		 if (parent.left !=null) {
     	    			 
     	    			 if (childHead == null) {
     	    				 childHead = parent.left;
     	    			 } else {
     	    				 nextChild.next = parent.left;
     	    			 }
     	    			 nextChild = parent.left;
     	    		 }
     	    		 
                       if (parent.right !=null) {
     	    			 
     	    			 if (childHead == null) {
     	    				 childHead = parent.right;
     	    			 } else {
     	    				 nextChild.next = parent.right;
     	    			 }
     	    			 nextChild = parent.right;
     	    		 }
     	    		 
     	    		 parent = parent.next;
     	    	 }    	    	 
     	    	 parent = childHead;
     	    	 nextChild = null;
     	    	 childHead = null;
     	    			 
     	     }
     	
     	return root;     
   
    }
    
    public static Node levelOrderConnectNode(Node root) {
		Queue<Node> mQueue = new LinkedList<Node>();
		if (root != null) {
			mQueue.add(root);
			while (!mQueue.isEmpty()) {
				// iterate by current size of queue so that it will be in current level
				int size = mQueue.size();
				while (size > 0) {
					Node node = mQueue.remove();
					if (size !=0) {
						node.next = mQueue.peek();
					} else {
						node.next = null;
					}
					if (node.left != null) {
						mQueue.add(node.left);
					}
					if (node.right != null) {
						mQueue.add(node.right);
					}
					size--;
				}
				// level end 
			}
		}
		
		return root;
	}
    

    
    /*
     * i don't know the logic
     */
	
	public void connect(Node root) {

        if(root == null)return;
        if(root.left != null)
        {
            if(root.right != null)
            {
                root.left.next = root.right;
                root.right.next = findNext(root);
            }
            else {
                root.left.next = findNext(root);
            }

        }else if (root.right != null)
        {
            root.right.next = findNext(root);
        }
        connect(root.right);
        connect(root.left);

    }

    private Node findNext(Node root)
    {
    	Node sibling = root.next;
        while (sibling != null)
        {
            if(sibling.left == null && sibling.right == null)
            {
                sibling = sibling.next;
            }else {
                break;
            }
        }
        if(sibling == null)return null;
        return sibling.left == null ? sibling.right : sibling.left;
    }

    
    public void connectIterative(Node root) {
        if(root == null)return;
        Node dummyHead = new Node(0);
        Node pre = dummyHead;
        while ( root != null)
        {
            pre = dummyHead;
            while (root != null)
            {
                if(root.left != null)
                {
                    pre.next = root.left;
                    pre = pre.next;
                }
                if(root.right != null)
                {
                    pre.next = root.right;
                    pre = pre.next;
                }
                root = root.next;  // sibling node
            }
            root = dummyHead.next; // next level
            dummyHead.next = null;
        }
    }
	/*
	   
	                    1
					2	   3
			    4 		       5
	 */
	
	public static void main(String[] args) {
//		Node root = new Node(1);
//		root.left = new Node(2);
//		root.right = new Node(3);
//		root.left.left = new Node(4);
//		root.left.right = new Node(5);
//		root.right.right = new Node(7);
		
		
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.right.right = new Node(7);
		levelOrderConnectNode(root);
	}
}
