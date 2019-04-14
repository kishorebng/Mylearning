package List;

public class PairSwap {


	ListNode head;

	/** Initialize your data structure here. */
	public PairSwap() {
		head = null;
	}
	
	/**
	 *  think for 3 below cases to solve this problem
	 *  1->2->NULL
	 *  1->2->3->NULL
	 *  1->2->3->4->NULL
	 * 	 */
	public ListNode pairSwap(ListNode head) {
		if (head == null || head.next==null) {
			return head;
		}
		ListNode prev = head;
		ListNode new_head = head.next;
		ListNode next = head.next;
		
		while (next != null) {
			if (next.next == null) {
				prev.next = null;
				next.next = prev;
				prev = next = null;
			} else {
				ListNode temp = next.next;
				if (temp.next != null) {
					prev.next = temp.next;
				} else {
					prev.next = temp;
				}
				next.next = prev;
				prev = temp;
				next = temp.next;
			}
		}
		return new_head;
	}
	
	public ListNode pairSwap_r(ListNode head) {
		if (head == null || head.next==null) {
			return head;
		}
		ListNode nextNode = head.next;
		head.next = pairSwap_r(nextNode.next);
		nextNode.next =  head;
	    return nextNode;
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
		
		PairSwap obj = new PairSwap();
		obj.head = new ListNode(1);
//		obj.head.next = new ListNode(2);
//		obj.head.next.next = new ListNode(3);
//		obj.head.next.next.next = new ListNode(4);
//		obj.head.next.next.next.next = new ListNode(5);
//		obj.head.next.next.next.next.next = new ListNode(6);
		obj.display();
		System.out.println("Pair Swap ");
		obj.head = obj.pairSwap_r(obj.head);
		obj.display();
		
	}
	
}
