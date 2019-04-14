package List;

public class HasCycle {

	
	ListNode head;

	/** Initialize your data structure here. */
	public HasCycle() {
		head = null;
	}
	
	/**
	 *   we should have 2 pointer 1) Fast pointer which moves 2 steps 2) slow pointer
	 *  if slow pointer and fast pointer meets there is cycle (loop in list)
	 * 
	 */
	public boolean hasCycle(ListNode head) {
		ListNode slowPtr = head;
		ListNode fastPtr = head;
		/*
		 * if (head != null) { while (slowPtr !=null || slowPtr.next !=null || fastPtr
		 * !=null || fastPtr.next !=null || fastPtr.next.next !=null) { slowPtr =
		 * slowPtr.next; fastPtr = fastPtr.next.next; if (slowPtr == fastPtr) { return
		 * true; } } }
		 */
		while (true) {
			if (fastPtr == null || fastPtr.next == null) {
				return false;
			}
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr == fastPtr) {
				return true;
			}
		}
	}
	
	public static void main(String[] args) {
		
		HasCycle obj = new HasCycle();
		obj.head = new ListNode(1);
		obj.head.next = new ListNode(2);
		obj.head.next.next = new ListNode(3);
		obj.head.next.next.next = new ListNode(4);
		obj.head.next.next.next.next = new ListNode(5);
		obj.head.next.next.next.next.next = new ListNode(6);
		obj.head.next.next.next.next.next.next = obj.head.next.next;
		System.out.println("has Cycle "+ obj.hasCycle(obj.head));
	}
}
