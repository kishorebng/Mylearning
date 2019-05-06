package List;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/*
 * 
 Recursive version: We have T(n) = 2T(n/2) + kn. So the complexity is O(kn logn).
 The depth of the recursive function is O(log n).

Priority queue version: Every time we add a new element to the queue, it costs O(log n). 
And we have kn elements. So the complexity is O(kn logn) + O(kn) = O(kn logn). 
And the space complexity is only O(n).
 */
public class MergeKNode {

	public static ListNode mergeKLists(List<ListNode> lists) {
	        if (lists.size() == 0)
	            return null;
	      return  mergeKLists(lists,0,lists.size()-1);
    }
	
	 static ListNode mergeKLists(List<ListNode> lists, int left, int right) {
		 if (left < right) {
	            int mid = (left + right) / 2;
	            return merge(mergeKLists(lists, left, mid), mergeKLists(lists, mid + 1, right));
	        }
	        return lists.get(left);
	 }
	
	  public static ListNode merge(ListNode left, ListNode right) {
	        ListNode head = new ListNode(0);
	        ListNode p = head;
	        while (left != null && right != null) {
	            if (left.val < right.val) {
	                p.next = left;
	                p = p.next;
	                left = left.next;
	            } else {
	                p.next = right;
	                p = p.next;
	                right = right.next;
	            }
	        }
	        if (left != null)
	            p.next = left;
	        else
	            p.next = right;
	        return head.next;
	    }
	 
	  
	  public static ListNode mergeKListsPQ(ListNode[] lists) {
		
		  if (lists==null || lists.length==0) return null;
		  
	    Comparator<ListNode> ListNodeCompare = new Comparator<ListNode>() {
	            @Override
	            public int compare(ListNode left, ListNode right) {
	                return left.val-right.val;
	            }
	     };
	     
	     PriorityQueue<ListNode> mergeQueue = new PriorityQueue<ListNode>(ListNodeCompare);
	     
	     for (int i =0;i < lists.length ;i ++) {
	    	 if (lists[i] !=null) {
	    		 mergeQueue.offer(lists[i]);
	    	 }
	    	
	     }
	        ListNode head = new ListNode(0);
	        ListNode p = head;
	        while (!mergeQueue.isEmpty()) {
	            ListNode node = mergeQueue.poll();
	            p.next = node;
	            if (node.next != null)
	            	mergeQueue.add(node.next);
	            p = p.next;
	        }
	        return head.next;
	 
      }
	  
	  public static void display(ListNode head) {
			ListNode start = head;
			while (start != null) {
				System.out.print(start.val + " ");
				start = start.next;
			}
			System.out.println();
		}
	  
	public static void main(String[] args) {
		
		/*
		 1->4->5,
  1->3->4,
  2->6
  1->1->2->3->4->4->5->6
		 */
		
		
		
		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(4);
		head1.next.next = new ListNode(15);
//		head1.next.next.next = new ListNode(4);
//		head1.next.next.next.next = new ListNode(5);
//		head1.next.next.next.next.next = new ListNode(6);
		
		ListNode head2 = new ListNode(1);
		head2.next = new ListNode(3);
		head2.next.next = new ListNode(4);
//		head2.next.next.next = new ListNode(4);
//		head2.next.next.next.next = new ListNode(5);
//		head2.next.next.next.next.next = new ListNode(6);
		
		ListNode head3 = new ListNode(2);
		head3.next = new ListNode(6);
//		head3.next.next = new ListNode(3);
//		head3.next.next.next = new ListNode(4);
//		head3.next.next.next.next = new ListNode(5);
//		head3.next.next.next.next.next = new ListNode(6);
		
//		ListNode [] lists = {head1,head2,head3};
//		ListNode head = mergeKListsPQ(lists);
//		display(head);
		
		List<ListNode> listsNode = new ArrayList<ListNode>();
		listsNode.add(head1);
		listsNode.add(head2);
		listsNode.add(head3);
		ListNode mhead = mergeKLists(listsNode);
		display(mhead);
		
		
	}
}
