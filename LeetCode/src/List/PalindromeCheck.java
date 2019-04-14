package List;

public class PalindromeCheck {

	ListNode head;

	/** Initialize your data structure here. */
	public PalindromeCheck() {
		head = null;
	}
	
	
	 /*
	  * use 2 pointer fast 2 steps and slow 1 step 
	  * when fast pointer reaches last node slow is in middle of list  
	  * move slowptr 1 step more in case of odd no of node
	  * reverse 2nd half the list
	  * reset fastptr to head
	  * now start compare of fastptr and reversed ptr until end is reached to check palindrome    
	  */
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
			slowPtr= reverse(slowPtr);
			fastPtr = head;
			while (slowPtr !=null) {
//				System.out.println("slowPtr val " + slowPtr.val);
//				System.out.println("fastPtr val " + fastPtr.val);
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

		public ListNode reverseRecuriveforPalindrome(ListNode head) {
//			if (head == null || head.next == null) {
//				return head; // exit condition
//			}
//			dump(head);
//			ListNode node = reverseRecuriveforPalindrome(head.next);
//			ListNode temp = node.next;
//			temp.next = node;
//			node.next = null;
//			return node;
			
			if (head == null || head.next == null) return head;
		    ListNode p = reverseRecuriveforPalindrome(head.next);
		    head.next.next = head;
		    head.next = null;
		    return p;
		}
		
		public static void main(String[] args) {
			
			PalindromeCheck obj = new PalindromeCheck();
			obj.head = new ListNode(1);
			obj.head.next = new ListNode(2);
			obj.head.next.next = new ListNode(3);
			obj.head.next.next.next = new ListNode(2);
			obj.head.next.next.next.next = new ListNode(1);
			//obj.head.next.next.next.next.next = new ListNode(6);
			System.out.println("is List Palindrome "+ obj.isPalindrome(obj.head));
		}
	
}
