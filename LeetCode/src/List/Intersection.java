package List;

public class Intersection {
	
	ListNode head;

	/** Initialize your data structure here. */
	public Intersection() {
		head = null;
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

		
//		 int aCnt = 0; int bCnt = 0;
//		  
//		 ListNode current = headA; 
//		  while (current != null) { 
//		  aCnt++;
//		   current = current.next;
//		  } 
//		  current = headB; 
//		  while (current != null) { 
//		  bCnt++; 
//		  current = current.next; 
//		  } 
//		  int diff = aCnt == bCnt ? 0 : aCnt - bCnt; 
//		  if (diff < 0) {
//		  diff *= -1; 
//		  }
//		  
//		  ListNode fastPtr = null; 
//		  ListNode slowPtr = null;
//		  
//		  if (aCnt > bCnt) { 
//		  fastPtr = headA; 
//		 slowPtr = headB; 
//		   } else {
//		   fastPtr = headB; 
//		   slowPtr = headA;
//		    } 
//		    for (int i = 0; i < diff; i++) {
//		     fastPtr = fastPtr.next; 
//		     } 
//		     if (slowPtr == fastPtr) {
//		      return slowPtr; 
//		    } 
//		   while (fastPtr != null && slowPtr != null) { 
//		   slowPtr = slowPtr.next; 
//		  fastPtr = fastPtr.next; 
//		   if (slowPtr == fastPtr) { 
//		   return slowPtr;
//		     } 
//		  } 
//		    return null;
		

		// without knowing the count
		ListNode aPtr = headA;
		ListNode bPtr = headB;
		while (aPtr != bPtr) { // reset the pointer to other list
			aPtr = aPtr == null ? headB : aPtr.next;
			bPtr = bPtr == null ? headA : bPtr.next;
		}

		return aPtr;

	}
	
	int getLength(ListNode current) {
		int counter = 0;
		while (current != null) {
			counter++;
			current = current.next;
		}
		return counter;
	}
	
	public static void main(String[] args) {
		
		Intersection obj1 = new Intersection();
		obj1.head = new ListNode(1);
		obj1.head.next = new ListNode(2);
		obj1.head.next.next = new ListNode(3);
		obj1.head.next.next.next = new ListNode(4);
		obj1.head.next.next.next.next = new ListNode(5);
		
		Intersection obj2 = new Intersection();
		obj2.head = new ListNode(4);
		obj2.head.next = new ListNode(5);
		obj2.head.next.next = new ListNode(6);
		//obj2.head.next.next.next = obj1.head.next.next;
	
		ListNode loopNode = obj1.getIntersectionNode(obj1.head ,obj2.head);
		if (loopNode != null) {
		System.out.println(" Loop node val "+loopNode.val);
		} else {
			System.out.println(" No Loop in List ");
		}
		
	}
}
