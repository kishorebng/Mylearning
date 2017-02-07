package com.kishore.learning.string;

public class StringReverse {

	public static void main(String[] args) {
		
		String data = "Hello World";
		System.out.println(" Reverse of String with Function "+reverse(data));
		System.out.println(" Reverse of String with Function "+ String.valueOf(reverseRecursivley(data.toCharArray(),0,(StringLength.findLength(data)-1))));
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	static String reverse(String data) {
		char dataArray[] = data.toCharArray();
		int length = StringLength.findLength(data)-1;
		int i = 0;
		while (i < length) {
				char temp = dataArray[i];
				dataArray[i] = dataArray[length];
				dataArray[length] = temp;
				i++;
     			length--;
		}
		return String.valueOf(dataArray);
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	static char[] reverseRecursivley(char dataArray[],int index,int n) { 
		if(index > n) {
			return dataArray;
		}
		char temp = dataArray[index];
		dataArray[index] = dataArray[n];
		dataArray[n] = temp;
		return reverseRecursivley(dataArray,index+1,n-1);
	}

	
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	static String reverseString(String data) {
		if(data == null || data.isEmpty()){
            return data;
        }       
        String reverse = "";
        for(int i = data.length() -1; i>=0; i--){
            reverse = reverse + data.charAt(i);
        }
      
        return reverse;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	static String reverseRecursivleyString(String data) {
		if(data == null || data.isEmpty()){
            return data;
        } 
		// last character + call method excluding last character 
		return data.charAt(data.length()-1)+reverseRecursivleyString(data.substring(0, data.length()-1));
	}
}
