package com.learn.hackerrank;

import java.util.Scanner;

/**
 * Task Given the meal price (base cost of a meal), tip percent (the percentage
 * of the meal price being added as tip), and tax percent (the percentage of the
 * meal price being added as tax) for a meal, find and print the meal's total
 * cost.
 * 
 * Note: Be sure to use precise values for your calculations, or you may end up
 * with an incorrectly rounded result!
 * 
 * Input Format
 * 
 * There are lines of numeric input: The first line has a double, (the cost of
 * the meal before tax and tip). The second line has an integer, (the percentage
 * of being added as tip). The third line has an integer, (the percentage of
 * being added as tax).
 * 
 * Output Format
 * 
 * Print The total meal cost is totalCost dollars., where is the rounded integer
 * result of the entire bill ( with added tax and tip).
 */

public class Day2_Operator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		double mealCost = scan.nextDouble(); // original meal price
		int tipPercent = scan.nextInt(); // tip percentage
		int taxPercent = scan.nextInt(); // tax percentage
		scan.close();

		// Write your calculation code here.
		double tip = mealCost * ((double) tipPercent / 100);
		double tax = mealCost * ((double) taxPercent / 100);
		// cast the result of the rounding operation to an int and save it as
		// totalCost
		int totalCost = (int) Math.round(mealCost + tip + tax/* numberToRoundHere */);
		// Print your result
		System.out.println("The total meal cost is " + totalCost + " dollars.");
	}
}
