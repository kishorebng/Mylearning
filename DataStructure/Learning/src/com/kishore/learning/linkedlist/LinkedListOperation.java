package com.kishore.learning.linkedlist;

import java.util.Scanner;

public class LinkedListOperation {
	
	// removes duplicate in sorted list
	public static SNode removeDuplicatesUnSorted(SNode head) {
		// Write your code here
		if (head == null) {
			return head;
		}
		SNode cur = head;
		while (cur != null && cur.next != null) {

			if (cur.getData() == cur.next.getData()) {
				cur.next = cur.next.getNext();
			} else {
				cur = cur.getNext();
			}
			cur = cur.getNext();
		}

		return head;

	}

	public static void main(String[] args) {
		// testSinglyList();
         testDoublyList();
	}
	
	static void testSinglyList() {
		Scanner sc = new Scanner(System.in);
		SinglyLinkedList sLinkedList = new SinglyLinkedList();
		int T = sc.nextInt();
		while (T-- > 0) {
			int ele = sc.nextInt();
			sLinkedList.insert(ele);
		}
		sLinkedList.display();
		sLinkedList.remove();
		sLinkedList.display();
	}
	
	static void testDoublyList() {
		Scanner sc = new Scanner(System.in);
		DoublyLinkedList dLinkedList = new DoublyLinkedList();
		int T = sc.nextInt();
		while (T-- > 0) {
			int ele = sc.nextInt();
			dLinkedList.insert(ele);
		}
		dLinkedList.displayFromHead();
		dLinkedList.displayFromLast();		
	}
}

