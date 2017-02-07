package com.learn.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Kishore
 *
 */

/*
 * Task Given names and phone numbers, assemble a phone book that maps friends'
 * names to their respective phone numbers. You will then be given an unknown
 * number of names to query your phone book for. For each queried, print the
 * associated entry from your phone book on a new line in the form
 * name=phoneNumber; if an entry for is not found, print Not found instead.
 * 
 * Note: Your phone book should be a Dictionary/Map/HashMap data structure.
 * 
 * Input Format
 * 
 * The first line contains an integer, , denoting the number of entries in the
 * phone book. Each of the subsequent lines describes an entry in the form of
 * space-separated values on a single line. The first value is a friend's name,
 * and the second value is an -digit phone number.
 * 
 * After the lines of phone book entries, there are an unknown number of lines
 * of queries. Each line (query) contains a to look up, and you must continue
 * reading lines until there is no more input.
 * 
 * Note: Names consist of lowercase English alphabetic letters and are first
 * names only.
 * 
 * Constraints
 * 
 * Output Format
 * 
 * On a new line for each query, print Not found if the name has no
 * corresponding entry in the phone book; otherwise, print the full and in the
 * format name=phoneNumber.
 * 
 * Sample Input
 * 
 * 3 sam 99912222 tom 11122222 harry 12299933 sam edward harry Sample Output
 * 
 * sam=99912222 Not found harry=12299933
 */

public class Day8_DictAndMap {

	public static void main(String[] args) {

		// Declare a String to Integer map
		Map<String, Integer> myMap;

		// Initialize it as a new String to String HashMap
		myMap = new HashMap<String, Integer>();

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for (int i = 0; i < n; i++) {
			String name = in.next();
			int phone = in.nextInt();
			// Write code here
			myMap.put(name, phone);
		}
		ArrayList<String> search = new ArrayList<String>();
		while (in.hasNext()) {
			String s = in.next();
			// Write code here
			search.add(s);
		}
		in.close();

		for (int i = 0; i < search.size(); i++) {
			if (myMap.containsKey(search.get(i))) {
				System.out.println(search.get(i) + "=" + myMap.get(search.get(i)));
			} else {
				System.out.println("Not found");
			}
		}
	}
}
