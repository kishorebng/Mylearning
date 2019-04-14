package Tree;

public class NextRightPtr {

	/*
	 *         1
	 *     2       3
	 * 4      5  6    7  
	 */
    public Node connect(Node root) {
    	Node levelstart = root;
    //	iterate every level
    	while (levelstart!=null) {
    		Node cur = levelstart;   // 1
    		while(cur != null) {
    			if (cur.left != null) {   // 1.left is 2
        			cur.left.next = cur.right;  // 1.left.next i.e 2->next = 3    //  4->next 5
        		}
    			if (cur.right != null && cur.next!=null) {  // 2.right not null   
    				cur.right.next = cur.next.left;  // 5->next = 6
    			}
    			cur = cur.next;  // for 1 is 3   
    		}
    		
    		levelstart = levelstart.left;   // 1->2
    	}
    	return root;
    }
    
    public void connect_r(Node root) {
        if(root == null)
            return;
            
        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }
        
        connect_r(root.left);
        connect_r(root.right);
    }
    
	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
	}
}
