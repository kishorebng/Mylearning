package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * @author Kishore
 *
 *         Task Complete the insert function in your editor so that it creates a
 *         new Node (pass as the Node constructor argument) and inserts it at
 *         the tail of the linked list referenced by the parameter. Once the new
 *         node is added, return the reference to the node.
 * 
 *         Note: If the argument passed to the insert function is null, then the
 *         initial list is empty.
 * 
 *         Input Format
 * 
 *         The insert function has parameters: a pointer to a Node named , and
 *         an integer value, . The constructor for Node has parameter: an
 *         integer value for the field.
 * 
 *         You do not need to read anything from stdin.
 * 
 *         Output Format
 * 
 *         Your insert function should return a reference to the node of the
 *         linked list.
 * 
 *         Sample Input
 * 
 *         The following input is handled for you by the locked code in the
 *         editor: The first line contains T, the number of test cases. The
 *         subsequent lines of test cases each contain an integer to be inserted
 *         at the list's tail.
 * 
 *         4 2 3 4 1 Sample Output
 * 
 *         The locked code in your editor prints the ordered data values for
 *         each element in your list as a single line of space-separated
 *         integers:
 * 
 *         2 3 4 1
 */

class Node {
	int data;
	Node next;

	// constructor

	Node(int data) {
		this.data = data;
		next = null;
	}

	Node(int data, Node next) {
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}

class LinkedListTest {

	Node head;
	int count;

	public LinkedListTest() {
		head = null;
		count = 0;
	}

	public LinkedListTest(Node head) {
		this.head = head;
		count = 1;
	}

	// insert

	public void add(int newData) {

		Node temp = new Node(newData);
		Node current = head;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(temp);
		count++;
	}

	// get
	public int get(int index) {
		if (index <= 0) {
			return -1; // invalid
		}
		Node current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getData();
	}
	// size

	public int size() {
		return count;
	}

	// isempty
	public boolean isEmpty() {
		return head == null;
	}

	// remove
	public void remove() {
		Node current = head;

		while (current.getNext().getNext() != null) {
			current = current.getNext();
		}
		current.setNext(null);
		count--;
	}

}

public class Daya15_LinkedList {

	public static Node insert(Node head, int data) {
		// Complete this method
		Node temp = new Node(data);
		if (head == null) {
			head = temp;
		} else {
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = temp;
		}
		return head;
	}

	public static void display(Node head) {
		Node start = head;
		while (start != null) {
			System.out.print(start.data + " ");
			start = start.next;
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		Node head = null;
		int N = sc.nextInt();
		while (N-- > 0) {
			int ele = sc.nextInt();
			head = insert(head, ele);
		}
		display(head);
		sc.close();
	}

}
