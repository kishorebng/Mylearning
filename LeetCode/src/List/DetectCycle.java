package List;

public class DetectCycle {

	
	ListNode head;

	/** Initialize your data structure here. */
	public DetectCycle() {
		head = null;
	}
	
	/**
	 * we should have 2 pointer 1) Fast pointer which moves 2 steps 2) slow pointer
	 *  if slow pointer and fast pointer meets there is cycle (loop in list) 
	 *  when both pointer meet together then reset fastpointer to head start moving in 1 step
	 *  when both fast & slow pointer meet that is intersection
	 */
	public ListNode detectCycle(ListNode head) {
		ListNode slowPtr = head;
		ListNode fastPtr = head;
		while (true) {
			if (fastPtr == null || fastPtr.next == null) {
				return null;
			}
			slowPtr = slowPtr.next;
			fastPtr = fastPtr.next.next;
			if (slowPtr == fastPtr) {
				fastPtr = head;
				while (slowPtr != fastPtr) {
					slowPtr = slowPtr.next;
					fastPtr = fastPtr.next;
				}
				return slowPtr;
			}
		}
	}
	
	public static void main(String[] args) {
		
		DetectCycle obj = new DetectCycle();
		obj.head = new ListNode(1);
		obj.head.next = new ListNode(2);
		obj.head.next.next = new ListNode(3);
		obj.head.next.next.next = new ListNode(4);
		obj.head.next.next.next.next = new ListNode(5);
		obj.head.next.next.next.next.next = new ListNode(6);
		obj.head.next.next.next.next.next.next = obj.head.next.next; // cycle code
		ListNode loopNode = obj.detectCycle(obj.head);
		if (loopNode != null) {
		System.out.println(" Loop node val "+loopNode.val);
		} else {
			System.out.println(" No Loop in List ");
		}
	}
}
