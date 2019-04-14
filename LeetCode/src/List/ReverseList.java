package List;

public class ReverseList {

	ListNode head;

	/** Initialize your data structure here. */
	public ReverseList() {
		head = null;
	}
	

	public ListNode reverseList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode prev = null;
		ListNode current = head;
		ListNode next = head.next;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}
	

	/* 
	 * 1->2->3->null
	 * */
	public void reverseRecurive(ListNode node) {
		if (node.next == null) {
			head = node; // exit condition
			return;
		}
		reverseRecurive(node.next);
		// Reverse stmt
		//eg for 2
		ListNode temp = node.next;  // temp = 2.next i.e 3
		temp.next = node;             // temp.next i.e 3.next = 2; 
		node.next = null;             // node.next  i.e 2.next = null  

	}
	
	public void display() {
		ListNode start = head;
		while (start != null) {
			System.out.print(start.val + " ");
			start = start.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		ReverseList obj = new ReverseList();
		obj.head = new ListNode(1);
		obj.head.next = new ListNode(2);
		obj.head.next.next = new ListNode(3);
		obj.head.next.next.next = new ListNode(4);
		obj.head.next.next.next.next = new ListNode(5);
		obj.head.next.next.next.next.next = new ListNode(6);
		obj.display();
//		obj.reverseRecurive(obj.head);
		obj.head = obj.reverseList(obj.head);
		System.out.println("After Reverse ");
		obj.display();
	}
}
