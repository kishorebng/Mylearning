package com.learn.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Kishore Problem Statement
 * 
 *         Consider a database table, Emails, which has the attributes First
 *         Name and Email ID. Given rows of data simulating the Emails table,
 *         print an alphabetically-ordered list of people whose email address
 *         ends in .
 * 
 *         Input Format
 * 
 *         The first line contains an integer, , total number of rows in the
 *         table. Each of the subsequent lines contains space-separated strings
 *         denoting a person's first name and email ID, respectively.
 * 
 *         Constraints
 * 
 *         Each of the first names consists of lower case letters only. Each of
 *         the email IDs consists of lower case letters , and only. The length
 *         of the first name is no longer than 20. The length of the email ID is
 *         no longer than 50. Output Format
 * 
 *         Print an alphabetically-ordered list of first names for every user
 *         with a gmail account. Each name must be printed on a new line.
 * 
 *         Sample Input
 * 
 *         6 riya riya@gmail.com julia julia@julia.me julia sjulia@gmail.com
 *         julia julia@gmail.com samantha samantha@gmail.com tanya
 *         tanya@gmail.com Sample Output
 * 
 *         julia julia riya samantha tanya
 */


public class Dayb28_Regex {
	
	 public static void main(String[] args) {
		 
		    HashMap<String, String> data = new HashMap<>();
	        Scanner in = new Scanner(System.in);
	        int N = in.nextInt();
	        for(int a0 = 0; a0 < N; a0++){
	            String firstName = in.next();
	            String emailID = in.next();
	            if (emailID.endsWith("@gmail.com")) {
	                // do your stuff
	            	data.put(emailID,firstName);
	            }
	            
	        }
	        
	        List<String> sortedKeys = new ArrayList<String>(data.size());
	        sortedKeys.addAll(data.values());
	        Collections.sort(sortedKeys);
	        for (String key:sortedKeys) {
	        	System.out.println(key);
	        }
	}
}
