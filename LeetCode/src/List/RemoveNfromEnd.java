package List;

public class RemoveNfromEnd {

	ListNode head;

	/** Initialize your data structure here. */
	public RemoveNfromEnd() {
		head = null;
	}
	

	public ListNode removeNthFromEnd(ListNode head, int n) {
		
//		int listLength = getLength(head);
//		int moveNodeLength = listLength - n;
//		if (moveNodeLength == 0) {
//			head = head.next;
//			return head;
//		}
//		ListNode current = head;
//		ListNode prev = head;
//		for (int i = 0; i < moveNodeLength; i++) {
//			prev = current;
//			current = current.next;
//		}
//		prev.next = current.next;
//		return head;

		ListNode fastPtr = head;
		for (int i = 0; i < n; i++) {
			fastPtr = fastPtr.next;
		}

		if (fastPtr == null) {
			head = head.next;
			return head;
		}
		ListNode slowPtr = head;
		while (fastPtr.next != null) {
			fastPtr = fastPtr.next;
			slowPtr = slowPtr.next;
		}
		slowPtr.next = slowPtr.next.next;
		return head;

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
		
		RemoveNfromEnd obj = new RemoveNfromEnd();
		obj.head = new ListNode(1);
		obj.head.next = new ListNode(2);
		obj.head.next.next = new ListNode(3);
		obj.head.next.next.next = new ListNode(4);
		obj.head.next.next.next.next = new ListNode(5);
		obj.head.next.next.next.next.next = new ListNode(6);
		obj.display();
		obj.removeNthFromEnd(obj.head,2);
		System.out.println("After remove ");
		obj.display();
	}
}
