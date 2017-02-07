package com.learn.hackerrank;

import java.util.Scanner;

public class Day1_DataType {
	
	 public static void main(String[] args) {
	        int i = 4;
	        double d = 4.0;
	        String s = "HackerRank ";
			
	        Scanner scan = new Scanner(System.in);
	        /* Declare second integer, double, and String variables. */
	          int intVal;
	          double doubleVal;
	          String stringVal;
	        /* Read and save an integer, double,   String to your variables.*/
	          intVal = Integer.parseInt(scan.nextLine());
	          doubleVal = Double.parseDouble(scan.nextLine());
	          stringVal = scan.nextLine();
	        /* Print the sum of both integer variables on a new line. */
	          System.out.println(i+intVal); 
	        /* Print the sum of the double variables on a new line. */
			  System.out.println(d+doubleVal); 
	        /* Concatenate and print the String variables on a new line; 
	        	the 's' variable above should be printed first. */
	         System.out.println(s+stringVal); 
	         scan.close();
	    }
}
