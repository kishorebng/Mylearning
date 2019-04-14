package List;

public class Operations {


	ListNode head;

	/** Initialize your data structure here. */
	public Operations() {
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
	
	int getLength(ListNode current) {
		int counter = 0;
		while (current != null) {
			counter++;
			current = current.next;
		}
		return counter;
	}

	
	public static void main(String[] args) {

		Operations obj = new Operations();
		obj.display();
		obj.addAtHead(1);
		 obj.display();
		obj.addAtTail(4);
    	obj.addAtTail(-1);
		obj.addAtTail(-1);
		obj.addAtTail(4);
		obj.addAtTail(1);
	}

}
