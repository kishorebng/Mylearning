package com.learn.hackerrank;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 
 * A palindrome is a word, phrase, number, or other sequence of characters which
 * reads the same backwards and forwards. Can you determine if a given string, ,
 * is a palindrome?
 * 
 * To solve this challenge, we must first take each character in , enqueue it in
 * a queue, and also push that same character onto a stack. Once that's done, we
 * must dequeue the first character from the queue and pop the top character off
 * the stack, then compare the two characters to see if they are the same; as
 * long as the characters match, we continue dequeueing, popping, and comparing
 * each character until our containers are empty (a non-match means isn't a
 * palindrome).
 * 
 * Write the following declarations and implementations:
 * 
 * Two instance variables: one for your , and one for your . A void
 * pushCharacter(char ch) method that pushes a character onto a stack. A void
 * enqueueCharacter(char ch) method that enqueues a character in the instance
 * variable. A char popCharacter() method that pops and returns the character at
 * the top of the instance variable. A char dequeueCharacter() method that
 * dequeues and returns the first character in the instance variable. Input
 * Format
 * 
 * You do not need to read anything from stdin. The locked stub code in your
 * editor reads a single line containing string . It then calls the methods
 * specified above to pass each character to your instance variables.
 * 
 * Constraints
 * 
 * is composed of lowercase English letters. Output Format
 * 
 * You are not responsible for printing any output to stdout. If your code is
 * correctly written and is a palindrome, the locked stub code will print ;
 * otherwise, it will print
 */


class Queuey {

	LinkedList queue;

	public Queuey() {
		// TODO Auto-generated constructor stub
		queue = new LinkedList();
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public int size() {
		return queue.size();
	}

	public void enqueue(String data) {
		queue.addLast(data);
	}

	public String dequeue() {
		return (String) queue.remove(0);
	}

	public String peek() {
		return (String) queue.get(0);
	}

}

class Stacky {

	LinkedList stack;

	public Stacky() {
		// TODO Auto-generated constructor stub
		stack = new LinkedList();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public int size() {
		return stack.size();
	}

	public void push(String data) {
		stack.addLast(data);
	}

	public String pop() {
		return (String) stack.remove(stack.size()-1);
	}

	public String peek() {
		return (String) stack.get(0);
	}

}

public class Daya18_StackQueue {
	
	LinkedList mQueueStack;

	Daya18_StackQueue() {
		mQueueStack = new LinkedList();
	}

	public void enqueueCharacter(char data) {
		mQueueStack.addLast(data);
	}

	public char dequeueCharacter() {
		return (char) mQueueStack.remove(0);
	}

	public void pushCharacter(char data) {
		mQueueStack.addLast(data);
	}

	public char popCharacter() {
		return (char) mQueueStack.remove(mQueueStack.size() - 1);
	}

	public char peek() {
		return (char) mQueueStack.get(0);
	}

	public static void main(String[] argh) {

		Queuey numberQueue = new Queuey();
		numberQueue.enqueue("AAA");
		numberQueue.enqueue("BBB");
		numberQueue.enqueue("CCC");
		System.out.println(" Queue First element " + numberQueue.peek());
		numberQueue.dequeue();
		numberQueue.dequeue();
		System.out.println(" Queue First element " + numberQueue.peek());
		
		Stacky numberStack = new Stacky();
		numberStack.push("AAA");
		numberStack.push("BBB");
		numberStack.push("CCC");
		System.out.println(" Stack Size " + numberStack.size());
		System.out.println(" Stack First element " + numberStack.peek());
		numberStack.pop();
		numberStack.pop();
		System.out.println(" Stack Size " + numberStack.size());
		
		
		 Scanner scan = new Scanner(System.in);
	        String input = scan.nextLine();
	        scan.close();

	        // Convert input String to an array of characters:
	        char[] s = input.toCharArray();

	        // Create a Solution object:
	        Daya18_StackQueue p = new Daya18_StackQueue();

	        // Enqueue/Push all chars to their respective data structures:
	        for (char c : s) {
	            p.pushCharacter(c);
	            p.enqueueCharacter(c);
	        }

	        // Pop/Dequeue the chars at the head of both data structures and compare them:
	        boolean isPalindrome = true;
	        for (int i = 0; i < s.length/2; i++) {
	            if (p.popCharacter() != p.dequeueCharacter()) {
	                isPalindrome = false;                
	                break;
	            }
	        }

	        //Finally, print whether string s is palindrome or not.
	        System.out.println( "The word, " + input + ", is " 
	                           + ( (!isPalindrome) ? "not a palindrome." : "a palindrome." ) );

		

	}
}
