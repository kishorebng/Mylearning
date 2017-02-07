package com.kishore.learning.string;

public class StringSubString {

	public static void main(String[] args) {
		String data = "Hello World";
		System.out.println("Substring "+subString(data, 0, 5));
		subStringRecurively(data.toCharArray(), 0, 5);
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	static String subString(String data, int begin, int end) {
		char dataArray[] = data.toCharArray();
		char result[] = new char[end - begin];
		int i = 0;
		while (i < (end-begin)) {
			result[i] = dataArray[begin + i];
			i++;
		}
		return String.valueOf(result);
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	static void subStringRecurively(char dataArray[],int begin,int end) {
		if (begin>end) {
			return ;
		}
		if (begin<=end)
	    {
			System.out.print(dataArray[begin]);
	        subStringRecurively(dataArray, begin+1, end);
	    }
	}
}
