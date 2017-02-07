package com.learn.hackerrank;

import java.util.Scanner;

/**
 * 
 * Task Write a single generic function named printArray; this function must
 * take an array of generic elements as a parameter (the exception to this is
 * C++, which takes a vector). The locked Solution class in your editor tests
 * your function.
 * 
 * Note: You must use generics to solve this challenge. Do not write overloaded
 * functions.
 * 
 * Input Format
 * 
 * There is no input for this challenge. The locked Solution class in your
 * editor will pass two different types of arrays to your printArray function.
 * 
 * Constraints
 * 
 * You must have exactly function named printArray. Output Format
 * 
 * Your printArray function should print each element of its generic array
 * parameter on a new line.
 */
 

public class Dayb21_Generics {

	
	
	static <E> void printArray(E[] array) {
		for (E element : array) {
			System.out.println(element);
		}
	}

	public static void main(String[] args) {
		Integer[] intArray = { 1, 2, 3 };
		String[] stringArray = { "Hello", "World" };

		printArray(intArray);
		printArray(stringArray);

		if (Dayb20_Sorting.class.getDeclaredMethods().length > 2) {
			System.out.println("You should only have 1 method named printArray.");
		}
	}

}

