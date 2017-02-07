package com.kishore.learning.linkedlist;

class DNode {
	int data;
	DNode next;
	DNode prev;

	// constructor

	DNode(int data) {
		this.data = data;
		next = null;
		prev = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public DNode getNext() {
		return next;
	}

	public void setNext(DNode next) {
		this.next = next;
	}

	public DNode getPrev() {
		return prev;
	}

	public void setPrev(DNode prev) {
		this.prev = prev;
	}

}

public class DoublyLinkedList {

	DNode head;
	DNode last;
	int count;

	public DoublyLinkedList() {
		head = null;
		last = null;
		count = 0;
	}

	// get
	public int get(int index) {
		if (index <= 0) {
			return -1; // invalid
		}
		DNode current = head;
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

	// insert
	public void addLast(int newData) {

		DNode temp = new DNode(newData);
		if (head == null) {
			head = last = temp;
		} else {
			last.setNext(temp);
			temp.setPrev(last);
			last = temp;
		}
		count++;
	}

	public void addFirst(int newData) {

		DNode temp = new DNode(newData);
		if (head == null) {
			head = last = temp;
		} else {
			head.setPrev(temp);
			temp.setNext(head);
			head = temp;
		}
		count++;
	}

	// remove
	public void removeLast() {
		DNode current = head;
		while (current.getNext().getNext() != null) {
			current = current.getNext();
		}
		current.setNext(null);
		last = current;
		count--;
	}

	// remove
	public void removeFirst() {
		DNode current = head;
		while (current.getNext().getNext() != null) {
			current = current.getNext();
		}
		current.setNext(null);
		last = current;
		count--;
	}

	public void insert(int data) {
		// Complete this method
		DNode temp = new DNode(data);
		if (head == null) {
			head = last = temp;
		} else {
			DNode current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = temp;
			temp.prev = current;
			last = temp;
		}
	}

	public void displayFromHead() {
		DNode start = head;
		System.out.println();
		while (start != null) {
			System.out.print(start.data + " ");
			start = start.next;
		}
	}

	public void displayFromLast() {
		DNode end = last;
		System.out.println();
		while (end != null) {
			System.out.print(end.data + " ");
			end = end.prev;
		}
	}

}
