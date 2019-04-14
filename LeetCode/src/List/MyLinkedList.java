package List;


public class MyLinkedList {

	ListNode head;

	/** Initialize your data structure here. */
	public MyLinkedList() {
		head = null;
	}

	/**
	 * Get the value of the index-th node in the linked list. If the index is
	 * invalid, return -1.
	 */
	public int get(int index) {
		ListNode current = head;
		if (index < 0) {
			return -1;
		}
		int i = 0;
		while (current != null) {
			if (i == index) {
				return current.val;
			}
			current = current.next;
			i++;
		}
		return -1;
	}

	/**
	 * Add a node of value val before the first element of the linked list. After
	 * the insertion, the new node will be the first node of the linked list.
	 */
	public void addAtHead(int val) {
		ListNode temp = new ListNode(val);
		temp.next = head;
		head = temp;
	}

	/** Append a node of value val to the last element of the linked list. */
	public void addAtTail(int val) {
		ListNode temp = new ListNode(val);
		temp.next = null;
		ListNode current = head;
		while (current.next != null) {
			current = current.next;
		}
		current.next = temp;
	}

	/**
	 * Add a node of value val before the index-th node in the linked list. If index
	 * equals to the length of linked list, the node will be appended to the end of
	 * linked list. If index is greater than the length, the node will not be
	 * inserted.
	 */
	public void addAtIndex(int index, int val) {
		if (index < 0) {
			return;
		} else if (index == 0) {
			addAtHead(val);
		} else {
			ListNode current = head;
			ListNode prev = head;
			int i = 0;
			while (current != null) {
				if (i == index) {
					ListNode temp = new ListNode(val);
					temp.next = current;
					prev.next = temp;
					break;
				}
				i++;
				prev = current;
				current = current.next;
			}

			if (current == null && i == index) {
				addAtTail(val);
			}
		}
	}

	/** Delete the index-th node in the linked list, if the index is valid. */
	public void deleteAtIndex(int index) {
		if (index < 0) {
			return;
		} else if (index == 0) {
			head = head.next;
			return;
		} else {
			ListNode current = head;
			ListNode prev = head;
			int i = 0;
			while (current != null) {
				if (i == index) {
					prev.next = current.next;
					break;
				}
				i++;
				prev = current;
				current = current.next;
			}
		}
	}

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

	public void displayRecurive(ListNode node) {
		if (node == null) {
			return;
		}
		System.out.print(node.val + " ");
		displayRecurive(node.next);
	}

	public void displayReverse(ListNode node) {
		if (node == null) {
			return;
		}
		displayReverse(node.next);
		System.out.print(node.val + " ");
	}

	public ListNode removeElements(ListNode head, int val) {
		
//		if (head != null) {
//			ListNode current = head;
//			ListNode prev = head;
//			while (current != null) {
//				if (head.val == val) {
//					head = head.next;
//					prev = head;
//					current = head;
//				} else {
//					if (current.val == val) {
//						prev.next = current.next;
//					} else {
//						prev = current;
//					}
//					current = current.next;
//				}
//			}
//
//		}
//		return head;

		if (head == null)
			return null;
		ListNode pointer = head;
		while (pointer.next != null) {
			if (pointer.next.val == val)
				pointer.next = pointer.next.next;
			else
				pointer = pointer.next;
		}
		return head.val == val ? head.next : head;
	}

	public ListNode removeElementsRecursive(ListNode head, int val) {
		if (head == null)
			return null;
		head.next = removeElementsRecursive(head.next, val);
		return head.val == val ? head.next : head;
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
		while (evenNode != null && evenNode.next !=null) {
			oddNode.next = evenNode.next;
			oddNode = oddNode.next;
			evenNode.next = oddNode.next;
			evenNode = evenNode.next;
		}	
		evenNode.next = oddHead;

		return head;

	}
	
	
	 public boolean isPalindrome(ListNode head) {
		    if (head == null) {
		    	return true;
		    }
		    ListNode slowPtr = head;
			ListNode fastPtr = head;
			while (fastPtr != null && fastPtr.next != null) {
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next.next;
			}
			if (fastPtr != null) {  // to handle odd number of nodes
				slowPtr = slowPtr.next;
			}
			slowPtr= reverseRecuriveforPalindrome(slowPtr);
			fastPtr = head;
			while (slowPtr !=null) {
				System.out.println("slowPtr val " + slowPtr.val);
				System.out.println("fastPtr val " + fastPtr.val);
				if (slowPtr.val != fastPtr.val) {
					return false;
				}
				slowPtr = slowPtr.next;
				fastPtr = fastPtr.next;
			}
	        return true;
	 }
	 
	 public ListNode reverse(ListNode head) {
		    ListNode prev = null;
		    while (head != null) {
		        ListNode next = head.next;
		        head.next = prev;
		        prev = head;
		        head = next;
		    }
		    return prev;
		}
	 
	 void dump(ListNode node) {
		 while(node != null) {
			 System.out.print(" " + node.val);
			 node = node.next;
		 }
		 System.out.println();

	 }

	public ListNode reverseRecuriveforPalindrome(ListNode head) {
//		if (head == null || head.next == null) {
//			return head; // exit condition
//		}
//		dump(head);
//		ListNode node = reverseRecuriveforPalindrome(head.next);
//		ListNode temp = node.next;
//		temp.next = node;
//		node.next = null;
//		return node;
		
		if (head == null || head.next == null) return head;
		dump(head);
	    ListNode p = reverseList(head.next);
	    head.next.next = head;
	    head.next = null;
	    return p;
	}
	

	public static void main(String[] args) {
		
		// [1,4,-1,-1,4,1]

		MyLinkedList obj = new MyLinkedList();
		// obj.display();
		obj.addAtHead(1);
		// obj.display();
		obj.addAtTail(4);
    	obj.addAtTail(-1);
		obj.addAtTail(-1);
		obj.addAtTail(4);
		obj.addAtTail(1);
//		obj.display();
		obj.reverseRecurive(obj.head);
//		obj.display();
		System.out.println("isPalindrome " +obj.isPalindrome(obj.head));
	//	obj.head = obj.removeElements(obj.head, 1);
	//	obj.display();
//		System.out.println("result " + obj.get(1));
//		System.out.println("result " + obj.get(0));
//		System.out.println("result " + obj.get(2));
//		obj.addAtIndex(1, 2);
//		obj.displayRecurive(obj.head);
//		System.out.println();
//		obj.displayReverse(obj.head);
//		System.out.println();
//		obj.head =obj.reverseList(obj.head);
//		obj.display();
//		obj.head = obj.removeNthFromEnd(obj.head, 2);
//		obj.display();
	}
}
