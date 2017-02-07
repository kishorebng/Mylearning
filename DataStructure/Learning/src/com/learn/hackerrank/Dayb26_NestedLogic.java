package com.learn.hackerrank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Kishore
 *
Your local library needs your help! Given the expected and actual return dates for a library book, create a program that calculates the fine (if any). The fee structure is as follows:

If the book is returned on or before the expected return date, no fine will be charged (i.e.: .
If the book is returned after the expected return day but still within the same calendar month and year as the expected return date, .
If the book is returned after the expected return month but still within the same calendar year as the expected return date, the .
If the book is returned after the calendar year in which it was expected, there is a fixed fine of .
Input Format

The first line contains  space-separated integers denoting the respective , , and  on which the book was actually returned. 
The second line contains  space-separated integers denoting the respective , , and  on which the book was expected to be returned (due date).

Constraints

Output Format

Print a single integer denoting the library fine for the book received as input.

Sample Input

9 6 2015
6 6 2015
Sample Output

45
Explanation

Given the following return dates: 
Actual:  
Expected: 

Because , we know it is less than a year late. 
Because , we know it's less than a month late. 
Because , we know that it was returned late (but still within the same month and year).

Per the library's fee structure, we know that our fine will be . We then print the result of  as our output.
 */

public class Dayb26_NestedLogic {

	public static void main(String args[]) {
		/* Enter your code here. */
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		int days = 0;
		int month =0;
		Scanner in = new Scanner(System.in);
		int[] arr = new int[6];
		for (int i = 0; i < 6; i++) {
			arr[i] = in.nextInt();
		}
		String inputString1 =arr[0] +" "+arr[1] +" "+arr[2];
		String inputString2 =arr[3] +" "+arr[4] +" "+arr[5];
		
		try {
		    Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    long diff = date1.getTime() - date2.getTime();
		    days = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		if (days<=0) {
			System.out.println(0);
		}else if (arr[1]== arr[4] && arr[2]==arr[5]) {
			System.out.println(15*days);
		}else if (arr[2]==arr[5]) {
			System.out.println(500*(arr[1]-arr[4]));
		}else {
			System.out.println(10000);
		}
	}
}
