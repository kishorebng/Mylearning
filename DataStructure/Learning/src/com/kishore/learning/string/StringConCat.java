package com.kishore.learning.string;

public class StringConCat {

	public static void main(String[] args) {
		String data1 = "Hello";
		String data2 = " World";
		System.out.println(" String is " + concat(data1, data2));
		System.out.println(" String is Recursive"
				+ concatRecurively(data1, data2));
	}

	static String concat(String data1, String data2) {
		char data1array[] = data1.toCharArray();
		char data2array[] = data2.toCharArray();
		char result[] = new char[StringLength.findLengthRecurively(data1array,0)
				+ StringLength.findLengthRecurively(data2array,0)];
		int i = 0;
		try {
			for (i = 0; data1array[i] != '\0'; ++i) {
				result[i] = data1array[i];
			}
		} catch (Exception e) {

		}
		try {
			for (int j = 0; data2array[j] != '\0'; ++j, ++i) {
				result[i] = data2array[j];
			}
		} catch (Exception e) {

		}
		return String.valueOf(result);
	}

	static String concatRecurively(String data1, String data2) {
		data1.concat(data2);
		return data1 + data2;
	}
}
