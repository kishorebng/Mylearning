package com.kishore.learning.linkedlist;

class SNode {
	int data;
	SNode next;

	// constructor

	SNode(int data) {
		this.data = data;
		next = null;
	}

	SNode(int data, SNode next) {
		this.data = data;
		this.next = next;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public SNode getNext() {
		return next;
	}

	public void setNext(SNode next) {
		this.next = next;
	}

}

public class SinglyLinkedList {

	SNode head;
	int count;

	public SinglyLinkedList() {
		head = null;
		count = 0;
	}

	// insert

	public void add(int newData) {
		SNode temp = new SNode(newData);
		SNode current = head;
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
		SNode current = head;
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
		SNode current = head;
		while (current.getNext().getNext() != null) {
			current = current.getNext();
		}
		current.setNext(null);
		count--;
	}
	
	public SNode insert(SNode head, int data) {
		// Complete this method
		SNode temp = new SNode(data);
		if (head == null) {
			head = temp;
		} else {
			SNode current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = temp;
		}
		return head;
	}
	
	public void insert(int data) {
		// Complete this method
		SNode temp = new SNode(data);
		if (head == null) {
			head = temp;
		} else {
			SNode current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = temp;
		}
		count++;
	}
	
	public void display() {
		SNode start = head;
		while (start != null) {
			System.out.print(start.data + " ");
			start = start.next;
		}
	}

	public void display(SNode head) {
		SNode start = head;
		while (start != null) {
			System.out.print(start.data + " ");
			start = start.next;
		}
	}

}

