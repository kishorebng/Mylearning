package com.kishore.learning.string;

public class StringLength {

	public static void main(String[] args) {
		String data = "Hello World";
		System.out.println(" String is "+findLengthString(data));
		System.out.println(" String is "+findLengthRecurively(data.toCharArray(),0));
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	static int findLength(String data) {
		char dataArray[] = data.toCharArray();
		int count =0;
		try {
		while ( dataArray[count]!='\0') {
			count ++;
		} } catch (Exception e) {
			
		}
		return count;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	static int findLengthRecurively(char dataArray[],int count) {
		try {
			if( dataArray[count]=='\0')
				return count;
			 } catch (Exception e) {
				return count;
		}
		return findLengthRecurively(dataArray,count+1);
		
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	static int findLengthString(String data) {
		int count =0;
		if(data == null || data.isEmpty()){
            return 0;
        } 
		try {
		while (data.charAt(count)!='\0') {
			count ++;
		} } catch (Exception e) {
			
		}
		return count;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	static int findLengthRecurivelyString(String data) {
		if(data == null || data.isEmpty()){
            return 0;
        } 
		// call recurive method excluding first character adding 1 as it is counted 
		return findLengthRecurivelyString(data.substring(1))+1;
	}
	
}
