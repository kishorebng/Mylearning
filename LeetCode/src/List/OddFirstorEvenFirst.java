package List;

public class OddFirstorEvenFirst {


	ListNode head;

	/** Initialize your data structure here. */
	public OddFirstorEvenFirst() {
		head = null;
	}
	
	public ListNode oddEvenList(ListNode head) {

		if (head == null) {
			return head;
		}
		ListNode oddNode = head;
		ListNode evenNode = head.next;
		ListNode evenHead = head.next;
		while (evenNode != null && evenNode.next !=null) {
			oddNode.next = evenNode.next;
			oddNode = oddNode.next;
			
			evenNode.next = oddNode.next;
			evenNode = evenNode.next;
		}	
		oddNode.next = evenHead;
		return head;

	}

	public ListNode evenOddList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode oddNode = head;
		ListNode evenNode = head.next;
		ListNode oddHead = head;
		ListNode evenHead = head.next;
		while ((evenNode != null && evenNode.next !=null)) {
			oddNode.next = evenNode.next;
			oddNode = oddNode.next;
		
			evenNode.next = oddNode.next;
			if(evenNode.next != null) {
				evenNode = evenNode.next;
			}
			
		}	
		evenNode.next = oddHead;
		oddNode.next = null;

		return evenHead;

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
		
		OddFirstorEvenFirst obj = new OddFirstorEvenFirst();
		obj.head = new ListNode(1);
		obj.head.next = new ListNode(2);
		obj.head.next.next = new ListNode(3);
		obj.head.next.next.next = new ListNode(4);
		obj.head.next.next.next.next = new ListNode(5);
		obj.head.next.next.next.next.next = new ListNode(6);
		obj.display();
		System.out.println("Even First ");
		obj.head = obj.evenOddList(obj.head);
		obj.display();
		
		OddFirstorEvenFirst obj1 = new OddFirstorEvenFirst();
		obj1.head = new ListNode(1);
		obj1.head.next = new ListNode(2);
		obj1.head.next.next = new ListNode(3);
		obj1.head.next.next.next = new ListNode(4);
		obj1.head.next.next.next.next = new ListNode(5);
		obj1.head.next.next.next.next.next = new ListNode(6);
		System.out.println("List ");
		obj1.display();
		System.out.println("odd First ");
		obj1.head = obj1.oddEvenList(obj1.head);
		obj1.display();
	}
	
}
